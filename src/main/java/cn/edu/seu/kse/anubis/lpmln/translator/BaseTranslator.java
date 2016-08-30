package cn.edu.seu.kse.anubis.lpmln.translator;

import cn.edu.seu.kse.anubis.lpmln.model.Rule;

import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/30.
 */
public class BaseTranslator {
    protected int factor=1;
    protected HashSet<String> herbrandUniverse;

    public String translate(List<Rule> rules){
        StringBuilder sb=new StringBuilder();
        String rulestr=null;
        for(Rule r:rules){
            if(r.isSoft()){
                rulestr=translateHardRule(r);
            }else {
                rulestr=translateSoftRule(r);
            }
            sb.append(rulestr).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String translateSoftRule(Rule rule){
        StringBuilder sb=new StringBuilder();

        return sb.toString();
    }

    public String translateHardRule(Rule rule){
        StringBuilder sb=new StringBuilder();

        return sb.toString();
    }
}
