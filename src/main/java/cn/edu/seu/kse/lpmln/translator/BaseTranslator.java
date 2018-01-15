package cn.edu.seu.kse.lpmln.translator;

import cn.edu.seu.kse.lpmln.model.Rule;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/30.
 */
public class BaseTranslator {
    protected int factor=1;
    protected HashSet<String> herbrandUniverse;
    protected String path="";
    protected String metarule=null;
    protected boolean isWeakTranslate = false;
    private List<String> unknownRules;
    private List<String> satRules;
    private List<String> unsatRules;
    private String staticPart = "";

    public BaseTranslator() {
        setUnknownRules(new ArrayList<>());
        setSatRules(new ArrayList<>());
        setUnsatRules(new ArrayList<>());
    };
    public BaseTranslator(String semantics){
        isWeakTranslate = semantics.equals("weak");
        setUnknownRules(new ArrayList<>());
        setSatRules(new ArrayList<>());
        setUnsatRules(new ArrayList<>());
    }

    public String translate(List<Rule> rules){
        StringBuilder sb=new StringBuilder();
        String rulestr = null;
        String unsatRulestr = null;
        sb.append(translateDeclarationPart(herbrandUniverse));
        for(Rule r:rules){
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
        sb.append(metarule);
        setStaticPart(sb.toString());
        return getText();
    }

    public String getText(){
        StringBuilder sb = new StringBuilder();
        sb.append(getStaticPart());
        getUnknownRules().forEach(rule->{
            sb.append(rule).append(System.lineSeparator());
        });
        return sb.toString();
    }

    public String trickPart(){
        return "";
    }

    public String translate2File(List<Rule> rules) throws IOException {
        Date now=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String outf=path+"rule"+(int) (Math.random()*1000)+sdf.format(now);
        BufferedWriter bw=new BufferedWriter(new FileWriter(new File(outf)));
        String rule=translate(rules);
        bw.write(rule);
        bw.close();
        return outf;
    }

    public String translateRule(Rule rule){
        StringBuilder sb=new StringBuilder();

        return sb.toString();
    }

    public String translateRuleUnsat(Rule rule){
        StringBuilder sb=new StringBuilder();

        return sb.toString();
    }

    protected String translateDeclarationPart(HashSet<String> hbu){
        StringBuilder sb=new StringBuilder();

        return sb.toString();
    }

    public String getStaticPart() {
        return staticPart;
    }

    public void setStaticPart(String staticPart) {
        this.staticPart = staticPart;
    }

    public List<String> getUnknownRules() {
        return unknownRules;
    }

    public void setUnknownRules(List<String> unknownRules) {
        this.unknownRules = unknownRules;
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

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public HashSet<String> getHerbrandUniverse() {
        return herbrandUniverse;
    }

    public void setHerbrandUniverse(HashSet<String> herbrandUniverse) {
        this.herbrandUniverse = herbrandUniverse;
    }

    public String getMetarule() {
        return metarule;
    }

    public void setMetarule(String metarule) {
        this.metarule = metarule;
    }

    public boolean isWeakTranslate() {
        return isWeakTranslate;
    }

    public void setWeakTranslate(boolean weakTranslate) {
        isWeakTranslate = weakTranslate;
    }
}
