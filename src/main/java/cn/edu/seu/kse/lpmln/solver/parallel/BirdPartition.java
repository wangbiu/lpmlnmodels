package cn.edu.seu.kse.lpmln.solver.parallel;

import cn.edu.seu.kse.lpmln.model.AugmentedSubset;
import cn.edu.seu.kse.lpmln.model.Rule;

import java.util.List;

/**
 * Created by 王彬 on 2017/3/27.
 */
public class BirdPartition extends BasePartition {
    private int cnt=0;
    private int rsize;
    private double log2=Math.log(2);

    public BirdPartition(List<Rule> rules, String asptext, int factor) {
        super(rules, asptext, factor);
        rsize=rules.size();
    }


    @Override
    public int selectSubset() {
        return 0;
    }



    @Override
    public int selectRule(AugmentedSubset subset) {
        boolean isok=false;
        int k=(int) (Math.log(split.size())/log2);

        Rule rule=null;
        for(int i=2*k;i<rsize;i++){
            rule=rules.get(i);
            isok = rule.isSoft() && !subset.getAsprules().contains(i) && !subset.getRejectrule().contains(i);
            if(isok){
                cnt=i+1;
                break;
            }
        }
        return cnt-1;

    }

    public int selectseqRule(AugmentedSubset subset) {
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
}
