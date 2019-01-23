package cn.edu.seu.kse.lpmln.translator.impl;

import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.translator.LPMLNTranslator;

/**
 * @author 许鸿翔
 * @date 2019/1/23
 */
public class LPMLN2MLNTranslator implements LPMLNTranslator{
    /**
     * 将LPMLN规则翻译为MLN规则
     * 程序约束：
     * 1.tight
     * 2.nlp
     */
    //Lee J, Talsania S, Wang Y. Computing LP MLN using ASP and MLN solvers[J]. Theory and Practice of Logic Programming, 2017, 17(5-6): 942-960.
    protected LpmlnProgram program;
    public static final String NETPART = "//NetworkPart"+System.lineSeparator();
    public static final String CNFPART = "//CNF part"+System.lineSeparator();
    public static final String COMPART = "//completion part"+System.lineSeparator();


    @Override
    public String translate(LpmlnProgram program) {
        StringBuilder translatedStr = new StringBuilder();
        this.program = program;

        translatedStr.append(NETPART);
        translatedStr.append(networkPart());
        translatedStr.append(System.lineSeparator());

        translatedStr.append(CNFPART);
        translatedStr.append(originalPart());
        translatedStr.append(System.lineSeparator());

        translatedStr.append(COMPART);
        translatedStr.append(completionPart());
        translatedStr.append(System.lineSeparator());

        return translatedStr.toString();
    }

    /**
     * body->head
     * @return ruleStr
     */
    private String originalPart(){
        StringBuilder sb = new StringBuilder();
        program.getRules().forEach(rule->{
            
        });
        return sb.toString();
    }

    /**
     * body->head
     * @return ruleStr
     */
    private String completionPart(){
        return "";
    }

    /**
     *
     * @return ruleStr
     */
    private String networkPart(){
        return "";
    }
}
