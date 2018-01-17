package cn.edu.seu.kse.lpmln.app;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.LPMLNBaseSolver;
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
    public enum SOLVER_TYPE{SOLVER_CLINGO, SOLVER_DLV, SOLVER_AUG, SOLVER_SPLIT};

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

                    solver = new LPMLNBaseSolver();

                    //求解
                    solve(solver,lpmlnrulefile);

                    if(!iskeeptranslation){
                        //translationoutfile.delete();
                    }

                }else if(cmd.hasOption("translation-input-file")){
                    //允许输入翻译后的文件
//
//                    File translationfile=new File(cmd.getOptionValue("translation-input-file"));
//
//                    switch (aspsolver){
//                        case SOLVER_CLINGO:
//                            solver = new Clingo4();
//                            break;
//                        case SOLVER_DLV:
//                            solver = new DLV();
//                            break;
//                        case SOLVER_AUG:
//                            //solver = new AugmentedSolver();
//                            break;
//                        case SOLVER_SPLIT:
//                            //solver = new SplitSetSolver();
//                            break;
//                        default:
//                            solver = new Clingo4();
//                            break;
//                    }
//                    if(aspsolver==SOLVER_TYPE.SOLVER_CLINGO){
//                        solver=new Clingo4();
//                    }else if(aspsolver==SOLVER_TYPE.SOLVER_DLV){
//                        solver=new DLV();
//                    }else{
//
//                    }
//
//                    solve(translationfile,solver);
                }


                Date exit=new Date();

                SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss.SSSS");
                System.out.printf("%n总用时%nenter %s, exit %s, cost %d ms %n", sdf.format(enter),sdf.format(exit),exit.getTime()-enter.getTime());



            }

        } catch (ParseException e) {
            System.err.println("wrong parameter");
        }
    }

    private static void solve(LPMLNBaseSolver solver,File ruleFile){
        List<WeightedAnswerSet> was=solver.solve(ruleFile);

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
}
