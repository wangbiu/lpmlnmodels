package cn.edu.seu.kse.lpmln.translator;

import cn.edu.seu.kse.lpmln.model.Rule;

/**
 * Created by 许鸿翔 on 2017/9/14.
 */
public class ASPTranslatorV2 extends  ASPTranslator{
    protected String satLabel;
    public ASPTranslatorV2(){ }

    public ASPTranslatorV2(String semantic){
        super(semantic);
    }

    @Override
    public String translateRule(Rule rule) {
        setSatLabel(rule);
        StringBuilder sb = new StringBuilder();
        sb.append(translateGenerationPart(rule));
        sb.append(translateTestPart(rule));
        sb.append(translateCountingPart(rule,true));
        return sb.toString();
    }

    @Override
    public String translateRuleUnsat(Rule rule){
        setSatLabel(rule);
        StringBuilder sb = new StringBuilder();
        sb.append(translateGenerationPartUnsat(rule));
        sb.append(translateTestPart(rule));
        sb.append(translateCountingPart(rule,true));
        return sb.toString();
    }

    @Override
    protected String translateGenerationPart(Rule rule){
        StringBuilder sb = new StringBuilder();
        sb.append(rule.getText())
                .append("not ").append(satLabel).append(", ");
        if(sb.charAt(sb.length()-2)==';'||sb.charAt(sb.length()-2)==','){
            sb.delete(sb.length()-2,sb.length());
        }
        sb.append(".").append(System.lineSeparator());
        return sb.toString();
    }

    protected String translateGenerationPartUnsat(Rule rule){
        StringBuilder sb = new StringBuilder();
        sb.append(":- ").append("not ").append(satLabel).append(", ");
        if(sb.charAt(sb.length()-2)==';'||sb.charAt(sb.length()-2)==','){
            sb.delete(sb.length()-2,sb.length());
        }
        sb.append(".").append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    protected String translateTestPart(Rule rule){
        StringBuilder sb = new StringBuilder();

        sb.append(satLabel).append(" :- ").append(rule.getBody());
        for (String str : rule.getHead()) {
            sb.append("not ").append(str).append(", ");
        }
        for (String str : rule.getHeadCondition()) {
            sb.append("not ").append(str).append("; ");
        }
        if(sb.charAt(sb.length()-2)==';'||sb.charAt(sb.length()-2)==','){
            sb.delete(sb.length()-2,sb.length());
        }

        sb.append(".").append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    protected String translateCountingPart(Rule rule, boolean isSoft){
        StringBuilder sb = new StringBuilder();
        sb.append(":~").append(satLabel).append(".")
                .append(" [").append(isSoft?((long)(rule.getWeight()*factor)+"@1, "):"1@2, ")
                .append(rule.getId()).append(rule.getVars().size()>0?", ":"").append(String.join(",",rule.getVars())).append("]")
                .append(System.lineSeparator());
        return sb.toString();
    }

    public String hubPart(Rule rule){
        StringBuilder sb = new StringBuilder();
        sb.append(" ").append(generateHerbrandBody(rule.getVars())).append(",");
        return sb.substring(0,sb.length()-1);
    }
    protected void setSatLabel(Rule rule){
        StringBuilder sb = new StringBuilder().append("unsat(").append(rule.getRuleLabelPara()).append(")");
        satLabel = sb.toString();
        rule.setRuleLabel(satLabel);
    }
}
