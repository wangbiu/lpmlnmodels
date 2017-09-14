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
    public String translateHardRule(Rule rule) {
        setSatLabel(rule);
        if(isWeakTranslate){
            return rule.getOriginalrule();
        }else{
            return super.translateHardRule(rule);
        }
    }

    @Override
    public String translateSoftRule(Rule rule){
        setSatLabel(rule);
        return super.translateSoftRule(rule);
    }

    @Override
    protected String translateGenerationPart(Rule rule){
        return new StringBuilder().append(rule.getText())
                .append("not ").append(satLabel)
                .append(".").append(System.lineSeparator()).toString();
    }

    @Override
    protected String translateTestPart(Rule rule){
        StringBuilder sb = new StringBuilder();

        sb.append(satLabel).append(" :- ").append(rule.getBody());
        if(rule.getHead().size()>0){
            if(rule.getBody().length()>0) sb.append(",");
            sb.append("not ").append(String.join(",not ",rule.getHead()));
        }
        sb.append(".").append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    protected String translateCountingPart(Rule rule, boolean isSoft){
        return new StringBuilder().append(":~").append(satLabel).append(".")
                .append(" [").append(isSoft?((int)(-rule.getWeight()*factor)+"@1, "):"-1@2, ")
                .append(rule.getId()).append(rule.getVars().size()>0?", ":"").append(String.join(",",rule.getVars())).append("]")
                .append(System.lineSeparator()).toString();
    }

    protected void setSatLabel(Rule rule){
        satLabel = new StringBuilder().append("unsat(").append(rule.getRuleLabelPara()).append(")").toString();
    }
}
