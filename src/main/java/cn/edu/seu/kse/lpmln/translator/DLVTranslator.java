package cn.edu.seu.kse.lpmln.translator;

import cn.edu.seu.kse.lpmln.model.Rule;

/**
 * Created by 王彬 on 2016/8/30.
 */
@Deprecated
public class DLVTranslator extends ASPTranslator {
    public DLVTranslator(){};
    public DLVTranslator(String semantics) {
        super(semantics);
    }

    @Override
    protected String translateCountingPart(Rule rule, boolean isSoft) {
        StringBuilder sb=new StringBuilder();
        sb.append(":~ sat(").append(rule.getRuleLabel()).append("). ");
        sb.append("[");
        if(isSoft){
            int weight= (int) (rule.getWeight()*factor);
            sb.append(weight).append(":1]");
        }else {
            sb.append("1:2] ");
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    public String trickPart() {
        StringBuilder sb=new StringBuilder();
        sb.append("kse_solve_trick.  ").append(System.lineSeparator());
        sb.append(":~ kse_solve_trick. [1:1]").append(System.lineSeparator());
        sb.append(":~ kse_solve_trick. [1:2]").append(System.lineSeparator());
        return sb.toString();
    }
}
