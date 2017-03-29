package cn.edu.seu.kse.anubis.lpmln.parallel;

import cn.edu.seu.kse.anubis.lpmln.model.AugmentedSubset;
import cn.edu.seu.kse.anubis.lpmln.model.Rule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.rmi.runtime.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Created by 王彬 on 2017/3/19.
 */
public abstract class BasePartition {
    protected List<AugmentedSubset> split=null;
    protected List<Rule> rules;
    protected String asptext;
    protected int factor;
    protected boolean isWeakPartition;
    protected String basepath="G:\\expriment\\parallel_reasoning\\bird";
    protected String partitionId=null;
    private static Logger logger = LogManager.getLogger(BasePartition.class.getName());

    public BasePartition(List<Rule> rules, String asptext, int factor){
        this.rules=rules;
        this.asptext=asptext;
        this.factor=factor;
        split=new ArrayList<>();
        AugmentedSubset subset=new AugmentedSubset();
        split.add(subset);
        partitionId= UUID.randomUUID().toString();
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
        split.add(sub2);
        split.add(sub1);
    }

    public void partition(int n){
        int subIdx=0;
        while (split.size() < n){
            subIdx=selectSubset();
            partitionOneSubset(subIdx);
        }
    }

    public List<String> genSplitTexts(){
        List<String> splits=new ArrayList<>();
        String prog=null;
        for(AugmentedSubset as:split){
            as.setWeakTranslate(isWeakPartition);
            prog=as.getTranslationText(rules,asptext);
            splits.add(prog);
        }
        return splits;
    }

    public List<File> genSplitFiles() throws IOException {
        List<File> splits=new ArrayList<>();
        String path=basepath+"/splits/"+partitionId;
        File dir=new File(path);
        dir.mkdir();
        String filename= path+"/sp_";
        int size=split.size();
        String prog=null;
        AugmentedSubset sub=null;
        File outf=null;
        BufferedWriter bw=null;
        for(int i=0;i<size;i++){
            outf=new File(filename+i+".lp");
//            System.out.println(outf.getAbsolutePath());
            bw=new BufferedWriter(new FileWriter(outf));
            sub=split.get(i);
            sub.setWeakTranslate(isWeakPartition);
            prog=sub.getTranslationText(rules,asptext);
            bw.write(prog);
            bw.close();

            splits.add(outf);
        }
        logger.debug("partitionId = {}, files = {}",partitionId,splits);
        return splits;
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

    public boolean isWeakPartition() {
        return isWeakPartition;
    }

    public void setWeakPartition(boolean weakPartition) {
        isWeakPartition = weakPartition;
    }

    public String getBasepath() {
        return basepath;
    }

    public void setBasepath(String basepath) {
        this.basepath = basepath;
    }

    public String getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(String partitionId) {
        this.partitionId = partitionId;
    }
}
