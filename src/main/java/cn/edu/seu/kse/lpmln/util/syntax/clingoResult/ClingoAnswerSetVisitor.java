package cn.edu.seu.kse.lpmln.util.syntax.clingoResult;

import cn.edu.seu.kse.lpmln.app.LPMLNApp;
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

        String weightstr=null;
        int weight=0;
        int cnt=0;

        for(int i=1;i>=0;i--){
            weight= Integer.valueOf(wctx.POSITIVE_INT(i).getText()) -1 ;
            weights.add(weight);
            if(i == 0){
                maxLevel2 = Math.max(maxLevel2,weight);
                minLevel2 = Math.min(minLevel2,weight);
            }else if(i == 1){
                maxLevel1 = Math.max(maxLevel1,weight);
                minLevel1 = Math.min(minLevel1,weight);
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

        int aimLevel=0;
        if(LPMLNApp.translation_type== LPMLNApp.TRANSLATION_TYPE.V1){
            aimLevel = maxLevel2;
        }else if(LPMLNApp.translation_type== LPMLNApp.TRANSLATION_TYPE.V2){
            aimLevel = minLevel2;
        }
        for(WeightedAnswerSet as : was){
            if(as.getWeights().get(1) == aimLevel){
                result.add(as);
            }
        }
        return result;
    }
}
