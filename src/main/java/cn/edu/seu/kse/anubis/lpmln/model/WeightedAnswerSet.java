package cn.edu.seu.kse.anubis.lpmln.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class WeightedAnswerSet {
    private List<Integer> weights;
    private double probability;
    private AnswerSet answerSet;

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
        sb.append("weights : ").append(weights).append(System.lineSeparator());
        sb.append("answer set : ").append(answerSet);
        sb.append("probability : ").append(probability).append(System.lineSeparator());
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
