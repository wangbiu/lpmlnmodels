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
    protected List<String> unknownRules;
    protected List<String> satRules;
    protected List<String> unsatRules;

    public BaseTranslator() {
        unknownRules = new ArrayList<>();
        satRules = new ArrayList<>();
        unsatRules = new ArrayList<>();
    };
    public BaseTranslator(String semantics){
        isWeakTranslate = semantics.equals("weak");
        unknownRules = new ArrayList<>();
        satRules = new ArrayList<>();
        unsatRules = new ArrayList<>();
    }

    public String translate(List<Rule> rules){
        StringBuilder sb=new StringBuilder();
        String rulestr = null;
        String unsatRulestr = null;
        sb.append(translateDeclarationPart(herbrandUniverse));
        for(Rule r:rules){
            if(isWeakTranslate&&!r.isSoft()){
                sb.append(r.getText()).append(System.lineSeparator());
            }else{
                rulestr = translateRule(r);
                unsatRulestr = translateRuleUnsat(r);
                satRules.add(r.getText());
                unknownRules.add(rulestr);
                unsatRules.add(unsatRulestr);
            }
        }

        sb.append(trickPart()).append(System.lineSeparator());
        sb.append(metarule);
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
}
