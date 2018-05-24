package cn.edu.seu.kse.lpmln.util;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.Rule;

import java.util.*;

/**
 * @author 许鸿翔
 * @date 2018/4/2
 */
public class LpmlnProgramHelper {
    public static Map<String,Set<String>> getDependency(LpmlnProgram program){
        Map<String,Set<String>> dependency = new HashMap<>();
        program.getRules().forEach(rule -> {
            Set<String> head = new HashSet<>();
            Set<String> body = new HashSet<>();
            Set<String> depend = new HashSet<>();
            rule.getHead().forEach(lit->head.add(getLiteral(lit)));
            rule.getPositiveBody().forEach(lit->body.add(getLiteral(lit)));
            rule.getNegativeBody().forEach(lit->body.add(getLiteral(lit)));
            depend.addAll(head);
            depend.addAll(body);
            head.forEach(lit->{
                if(dependency.containsKey(lit)){
                    dependency.get(lit).addAll(depend);
                }else{
                    dependency.put(lit,depend);
                }
            });
        });
        return dependency;
    }

    public static Map<String,Set<String>> getPostiveDependency(LpmlnProgram program){
        Map<String,Set<String>> dependency = new HashMap<>();
        program.getRules().forEach(rule -> {
            Set<String> head = new HashSet<>();
            Set<String> depend = new HashSet<>();
            rule.getHead().forEach(lit->head.add(getLiteral(lit)));
            rule.getPositiveBody().forEach(lit->depend.add(getLiteral(lit)));
            head.forEach(lit->{
                if(dependency.containsKey(lit)){
                    dependency.get(lit).addAll(depend);
                }else{
                    dependency.put(lit,depend);
                }
            });
        });
        return dependency;
    }

    public static Map<String,Set<String>> getAtomDependency(LpmlnProgram program){
        Map<String,Set<String>> dependency = new HashMap<>();
        program.getRules().forEach(rule -> {
            Set<String> head = new HashSet<>();
            Set<String> depend = new HashSet<>();
            rule.getHead().forEach(lit->head.add(getLiteral(lit)));
            rule.getPositiveBody().forEach(lit->depend.add(getLiteral(lit)));
            rule.getNegativeBody().forEach(lit->depend.add(getLiteral(lit)));
            head.forEach(lit->{
                if(dependency.containsKey(lit)){
                    dependency.get(lit).addAll(depend);
                }else{
                    dependency.put(lit,depend);
                }
            });
        });
        return dependency;
    }

    public static String getLiteral(String lit){
        lit = lit.trim();
        while(lit.startsWith("not")||lit.startsWith("-")){
            if(lit.startsWith("not")){
                lit = lit.substring(3).trim();
            }else if(lit.startsWith("-")){
                lit = lit.substring(1).trim();
            }
        }
        return lit;
    }

    public static Set<String> getRuleLiteral(Rule r){
        Set<String> ans = new HashSet<>();
        for (String lit : r.getHead()) {
            ans.add(LpmlnProgramHelper.getLiteral(lit));
        }
        for (String lit : r.getPositiveBody()) {
            ans.add(LpmlnProgramHelper.getLiteral(lit));
        }
        for (String lit : r.getNegativeBody()) {
            ans.add(LpmlnProgramHelper.getLiteral(lit));
        }
        return ans;
    }

    public static Map<String,Set<String>> dependToReachable(Map<String,Set<String>> dependency){
        Map<String,Set<String>> reachable = new HashMap<>();
        dependency.keySet().forEach(start->{
            Set<String> end = new HashSet<>();
            reachable.put(start,end);
            LinkedList<String> toVisit = new LinkedList<>(dependency.get(start));
            while(toVisit.size()>0){
                String next = toVisit.poll();
                if(!end.contains(next)){
                    end.add(next);
                    if(dependency.containsKey(next)){
                        toVisit.addAll(dependency.get(next));
                    }
                }
            }
        });
        return reachable;
    }

    public static Set<Set<String>> reachableToLitSets(Map<String,Set<String>> reachable){
        UnionFindSet<String> unionFindSet = new UnionFindSet<>(reachable.keySet());
        reachable.forEach((from,toSet)->{
            toSet.forEach(to->{
                if(reachable.keySet().contains(to)&&reachable.get(to).contains(from)){
                    unionFindSet.join(from,to);
                }
            });
        });
        Map<String,Set<String>> ansMap = new HashMap<>();
        reachable.keySet().forEach(lit->{
            String root = unionFindSet.find(lit);
            Set<String> rootAim;
            if(ansMap.containsKey(root)){
                rootAim = ansMap.get(root);
            }else{
                rootAim = new HashSet<>();
                ansMap.put(root,rootAim);
            }
            rootAim.add(lit);
        });
        return new HashSet<>(ansMap.values());
    }
}
