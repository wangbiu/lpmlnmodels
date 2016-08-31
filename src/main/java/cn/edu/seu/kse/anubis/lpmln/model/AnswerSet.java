package cn.edu.seu.kse.anubis.lpmln.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class AnswerSet {
    private HashSet<String> literals;

    public AnswerSet() {
        this.literals = new HashSet<>();
    }

    public void add(String e){
        literals.add(e);
    }

    public int size(){
        return literals.size();
    }

    public boolean contains(String e){
        return literals.contains(e);
    }

    public HashSet<String> getLiterals() {
        return literals;
    }

    public void setLiterals(HashSet<String> literals) {
        this.literals = literals;
    }
}
