package cn.edu.seu.kse.lpmln.translator.impl;

import cn.edu.seu.kse.lpmln.exception.solveexception.SolveException;
import cn.edu.seu.kse.lpmln.model.LpmlnProgram;
import cn.edu.seu.kse.lpmln.translator.LPMLNTranslator;

import java.util.*;

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
    public static final String NETPART = "//network part"+System.lineSeparator();
    public static final String CNFPART = "//CNF part"+System.lineSeparator();
    public static final String COMPART = "//completion part"+System.lineSeparator();
    public static final String MAPPING = "//mapping part"+System.lineSeparator();
    public static final String SEPARATOR = "\r\n";
    public static final String GET = " => ";
    public static final String NEG = "-";

    private Map<String,Integer> literalMap = new HashMap<>();
    private Map<Integer,String> reverseMapping = new HashMap<>();


    @Override
    public String translate(LpmlnProgram program) {
        StringBuilder translatedStr = new StringBuilder();
        this.program = program;

        translatedStr.append(NETPART);
        translatedStr.append(networkPart());
        translatedStr.append(SEPARATOR);
        translatedStr.append(SEPARATOR);

        translatedStr.append(CNFPART);
        translatedStr.append(originalPart());
        translatedStr.append(SEPARATOR);
        translatedStr.append(SEPARATOR);

        translatedStr.append(COMPART);
        translatedStr.append(completionPart());
        translatedStr.append(SEPARATOR);
        translatedStr.append(SEPARATOR);

        translatedStr.append(MAPPING);
        translatedStr.append(mappingPart());
        translatedStr.append(SEPARATOR);

        return translatedStr.toString();
    }

    /**
     * 文字映射
     * @return 映射说明
     */
    private String mappingPart(){
        StringBuilder sb = new StringBuilder();
        literalMap.forEach((k,v)->{
            sb.append("//");
            sb.append(k);
            sb.append(" -> ");
            sb.append(v);
            sb.append(SEPARATOR);
        });
        return sb.toString();
    }

    /**
     * body->head
     * @return ruleStr
     */
    private String originalPart(){
        StringJoiner sj = new StringJoiner(SEPARATOR);
        program.getRules().forEach(rule->{
            //MLN语法不支持not
            if(rule.getNegativeBody().size()>0){
                throw new SolveException("'not' is not supported in MLN");
            }



            StringBuilder sb = new StringBuilder();
            if (rule.isSoft()){
                sb.append(rule.getWeight()).append(" ");
            }

            if(rule.getHead().size()==0){
                List<String> opp = new ArrayList<>();
                rule.getPositiveBody().forEach(lit->{
                    if(lit.startsWith("!")){
                        opp.add(lit.substring(1));
                    }else{
                        opp.add("!"+lit);
                    }
                });
                sb.append("(").append(String.join(" v ",opp)).append(")");
            }else if(rule.getPositiveBody().size()>0){
                sb.append(String.join(",",rule.getPositiveBody()));
                sb.append(GET);
            }

            sb.append(String.join(",",rule.getHead()));

            if(!rule.isSoft()){
                sb.append(".");
            }

            sj.add(sb.toString());
        });
        return sj.toString();
    }

    /**
     * body->head
     * @return ruleStr
     */
    private String completionPart(){
        StringJoiner sj = new StringJoiner(SEPARATOR);
        Map<String,List<String>> headToBody = new HashMap<>();
        program.getRules().forEach(rule->{
            if(rule.getPositiveBody().size()==0){
                return;
            }
            String body ="("+String.join(" ^ ",rule.getPositiveBody())+")";
            rule.getHead().forEach(headLit->{
                List<String> bodyList;
                if(headToBody.containsKey(headLit)){
                    bodyList = headToBody.get(headLit);
                }else{
                    bodyList = new ArrayList<>();
                    headToBody.put(headLit,bodyList);
                }
                bodyList.add(body);
            });
        });

        headToBody.forEach((head,body)->{
            StringBuilder sb = new StringBuilder();
            sb.append(head);
            sb.append(GET);
            sb.append(String.join(" v ",body));
            sb.append(".");
            sj.add(sb);
        });
        return sj.toString();
    }

    /**
     *
     * @return ruleStr
     */
    private String networkPart(){
        //基于实例化程序
        literalMap.clear();
        program.getRules().forEach(rule->{
            rule.getHead().forEach(lit->{
                if(lit.startsWith(NEG)){
                    lit = lit.substring(1);
                }
                if(!literalMap.containsKey(lit)){
                    literalMap.put(lit,literalMap.size());
                }
            });
            rule.getPositiveBody().forEach(lit->{
                if(lit.startsWith(NEG)){
                    lit = lit.substring(1);
                }
                if(!literalMap.containsKey(lit)){
                    literalMap.put(lit,literalMap.size());
                }
            });
        });

        if(literalMap.size()==0){
            return "";
        }

        program.getRules().forEach(rule->{
            List<String> head = rule.getHead();
            List<String> body = rule.getPositiveBody();
            for(int i=0;i<head.size();i++){
                head.set(i,toEncoding(head.get(i)));
            }
            for(int i=0;i<body.size();i++){
                body.set(i,toEncoding(body.get(i)));
            }
        });

        reverseMapping.clear();
        literalMap.forEach((k,v)->{
            reverseMapping.put(v,k);
        });

        return "count={0,...,"+(literalMap.size()-1)+"}"+SEPARATOR+"entity(count)"+SEPARATOR;
    }

    private String toEncoding(String literal){
        StringBuilder sb = new StringBuilder();
        if(literal.startsWith(NEG)){
            literal = literal.substring(1);
            sb.append("!");
        }
        if(!literalMap.containsKey(literal)){
            throw new SolveException("translation MLN miss literal.");
        }

        sb.append("entity("+literalMap.get(literal)+")");
        return sb.toString();
    }

    public Map<Integer, String> getReverseMapping() {
        return reverseMapping;
    }
}