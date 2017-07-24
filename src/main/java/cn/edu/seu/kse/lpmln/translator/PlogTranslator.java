package cn.edu.seu.kse.lpmln.translator;

import cn.edu.seu.kse.lpmln.model.Rule;

import java.util.HashSet;

/**
 * Created by 王彬 on 2016/8/31.
 */
public class PlogTranslator extends ASPTranslator {


    @Override
    public String translateHardRule(Rule rule) {
        StringBuilder sb=new StringBuilder();
        sb.append(rule.getText()).append(" not ab(");
        sb.append(rule.getRuleLabel()).append("). ").append(System.lineSeparator());
        sb.append("ab(").append(rule.getRuleLabel()).append(") :+ ");
        HashSet<String> vars=rule.getVars();
        int cnt=0;
        int size=vars.size()-1;

        for(String v:vars){
            sb.append("hbu(").append(v).append(")");
            if(cnt!=size){
                sb.append(", ");
            }
            cnt++;
        }
        sb.append(".").append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    protected String translateDeclarationPart(HashSet<String> hbu) {
        return super.translateDeclarationPart(hbu);
    }

    @Override
    protected String translateGenerationPart(Rule rule) {
        StringBuilder sb=new StringBuilder();
        sb.append(rule.getText()).append(" apply(").append(rule.getRuleLabel()).append(").");
        sb.append(System.lineSeparator());

        sb.append("random(").append(" apply(").append(rule.getRuleLabel()).append(")");
        HashSet<String> vars=rule.getVars();
        int cnt=0;
        int size=vars.size()-1;

        if(size>=0){
            sb.append(" :- ");
        }

        for(String v:vars){
            sb.append("hbu(").append(v).append(")");
            if(cnt!=size){
                sb.append(", ");
            }
            cnt++;
        }
        sb.append(". ").append(System.lineSeparator());
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    protected String translateTestPart(Rule rule) {
        return super.translateTestPart(rule);
    }

    @Override
    protected String translateCountingPart(Rule rule, boolean isSoft) {
        StringBuilder sb=new StringBuilder();
        sb.append("pr(");
        sb.append("apply(").append(rule.getRuleLabel()).append(") = true ) = ");
        double weight=Math.exp(rule.getWeight());
        weight=weight/(1+weight);
        sb.append(weight).append(".");

        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
