package cn.edu.seu.kse.anubis.lpmln.model;

import java.util.ArrayList;
import java.util.HashMap;
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
    private String body;
    private List<String> head;

    public Rule(){
        vars=new HashSet<>();
        head=new ArrayList<>();
        body="";
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        String ls=System.lineSeparator();
        sb.append("rule text ").append(text).append(ls);
        sb.append("rule id ").append(id).append(ls);
        sb.append("varialbes ").append(vars).append(ls);
        sb.append("is soft rule ").append(isSoft).append(ls);
        sb.append("weight ").append(weight).append(ls);
        sb.append("rule body ").append(body).append(ls);
        sb.append("rule head ").append(head).append(ls);
        sb.append("");
        return sb.toString();
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
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getHead() {
        return head;
    }

    public void setHead(List<String> head) {
        this.head = head;
    }
}
