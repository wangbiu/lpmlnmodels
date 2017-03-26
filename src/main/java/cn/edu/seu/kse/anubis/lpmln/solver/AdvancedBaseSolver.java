package cn.edu.seu.kse.anubis.lpmln.solver;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.anubis.util.AdvancedCommandLine;
import cn.edu.seu.kse.anubis.util.CommandLineExecute;

import java.util.Date;
import java.util.List;

/**
 * Created by 王彬 on 2017/3/26.
 */
public class AdvancedBaseSolver extends BaseSolver {
    @Override
    public List<WeightedAnswerSet> call(String cmd) {
        Date enter=new Date();
        AdvancedCommandLine acmd=new AdvancedCommandLine();
//        System.out.println(cmd);

        acmd.call(cmd);
        Date cmdExit=new Date();

//        System.out.println("result "+cmdres[0]);
//        System.out.println("error: "+cmdres[1]);
        List<WeightedAnswerSet> was=acmd.getWas();
        weightedAs=was;

        totalSolverTime=acmd.getCputime();
        stats=genSolverStatisticsInfo();
        Date exit=new Date();

        StringBuilder sb=new StringBuilder();

        sb.append("进入推理核心时间：").append(sdf.format(enter)).append(System.lineSeparator());
        sb.append("推理机核心调用结束：").append(sdf.format(cmdExit)).append(System.lineSeparator());
        sb.append("推理结果预处理完成：").append(sdf.format(exit)).append(System.lineSeparator());


        executeTime.add(exit.getTime()-enter.getTime());
//        executeTime.add(cmdExit.getTime()-enter.getTime());
//        executeTime.add(exit.getTime()-cmdExit.getTime());
        sb.append("推理核心用时：").append(executeTime.get(0)).append(" ms").append(System.lineSeparator());
        executeProfile=sb.toString();

        return was;
    }
}
