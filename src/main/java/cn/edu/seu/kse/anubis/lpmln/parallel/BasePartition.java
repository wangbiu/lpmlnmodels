package cn.edu.seu.kse.anubis.lpmln.parallel;

import cn.edu.seu.kse.anubis.lpmln.model.AugmentedSubset;
import cn.edu.seu.kse.anubis.lpmln.model.Rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2017/3/19.
 */
public abstract class BasePartition {
    protected List<AugmentedSubset> split=null;
    protected List<Rule> rules;
    protected String asptext;
    protected int factor;

    public BasePartition(List<Rule> rules, String asptext, int factor){
        this.rules=rules;
        this.asptext=asptext;
        this.factor=factor;
        split=new ArrayList<>();
        AugmentedSubset subset=new AugmentedSubset();
        split.add(subset);
    }


    public abstract int selectSubset();

    public abstract int selectRule(AugmentedSubset subset);

    public void partitionOneSubset(int subsetIdx){
        AugmentedSubset subset=split.get(subsetIdx);
        int ruleIdx=selectRule(subset);
        Rule rule=rules.get(ruleIdx);

        AugmentedSubset sub1=new AugmentedSubset(subset);
        AugmentedSubset sub2=new AugmentedSubset(subset);
        HashSet<Integer> asprule=null;
        HashSet<Integer> rejectrule=null;
        rejectrule=sub1.getRejectrule();
        rejectrule.add(ruleIdx);
        asprule=sub2.getAsprules();
        asprule.add(ruleIdx);

        rule.setInnerweight((int )(rule.getWeight()*factor));
        if(rule.isSoft()){
            sub2.setWs(sub2.getWs()+rule.getInnerweight());
        }else {
            sub2.setWh(sub2.getWh()+1);
        }

        split.remove(subsetIdx);
        split.add(sub1);
        split.add(sub2);
    }

    public void partition(int n){
        int subIdx=0;
        while (split.size() < n){
            subIdx=selectSubset();
            partitionOneSubset(subIdx);
        }
    }

    public List<AugmentedSubset> getSplit() {
        return split;
    }

    public void setSplit(List<AugmentedSubset> split) {
        this.split = split;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public String getAsptext() {
        return asptext;
    }

    public void setAsptext(String asptext) {
        this.asptext = asptext;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }
}
