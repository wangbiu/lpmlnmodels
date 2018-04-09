package cn.edu.seu.kse.lpmln.util;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.Rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
}
