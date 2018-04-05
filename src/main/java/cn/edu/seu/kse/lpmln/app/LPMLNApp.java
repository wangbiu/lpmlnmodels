package cn.edu.seu.kse.lpmln.app;

import cn.edu.seu.kse.lpmln.exception.cmdlineexception.CommandLineException;
import cn.edu.seu.kse.lpmln.experiment.util.ExperimentReporter;
import cn.edu.seu.kse.lpmln.model.ExperimentReport;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.augmentedsubsetway.AugmentedSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.independentway.IndependentSolver;
import cn.edu.seu.kse.lpmln.util.FileHelper;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 王彬 on 2016/10/14.
 */
public class LPMLNApp {
    private static  String lpmlnfile=null;
    public static  String semantics="strong";
    public static  String translationFilePrefix=null;
    public static  boolean iskeeptranslation=false;
    private static boolean isShowAll=false;
    private static boolean isMax=false;
    private static boolean isMarginal=false;
    private static LPMLNBaseSolver solver;
    private static String reportFileName = null;

    private static Logger logger = LogManager.getLogger(LPMLNApp.class.getName());

    public static void main(String args[]) throws IOException {
        Date enter=new Date();

        CommandLine cmd = parseCmd(args);

        if(cmd.hasOption("help") || cmd.getOptions().length == 0){
            printHelp();
            return;
        }

        if(cmd.hasOption("report-json")){
            String filename = cmd.getOptionValue("report-json");
            //加载类，消除类加载影响
            //new LpmlnExperiment().testSpecified("asu_2asp_SimpleExample.lp");
            reportFileName = filename;
        }

        if(cmd.hasOption("translation-input-file") && cmd.hasOption("input-file")){
            throw new CommandLineException("i and I are used once at a time");
        }

        //初始化参数
        initLpmlnmodels(cmd);

        if(cmd.hasOption("input-file")){
            File lpmlnrulefile=new File(lpmlnfile);

            //求解
            solver.solve(lpmlnrulefile);

            printResult(solver);



        }else if(cmd.hasOption("translation-input-file")){
            lpmlnfile = cmd.getOptionValue("translation-input-file");
            File lpmlnrulefile = new File(lpmlnfile);
            //求解
            solver.solveTranslated(lpmlnrulefile);

            printResult(solver);
        }

        if(reportFileName!=null){
            ExperimentReport report = solver.getReport();
            report.setExperimentName(lpmlnfile);
            ExperimentReporter.report(report,reportFileName);
        }

        FileHelper.cleanFiles();


        Date exit=new Date();

        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss.SSSS");
        System.out.printf("%n总用时%nenter %s, exit %s, cost %d ms %n", sdf.format(enter),sdf.format(exit),exit.getTime()-enter.getTime());
    }

    private static void printResult(LPMLNBaseSolver solver){
        List<WeightedAnswerSet> was = solver.getAllWeightedAs();

        if(isShowAll){
            System.out.println("all non-zero probability possible world ");
            System.out.println(was);
        }

        if(isMarginal){
            String marginal=solver.getMarginalDistribution();
            System.out.println("marginal result ");
            System.out.println(marginal);
            //System.out.println(solver.getMarginalTime());
        }

        if(isMax) {
            List<WeightedAnswerSet> maxWas=null;
            maxWas=solver.findMaxWeightedAs();
            System.out.println(System.lineSeparator());
            System.out.println("maximal weight possible world ");
            System.out.println(maxWas);
            //System.out.println("weight "+solver.getMaxWeight());
            //System.out.println(solver.getMaximalTime());
        }

        printStatsInfo(solver);


    }

    private static void printStatsInfo(LPMLNBaseSolver solver){
        //TODO:收集推理信息
//        System.out.println(solver.getStats());
//        System.out.println(solver.getExecuteProfile());
    }


    private static void initLpmlnmodels(CommandLine cmd){
        if(!cmd.hasOption("input-file") && !cmd.hasOption("translation-input-file")){
            throw new RuntimeException("missing parameter input-file");
        }
        lpmlnfile=cmd.getOptionValue("input-file");
        if(cmd.hasOption("lpmln-semantics")){
            semantics=cmd.getOptionValue("lpmln-semantics");
            if(!semantics.equals("strong") && !semantics.equals("weak")){
                throw  new CommandLineException("unsupport lpmln semantics "+semantics);
            }
        }

        if(cmd.hasOption("translation-output-file")){
            translationFilePrefix=cmd.getOptionValue("translation-output-file");
            iskeeptranslation=true;
        }
        Date now=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        if(translationFilePrefix==null){
            translationFilePrefix= UUID.randomUUID().toString()+sdf.format(now);
            iskeeptranslation=false;
        }

        if(cmd.hasOption("asp-solver")){
            throw new RuntimeException("Parameter asp solver is no longer supported. The default solver is clingo.");
//            aspsolver=cmd.getOptionValue("asp-solver");
//            if(!aspsolver.equals("clingo") && !aspsolver.equals("dlv")){
//                throw new RuntimeException("unsupported ASP solver "+aspsolver);
//            }
        }
        //TODO:给推理机使用一个统一框架
        //TODO:solver的在CMD中的组织方式
        if(cmd.hasOption("parallel")){
            String solverName = cmd.getOptionValue("parallel");
            if(solverName==null){
                solver = new AugmentedSolver();
            }else{
                switch (solverName){
                    case "i":
                        solver = new IndependentSolver();
                        break;
                    case "a":
                        solver = new AugmentedSolver();
                    default:
                        throw new CommandLineException("No correspond solver, i=independent,a=augmented,s=splitset");
                }
            }
        }else{
            solver = new LPMLNBaseSolver();
        }

        //TODO:推理机使用的定义方式需要修改
        if(cmd.hasOption("lpmln-solver")){
            String solverTag = cmd.getOptionValue("lpmln-solver");
            switch (solverTag){
                case "a" :
                    solver = new AugmentedSolver();
                    break;
                case "b" :
                    solver = new LPMLNBaseSolver();
                    break;
                default :
                    solver = new LPMLNBaseSolver();
                    break;
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

    public static CommandLine parseCmd(String args[]){
        Options opts= LPMLNOpts.getCommandLineOptions();
        CommandLineParser cmdParser=new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd=cmdParser.parse(opts,args);
        } catch (ParseException e) {
            throw new RuntimeException("wrong parameter");
        }
        return cmd;
    }

    public static void printHelp(){
        HelpFormatter formatter=new HelpFormatter();
        formatter.setWidth(150);
        formatter.printHelp("lpmlnmodels <params>",LPMLNOpts.getCommandLineOptions());
    }
}
