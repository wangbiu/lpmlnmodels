package cn.edu.seu.kse.lpmln.experiment.util;

import cn.edu.seu.kse.lpmln.solver.LPMLNSolver;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 许鸿翔
 * @date 2018/2/26
 */
public class AnswerValidater {
    private static int CHECKLENGTH = 5;

    public static boolean isConsistent(LPMLNSolver solver1, LPMLNSolver solver2){
        String[] answer1 = solver1.getMarginalDistribution().replaceAll("\r\n"," ").replaceAll("  "," ").split(" ");
        String[] answer2 = solver2.getMarginalDistribution().replaceAll("\r\n"," ").replaceAll("  "," ").split(" ");
        int ansLen;
        Map<String,String> answerMap1 = new HashMap<>();
        Map<String,String> answerMap2 = new HashMap<>();
        if(answer1.length!=answer2.length){
            return false;
        }
        ansLen = answer1.length;
        for(int i=0;i<ansLen;i+=2){
            String prob1;
            String prob2;
            if(answer1[i+1].length()>CHECKLENGTH){
                prob1 = answer1[i+1].substring(0,CHECKLENGTH);
            }else{
                prob1 = answer1[i+1];
            }
            if(answer2[i+1].length()>CHECKLENGTH){
                prob2 = answer2[i+1].substring(0,CHECKLENGTH);
            }else{
                prob2 = answer2[i+1];
            }
            answerMap1.put(answer1[i],prob1);
            answerMap2.put(answer2[i],prob2);
        }
        System.out.println("literal\t\tprobability");
        for (Map.Entry<String,String> ent : answerMap1.entrySet()) {
            System.out.println(ent.getKey()+"\t\t"+ent.getValue());
            String prob2 = answerMap2.get(ent.getKey());
            if(prob2==null || !(Double.parseDouble(prob2)==Double.parseDouble(ent.getValue()))){
                return false;
            }
        }
        return true;
    }
}
