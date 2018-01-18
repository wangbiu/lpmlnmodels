package cn.edu.seu.kse.lpmln.translator.impl;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.model.Rule;
import cn.edu.seu.kse.lpmln.translator.LPMLNTranslator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 许鸿翔
 * @date 2018/1/17
 */
public class LPMLN2ASPTranslator implements LPMLNTranslator {
    //将LPMLN规则翻译为ASP规则
    //Lee J, Talsania S, Wang Y. Computing LP MLN using ASP and MLN solvers[J]. Theory and Practice of Logic Programming, 2017, 17(5-6): 942-960.
    protected String satLabel;
    protected boolean isWeakTranslate;
    protected List<String> satRules = new ArrayList<>();
    protected List<String> unsatRules = new ArrayList<>();
    protected List<String> unknownRules = new ArrayList<>();
    protected String staticPart = "";
    protected LpmlnProgram program;
    public LPMLN2ASPTranslator(){ }

    public LPMLN2ASPTranslator(String semantics){
        //TODO:弱翻译方式赋值方法需要修改
        isWeakTranslate = semantics.equals("weak");
    }

    @Override
    public String translate(LpmlnProgram program){
        this.program = program;
        StringBuilder sb=new StringBuilder();
        String rulestr;
        String unsatRulestr;
        sb.append(translateDeclarationPart(program.getHerbrandUniverse()));
        for(Rule r:program.getRules()){
            if(isWeakTranslate&&!r.isSoft()){
                sb.append(r.getOriginalrule()).append(System.lineSeparator());
            }else{
                rulestr = translateRule(r);
                unsatRulestr = translateRuleUnsat(r);
                getSatRules().add(r.getOriginalrule()+System.lineSeparator());
                getUnknownRules().add(rulestr);
                getUnsatRules().add(unsatRulestr);
            }
        }

        sb.append(trickPart()).append(System.lineSeparator());
        sb.append(program.getMetarule());
        setStaticPart(sb.toString());
        return getText();
    }

    public String translateRule(Rule rule) {
        setSatLabel(rule);
        StringBuilder sb = new StringBuilder();
        sb.append(translateGenerationPart(rule));
        sb.append(translateTestPart(rule));
        sb.append(translateCountingPart(rule,true));
        return sb.toString();
    }

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
                .append(" [").append(rule.isSoft()?((long)(rule.getWeight()*program.getFactor())+"@1, "):"1@2, ")
                .append(rule.getId()).append(rule.getVars().size()>0?", ":"").append(String.join(",",rule.getVars())).append("]")
                .append(System.lineSeparator());
        return sb.toString();
    }

    public String trickPart(){
        StringBuilder sb=new StringBuilder();
        sb.append("kse_solve_trick.  ").append(System.lineSeparator());
        sb.append(":~ kse_solve_trick. [1@1]").append(System.lineSeparator());
        sb.append(":~ kse_solve_trick. [1@2]").append(System.lineSeparator());

        return sb.toString();
    }

    //TODO:验证一下是不是可以去掉
    protected String translateDeclarationPart(Set<String> hbu){
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

    public String getText(){
        StringBuilder sb = new StringBuilder();
        sb.append(getStaticPart());
        getUnknownRules().forEach(rule->{
            sb.append(rule).append(System.lineSeparator());
        });
        return sb.toString();
    }

    public void setStaticPart(String staticPart) {
        this.staticPart = staticPart;
    }

    public List<String> getSatRules() {
        return satRules;
    }

    public void setSatRules(List<String> satRules) {
        this.satRules = satRules;
    }

    public List<String> getUnsatRules() {
        return unsatRules;
    }

    public void setUnsatRules(List<String> unsatRules) {
        this.unsatRules = unsatRules;
    }

    public List<String> getUnknownRules() {
        return unknownRules;
    }

    public void setUnknownRules(List<String> unknownRules) {
        this.unknownRules = unknownRules;
    }

    public String getStaticPart() {
        return staticPart;
    }

    public boolean isWeakTranslate() {
        return isWeakTranslate;
    }

    public void setWeakTranslate(boolean weakTranslate) {
        isWeakTranslate = weakTranslate;
    }
}
