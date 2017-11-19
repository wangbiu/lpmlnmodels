package cn.edu.seu.kse.lpmln.solver.parallel.SplitSetWay;

import cn.edu.seu.kse.lpmln.model.Rule;
import cn.edu.seu.kse.lpmln.solver.Clingo4;

import java.util.HashSet;

/**
 * Created by 许鸿翔 on 2017/9/23.
 */
public class SplitSetSolver extends Clingo4 {
    //通过分割集 并行推理
    //Splitting an LPMLN Program
    protected SPLIT_POLICY split_policy = SPLIT_POLICY.RANDOM;
    protected HashSet<String> setU;
    protected HashSet<Rule> botPart;
    protected HashSet<Rule> topPart;
    public enum SPLIT_POLICY{RANDOM};

    public  SplitSetSolver(){

    }

    public HashSet<String> getSplitSet(){
        return null;
    }


}
