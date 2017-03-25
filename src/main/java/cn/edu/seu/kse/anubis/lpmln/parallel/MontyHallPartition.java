package cn.edu.seu.kse.anubis.lpmln.parallel;

import cn.edu.seu.kse.anubis.lpmln.model.AugmentedSubset;
import cn.edu.seu.kse.anubis.lpmln.model.Rule;

import java.util.List;

/**
 * Created by 王彬 on 2017/3/25.
 */
public class MontyHallPartition extends BasePartition {
    private int cnt=1;
    private int level=0;
    private double base=Math.log(2);


    public MontyHallPartition(List<Rule> rules, String asptext, int factor) {
        super(rules, asptext, factor);
    }

    @Override
    public int selectSubset() {
        return 0;
    }

    @Override
    public int selectRule(AugmentedSubset subset) {
        boolean isok=false;
        int rid=0;
        Rule rule=null;

        while (!isok){
            rid=(int) (Math.log(cnt)/base);
            rule=rules.get(rid);
            isok = rule.isSoft() && !subset.getAsprules().contains(rid) && !subset.getRejectrule().contains(rid);
            cnt++;
        }
        return rid;
    }
}
