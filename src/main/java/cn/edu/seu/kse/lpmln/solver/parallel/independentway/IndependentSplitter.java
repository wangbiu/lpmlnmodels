package cn.edu.seu.kse.lpmln.solver.parallel.independentway;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.Rule;
import cn.edu.seu.kse.lpmln.util.LpmlnProgramHelper;

import java.util.*;

/**
 * @author 许鸿翔
 * @date 2018/4/2
 */
public class IndependentSplitter {
    /**
     * 分割子程序
     * @param program 原程序
     * @return 子程序list
     */
    public static List<LpmlnProgram> split(LpmlnProgram program){
        List<Rule> programRuleList = program.getRules();
        List<LpmlnProgram> ind = new ArrayList<>();
        Map<String,Set<String>> dependency = LpmlnProgramHelper.getDependency(program);
        merge(dependency);
        dependency.forEach((k,v)->{
            List<Rule> subRules = new ArrayList<>();
            programRuleList.forEach(rule -> {
                if(contain(rule,v)){
                    subRules.add(rule);
                }
            });
            ind.add(new LpmlnProgram(subRules,program.getFactor(),program.getHerbrandUniverse(),program.getMetarule()));
        });
        return ind;
    }

    private static boolean contain(Rule r, Set<String> lits){
        for (String lit : r.getHead()) {
            if(lits.contains(LpmlnProgramHelper.getLiteral(lit))){
                return true;
            }
        }
        for (String lit : r.getPositiveBody()) {
            if(lits.contains(LpmlnProgramHelper.getLiteral(lit))){
                return true;
            }
        }
        for (String lit : r.getNegativeBody()) {
            if(lits.contains(LpmlnProgramHelper.getLiteral(lit))){
                return true;
            }
        }
        return false;
    }

    private static void merge(Map<String,Set<String>> dependency){
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
