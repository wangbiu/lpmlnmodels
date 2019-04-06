package cn.edu.seu.kse.lpmln.model;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class WeightedAnswerSet {
    // weights[0] soft weight， weights[1] hard weight
    private List<Integer> weights;
    private double probability;
    private AnswerSet answerSet;
    private int factor;

    public WeightedAnswerSet(){
        weights=new ArrayList<>();
        answerSet=new AnswerSet();
    }

    public WeightedAnswerSet(String[] strs,int weight0,int weight1){
        this.answerSet = new AnswerSet();
        CollectionUtils.addAll(answerSet.getLiterals(),strs);
        Integer[] in = {weight0,weight1};
        weights = Arrays.asList(in);
    }

    public List<Integer> getWeights() {
        return weights;
    }

    public void setWeights(List<Integer> weights) {
        this.weights = weights;
    }

    public AnswerSet getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(AnswerSet answerSet) {
        this.answerSet = answerSet;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        String weightStr = String.valueOf(weights.get(0));
        String decWeight = "";
        if(factor!=0){
            int len = Math.max(0,weightStr.length()-factor);
            decWeight = String.format("%."+len+"f",Double.valueOf(weightStr)/Math.pow(10,factor));
            //decWeight = weightStr.substring(0,weightStr.length()-factor)+"."+weightStr.substring(weightStr.length()-factor);
        }else{
            decWeight = weightStr;
        }
        sb.append("weights : [").append(decWeight)
                .append(", ").append(weights.get(1)).append("]")
                .append(System.lineSeparator());
        sb.append("answer set : ").append(answerSet);
        sb.append("probability : ").append(probability).append(System.lineSeparator())
                .append(System.lineSeparator());
        return sb.toString();
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    /**
     * 深复制，合并回答集的时候用
     * @return 克隆结果
     */
    @Override
    public WeightedAnswerSet clone(){
        WeightedAnswerSet cloned = new WeightedAnswerSet();
        cloned.getWeights().addAll(weights);
        cloned.getAnswerSet().getLiterals().addAll(answerSet.getLiterals());
        cloned.probability = probability;
        cloned.factor = factor;
        return cloned;
    }
}
