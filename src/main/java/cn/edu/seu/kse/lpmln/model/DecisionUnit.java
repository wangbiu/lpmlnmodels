package cn.edu.seu.kse.lpmln.model;

import java.util.List;
import java.util.Set;

/**
 * @author 许鸿翔
 * @date 2018/4/7
 */
public class DecisionUnit {
    private Set<String> lit;
    private double wr;
    private double wl;
    private List<DecisionUnit> to;
    private List<DecisionUnit> from;
    //TODO:完成这个模型
    public DecisionUnit(Set<String> lit){
        this.lit =lit;
    }
}
