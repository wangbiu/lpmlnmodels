package cn.edu.seu.kse.lpmln.experiment;

import cn.edu.seu.kse.lpmln.experiment.util.AnswerValidater;
import cn.edu.seu.kse.lpmln.experiment.util.ExperimentReporter;
import cn.edu.seu.kse.lpmln.model.ExperimentReport;
import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;
import cn.edu.seu.kse.lpmln.solver.impl.LPMLNBaseSolver;
import cn.edu.seu.kse.lpmln.solver.parallel.augmentedsubsetway.AugmentedSolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 许鸿翔
 * @date 2018/2/26
 */
public class LpmlnExperiment {
    protected List<LPMLNBaseSolver> solvers;
    public static final String PROGRAM_PATH = "/lpmlnmodels/experiment/lpmln/";
    public static final String SIMPLE_EXAMPLE = "asu_2asp_SimpleExample.lp";
    private static Logger logger= LogManager.getLogger(LpmlnExperiment.class.getName());

    public LpmlnExperiment(){

    }

    public void testAll(){
        simpletest();
    }

    public void simpletest(){
        ExperimentReport report = test(SIMPLE_EXAMPLE);
        ExperimentReporter.report(report);
    }

    public void testSpecified(String filename){
        ExperimentReport report = test(filename);
        ExperimentReporter.report(report);
    }

    public void prepareSolvers(){
        solvers = new ArrayList<>();
        solvers.add(new LPMLNBaseSolver());
        solvers.add(new AugmentedSolver());
    }

    public ExperimentReport test(String filename){
        prepareSolvers();
        ExperimentReport report = new ExperimentReport();
        File toTest = new File(PROGRAM_PATH+filename);
        solvers.forEach(lpmlnSolver -> lpmlnSolver.solve(toTest));
        LPMLNSolver baseline = solvers.get(0);
        for(int i=1;i<solvers.size();i++){
            if(!AnswerValidater.isConsistent(baseline,solvers.get(i))){
                report.setStatus("inConsistent");
                System.out.println("Test "+filename+" failed!!!!!!!!!!!");
            }
        }
        logger.info("Test "+filename+" passed.");

        report.setExperimentName(filename);
        report.getSolvers().addAll(solvers);
        for (LPMLNBaseSolver solver : solvers) {
            report.getTime().add(solver.getTimes().toString());
        }
        return report;
    }
}
