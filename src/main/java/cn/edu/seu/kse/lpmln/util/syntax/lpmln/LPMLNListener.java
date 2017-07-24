// Generated from LPMLN.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.lpmln.util.syntax.lpmln;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parseLPMLN tree produced by
 * {@link LPMLNParser}.
 */
public interface LPMLNListener extends ParseTreeListener {
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#negative_int}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterNegative_int(LPMLNParser.Negative_intContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#negative_int}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitNegative_int(LPMLNParser.Negative_intContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#integer}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterInteger(LPMLNParser.IntegerContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#integer}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitInteger(LPMLNParser.IntegerContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#natural_number}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterNatural_number(LPMLNParser.Natural_numberContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#natural_number}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitNatural_number(LPMLNParser.Natural_numberContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#arithmetic_op}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterArithmetic_op(LPMLNParser.Arithmetic_opContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#arithmetic_op}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitArithmetic_op(LPMLNParser.Arithmetic_opContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#relation_op}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterRelation_op(LPMLNParser.Relation_opContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#relation_op}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitRelation_op(LPMLNParser.Relation_opContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#simple_arithmetic_expr}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterSimple_arithmetic_expr(LPMLNParser.Simple_arithmetic_exprContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#simple_arithmetic_expr}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitSimple_arithmetic_expr(LPMLNParser.Simple_arithmetic_exprContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#simple_arithmetic_expr2}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterSimple_arithmetic_expr2(LPMLNParser.Simple_arithmetic_expr2Context ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#simple_arithmetic_expr2}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitSimple_arithmetic_expr2(LPMLNParser.Simple_arithmetic_expr2Context ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#arithmethic_expr}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterArithmethic_expr(LPMLNParser.Arithmethic_exprContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#arithmethic_expr}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitArithmethic_expr(LPMLNParser.Arithmethic_exprContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#function}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterFunction(LPMLNParser.FunctionContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#function}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitFunction(LPMLNParser.FunctionContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#term}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterTerm(LPMLNParser.TermContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#term}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitTerm(LPMLNParser.TermContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#atom}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterAtom(LPMLNParser.AtomContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#atom}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitAtom(LPMLNParser.AtomContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#range_atom}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterRange_atom(LPMLNParser.Range_atomContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#range_atom}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitRange_atom(LPMLNParser.Range_atomContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#literal}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterLiteral(LPMLNParser.LiteralContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#literal}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitLiteral(LPMLNParser.LiteralContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#default_literal}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterDefault_literal(LPMLNParser.Default_literalContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#default_literal}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitDefault_literal(LPMLNParser.Default_literalContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#extended_literal}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterExtended_literal(LPMLNParser.Extended_literalContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#extended_literal}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitExtended_literal(LPMLNParser.Extended_literalContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#aggregate_atom}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterAggregate_atom(LPMLNParser.Aggregate_atomContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#aggregate_atom}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitAggregate_atom(LPMLNParser.Aggregate_atomContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#aggregate_expr}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterAggregate_expr(LPMLNParser.Aggregate_exprContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#aggregate_expr}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitAggregate_expr(LPMLNParser.Aggregate_exprContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#relation_expr}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterRelation_expr(LPMLNParser.Relation_exprContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#relation_expr}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitRelation_expr(LPMLNParser.Relation_exprContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#head}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterHead(LPMLNParser.HeadContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#head}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitHead(LPMLNParser.HeadContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#body}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterBody(LPMLNParser.BodyContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#body}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitBody(LPMLNParser.BodyContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#fact}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterFact(LPMLNParser.FactContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#fact}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitFact(LPMLNParser.FactContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#constraint}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterConstraint(LPMLNParser.ConstraintContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#constraint}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitConstraint(LPMLNParser.ConstraintContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#full_rule}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterFull_rule(LPMLNParser.Full_ruleContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#full_rule}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitFull_rule(LPMLNParser.Full_ruleContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#hard_rule}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterHard_rule(LPMLNParser.Hard_ruleContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#hard_rule}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitHard_rule(LPMLNParser.Hard_ruleContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#soft_rule}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterSoft_rule(LPMLNParser.Soft_ruleContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#soft_rule}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitSoft_rule(LPMLNParser.Soft_ruleContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#meta_rule}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterMeta_rule(LPMLNParser.Meta_ruleContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#meta_rule}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitMeta_rule(LPMLNParser.Meta_ruleContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link LPMLNParser#lpmln_rule}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterLpmln_rule(LPMLNParser.Lpmln_ruleContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link LPMLNParser#lpmln_rule}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitLpmln_rule(LPMLNParser.Lpmln_ruleContext ctx);
}