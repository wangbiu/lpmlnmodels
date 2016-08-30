package cn.edu.seu.kse.anubis.lpmln.translator;

import cn.edu.seu.kse.anubis.lpmln.model.Rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by 王彬 on 2016/8/30.
 */
public class ASPTranslator extends BaseTranslator {

    @Override
    public String translateSoftRule(Rule rule) {
        StringBuilder sb=basicTranslate(rule);
        sb.append(translateCountingPart(rule,true));
        return sb.toString();
    }

    @Override
    public String translateHardRule(Rule rule) {
        StringBuilder sb=basicTranslate(rule);
        sb.append(translateCountingPart(rule,false));
        return sb.toString();
    }

    public StringBuilder basicTranslate(Rule rule){
        StringBuilder sb=new StringBuilder();
        sb.append(translateDeclarationPart(herbrandUniverse));
        sb.append(translateGenerationPart(rule));
        sb.append(translateTestPart(rule));
        return sb;
    }

    public String translateDeclarationPart(HashSet<String> hbu){
        StringBuilder sb=new StringBuilder();
        for(String hb : hbu){
            sb.append("hbu(").append(hb).append(").").append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String translateGenerationPart(Rule rule){
        StringBuilder sb=new StringBuilder();

        sb.append("apply(").append(rule.getRuleLabel()).append(")");
        sb.append(" | -apply(").append(rule.getRuleLabel()).append(") :- ");

        HashSet<String> vars=rule.getVars();
        int cnt=0;
        int size=vars.size()-1;

        for(String v:vars){
            sb.append("hbu(").append(v).append(")");
            if(cnt!=size){
                sb.append(", ");
            }else {
                sb.append(". ").append(System.lineSeparator());
            }
        }

        sb.append(rule.getText()).append(" apply(").append(rule.getRuleLabel()).append(").");
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    public String translateTestPart(Rule rule){
        StringBuilder sb=new StringBuilder();
        String sat="sat("+rule.getRuleLabel()+")";
        String head="h("+rule.getRuleLabel()+")";
        String body="b("+rule.getRuleLabel()+")";
        String apply="apply("+rule.getRuleLabel()+")";

        sb.append(":- ").append(sat).append(", -").append(apply).append(".");
        sb.append(System.lineSeparator());
        sb.append(sat).append(" :- ").append(head).append(", ").append(body).append(".");
        sb.append(System.lineSeparator());
        sb.append(sat).append(" :- not ").append(body).append(", ").append(apply).append(".");
        sb.append(System.lineSeparator());
        sb.append(sat).append(" :- not ").append(body).append(", -").append(apply).append(".");
        sb.append(System.lineSeparator());
        sb.append(body).append(" :- ").append(rule.getBody()).append(".");
        sb.append(System.lineSeparator());

        List<String> heads= rule.getHead();
        for(String h:heads){
            sb.append(head).append(" :- ").append(h).append(", ").append(apply);
            sb.append(System.lineSeparator());
            sb.append(head).append(" :- ").append(h).append(", -").append(apply);
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    public String translateCountingPart(Rule rule, boolean isSoft){
        StringBuilder sb=new StringBuilder();
        sb.append(":~ sat(").append(rule.getRuleLabel()).append("). ");
        sb.append("[");
        if(isSoft){
            int weight= (int) (rule.getWeight()*factor);
            sb.append(weight).append("@0, ");
        }else {
            sb.append("1@1, ");
        }
        sb.append(rule.getId()).append(", ");
        generateVarString(rule.getVars(),sb);
        return sb.toString();
    }

    private void generateVarString(HashSet<String> vars, StringBuilder sb){
        int size=vars.size();
        int cnt=0;
        for(String v:vars){
            sb.append(v);
            if(cnt == size){
                sb.append(", ");
            }else {
                sb.append("]").append(System.lineSeparator());
            }
            cnt++;
        }
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }
}
