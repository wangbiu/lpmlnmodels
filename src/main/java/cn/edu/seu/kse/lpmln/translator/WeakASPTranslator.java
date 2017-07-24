package cn.edu.seu.kse.lpmln.translator;

import cn.edu.seu.kse.lpmln.model.Rule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王彬 on 2016/10/20.
 */
public class WeakASPTranslator extends ASPTranslator {
    @Override
    public String translateHardRule(Rule rule) {
        return rule.getOriginalrule();
    }

    @Override
    public String translate_parts(List<Rule> rules) {
        StringBuilder sb=new StringBuilder();
        sb.append("%%---- declaration part ----%%").append(System.lineSeparator());
        sb.append(translateDeclarationPart(herbrandUniverse));
        sb.append(System.lineSeparator());

        herbrandUniverse.clear();

        List<Rule> softrules=new ArrayList<>();
        sb.append("%%---- hard part ----%%").append(System.lineSeparator());
        for(Rule r : rules){
            if (r.isSoft()){
                softrules.add(r);
            }else {
                sb.append(r.getOriginalrule()).append(System.lineSeparator());
            }
        }

        sb.append("%%---- soft part ----%%").append(System.lineSeparator());
        sb.append(super.translate_parts(softrules));

        return sb.toString();
    }
}
