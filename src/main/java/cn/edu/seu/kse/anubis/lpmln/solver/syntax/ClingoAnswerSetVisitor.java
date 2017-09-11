package cn.edu.seu.kse.anubis.lpmln.solver.syntax;

import cn.edu.seu.kse.anubis.lpmln.model.WeightedAnswerSet;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2017/3/22.
 */
public class ClingoAnswerSetVisitor extends ClingoResultBaseVisitor {
    public int maxLevel2=0;
    public int maxLevel1=0;
    private List<WeightedAnswerSet> was=new ArrayList<>();

    @Override
    public Object visitWeighted_answer_set(ClingoResultParser.Weighted_answer_setContext ctx) {
        WeightedAnswerSet as=new WeightedAnswerSet();
        ClingoResultParser.Answer_setContext asctx=ctx.answer_set();
        ClingoResultParser.WeightContext wctx=ctx.weight();
        HashSet<String> literals=as.getAnswerSet().getLiterals();
        List<Integer> weights=as.getWeights();

        for(ClingoResultParser.LiteralContext lctx:asctx.literal()){
            literals.add(lctx.getText());
        }

        String weightstr=null;
        int weight=0;
        int cnt=0;

        for(int i=1;i>=0;i--){
            weight= Integer.valueOf(wctx.POSITIVE_INT(i).getText()) -1 ;
            weights.add(weight);
            if(i == 0){
                if(maxLevel2 < weight){
                    maxLevel2=weight;
                }
            }else if(i == 1){
                if(maxLevel1 < weight){
                    maxLevel1=weight;
                }
            }
        }

        was.add(as);
        return null;
    }

    @Override
    public List<WeightedAnswerSet> visitPossible_worlds(ClingoResultParser.Possible_worldsContext ctx) {
        List<WeightedAnswerSet> result=new ArrayList<>();
        int weight=0;
        for(ClingoResultParser.Weighted_answer_setContext wctx:ctx.weighted_answer_set()){
            visitWeighted_answer_set(wctx);
        }

        for(WeightedAnswerSet as : was){
            if(as.getWeights().get(1) == maxLevel2){
                result.add(as);
            }
        }
        return result;
    }
}
