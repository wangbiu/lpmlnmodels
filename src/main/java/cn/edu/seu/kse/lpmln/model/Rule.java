package cn.edu.seu.kse.lpmln.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/30.
 */
public class Rule {
    private String text;
    private int id;
    private HashSet<String> vars;
    private boolean isSoft;
    private double weight;
    private int innerweight;
    private List<String> head;
    private List<String> headCondition;
    private List<String> positiveBody;
    private List<String> negativeBody;
    private List<String> bodyContion;
    private String ruleLabel=null;
    private String originalrule;
    private boolean unWeighted = false;

    public Rule(){
        vars=new HashSet<>();
        head=new ArrayList<>();
        positiveBody = new ArrayList<>();
        negativeBody = new ArrayList<>();
        bodyContion = new ArrayList<>();
        headCondition = new ArrayList<>();
    }

    @Override
    public String toString() {
//        StringBuilder sb=new StringBuilder();
//        String ls=System.lineSeparator();
//        sb.append("rule text ").append(text).append(ls);
//        sb.append("rule id ").append(id).append(ls);
//        sb.append("varialbes ").append(vars).append(ls);
//        sb.append("is soft rule ").append(isSoft).append(ls);
//        sb.append("weight ").append(weight).append(ls);
//        sb.append("rule body ").append(getBody()).append(ls);
//        sb.append("rule head ").append(head).append(ls);
//        sb.append("");
//        return sb.toString();
        return getOriginalrule();
    }

    public int getInnerweight() {
        return innerweight;
    }

    public void setInnerweight(int innerweight) {
        this.innerweight = innerweight;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashSet<String> getVars() {
        return vars;
    }

    public void setVars(HashSet<String> vars) {
        this.vars = vars;
    }

    public boolean isSoft() {
        return isSoft;
    }

    public void setSoft(boolean soft) {
        isSoft = soft;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getBody() {
        StringBuilder sb = new StringBuilder();
        for (String positive : positiveBody) {
            sb.append(positive);
            sb.append(", ");
        }
        for (String negative : negativeBody) {
            sb.append(negative);
            sb.append(", ");
        }
        for (String cond : bodyContion) {
            sb.append(cond);
            sb.append("; ");
        }
        return sb.toString();
    }

    public List<String> getHead() {
        return head;
    }

    public void setHead(List<String> head) {
        this.head = head;
    }

    public String getRuleLabel() {
        ruleLabel = new StringBuilder().append("unsat(").append(getRuleLabelPara()).append(")").toString();
        return ruleLabel;
    }

    public String getRuleLabelPara(){
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        for(String v:vars){
            sb.append(", ").append(v);
        }
        if(isSoft){
            sb.append(", 1, ").append((int)weight);
        }else {
            sb.append(", 2, 1");
        }
        return sb.toString();
    }

    @Override
    public Rule clone(){
        Rule cloned = new Rule();
        cloned.text = text;
        cloned.id = id;
        cloned.vars = new HashSet<>(vars);
        cloned.isSoft = isSoft;
        cloned.weight = weight;
        cloned.innerweight = innerweight;
        cloned.head = new ArrayList<>(head);
        cloned.headCondition = new ArrayList<>(headCondition);
        cloned.positiveBody = new ArrayList<>(positiveBody);
        cloned.negativeBody = new ArrayList<>(negativeBody);
        cloned.bodyContion = new ArrayList<>(bodyContion);
        cloned.ruleLabel = ruleLabel;
        cloned.originalrule = originalrule;
        cloned.unWeighted = unWeighted;
        return cloned;
    }

    public String getOriginalrule() {
        return originalrule;
    }

    public void setOriginalrule(String originalrule) {
        this.originalrule = originalrule;
    }

    public List<String> getPositiveBody() {
        return positiveBody;
    }

    public void setPositiveBody(List<String> positiveBody) {
        this.positiveBody = positiveBody;
    }

    public List<String> getNegativeBody() {
        return negativeBody;
    }

    public void setNegativeBody(List<String> negativeBody) {
        this.negativeBody = negativeBody;
    }

    public List<String> getBodyContion() {
        return bodyContion;
    }

    public void setBodyContion(List<String> bodyContion) {
        this.bodyContion = bodyContion;
    }

    public List<String> getHeadCondition() {
        return headCondition;
    }

    public void setHeadCondition(List<String> headCondition) {
        this.headCondition = headCondition;
    }

    public void setRuleLabel(String ruleLabel) {
        this.ruleLabel = ruleLabel;
    }

    public boolean isUnWeighted() {
        return unWeighted;
    }

    public void setUnWeighted(boolean unWeighted) {
        this.unWeighted = unWeighted;
    }
}
