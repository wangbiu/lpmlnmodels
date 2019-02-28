package cn.edu.seu.kse.lpmln.solver.impl;

import cn.edu.seu.kse.lpmln.grounder.GringoGrounder;
import cn.edu.seu.kse.lpmln.grounder.LPMLNGrounder;
import cn.edu.seu.kse.lpmln.model.ExperimentReport;
import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;
import cn.edu.seu.kse.lpmln.util.syntax.SyntaxModule;

import java.io.File;
import java.util.List;

/**
 * 反以为MLN程序求解，功能有限
 * 语法不充分，只能求边缘概率，不过效率高
 * TODO:支持更多接口
 * @author 许鸿翔
 * @date 2019/2/28
 */
public class LPMLN2MLNSolver implements LPMLNSolver {
    private LpmlnProgram program;

    private void groundAndParse(File ruleFile){
        LPMLNGrounder grounder = new GringoGrounder();
        String groundProgram = grounder.grounding(ruleFile);

        program = SyntaxModule.parseLPMLN(groundProgram);
    }


    @Override
    public List<WeightedAnswerSet> solve(File ruleFile) {
        groundAndParse(ruleFile);
        return null;
    }

    @Override
    public List<WeightedAnswerSet> solve(String program) {

        return null;
    }

    @Override
    public List<WeightedAnswerSet> solveTranslated(File translatedFile) {
        return null;
    }

    @Override
    public List<WeightedAnswerSet> solveProgram(LpmlnProgram program) {
        return null;
    }

    @Override
    public boolean containsLiteral(String literal) {
        return false;
    }

    @Override
    public double getLiteralProbability(String literal) {
        return 0;
    }

    @Override
    public List<WeightedAnswerSet> findMaxWeightedAs() {
        return null;
    }

    @Override
    public List<WeightedAnswerSet> getAllWeightedAs() {
        return null;
    }

    @Override
    public String getMarginalDistribution() {
        return null;
    }

    @Override
    public ExperimentReport getReport() {
        return null;
    }

    @Override
    public void setLpmlnProgram(LpmlnProgram lpmlnProgram) {

    }

    @Override
    public void setFiltResult(boolean filtResult) {

    }

    @Override
    public void setCalculatePossibility(boolean calculatePossibility) {

    }

    @Override
    public void run() {

    }
}
