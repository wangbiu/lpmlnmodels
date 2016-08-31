package cn.edu.seu.kse.anubis.lpmln.solver;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.lpmln.solver.syntax.SyntaxMoudle;
import cn.edu.seu.kse.anubis.util.CommandLineExecute;

import java.util.List;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class DLV extends BaseSolver {
    @Override
    public List<WeightedAnswerSet> call(String rulefile) {
        StringBuilder cmd=new StringBuilder();
        cmd.append("dlv -costbound=_,_ -stats ").append(rulefile);
        return super.call(cmd.toString());
    }

    @Override
    public List<WeightedAnswerSet> solverResultProcess(String result) {
        SyntaxMoudle sm=new SyntaxMoudle();
        int pos=result.indexOf(System.lineSeparator());
        result=result.substring(pos);
        List<WeightedAnswerSet> was=sm.parse(result);
        maxWeight=sm.getMaxWeight();
        maxWeightAs=sm.getMaxWeightAs();
        return was;
    }


}
