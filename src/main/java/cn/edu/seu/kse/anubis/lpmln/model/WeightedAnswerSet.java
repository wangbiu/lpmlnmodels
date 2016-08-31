package cn.edu.seu.kse.anubis.lpmln.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class WeightedAnswerSet {
    private List<Integer> weights;
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

}
