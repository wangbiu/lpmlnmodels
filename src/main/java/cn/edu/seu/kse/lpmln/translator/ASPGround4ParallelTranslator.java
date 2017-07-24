package cn.edu.seu.kse.lpmln.translator;

import cn.edu.seu.kse.lpmln.model.Rule;

import java.util.HashSet;

/**
 * Created by 王彬 on 2017/3/23.
 */
public class ASPGround4ParallelTranslator extends ASPGroundTranslator {
    @Override
    protected String translateGenerationPart(Rule rule) {
        StringBuilder sb=new StringBuilder();


        HashSet<String> vars=rule.getVars();
        int size=vars.size();

        sb.append(rule.getText()).append(" apply(").append(rule.getRuleLabel()).append(").");
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
