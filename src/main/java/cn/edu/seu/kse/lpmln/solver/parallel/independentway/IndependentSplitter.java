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
        Set<Set<String>> litSets = merge(dependency);
        litSets.forEach(lits->{
            List<Rule> subRules = new ArrayList<>();
            programRuleList.forEach(rule -> {
                if(contain(rule,lits)){
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

    private static Set<Set<String>> merge(Map<String,Set<String>> dependency){
        Set<Set<String>> ans = new HashSet<>();
        Set<String> visited = new HashSet<>();
        Set<String> lits = dependency.keySet();
        lits.forEach(lit->{
            if(visited.contains(lit)){
                return;
            }
            Set<String> subset = new HashSet<>();
            LinkedList<String> current = new LinkedList<>();
            subset.add(lit);
            current.offer(lit);
            while(current.size()>0){
                String next = current.poll();
                if(!dependency.containsKey(next)){
                    continue;
                }
                Set<String> nextDepend = dependency.get(next);
                nextDepend.forEach(nextLit->{
                    if(!subset.contains(nextLit)){
                        subset.add(nextLit);
                        current.offer(nextLit);
                    }
                });
            }
            visited.addAll(subset);
            ans.add(subset);
        });
        return ans;
    }
}
