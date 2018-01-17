package cn.edu.seu.kse.lpmln.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 许鸿翔
 * @date 2018/1/17
 */
public class LpmlnProgram {
    private List<Rule> rules;
    private int factor;
    private Set<String> herbrandUniverse;
    private String metarule;

    public LpmlnProgram(){
        rules = new ArrayList<>();
        herbrandUniverse = new HashSet<>();
    }

    public LpmlnProgram(List<Rule> rules,int factor,Set<String> herbrandUniverse,String metarule){
        this.rules = rules;
        this.factor = factor;
        this.herbrandUniverse = herbrandUniverse;
        this.metarule = metarule;
    }

    @Override
    public String toString(){
        return "rules:"+rules+System.lineSeparator()
                +"factor:"+factor+System.lineSeparator()
                +"herbrandUniverse:"+herbrandUniverse+System.lineSeparator()
                +"metarule:"+metarule+System.lineSeparator();
    }


    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public Set<String> getHerbrandUniverse() {
        return herbrandUniverse;
    }

    public void setHerbrandUniverse(Set<String> herbrandUniverse) {
        this.herbrandUniverse = herbrandUniverse;
    }

    public String getMetarule() {
        return metarule;
    }

    public void setMetarule(String metarule) {
        this.metarule = metarule;
    }
}
