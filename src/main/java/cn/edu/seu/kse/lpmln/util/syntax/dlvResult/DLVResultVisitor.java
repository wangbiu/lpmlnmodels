// Generated from DLVResult.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.lpmln.util.syntax.dlvResult;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parseLPMLN tree produced
 * by {@link DLVResultParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DLVResultVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parseLPMLN tree produced by {@link DLVResultParser#negative_int}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitNegative_int(DLVResultParser.Negative_intContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link DLVResultParser#integer}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitInteger(DLVResultParser.IntegerContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link DLVResultParser#natural_number}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitNatural_number(DLVResultParser.Natural_numberContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link DLVResultParser#function}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitFunction(DLVResultParser.FunctionContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link DLVResultParser#term}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitTerm(DLVResultParser.TermContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link DLVResultParser#atom}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitAtom(DLVResultParser.AtomContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link DLVResultParser#literal}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitLiteral(DLVResultParser.LiteralContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link DLVResultParser#answer_set}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitAnswer_set(DLVResultParser.Answer_setContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link DLVResultParser#weight_level_flag}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitWeight_level_flag(DLVResultParser.Weight_level_flagContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link DLVResultParser#weight_level}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitWeight_level(DLVResultParser.Weight_levelContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link DLVResultParser#weight}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitWeight(DLVResultParser.WeightContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link DLVResultParser#weighted_answer_set}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitWeighted_answer_set(DLVResultParser.Weighted_answer_setContext ctx);
	/**
	 * Visit a parseLPMLN tree produced by {@link DLVResultParser#possible_worlds}.
	 * @param ctx the parseLPMLN tree
	 * @return the visitor result
	 */
	T visitPossible_worlds(DLVResultParser.Possible_worldsContext ctx);
}