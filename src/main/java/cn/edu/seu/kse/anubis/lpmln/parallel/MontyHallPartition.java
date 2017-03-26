package cn.edu.seu.kse.anubis.lpmln.parallel;

import cn.edu.seu.kse.anubis.lpmln.model.AugmentedSubset;
import cn.edu.seu.kse.anubis.lpmln.model.Rule;

import java.util.List;

/**
 * Created by 王彬 on 2017/3/25.
 */
public class MontyHallPartition extends BasePartition {
    private int cnt=0;
    private int rsize;

    // 至少需要8个门
    private int problemN=3;


    public MontyHallPartition(List<Rule> rules, String asptext, int factor) {
        super(rules, asptext, factor);
        rsize =rules.size();
    }

    @Override
    public int selectSubset() {
        return  split.size()-1;
    }

    @Override
    public int selectRule(AugmentedSubset subset) {
        boolean isok=false;

        Rule rule=null;

        for(int i=cnt;i<rsize;i++){
            if(i%problemN == problemN -1){
                continue;
            }
            rule=rules.get(i);
            isok = rule.isSoft() && !subset.getAsprules().contains(i) && !subset.getRejectrule().contains(i);
            if(isok){
                cnt=i+1;
                break;
            }
        }
        return cnt-1;
    }

    public int selectSeqRule(AugmentedSubset subset) {
        boolean isok=false;
        Rule rule=null;
        for(int i=cnt;i<rsize;i++){
            rule=rules.get(i);
            isok = rule.isSoft() && !subset.getAsprules().contains(i) && !subset.getRejectrule().contains(i);
            if(isok){
                cnt=i+1;
                break;
            }
        }
        return cnt-1;
    }

    public int getProblemN() {
        return problemN;
    }

    public void setProblemN(int problemN) {
        this.problemN = problemN;
    }
}
