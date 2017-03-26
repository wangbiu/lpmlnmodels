package cn.edu.seu.kse.anubis.lpmln.parallel;

import cn.edu.seu.kse.anubis.lpmln.grounder.BaseGrounder;
import cn.edu.seu.kse.anubis.lpmln.model.AugmentedSubset;
import cn.edu.seu.kse.anubis.lpmln.model.Rule;

import java.util.List;

/**
 * Created by 王彬 on 2017/3/27.
 */
public class BirdPartition extends BasePartition {
    private int cnt=0;
    private int rsize;

    public BirdPartition(List<Rule> rules, String asptext, int factor) {
        super(rules, asptext, factor);
    }


    @Override
    public int selectSubset() {
        return split.size()-1;
    }

    @Override
    public int selectRule(AugmentedSubset subset) {
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
