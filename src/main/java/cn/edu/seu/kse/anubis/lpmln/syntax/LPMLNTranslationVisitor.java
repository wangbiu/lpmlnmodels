package cn.edu.seu.kse.anubis.lpmln.syntax;

import cn.edu.seu.kse.anubis.lpmln.model.Rule;
import cn.edu.seu.kse.anubis.lpmln.solver.syntax.DLVResultParser;
import org.antlr.v4.runtime.misc.DoubleKeyMap;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 王彬 on 2016/8/30.
 */
public class LPMLNTranslationVisitor extends LPMLNBaseVisitor {
    private List<Rule> rules=null;
    private int cnt=0;
    private Double minremains=null;
    private HashSet<String> herbrandUniverse=new HashSet<>();
    private StringBuilder metarule=new StringBuilder();

    public LPMLNTranslationVisitor(){
        rules=new ArrayList<>();
    }

    @Override
    public Object visitMeta_rule(LPMLNParser.Meta_ruleContext ctx) {
        metarule.append(ctx.getText()).append(System.lineSeparator());

        return null;
    }

    @Override
    public Rule visitHard_rule(LPMLNParser.Hard_ruleContext ctx) {
        Rule rule=null;
        rule=visitConstraint(ctx.constraint());

        if(rule == null){
            rule=visitFull_rule(ctx.full_rule());
        }

        if(rule == null){
            rule=visitFact(ctx.fact());
        }
        List<String> heads=rule.getHead();
        rule.setId(cnt++);
        if(heads.size() == 0){
            heads.add("impossible("+rule.getId()+")");
        }


        rule.setSoft(false);
//        rule.setText(ctx.getText());

        rules.add(rule);
        return rule;
    }

    @Override
    public Rule visitSoft_rule(LPMLNParser.Soft_ruleContext ctx) {
        Rule rule=visitHard_rule(ctx.hard_rule());
        rule.setSoft(true);
        String weightnode=null;

        if(ctx.DECIMAL() != null){
            weightnode=ctx.DECIMAL().getText();
        }

        if(weightnode == null){
            weightnode=ctx.integer().getText();
        }
        double weight=Double.valueOf(weightnode);
        double remains=weight-Math.floor(weight);

        if(minremains == null){
            minremains=remains;
        }else if(minremains > remains){
            if (Math.abs(remains) > 0.00001){
                minremains=remains;
            }

        }
        rule.setWeight(weight);
        return rule;
    }

    @Override
    public Rule visitConstraint(LPMLNParser.ConstraintContext ctx) {
        if(ctx == null){
            return null;
        }
        Rule rule=visitBody(ctx.body());
        rule.setText(":- "+ctx.body().getText()+", ");
        rule.setOriginalrule(ctx.getText());
        return rule;
    }

    @Override
    public Rule visitFact(LPMLNParser.FactContext ctx) {
        if(ctx == null){
            return null;
        }

        Rule rule=visitHead(ctx.head());
        rule.setText(ctx.head().getText() +" :- ");
        rule.setOriginalrule(ctx.getText());
        return rule;
    }

    @Override
    public Rule visitFull_rule(LPMLNParser.Full_ruleContext ctx) {
        if(ctx == null){
            return null;
        }
        Rule rb=visitBody(ctx.body());
        Rule rh=visitHead(ctx.head());
        rb.setText(ctx.head().getText() + " :- "+ctx.body().getText()+", ");
        rb.setHead(rh.getHead());
        rb.setOriginalrule(ctx.getText());
        return rb;
    }

    @Override
    public Rule visitBody(LPMLNParser.BodyContext ctx) {
        if(ctx == null){
            return null;
        }
        Rule rule=new Rule();
        rule.setBody(ctx.getText());
        HashSet<String> vars=rule.getVars();
        for(LPMLNParser.Extended_literalContext elctx : ctx.extended_literal()){
            vars.addAll(visitExtended_literal(elctx));
        }

        for(LPMLNParser.Relation_exprContext rctx : ctx.relation_expr()){
            vars.addAll(visitRelation_expr(rctx));
        }

        return rule;
    }

    @Override
    public HashSet<String> visitExtended_literal(LPMLNParser.Extended_literalContext ctx) {
        HashSet<String> vars=null;
        LPMLNParser.LiteralContext lctx=ctx.literal();
        if(lctx == null){
            lctx=ctx.default_literal().literal();
        }
        vars=visitLiteral(lctx);
        return vars;
    }

    @Override
    public HashSet<String> visitLiteral(LPMLNParser.LiteralContext ctx) {
        HashSet<String> vars=new HashSet<>();
        LPMLNParser.AtomContext actx=ctx.atom();

        String var=null;

        for(LPMLNParser.TermContext tctx:actx.term()){
            var=visitTerm(tctx);
            if(var!=null){
                vars.add(var);
            }
        }

        return vars;
    }

    @Override
    public HashSet<String> visitRelation_expr(LPMLNParser.Relation_exprContext ctx) {
        HashSet<String> vars=new HashSet<>();
        for(TerminalNode vtn : ctx.VAR()){
            vars.add(vtn.getText());
        }

        for(LPMLNParser.Arithmethic_exprContext actx : ctx.arithmethic_expr()){
            herbrandUniverse.add(actx.getText());
        }

        return vars;
    }

    @Override
    public String visitTerm(LPMLNParser.TermContext ctx) {
        String var=null;
        TerminalNode vtn=ctx.VAR();
        if(vtn!=null){
            var=vtn.getText();
        }

        TerminalNode ctn=ctx.CONSTANT();
        if(ctn != null){
            herbrandUniverse.add(ctn.getText());
        }

        ctn=ctx.STRING();
        if(ctn!=null){
            herbrandUniverse.add(ctn.getText());
        }

        LPMLNParser.IntegerContext ictx=ctx.integer();
        if(ictx != null){
            herbrandUniverse.add(ictx.getText());
        }

        return var;
    }

    @Override
    public Object visitNegative_int(LPMLNParser.Negative_intContext ctx) {
        herbrandUniverse.add(ctx.getText());

        return null;
    }

    @Override
    public Rule visitHead(LPMLNParser.HeadContext ctx) {
        if(ctx == null){
            return null;
        }

        Rule rule=new Rule();

        List<String> heads=rule.getHead();
        for(LPMLNParser.LiteralContext lctx : ctx.literal()){
            heads.add(lctx.getText());
            visitLiteral(lctx);
        }

        return rule;
    }



    public int getFactor(){
        int factor=100;
        double tmpremains=0;
        minremains=Math.abs(minremains);
//        System.out.println(minremains);
        if(minremains < 0.00000001 ){
            return 1;
        }


        while (true){
            tmpremains=minremains*factor;
            if(tmpremains > 10){
                factor/=10;
            }else {
                break;
            }

            if(factor == 1){
                break;
            }
        }

        return factor;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public HashSet<String> getHerbrandUniverse() {
        return herbrandUniverse;
    }

    public void setHerbrandUniverse(HashSet<String> herbrandUniverse) {
        this.herbrandUniverse = herbrandUniverse;
    }

    public String getMetarule() {
        return metarule.toString();
    }


}
