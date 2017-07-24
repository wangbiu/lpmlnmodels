package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.util.syntax.SyntaxModule;

import java.util.List;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class DLV extends LPMLNBaseSolver {
    @Override
    public List<WeightedAnswerSet> call(String rulefile) {
        StringBuilder cmd=new StringBuilder();
        cmd.append("dlv -costbound=_,_ -stats ").append(rulefile);
        return super.call(cmd.toString());
    }

    @Override
    public List<WeightedAnswerSet> solverResultProcess(String result) {
        SyntaxModule sm=new SyntaxModule();
        int pos=result.indexOf(System.lineSeparator());
        result=result.substring(pos);
        List<WeightedAnswerSet> was=sm.parseDLVResult(result);
        return was;
    }


}
