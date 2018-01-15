package cn.edu.seu.kse.lpmln.solver;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;
import cn.edu.seu.kse.lpmln.util.commandLine.AdvancedCommandLine;
import cn.edu.seu.kse.lpmln.util.syntax.lpmln.LPMLNTranslationVisitor;

import java.util.ArrayList;
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
        List<WeightedAnswerSet> originnalWas=acmd.getWas();
        weightedAs=calculateProbability(filtWas(originnalWas));

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

        return weightedAs;
    }

    public List<WeightedAnswerSet> filtWas(List<WeightedAnswerSet> origin){
        List<WeightedAnswerSet> ans = new ArrayList<>(origin.size());
        //再次筛选level2上的最小值，并且将除factor以保证小数位数
        int minLevel2 = Integer.MAX_VALUE;
        int maxLevel2 = Integer.MIN_VALUE;
        int aimLevel2=0;
        int factor = LPMLNTranslationVisitor.getFactor();
        for (WeightedAnswerSet was : origin) {
            minLevel2 = Math.min(was.getWeights().get(1),minLevel2);
            maxLevel2 = Math.max(was.getWeights().get(1),maxLevel2);
        }
        aimLevel2 = minLevel2;
        for (WeightedAnswerSet was : origin) {
            if(was.getWeights().get(1)==aimLevel2){
                was.setFactor(factor);
                ans.add(was);
            }
        }
        return  ans;
    }

    public List<WeightedAnswerSet> calculateProbability(List<WeightedAnswerSet> origin){
        int factor = 1;
        //TODO:考虑pow(10,n)
        factor = -1;
        double wsum=0;
        double expw=0;
        for(WeightedAnswerSet as:origin){
            expw= Math.exp(factor*as.getWeights().get(0)*1.0);
            wsum+=expw;
            as.setProbability(expw);
        }

        for(WeightedAnswerSet as:origin){
            expw=as.getProbability();
            as.setProbability(expw/wsum);
        }
        return origin;
    }
}
