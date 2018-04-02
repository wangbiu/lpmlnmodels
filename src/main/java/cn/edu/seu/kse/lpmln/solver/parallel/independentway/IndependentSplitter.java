package cn.edu.seu.kse.lpmln.solver.parallel.independentway;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper;

import java.util.*;

/**
 * @author 许鸿翔
 * @date 2018/4/2
 */
public class IndependentSplitter {
    protected LpmlnProgram program;
    protected List<LpmlnProgram> independentPrograms;
    public IndependentSplitter(LpmlnProgram program){
        this.program = program;
    }

    /**
     * 分割子程序
     * @param program 原程序
     * @return 子程序list
     */
    public List<LpmlnProgram> split(LpmlnProgram program){
        List<LpmlnProgram> ind = new ArrayList<>();
        Map<String,Set<String>> dependency = LpmlnProgramHelper.getDependency(program);
        merge(dependency);


        independentPrograms = ind;
        return ind;
    }

    protected void merge(Map<String,Set<String>> dependency){
        new HashSet<>(dependency.keySet()).forEach(lit->{
            new HashSet<>(dependency.entrySet()).forEach(ent->{
                //把lit的set合并到ent的set中
                if((!lit.equals(ent.getKey()))
                        &&ent.getValue().contains(lit)){
                    dependency.get(ent.getKey()).addAll(dependency.get(lit));
                    dependency.remove(lit);
                }
            });
        });
    }
}
