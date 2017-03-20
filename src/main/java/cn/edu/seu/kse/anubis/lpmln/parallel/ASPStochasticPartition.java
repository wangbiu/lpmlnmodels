package cn.edu.seu.kse.anubis.lpmln.parallel;

import cn.edu.seu.kse.anubis.lpmln.model.AugmentedSubset;
import cn.edu.seu.kse.anubis.lpmln.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Created by 王彬 on 2017/3/19.
 */
public class ASPStochasticPartition extends BasePartition {
    public ASPStochasticPartition(List<Rule> rules, String asptext, int factor) {
        super(rules, asptext, factor);
    }

    @Override
    public int selectSubset() {
        int size=split.size();
        return getRandomNumber(size);
    }

    @Override
    public int selectRule(AugmentedSubset subset) {
        int size=rules.size();
        int rand=0;
        HashSet<Integer> asprule=subset.getAsprules();
        HashSet<Integer> rejectrule=subset.getRejectrule();
        for(;;){
            rand=getRandomNumber(size);
            if(asprule.contains(rand) || rejectrule.contains(rand)){
                continue;
            }else {
                break;
            }
        }

        return rand;
    }

    private int getRandomNumber(int upper){
        int num=0;
        Random rand=new Random();
        num=rand.nextInt(upper);
        return num;
    }
}
