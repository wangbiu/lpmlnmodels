// Generated from ClingoResult.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.lpmln.util.syntax.clingoResult;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parseLPMLN tree produced
 * by {@link ClingoResultParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ClingoResultVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parseLPMLN tree produced by {@link ClingoResultParser#negative_int}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitNegative_int(ClingoResultParser.Negative_intContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link ClingoResultParser#integer}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitInteger(ClingoResultParser.IntegerContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link ClingoResultParser#natural_number}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitNatural_number(ClingoResultParser.Natural_numberContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link ClingoResultParser#function}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitFunction(ClingoResultParser.FunctionContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link ClingoResultParser#term}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitTerm(ClingoResultParser.TermContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link ClingoResultParser#atom}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitAtom(ClingoResultParser.AtomContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link ClingoResultParser#literal}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitLiteral(ClingoResultParser.LiteralContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link ClingoResultParser#answer_set_flag}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitAnswer_set_flag(ClingoResultParser.Answer_set_flagContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link ClingoResultParser#answer_set}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitAnswer_set(ClingoResultParser.Answer_setContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link ClingoResultParser#weight}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitWeight(ClingoResultParser.WeightContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link ClingoResultParser#weighted_answer_set}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitWeighted_answer_set(ClingoResultParser.Weighted_answer_setContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link ClingoResultParser#possible_worlds}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitPossible_worlds(ClingoResultParser.Possible_worldsContext ctx);
}