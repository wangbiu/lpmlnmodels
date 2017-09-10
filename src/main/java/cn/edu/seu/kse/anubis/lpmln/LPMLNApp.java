package cn.edu.seu.kse.anubis.lpmln;

import cn.edu.seu.kse.anubis.experiment.ScheduleExperiment;
import cn.edu.seu.kse.anubis.experiment.monty_hall.MontyHallExperiment;
import cn.edu.seu.kse.anubis.lpmln.app.LPMLNOpts;
import cn.edu.seu.kse.anubis.lpmln.model.Rule;
import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.lpmln.solver.Clingo4;
import cn.edu.seu.kse.anubis.lpmln.solver.DLV;
import cn.edu.seu.kse.anubis.lpmln.solver.LPMLNBaseSolver;
import cn.edu.seu.kse.anubis.lpmln.splitting.BotWithTop;
import cn.edu.seu.kse.anubis.lpmln.syntax.SyntaxModule;
import cn.edu.seu.kse.anubis.lpmln.translator.ASPTranslator;
import cn.edu.seu.kse.anubis.lpmln.translator.DLVTranslator;
import cn.edu.seu.kse.anubis.lpmln.translator.WeakASPTranslator;
import cn.edu.seu.kse.anubis.lpmln.translator.WeakDLVTranslator;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Created by 王彬 on 2016/10/14.
 */
public class LPMLNApp {
    private static  String lpmlnfile=null;
    private static  String semantics="strong";
    private static  String translationfile=null;
    private static  boolean iskeeptranslation=false;
    private static  String aspsolver="clingo";
    private static int factor=1;
    private static boolean isShowAll=false;
    private static boolean isMax=false;
    private static boolean isMarginal=false;

    private static Logger logger = LogManager.getLogger(LPMLNApp.class.getName());

    public static void main(String args[]) throws IOException {
        Date enter=new Date();

        Options opts= LPMLNOpts.getCommandLineOptions();
        CommandLineParser cmdParser=new DefaultParser();

        try {
            CommandLine cmd=cmdParser.parse(opts,args);

            if(cmd.hasOption("help") || cmd.getOptions().length == 0){
                HelpFormatter formatter=new HelpFormatter();
                formatter.setWidth(150);
                formatter.printHelp("lpmlnmodels <params>",opts);
            }else if(cmd.hasOption("experiment")){
                experiment(cmd);
            }else if(cmd.hasOption("schedule")){
                ScheduleExperiment se=new ScheduleExperiment();
                se.startTest();
            }else if(cmd.hasOption("translate")){
                initLpmlnmodels(cmd);

                File translationoutfile=new File(translationfile);
                File lpmlnrulefile=new File(lpmlnfile);
                //翻译
                translation(lpmlnrulefile,translationoutfile,semantics,aspsolver);

            } else if(cmd.hasOption("spe")){
                String pn = cmd.getOptionValue("split-problem-n");
                pn = pn==null?"10":pn;
                String pmn = cmd.getOptionValue("split-max-problem-n");
                pmn = pmn==null?"14":pmn;
                BotWithTop.executeExperiment(Integer.valueOf(pn),Integer.valueOf(pmn));
            }else {
                if(cmd.hasOption("translation-input-file") && cmd.hasOption("input-file")){
                    throw new RuntimeException("i and I are used once at a time");
                }

                //初始化参数
                initLpmlnmodels(cmd);
                LPMLNBaseSolver solver=null;

                if(cmd.hasOption("input-file")){
                    File lpmlnrulefile=new File(lpmlnfile);
                    File translationoutfile=new File(translationfile);

                    //翻译
                    solver=translation(lpmlnrulefile,translationoutfile,semantics,aspsolver);

                    //求解
                    solve(translationoutfile,solver);

                    if(!iskeeptranslation){
                        translationoutfile.delete();
                    }

                }else if(cmd.hasOption("translation-input-file")){

                    File translationfile=new File(cmd.getOptionValue("translation-input-file"));

                    if(aspsolver.equals("clingo")){
                        solver=new Clingo4();
                    }else if(aspsolver.equals("dlv")){
                        solver=new DLV();
                    }
                    
                    solve(translationfile,solver);
                }


                Date exit=new Date();

                SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss.SSSS");
                System.out.printf("%n总用时%nenter %s, exit %s, cost %d ms %n", sdf.format(enter),sdf.format(exit),exit.getTime()-enter.getTime());



            }

        } catch (ParseException e) {
            System.err.println("wrong parameter");
        }
    }

    private static void solve(File translationFile, LPMLNBaseSolver solver){
        List<WeightedAnswerSet> was=solver.call(translationFile.getAbsolutePath());

        if(isShowAll){
            System.out.println("all non-zero probability possible world ");
            System.out.println(was);
        }

        if(isMarginal){
            String marginal=solver.marginalDistribution(factor);
            System.out.println("marginal result ");
            System.out.println(marginal);
            System.out.println(solver.getMarginalTime());
        }

        if(isMax) {
            List<WeightedAnswerSet> maxWas=null;
            maxWas=solver.findMaxWeightedAs();
            System.out.println(System.lineSeparator());
            System.out.println("maximal weight possible world ");
            System.out.println(maxWas);
            System.out.println("weight "+solver.getMaxWeight());
            System.out.println(solver.getMaximalTime());
        }

        printStatsInfo(solver);


    }

    private static void printStatsInfo(LPMLNBaseSolver solver){
        System.out.println(solver.getStats());
        System.out.println(solver.getExecuteProfile());
    }


    private static void initLpmlnmodels(CommandLine cmd){
        if(!cmd.hasOption("input-file") && !cmd.hasOption("translation-input-file")){
            throw new RuntimeException("missing parameter input-file");
        }
        lpmlnfile=cmd.getOptionValue("input-file");
        if(cmd.hasOption("lpmln-semantics")){
            semantics=cmd.getOptionValue("lpmln-semantics");
            if(!semantics.equals("strong") && !semantics.equals("weak")){
                throw  new RuntimeException("unsupport lpmln semantics "+semantics);
            }

        }
        if(cmd.hasOption("translation-output-file")){
            translationfile=cmd.getOptionValue("translation-output-file");
            iskeeptranslation=true;
        }
        Date now=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        if(translationfile==null){
            translationfile= UUID.randomUUID().toString()+sdf.format(now)+".lp";
            iskeeptranslation=false;
        }

        if(cmd.hasOption("asp-solver")){
            aspsolver=cmd.getOptionValue("asp-solver");
            if(!aspsolver.equals("clingo") && !aspsolver.equals("dlv")){
                throw new RuntimeException("unsupported ASP solver "+aspsolver);
            }
        }

        if(cmd.hasOption("marginal-probability-reasoning")){
            isMarginal=true;
        }

        if(cmd.hasOption("maximal-weight-stable-models")){
            isMax=true;
        }

        if(cmd.hasOption("show-all-stable-models")){
            isShowAll=true;
        }

    }

    private static void experiment(CommandLine cmd){
        int problemN,maxProblemN,cores,maxCores,round,taskId;
        boolean isParallel;
        String expName,expFPrefix;
        if(cmd.hasOption("parallel")){
            isParallel=true;
        }else {
            isParallel=false;
        }

        problemN=Integer.valueOf(cmd.getOptionValue("exp-problem-n"));
        maxProblemN=Integer.valueOf(cmd.getOptionValue("exp-max-problem-n"));
        cores=Integer.valueOf(cmd.getOptionValue("exp-cores"));
        maxCores=Integer.valueOf(cmd.getOptionValue("exp-max-cores"));
        round=Integer.valueOf(cmd.getOptionValue("exp-round"));
        taskId=Integer.valueOf(cmd.getOptionValue("exp-task-id"));
        expName=cmd.getOptionValue("exp-name");
        expFPrefix=cmd.getOptionValue("exp-file-prefix");

//        System.out.printf("problemN: %d, maxPN: %d, cores: %d, maxCores: %d, round: %d, taskId: %d",problemN,maxProblemN,cores,
//                maxCores,round,taskId);
        MontyHallExperiment mhe=new MontyHallExperiment();
        mhe.setProblemN(problemN);
        mhe.setCores(cores);
        mhe.setMaxCores(maxCores);
        mhe.setMaxProblemN(maxProblemN);
        mhe.setRound(round);
        mhe.setExperimentName(expName);
        mhe.setProgramPrefix(expFPrefix);

        try {
            mhe.test(isParallel,taskId);
        } catch (Exception e) {
            StringBuilder sb=new StringBuilder();
            try {
                for(StackTraceElement ste:e.getStackTrace()){
                    sb.append(ste.toString()).append("<br>");

                }
                logger.error(e.getMessage());
                mhe.emailWarn(sb.toString());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

    private static LPMLNBaseSolver translation(File lpmlnRuleFile, File translationOutputFile, String semantics, String aspsolver) throws IOException {
        Date start =new Date();
        LPMLNBaseSolver solver=null;
        SyntaxModule sm=new SyntaxModule();
        List<Rule> rules=rules= sm.parse(lpmlnRuleFile);
        factor=sm.getFactor();
        HashSet<String> herbrandUniverse=sm.getHerbrandUniverse();
//        System.out.println("factor "+factor);

        ASPTranslator translator=null;



        if(semantics.equals("weak")){
            if(aspsolver.equals("clingo")){
                translator=new WeakASPTranslator();
                solver=new Clingo4();
            }else {
                translator=new WeakDLVTranslator();
                solver=new DLV();
            }
        }else {
            if(aspsolver.equals("clingo")){
                translator=new ASPTranslator();
                solver=new Clingo4();
            }else {
                translator=new DLVTranslator();
                solver=new DLV();
            }
        }

        translator.setFactor(factor);
        translator.setHerbrandUniverse(herbrandUniverse);
        translator.setMetarule(sm.getMetarule());
        String asprules=translator.translate(rules);

        BufferedWriter bw=new BufferedWriter(new FileWriter(translationOutputFile));
        bw.write(asprules);
        bw.close();

        Date end=new Date();
        System.out.println("翻译用时 "+(end.getTime()-start.getTime())+" ms");

        return solver;
    }
}