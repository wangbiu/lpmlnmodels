package cn.edu.seu.kse.lpmln.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;

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

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        int cnt=0;
        int size=literals.size()-1;
        for(String s : literals){
            sb.append(s);

            if(cnt != size){
                sb.append(", ");
            }
            cnt++;
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
