package cn.edu.seu.kse.lpmln.model;

import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2017/3/19.
 */
public class AugmentedSubset {
    private HashSet<Integer> asprules;
    private HashSet<Integer> rejectrule;
    private boolean isWeakTranslate=false;
    private int wh;
    private int ws;

    public AugmentedSubset(AugmentedSubset subset) {
        asprules =new HashSet<>();
        asprules.addAll(subset.getAsprules());
        rejectrule=new HashSet<>();
        rejectrule.addAll(subset.getRejectrule());
        wh=subset.getWh();
        ws=subset.getWs();
    }

    public AugmentedSubset() {
        asprules =new HashSet<>();
        rejectrule=new HashSet<>();
        wh=0;
        ws=0;
    }

    public HashSet<Integer> getRejectrule() {
        return rejectrule;
    }

    public void setRejectrule(HashSet<Integer> rejectrule) {
        this.rejectrule = rejectrule;
    }

    public HashSet<Integer> getAsprules() {
        return asprules;
    }

    public void setAsprules(HashSet<Integer> asprules) {
        this.asprules = asprules;
    }

    public int getWh() {
        return wh;
    }

    public void setWh(int wh) {
        this.wh = wh;
    }

    public int getWs() {
        return ws;
    }

    public void setWs(int ws) {
        this.ws = ws;
    }

    public String getTranslationText(List<Rule> rules, String asptext){
        StringBuilder sb=new StringBuilder();
        String line=System.lineSeparator();
        sb.append(asptext).append(line);
        String rb=null;
//        for(int r: asprules){
//            rb=rules.get(r).getRuleLabel();
//            sb.append("apply(").append(rb).append(").").append(line);
//        }
//
//        for(int r:rejectrule){
//            rb=rules.get(r).getRuleLabel();
//            sb.append("-apply(").append(rb).append(").").append(line);
//        }

        int size=rules.size();
        for(int i=0;i<size;i++){
            rb=rules.get(i).getRuleLabel();
            if(asprules.contains(i)){
                sb.append("apply(").append(rb).append(").").append(line);
            }else if(rejectrule.contains(i)){
                sb.append("-apply(").append(rb).append(").").append(line);
            }else {
                if(isWeakTranslate){
                    if(rules.get(i).isSoft()){
                        sb.append("apply(").append(rb).append(") | -apply(");
                        sb.append(rb).append(").").append(line);
                    }
                }else {
                    sb.append("apply(").append(rb).append(") | -apply(");
                    sb.append(rb).append(").").append(line);
                }
            }
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("augmented subset: asprules = ").append(asprules);
        sb.append(", rejectrules = ").append(rejectrule);
        sb.append(", ws = ").append(ws);
        sb.append(", wh = ").append(wh);
        return sb.toString();
    }

    public boolean isWeakTranslate() {
        return isWeakTranslate;
    }

    public void setWeakTranslate(boolean weakTranslate) {
        isWeakTranslate = weakTranslate;
    }
}
