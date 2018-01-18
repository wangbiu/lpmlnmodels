package cn.edu.seu.kse.lpmln.app;

import cn.edu.seu.kse.lpmln.exception.cmdLineException.CommandLineException;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.AugmentedSubsetWay.AugmentedSolver;
import cn.edu.seu.kse.lpmln.util.FileHelper;
import cn.edu.seu.kse.lpmln.util.LpmlnThreadPool;
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
    private static  String semantics="strong";
    private static  String translationfile=null;
    private static  boolean iskeeptranslation=false;
    private static  SOLVER_TYPE aspsolver=SOLVER_TYPE.SOLVER_CLINGO;
    private static int factor=1;
    private static boolean isShowAll=false;
    private static boolean isMax=false;
    private static boolean isMarginal=false;
    private static LPMLNBaseSolver solver;
    public enum SOLVER_TYPE{SOLVER_CLINGO, SOLVER_DLV, SOLVER_AUG, SOLVER_SPLIT};
    //TODO:线程管理
    private List<LpmlnThreadPool> threadPools;

    private static Logger logger = LogManager.getLogger(LPMLNApp.class.getName());

    public static void main(String args[]) throws IOException {
        Date enter=new Date();

        CommandLine cmd = parseCmd(args);

        if(cmd.hasOption("help") || cmd.getOptions().length == 0){
            printHelp();
            return;
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
            File lpmlnrulefile = new File(cmd.getOptionValue("translation-input-file"));
            //求解
            solver.solveTranslated(lpmlnrulefile);

            printResult(solver);
        }

        if(!iskeeptranslation){
            //TODO:临时文件的处理
            //translationoutfile.delete();
        }


        Date exit=new Date();

        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss.SSSS");
        System.out.printf("%n总用时%nenter %s, exit %s, cost %d ms %n", sdf.format(enter),sdf.format(exit),exit.getTime()-enter.getTime());
        //TODO:删掉这两行
        System.out.println("current time:1");

        FileHelper.cleanFiles();
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
            solver = new AugmentedSolver();
        }else{
            solver = new LPMLNBaseSolver();
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
