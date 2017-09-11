package cn.edu.seu.kse.anubis.lpmln.translator;

import cn.edu.seu.kse.anubis.lpmln.model.Rule;

import java.util.HashSet;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class PlogCRRuleTranslator extends PlogTranslator{
    @Override
    public String translateHardRule(Rule rule) {
        StringBuilder sb=new StringBuilder();
        sb.append(translateHardRuleGenerationPart(rule));
        sb.append(translateCountingPart(rule,false));
        return sb.toString();
    }

    @Override
    protected String translateCountingPart(Rule rule, boolean isSoft) {
        if(isSoft){
            return super.translateCountingPart(rule, isSoft);
        }else {
            StringBuilder sb=new StringBuilder();
            sb.append(":~ ");
            if(!rule.getBody().equals("")){
                sb.append(rule.getBody());
            }
            sb.append("apply(").append(rule.getRuleLabel()).append("). [1@1, ");
            sb.append(rule.getId());
            HashSet<String> vars=rule.getVars();
            for(String v:vars){
                sb.append(",").append(v);
            }
            sb.append("]").append(System.lineSeparator());

            return sb.toString();
        }
    }

    public String translateHardRuleGenerationPart(Rule rule){
        StringBuilder sb=new StringBuilder();

        sb.append("apply(").append(rule.getRuleLabel()).append(")");
        sb.append(" | -apply(").append(rule.getRuleLabel()).append(") ");

        HashSet<String> vars=rule.getVars();
        int cnt=0;
        int size=vars.size()-1;

        if(size>=0){
            sb.append(":- ");
        }

        for(String v:vars){
            sb.append("hbu(").append(v).append(")");
            if(cnt!=size){
                sb.append(", ");
            }
            cnt++;
        }
        sb.append(". ").append(System.lineSeparator());

        sb.append(rule.getText()).append(" apply(").append(rule.getRuleLabel()).append(").");
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
