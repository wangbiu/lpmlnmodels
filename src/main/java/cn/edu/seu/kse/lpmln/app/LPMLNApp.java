package cn.edu.seu.kse.lpmln.app;

import cn.edu.seu.kse.lpmln.model.Rule;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.Clingo4;
import cn.edu.seu.kse.lpmln.solver.DLV;
import cn.edu.seu.kse.lpmln.solver.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.AugmentedSubsetWay.AugmentedSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.AugmentedSubsetWay.AugmentedSubsetPartitioner;
import cn.edu.seu.kse.lpmln.translator.ASPTranslator;
import cn.edu.seu.kse.lpmln.translator.ASPTranslatorV2;
import cn.edu.seu.kse.lpmln.util.syntax.SyntaxModule;
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
    private static  SOLVER_TYPE aspsolver=SOLVER_TYPE.SOLVER_CLINGO;
    private static int factor=1;
    private static boolean isShowAll=false;
    private static boolean isMax=false;
    private static boolean isMarginal=false;
    public enum SOLVER_TYPE{SOLVER_CLINGO, SOLVER_DLV, SOLVER_AUG, SOLVER_SPLIT};
    public enum TRANSLATION_TYPE{V1,V2};
    public static TRANSLATION_TYPE translation_type = TRANSLATION_TYPE.V2;

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
            }
            else {
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
                        //translationoutfile.delete();
                    }

                }else if(cmd.hasOption("translation-input-file")){

                    File translationfile=new File(cmd.getOptionValue("translation-input-file"));

                    switch (aspsolver){
                        case SOLVER_CLINGO:
                            solver = new Clingo4();
                            break;
                        case SOLVER_DLV:
                            solver = new DLV();
                            break;
                        case SOLVER_AUG:
                            //solver = new AugmentedSolver();
                            break;
                        case SOLVER_SPLIT:
                            //solver = new SplitSetSolver();
                            break;
                        default:
                            solver = new Clingo4();
                            break;
                    }
                    if(aspsolver==SOLVER_TYPE.SOLVER_CLINGO){
                        solver=new Clingo4();
                    }else if(aspsolver==SOLVER_TYPE.SOLVER_DLV){
                        solver=new DLV();
                    }else{

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
            throw new RuntimeException("Parameter asp solver is no longer supported. The default solver is clingo.");
//            aspsolver=cmd.getOptionValue("asp-solver");
//            if(!aspsolver.equals("clingo") && !aspsolver.equals("dlv")){
//                throw new RuntimeException("unsupported ASP solver "+aspsolver);
//            }
        }
        if(cmd.hasOption("parallel")){
            aspsolver = SOLVER_TYPE.SOLVER_AUG;
            //选择并行推理方式
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

    private static LPMLNBaseSolver translation(File lpmlnRuleFile, File translationOutputFile, String semantics, SOLVER_TYPE aspsolver) throws IOException {
        Date start =new Date();
        LPMLNBaseSolver solver=null;
        SyntaxModule sm=new SyntaxModule();
        List<Rule> rules=rules= sm.parseLPMLN(lpmlnRuleFile);
        factor=sm.getFactor();
        HashSet<String> herbrandUniverse=sm.getHerbrandUniverse();
//        System.out.println("factor "+factor);

        ASPTranslator translator=null;

        switch (translation_type){
            case V1:
                translator=new ASPTranslator(semantics);
                break;
            case V2:
            default:
                translator=new ASPTranslatorV2(semantics);
                break;

        }
        translator.setFactor(factor);
        translator.setHerbrandUniverse(herbrandUniverse);
        translator.setMetarule(sm.getMetarule());
        switch (aspsolver){
            case SOLVER_AUG:
                solver = new AugmentedSolver();
                List<String> lpmlnFileList = ((AugmentedSolver)solver).getTranslatedFiles();
                List extraweights = ((AugmentedSolver)solver).getExtraWeights();
                AugmentedSubsetPartitioner partitioner = new AugmentedSubsetPartitioner((AugmentedSolver) solver);
                String translatedText=translator.translate(rules);
                partitioner.partition(rules, translatedText);
                break;
            case SOLVER_SPLIT:
                solver = new AugmentedSolver();
                break;
            case SOLVER_CLINGO:
            default:
                solver = new Clingo4();
                String asprules=translator.translate(rules);
                BufferedWriter bw=new BufferedWriter(new FileWriter(translationOutputFile));
                bw.write(asprules);
                bw.close();
                break;
        }

//        translator=new DLVTranslator(semantics);
//        solver=new DLV();
        Date end=new Date();
        System.out.println("翻译用时 "+(end.getTime()-start.getTime())+" ms");

        return solver;
    }
}
