package cn.edu.seu.kse.lpmln.model;

import java.util.ArrayList;
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
            decWeight = weightStr.substring(0,weightStr.length()-factor)+"."+weightStr.substring(weightStr.length()-factor);
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
}
