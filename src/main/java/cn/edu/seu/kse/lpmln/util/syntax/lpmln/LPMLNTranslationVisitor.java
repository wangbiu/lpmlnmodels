package cn.edu.seu.kse.lpmln.util.syntax.lpmln;

import cn.edu.seu.kse.lpmln.model.Rule;
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
    private static int factor;
    private HashSet<String> herbrandUniverse=new HashSet<>();
    private StringBuilder metarule=new StringBuilder();

    public LPMLNTranslationVisitor(){
        rules=new ArrayList<>();
        factor=0;
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
        String[] numbers = weightnode.split("\\.");
        if(numbers.length>1){
            int newLength = numbers[1].length();
            factor = Math.max(factor,newLength);
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
        List<String> positivebody = rule.getPositiveBody();
        List<String> negativebody = rule.getNegativeBody();
        HashSet<String> vars=rule.getVars();
        for(LPMLNParser.Body_literalContext bctx : ctx.body_literal()){
            if(bctx.getText().startsWith("not")){
                negativebody.add(bctx.getText());
            }else{
                positivebody.add(bctx.getText());
            }
            vars.addAll(visitBody_literal(bctx));
        }

        return rule;
    }

    @Override
    public HashSet<String> visitBody_literal(LPMLNParser.Body_literalContext ctx){
        HashSet<String> vars=new HashSet<>();
        if(ctx==null) return vars;
        vars.addAll(visitLiteral(ctx.literal()));
        vars.addAll(visitRelation_expr(ctx.relation_expr()));
        vars.addAll(visitBody_aggregate(ctx.body_aggregate()));
        vars.addAll(visitBody_literal(ctx.body_literal()));
        return vars;
    }

    @Override
    public HashSet<String> visitBody_aggregate(LPMLNParser.Body_aggregateContext ctx){
        HashSet<String> vars = new HashSet<>();
        if(ctx==null) return vars;
        for (LPMLNParser.TermContext tctx : ctx.term()) {
            vars.addAll(visitTerm(tctx));
        }
        vars.addAll(visitAggregate_elements(ctx.aggregate_elements()));
        return vars;
    }

    @Override
    public HashSet<String> visitAggregate_elements(LPMLNParser.Aggregate_elementsContext ctx){
        HashSet<String> vars = new HashSet<>();
        for (LPMLNParser.Term_tupleContext tctx: ctx.term_tuple()) {
            vars.addAll(visitTerm_tuple(tctx));
        }
        for (LPMLNParser.Literal_tupleContext lctx: ctx.literal_tuple()) {
            vars.addAll(visitLiteral_tuple(lctx));
        }
        return vars;
    }

    @Override
    public HashSet<String> visitTerm_tuple(LPMLNParser.Term_tupleContext ctx){
        HashSet<String> vars = new HashSet<>();
        for (LPMLNParser.TermContext tctx: ctx.term()) {
            vars.addAll(visitTerm(tctx));
        }
        return vars;
    }

    @Override
    public HashSet<String> visitLiteral_tuple(LPMLNParser.Literal_tupleContext ctx){
        HashSet<String> vars = new HashSet<>();
        for (LPMLNParser.LiteralContext lctx : ctx.literal()) {
            vars.addAll(visitLiteral(lctx));
        }
        return vars;
    }

    @Override
    public HashSet<String> visitLiteral(LPMLNParser.LiteralContext ctx) {
        HashSet<String> vars=new HashSet<>();
        if(ctx==null) return vars;
        LPMLNParser.AtomContext actx=ctx.atom();

        vars.addAll(visitLiteral(ctx.literal()));
        if(actx!=null) {
            for (LPMLNParser.TermContext tctx : actx.term()) {
                vars.addAll(visitTerm(tctx));
            }
        }

        return vars;
    }

    @Override
    public HashSet<String> visitRelation_expr(LPMLNParser.Relation_exprContext ctx) {
        HashSet<String> vars=new HashSet<>();
        if(ctx==null) return vars;
        for(TerminalNode vtn : ctx.VAR()){
            vars.add(vtn.getText());
        }

        for(LPMLNParser.Arithmethic_exprContext actx : ctx.arithmethic_expr()){
            vars.addAll(visitArithmethic_expr(actx));
        }

        return vars;
    }

    @Override
    public HashSet<String> visitArithmethic_expr(LPMLNParser.Arithmethic_exprContext ctx){
        return visitSimple_arithmetic_expr2(ctx.simple_arithmetic_expr2());
    }

    @Override
    public HashSet<String> visitSimple_arithmetic_expr2(LPMLNParser.Simple_arithmetic_expr2Context ctx){
        HashSet<String> vars=new HashSet<>();
        if(ctx==null) return vars;
        for (LPMLNParser.Simple_arithmetic_expr2Context sctx : ctx.simple_arithmetic_expr2()) {
            vars.addAll(visitSimple_arithmetic_expr2(sctx));
        }
        vars.addAll(visitSimple_arithmetic_expr(ctx.simple_arithmetic_expr()));

        return  vars;
    }

    @Override
    public HashSet<String> visitSimple_arithmetic_expr(LPMLNParser.Simple_arithmetic_exprContext ctx){
        HashSet<String> vars=new HashSet<>();
        if(ctx==null) return vars;
        for (TerminalNode vtn : ctx.VAR()) {
            vars.add(vtn.getText());
        }
        return  vars;
    }

    @Override
    public HashSet<String> visitTerm(LPMLNParser.TermContext ctx) {
        HashSet<String> vars=new HashSet<>();
        if(ctx==null) return vars;
        String var = visitSimpleterm(ctx.simpleterm());
        if(var!=null) vars.add(var);
        vars.addAll(visitFunction(ctx.function()));
        vars.addAll(visitTuple(ctx.tuple()));
        return vars;
    }
    @Override
    public String visitSimpleterm(LPMLNParser.SimpletermContext ctx){
        String var = null;
        if(ctx!=null&&ctx.VAR()!=null) var = ctx.VAR().getText();
        return var;
    }

    @Override
    public HashSet<String> visitFunction(LPMLNParser.FunctionContext ctx) {
        HashSet<String> vars=new HashSet<>();
        if(ctx==null) return vars;
        for (LPMLNParser.TermContext tctx : ctx.term()) {
            vars.addAll(visitTerm(tctx));
        }
        return vars;
    }

    @Override
    public HashSet<String> visitTuple(LPMLNParser.TupleContext ctx) {
        HashSet<String> vars=new HashSet<>();
        if(ctx==null) return vars;
        for (LPMLNParser.TermContext tctx : ctx.term()) {
            vars.addAll(visitTerm(tctx));
        }
        return vars;
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
        for(LPMLNParser.Head_literalContext hctx : ctx.head_literal()){
            heads.add(hctx.getText());
            visitHead_literal(hctx);
        }

        return rule;
    }


    public static int getFactor(){
        factor = factor>9?9:factor;
        return (int)Math.pow(10,factor);
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
