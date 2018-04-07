package cn.edu.seu.kse.lpmln.model;

import cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 许鸿翔
 * @date 2018/4/7
 */
public class DecisionUnit {
    private Set<String> lit;
    private int wr;
    private int wl;
    private Set<DecisionUnit> to;
    private Set<DecisionUnit> from;
    private LpmlnProgram program;
    //TODO:完成这个模型
    public DecisionUnit(LpmlnProgram program,Set<String> lit){
        this.lit =lit;
        this.program = program;
        from = new HashSet<>();
        to = new HashSet<>();
        generateWeight();
    }

    private void generateWeight(){
        wl = lit.size();
        wr=0;
        for (Rule rule : program.getRules()) {
            for (String headLit : rule.getHead()) {
                if(lit.contains(LpmlnProgramHelper.getLiteral(headLit))){
                    wr++;
                    break;
                }
            }
        }
    }

    @Override
    public String toString(){
        return String.join(",",lit);
    }

    public int getWr() {
        return wr;
    }

    public void setWr(int wr) {
        this.wr = wr;
    }

    public int getWl() {
        return wl;
    }

    public void setWl(int wl) {
        this.wl = wl;
    }

    public Set<DecisionUnit> getTo() {
        return to;
    }

    public void setTo(Set<DecisionUnit> to) {
        this.to = to;
    }

    public Set<DecisionUnit> getFrom() {
        return from;
    }

    public void setFrom(Set<DecisionUnit> from) {
        this.from = from;
    }

    public Set<String> getLit() {
        return lit;
    }

    public void setLit(Set<String> lit) {
        this.lit = lit;
    }
}
