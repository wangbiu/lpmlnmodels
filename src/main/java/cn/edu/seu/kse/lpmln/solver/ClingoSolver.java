package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.util.FileHelper;
import cn.edu.seu.kse.lpmln.util.commandLine.AdvancedCommandLine;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 许鸿翔
 * @date 2018/1/15
 */
public class ClingoSolver implements AspSolver{
    protected AdvancedCommandLine acmd = new AdvancedCommandLine();
    public static final String CMD_CLINGO ="clingo 0 --opt-mode enum ";

    @Override
    public List<WeightedAnswerSet> solve(File file) {
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
