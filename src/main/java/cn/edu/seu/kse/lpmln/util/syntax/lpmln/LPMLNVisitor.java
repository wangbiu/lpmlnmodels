// Generated from LPMLN.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.lpmln.util.syntax.lpmln;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LPMLNParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LPMLNVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#negative_int}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegative_int(LPMLNParser.Negative_intContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(LPMLNParser.IntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#natural_number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNatural_number(LPMLNParser.Natural_numberContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#arithmetic_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic_op(LPMLNParser.Arithmetic_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#bitwise_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwise_op(LPMLNParser.Bitwise_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#binary_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_op(LPMLNParser.Binary_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#unary_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_op(LPMLNParser.Unary_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#bit_number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBit_number(LPMLNParser.Bit_numberContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#relation_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation_op(LPMLNParser.Relation_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#simple_arithmetic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_arithmetic_expr(LPMLNParser.Simple_arithmetic_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#simple_arithmetic_expr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_arithmetic_expr2(LPMLNParser.Simple_arithmetic_expr2Context ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#arithmethic_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmethic_expr(LPMLNParser.Arithmethic_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(LPMLNParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#simpleterm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleterm(LPMLNParser.SimpletermContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#tuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTuple(LPMLNParser.TupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#interval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterval(LPMLNParser.IntervalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(LPMLNParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(LPMLNParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(LPMLNParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#term_tuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm_tuple(LPMLNParser.Term_tupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#literal_tuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral_tuple(LPMLNParser.Literal_tupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#aggregate_elements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregate_elements(LPMLNParser.Aggregate_elementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#aggregate_elements_condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregate_elements_condition(LPMLNParser.Aggregate_elements_conditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#body_aggregate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody_aggregate(LPMLNParser.Body_aggregateContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#head_aggregate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHead_aggregate(LPMLNParser.Head_aggregateContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#relation_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation_expr(LPMLNParser.Relation_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#head}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHead(LPMLNParser.HeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#head_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHead_literal(LPMLNParser.Head_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(LPMLNParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#body_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody_literal(LPMLNParser.Body_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#fact}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFact(LPMLNParser.FactContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraint(LPMLNParser.ConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#full_rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFull_rule(LPMLNParser.Full_ruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#hard_rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHard_rule(LPMLNParser.Hard_ruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#soft_rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSoft_rule(LPMLNParser.Soft_ruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#meta_rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeta_rule(LPMLNParser.Meta_ruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPMLNParser#lpmln_rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLpmln_rule(LPMLNParser.Lpmln_ruleContext ctx);
}