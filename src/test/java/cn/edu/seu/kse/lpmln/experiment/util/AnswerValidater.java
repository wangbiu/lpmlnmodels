package cn.edu.seu.kse.lpmln.experiment.util;

import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 许鸿翔
 * @date 2018/2/26
 */
public class AnswerValidater {
    public static int CHECKLENGTH = 5;

    public boolean isConsistent(LPMLNSolver sover1, LPMLNSolver solver2){
        String[] answer1 = sover1.getMarginalDistribution().replaceAll("\r\n"," ").replaceAll("  "," ").split(" ");
        String[] answer2 = sover1.getMarginalDistribution().replaceAll("\r\n"," ").replaceAll("  "," ").split(" ");
        int ansLen;
        Map<String,String> answerMap1 = new HashMap<>();
        Map<String,String> answerMap2 = new HashMap<>();
        if(answer1.length!=answer2.length){
            return false;
        }
        ansLen = answer1.length;
        for(int i=0;i<ansLen;i+=2){
            answerMap1.put(answer1[i],answer1[i+1].substring(0,CHECKLENGTH));
            answerMap2.put(answer2[i],answer2[i+1]).substring(0,CHECKLENGTH);
        }
        for (Map.Entry<String,String> ent : answerMap1.entrySet()) {
            String prob2 = answerMap2.get(ent.getKey());
            if(prob2==null || !prob2.equals(ent.getValue())){
                return false;
            }
        }
        return true;
    }
}
