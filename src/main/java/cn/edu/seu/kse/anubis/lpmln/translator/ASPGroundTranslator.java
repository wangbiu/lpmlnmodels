package cn.edu.seu.kse.anubis.lpmln.translator;

import cn.edu.seu.kse.anubis.lpmln.model.Rule;

import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2017/1/3.
 */
public class ASPGroundTranslator extends ASPTranslator {
    @Override
    protected String translateDeclarationPart(HashSet<String> hbu) {
        return "";
    }

//    @Override
//    protected String translateCountingPart(Rule rule, boolean isSoft) {
//        StringBuilder sb=new StringBuilder();
//        sb.append(":~ sat(").append(rule.getRuleLabel()).append("). ");
//        sb.append("[");
//        if(isSoft){
//            int weight= (int) (rule.getWeight()*factor);
//            sb.append(weight).append("@1, ");
//        }else {
//            sb.append("1@2, ");
//        }
//        sb.append(rule.getId());
//        generateVarString(rule.getVars(),sb);
//        sb.append("]").append(System.lineSeparator());
//        return sb.toString();
//    }

    @Override
    protected String translateTestPart(Rule rule) {
        StringBuilder sb=new StringBuilder();
        String sat="sat("+rule.getRuleLabel()+")";
        String head="h("+rule.getRuleLabel()+")";
        String body="b("+rule.getRuleLabel()+")";
        String apply="apply("+rule.getRuleLabel()+")";

        sb.append(":- ").append(sat).append(", -").append(apply).append(".");
        sb.append(System.lineSeparator());
        sb.append(sat).append(" :- ").append(head).append(", ").append(body).append(".");
        sb.append(System.lineSeparator());
        sb.append(sat).append(" :- not ").append(body).append(".");
        sb.append(System.lineSeparator());
        if(rule.getBody().equals("")){
            sb.append(body).append(".");
        }else {
            sb.append(body).append(" :- ").append(rule.getBody()).append(".");
        }

        sb.append(System.lineSeparator());

        List<String> heads= rule.getHead();
        for(String h:heads){
            sb.append(head).append(" :- ").append(h).append(".");
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    protected String translateGenerationPart(Rule rule) {
        StringBuilder sb=new StringBuilder();

        sb.append("apply(").append(rule.getRuleLabel()).append(")");
        sb.append(" | -apply(").append(rule.getRuleLabel()).append(") ");

        HashSet<String> vars=rule.getVars();
        int size=vars.size();

        if(size>0){
            sb.append(" :- ");
        }

        sb.append(generateHerbrandBody(vars));

        sb.append(". ").append(System.lineSeparator());

        sb.append(rule.getText()).append(" apply(").append(rule.getRuleLabel()).append(").");
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public String translateSoftRule(Rule rule) {
        StringBuilder sb=basicTranslate(rule);
//        sb.append(translateCountingPart(rule,true));
        return sb.toString();
    }

    @Override
    public String translateHardRule(Rule rule) {
        StringBuilder sb=basicTranslate(rule);
//        sb.append(translateCountingPart(rule,false));
        return sb.toString();
    }

    @Override
    public String translate(List<Rule> rules){
        StringBuilder sb=new StringBuilder();
        String rulestr=null;
        sb.append(translateDeclarationPart(herbrandUniverse));
        for(Rule r:rules){
            if(r.isSoft()){
                rulestr=translateSoftRule(r);
            }else {
                rulestr=translateHardRule(r);
            }
            sb.append(rulestr).append(System.lineSeparator());
        }

        for(Rule r:rules){
            if(r.isSoft()){
                rulestr=translateCountingPart(r,true);
            }else {
                rulestr=translateCountingPart(r,false);
            }
            sb.append(rulestr);
        }
        sb.append(trickPart()).append(System.lineSeparator());
        if(metarule != null){
            sb.append(metarule);
        }
        return sb.toString();
    }
}
