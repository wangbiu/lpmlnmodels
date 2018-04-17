package cn.edu.seu.kse.lpmln.solver.impl;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.solver.AspSolver;
import cn.edu.seu.kse.lpmln.util.FileHelper;
import cn.edu.seu.kse.lpmln.util.LpmlnThreadPool;
import cn.edu.seu.kse.lpmln.util.commandline.AdvancedCommandLine;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 许鸿翔
 * @date 2018/1/15
 */
public class ClingoSolver implements AspSolver {
    protected AdvancedCommandLine acmd;
    public static final String CMD_CLINGO ="clingo 0 --opt-mode enum ";
    public static String THREAD_NAME = "ClingoSolver";
    public LpmlnThreadPool threadPool;

    public ClingoSolver() {

    }

    @Override
    public List<WeightedAnswerSet> solve(File file) {
        threadPool = new LpmlnThreadPool(THREAD_NAME);
        acmd = new AdvancedCommandLine(threadPool);
        String cmd = null;
        List<WeightedAnswerSet> result;
        try {
            cmd = CMD_CLINGO + file.getCanonicalFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        acmd.call(cmd);
        result = acmd.getWas();
        return result;
    }

    @Override
    public List<WeightedAnswerSet> solve(String aspProgram) {
        File aspFile = FileHelper.randomFile();
        FileHelper.writeFile(aspFile, aspProgram);
        return solve(aspFile);
    }
}
