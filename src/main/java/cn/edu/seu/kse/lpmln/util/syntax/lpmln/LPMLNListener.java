// Generated from LPMLN.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.lpmln.util.syntax.lpmln;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LPMLNParser}.
 */
public interface LPMLNListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#negative_int}.
	 * @param ctx the parse tree
	 */
	void enterNegative_int(LPMLNParser.Negative_intContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#negative_int}.
	 * @param ctx the parse tree
	 */
	void exitNegative_int(LPMLNParser.Negative_intContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(LPMLNParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(LPMLNParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#natural_number}.
	 * @param ctx the parse tree
	 */
	void enterNatural_number(LPMLNParser.Natural_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#natural_number}.
	 * @param ctx the parse tree
	 */
	void exitNatural_number(LPMLNParser.Natural_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#arithmetic_op}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic_op(LPMLNParser.Arithmetic_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#arithmetic_op}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic_op(LPMLNParser.Arithmetic_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#bitwise_op}.
	 * @param ctx the parse tree
	 */
	void enterBitwise_op(LPMLNParser.Bitwise_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#bitwise_op}.
	 * @param ctx the parse tree
	 */
	void exitBitwise_op(LPMLNParser.Bitwise_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#binary_op}.
	 * @param ctx the parse tree
	 */
	void enterBinary_op(LPMLNParser.Binary_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#binary_op}.
	 * @param ctx the parse tree
	 */
	void exitBinary_op(LPMLNParser.Binary_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#unary_op}.
	 * @param ctx the parse tree
	 */
	void enterUnary_op(LPMLNParser.Unary_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#unary_op}.
	 * @param ctx the parse tree
	 */
	void exitUnary_op(LPMLNParser.Unary_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#bit_number}.
	 * @param ctx the parse tree
	 */
	void enterBit_number(LPMLNParser.Bit_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#bit_number}.
	 * @param ctx the parse tree
	 */
	void exitBit_number(LPMLNParser.Bit_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#relation_op}.
	 * @param ctx the parse tree
	 */
	void enterRelation_op(LPMLNParser.Relation_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#relation_op}.
	 * @param ctx the parse tree
	 */
	void exitRelation_op(LPMLNParser.Relation_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#simple_arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterSimple_arithmetic_expr(LPMLNParser.Simple_arithmetic_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#simple_arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitSimple_arithmetic_expr(LPMLNParser.Simple_arithmetic_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#simple_arithmetic_expr2}.
	 * @param ctx the parse tree
	 */
	void enterSimple_arithmetic_expr2(LPMLNParser.Simple_arithmetic_expr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#simple_arithmetic_expr2}.
	 * @param ctx the parse tree
	 */
	void exitSimple_arithmetic_expr2(LPMLNParser.Simple_arithmetic_expr2Context ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#arithmethic_expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmethic_expr(LPMLNParser.Arithmethic_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#arithmethic_expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmethic_expr(LPMLNParser.Arithmethic_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(LPMLNParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(LPMLNParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#simpleterm}.
	 * @param ctx the parse tree
	 */
	void enterSimpleterm(LPMLNParser.SimpletermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#simpleterm}.
	 * @param ctx the parse tree
	 */
	void exitSimpleterm(LPMLNParser.SimpletermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#tuple}.
	 * @param ctx the parse tree
	 */
	void enterTuple(LPMLNParser.TupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#tuple}.
	 * @param ctx the parse tree
	 */
	void exitTuple(LPMLNParser.TupleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(LPMLNParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(LPMLNParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(LPMLNParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(LPMLNParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#range_atom}.
	 * @param ctx the parse tree
	 */
	void enterRange_atom(LPMLNParser.Range_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#range_atom}.
	 * @param ctx the parse tree
	 */
	void exitRange_atom(LPMLNParser.Range_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(LPMLNParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(LPMLNParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#term_tuple}.
	 * @param ctx the parse tree
	 */
	void enterTerm_tuple(LPMLNParser.Term_tupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#term_tuple}.
	 * @param ctx the parse tree
	 */
	void exitTerm_tuple(LPMLNParser.Term_tupleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#literal_tuple}.
	 * @param ctx the parse tree
	 */
	void enterLiteral_tuple(LPMLNParser.Literal_tupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#literal_tuple}.
	 * @param ctx the parse tree
	 */
	void exitLiteral_tuple(LPMLNParser.Literal_tupleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#aggregate_elements}.
	 * @param ctx the parse tree
	 */
	void enterAggregate_elements(LPMLNParser.Aggregate_elementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#aggregate_elements}.
	 * @param ctx the parse tree
	 */
	void exitAggregate_elements(LPMLNParser.Aggregate_elementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#aggregate_elements_condition}.
	 * @param ctx the parse tree
	 */
	void enterAggregate_elements_condition(LPMLNParser.Aggregate_elements_conditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#aggregate_elements_condition}.
	 * @param ctx the parse tree
	 */
	void exitAggregate_elements_condition(LPMLNParser.Aggregate_elements_conditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#body_aggregate}.
	 * @param ctx the parse tree
	 */
	void enterBody_aggregate(LPMLNParser.Body_aggregateContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#body_aggregate}.
	 * @param ctx the parse tree
	 */
	void exitBody_aggregate(LPMLNParser.Body_aggregateContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#head_aggregate}.
	 * @param ctx the parse tree
	 */
	void enterHead_aggregate(LPMLNParser.Head_aggregateContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#head_aggregate}.
	 * @param ctx the parse tree
	 */
	void exitHead_aggregate(LPMLNParser.Head_aggregateContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#relation_expr}.
	 * @param ctx the parse tree
	 */
	void enterRelation_expr(LPMLNParser.Relation_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#relation_expr}.
	 * @param ctx the parse tree
	 */
	void exitRelation_expr(LPMLNParser.Relation_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#head}.
	 * @param ctx the parse tree
	 */
	void enterHead(LPMLNParser.HeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#head}.
	 * @param ctx the parse tree
	 */
	void exitHead(LPMLNParser.HeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#head_literal}.
	 * @param ctx the parse tree
	 */
	void enterHead_literal(LPMLNParser.Head_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#head_literal}.
	 * @param ctx the parse tree
	 */
	void exitHead_literal(LPMLNParser.Head_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(LPMLNParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(LPMLNParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#body_literal}.
	 * @param ctx the parse tree
	 */
	void enterBody_literal(LPMLNParser.Body_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#body_literal}.
	 * @param ctx the parse tree
	 */
	void exitBody_literal(LPMLNParser.Body_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#fact}.
	 * @param ctx the parse tree
	 */
	void enterFact(LPMLNParser.FactContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#fact}.
	 * @param ctx the parse tree
	 */
	void exitFact(LPMLNParser.FactContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterConstraint(LPMLNParser.ConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitConstraint(LPMLNParser.ConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#full_rule}.
	 * @param ctx the parse tree
	 */
	void enterFull_rule(LPMLNParser.Full_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#full_rule}.
	 * @param ctx the parse tree
	 */
	void exitFull_rule(LPMLNParser.Full_ruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#hard_rule}.
	 * @param ctx the parse tree
	 */
	void enterHard_rule(LPMLNParser.Hard_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#hard_rule}.
	 * @param ctx the parse tree
	 */
	void exitHard_rule(LPMLNParser.Hard_ruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#soft_rule}.
	 * @param ctx the parse tree
	 */
	void enterSoft_rule(LPMLNParser.Soft_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#soft_rule}.
	 * @param ctx the parse tree
	 */
	void exitSoft_rule(LPMLNParser.Soft_ruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#meta_rule}.
	 * @param ctx the parse tree
	 */
	void enterMeta_rule(LPMLNParser.Meta_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#meta_rule}.
	 * @param ctx the parse tree
	 */
	void exitMeta_rule(LPMLNParser.Meta_ruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPMLNParser#lpmln_rule}.
	 * @param ctx the parse tree
	 */
	void enterLpmln_rule(LPMLNParser.Lpmln_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPMLNParser#lpmln_rule}.
	 * @param ctx the parse tree
	 */
	void exitLpmln_rule(LPMLNParser.Lpmln_ruleContext ctx);
}