package cn.edu.seu.kse.lpmln.util.syntax.clingoResult;

import cn.edu.seu.kse.lpmln.model.WeightedAnswerSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2017/3/22.
 */
public class ClingoAnswerSetVisitor extends ClingoResultBaseVisitor {
    public int minLevel1=Integer.MAX_VALUE;
    public int maxLevel1=Integer.MIN_VALUE;
    public int minLevel2=Integer.MAX_VALUE;
    public int maxLevel2=Integer.MIN_VALUE;


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

        for(int i=1;i>=0;i--){
            weights.add( Integer.valueOf(wctx.integer(i).getText()) -1);
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

        return was;
    }
}
