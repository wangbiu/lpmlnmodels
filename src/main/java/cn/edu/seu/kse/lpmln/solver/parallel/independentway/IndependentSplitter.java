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
        Set<Set<String>> litSets = merge(program);
        litSets.forEach(lits->{
            List<Rule> subRules = new ArrayList<>();
            programRuleList.forEach(rule -> {
                if(contain(rule,lits)){
                    subRules.add(rule);
                }
            });
            ind.add(new LpmlnProgram(subRules,program.getFactor(),program.getHerbrandUniverse(),program.getMetarule()));
        });
        int sum=0;
        for(int i=0;i<ind.size();i++){
            sum += ind.get(i).getRules().size();
        }
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

    private static Set<Set<String>> merge(LpmlnProgram program){
        Set<Set<String>> ans = new HashSet<>();
        Set<String> allLiterals = new HashSet<>();
        Set<Set<String>> ruleLiterals = new HashSet<>();
        program.getRules().forEach(rule->ruleLiterals.add(LpmlnProgramHelper.getRuleLiteral(rule)));
        ruleLiterals.forEach(ruleLiteral->allLiterals.addAll(ruleLiteral));
        Map<String,Integer> litMap = new HashMap<>();
        Map<Integer,String> idxMap = new HashMap<>();
        Map<Integer,Set<String>> groups = new HashMap<>();
        allLiterals.forEach(lit->{
            litMap.put(lit,litMap.size());
            idxMap.put(idxMap.size(),lit);
        });
        Integer[] literalIdx = new Integer[allLiterals.size()];
        Arrays.fill(literalIdx,-1);

        ruleLiterals.forEach(ruleLiteral->{
            String first = ruleLiteral.iterator().next();
            ruleLiteral.forEach(insamerule->{
                join(litMap.get(first),litMap.get(insamerule),literalIdx);
            });

        });
        for(int i=0;i<literalIdx.length;i++){
            if(literalIdx[i]==-1){
                groups.put(i,new HashSet<>());
                groups.get(i).add(idxMap.get(i));
            }
        }
        for(int i=0;i<literalIdx.length;i++){
            int currentIdx = i;
            if(groups.containsKey(currentIdx)){
                continue;
            }
            while(!groups.keySet().contains(literalIdx[currentIdx])){
                currentIdx = literalIdx[currentIdx];
            }
            groups.get(literalIdx[currentIdx]).add(idxMap.get(i));
        }
        groups.values().forEach(member->ans.add(member));
        return ans;
    }

    private static int find(int litIdx, Integer[] literalIdx){
        int r=litIdx;
        while(literalIdx[r]!=-1) {
            //找到他的前导结点
            r = literalIdx[r];
        }
        int i=litIdx,j;
        //路径压缩算法
        while(i!=r)
        {
            //记录x的前导结点
            j=literalIdx[i];
            //将i的前导结点设置为r根节点
            literalIdx[i]=r;
            i=j;
        }
        return r;
    }

    private static void join(int x,int y,Integer[] literalIdx){
        //x的根节点为a
        int a=find(x,literalIdx);
        //y的根节点为b
        int b=find(y,literalIdx);
        //如果a,b不是相同的根节点，则说明ab不是连通的
        if(a!=b)
        {
            //我们将ab相连 将a的前导结点设置为b
            literalIdx[a]=b;
        }
    }
}
