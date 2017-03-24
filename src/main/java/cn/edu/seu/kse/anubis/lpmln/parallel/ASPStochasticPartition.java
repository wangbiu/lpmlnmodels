package cn.edu.seu.kse.anubis.lpmln.parallel;

import cn.edu.seu.kse.anubis.lpmln.model.AugmentedSubset;
import cn.edu.seu.kse.anubis.lpmln.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by 王彬 on 2017/3/19.
 */
public class ASPStochasticPartition extends BasePartition {

    public ASPStochasticPartition(List<Rule> rules, String asptext, int factor) {
        super(rules, asptext, factor);
        partitionId= UUID.randomUUID().toString();
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
        Rule rule=null;
        for(;;){
            rand=getRandomNumber(size);
            if(asprule.contains(rand) || rejectrule.contains(rand)){
                continue;
            }else {
                if(isWeakPartition){
                    rule=rules.get(rand);
                    if(!rule.isSoft()){
                        continue;
                    }else {
                        break;
                    }
                }else {
                    break;
                }
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

    public boolean isWeakPartition() {
        return isWeakPartition;
    }

    public void setWeakPartition(boolean weakPartition) {
        isWeakPartition = weakPartition;
    }
}
