package cn.edu.seu.kse.lpmln.model;

import cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper;

import java.util.List;
import java.util.Set;

/**
 * @author 许鸿翔
 * @date 2018/4/7
 */
public class DecisionUnit {
    private Set<String> lit;
    private int wr;
    private int wl;
    private List<DecisionUnit> to;
    private List<DecisionUnit> from;
    private LpmlnProgram program;
    //TODO:完成这个模型
    public DecisionUnit(LpmlnProgram program,Set<String> lit){
        this.lit =lit;
        this.program = program;
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

    public List<DecisionUnit> getTo() {
        return to;
    }

    public void setTo(List<DecisionUnit> to) {
        this.to = to;
    }

    public List<DecisionUnit> getFrom() {
        return from;
    }

    public void setFrom(List<DecisionUnit> from) {
        this.from = from;
    }
}
