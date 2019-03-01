package cn.edu.seu.kse.lpmln.solver.impl;

import cn.edu.seu.kse.lpmln.grounder.GringoGrounder;
import cn.edu.seu.kse.lpmln.grounder.LPMLNGrounder;
import cn.edu.seu.kse.lpmln.model.ExperimentReport;
import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;
import cn.edu.seu.kse.lpmln.translator.LPMLNTranslator;
import cn.edu.seu.kse.lpmln.translator.impl.LPMLN2MLNTranslator;
import cn.edu.seu.kse.lpmln.util.FileHelper;
import cn.edu.seu.kse.lpmln.util.commandline.CommonCmd;
import cn.edu.seu.kse.lpmln.util.syntax.SyntaxModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 反以为MLN程序求解，功能有限
 * 语法不充分，只能求边缘概率，不过效率高
 * TODO:支持更多接口
 * @author 许鸿翔
 * @date 2019/2/28
 */
public class LPMLN2MLNSolver implements LPMLNSolver {
    private static Logger logger = LogManager.getLogger(LPMLN2MLNSolver.class.getName());
    private LpmlnProgram program;

    private void groundAndParse(File ruleFile){
        LPMLNGrounder grounder = new GringoGrounder();
        String groundProgram = grounder.grounding(ruleFile);
        program = SyntaxModule.parseLPMLN(groundProgram);
    }

    private static File translate(LpmlnProgram program){
        LPMLNTranslator translator = new LPMLN2MLNTranslator();
        String mlnProgram = translator.translate(program);
        File mlnFile = FileHelper.randomFile();
        FileHelper.writeFile(mlnFile,mlnProgram);
        return mlnFile;
    }

    /**
     * alchemy，先写这里，需要的话抽成类
     * @param mlnFile mln
     * @return mln result
     */
    private String getAlchemyResult(File mlnFile){
        File outFile = FileHelper.randomFile();
        CommonCmd cmd = new CommonCmd();
        String cmdStr = getCmd(mlnFile,outFile);

        cmd.call(cmdStr);
        logger.debug("alchemy output:{}",cmd.getOutput().toString());
        String mlnResult = FileHelper.getFileContent(outFile);
        logger.debug("alchemy result:{}",mlnResult);
        return mlnResult;
    }

    //"infer -i test.mln -e empty.db -r out.txt -q entity"
    private String getCmd(File mlnFile,File outFile){
        checkEmptyDBExist();

        StringBuilder cmdBuilder = new StringBuilder();
        cmdBuilder.append("infer");

        cmdBuilder.append(" -i ");
        cmdBuilder.append(mlnFile.getPath());

        cmdBuilder.append(" -e ");
        cmdBuilder.append("empty.db");

        cmdBuilder.append(" -r ");
        cmdBuilder.append(outFile.getPath());

        cmdBuilder.append(" -q ");
        cmdBuilder.append("entity");

        return cmdBuilder.toString();
    }

    private void checkEmptyDBExist(){
        File emptydb = new File("empty.db");
        try {
            emptydb.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<WeightedAnswerSet> solve(File ruleFile) {
        groundAndParse(ruleFile);
        File mlnProgram = translate(program);
        String alchemyResult = getAlchemyResult(mlnProgram);

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
