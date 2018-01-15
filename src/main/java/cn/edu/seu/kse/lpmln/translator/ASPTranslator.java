package cn.edu.seu.kse.lpmln.translator;

import cn.edu.seu.kse.lpmln.model.Rule;

import java.util.HashSet;

/**
 * Created by 许鸿翔 on 2017/9/14.
 */
public class ASPTranslator extends BaseTranslator{
    //将LPMLN规则翻译为ASP规则
    //Lee J, Talsania S, Wang Y. Computing LP MLN using ASP and MLN solvers[J]. Theory and Practice of Logic Programming, 2017, 17(5-6): 942-960.
    protected String satLabel;
    public ASPTranslator(){ }

    public ASPTranslator(String semantic){
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

    protected String translateCountingPart(Rule rule, boolean isSoft){
        StringBuilder sb = new StringBuilder();
        sb.append(":~").append(satLabel).append(".")
                .append(" [").append(rule.isSoft()?((long)(rule.getWeight()*factor)+"@1, "):"1@2, ")
                .append(rule.getId()).append(rule.getVars().size()>0?", ":"").append(String.join(",",rule.getVars())).append("]")
                .append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    public String trickPart(){
        StringBuilder sb=new StringBuilder();
        sb.append("kse_solve_trick.  ").append(System.lineSeparator());
        sb.append(":~ kse_solve_trick. [1@1]").append(System.lineSeparator());
        sb.append(":~ kse_solve_trick. [1@2]").append(System.lineSeparator());

        return sb.toString();
    }

    //TODO:验证一下是不是可以去掉
    @Override
    protected String translateDeclarationPart(HashSet<String> hbu){
        StringBuilder sb=new StringBuilder();
        for(String hb : hbu){
            sb.append("hbu(").append(hb).append(").").append(System.lineSeparator());
        }
        return sb.toString();
    }

    protected void setSatLabel(Rule rule){
        StringBuilder sb = new StringBuilder().append("unsat(").append(rule.getRuleLabelPara()).append(")");
        satLabel = sb.toString();
        rule.setRuleLabel(satLabel);
    }
}
