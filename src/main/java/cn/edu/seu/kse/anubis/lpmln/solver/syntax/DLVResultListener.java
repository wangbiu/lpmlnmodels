// Generated from DLVResult.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.anubis.lpmln.solver.syntax;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DLVResultParser}.
 */
public interface DLVResultListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DLVResultParser#negative_int}.
	 * @param ctx the parse tree
	 */
	void enterNegative_int(DLVResultParser.Negative_intContext ctx);
	/**
	 * Exit a parse tree produced by {@link DLVResultParser#negative_int}.
	 * @param ctx the parse tree
	 */
	void exitNegative_int(DLVResultParser.Negative_intContext ctx);
	/**
	 * Enter a parse tree produced by {@link DLVResultParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(DLVResultParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link DLVResultParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(DLVResultParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link DLVResultParser#natural_number}.
	 * @param ctx the parse tree
	 */
	void enterNatural_number(DLVResultParser.Natural_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link DLVResultParser#natural_number}.
	 * @param ctx the parse tree
	 */
	void exitNatural_number(DLVResultParser.Natural_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link DLVResultParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(DLVResultParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DLVResultParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(DLVResultParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DLVResultParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(DLVResultParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link DLVResultParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(DLVResultParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link DLVResultParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(DLVResultParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link DLVResultParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(DLVResultParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link DLVResultParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(DLVResultParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link DLVResultParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(DLVResultParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link DLVResultParser#answer_set}.
	 * @param ctx the parse tree
	 */
	void enterAnswer_set(DLVResultParser.Answer_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link DLVResultParser#answer_set}.
	 * @param ctx the parse tree
	 */
	void exitAnswer_set(DLVResultParser.Answer_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link DLVResultParser#weight_level}.
	 * @param ctx the parse tree
	 */
	void enterWeight_level(DLVResultParser.Weight_levelContext ctx);
	/**
	 * Exit a parse tree produced by {@link DLVResultParser#weight_level}.
	 * @param ctx the parse tree
	 */
	void exitWeight_level(DLVResultParser.Weight_levelContext ctx);
	/**
	 * Enter a parse tree produced by {@link DLVResultParser#weight}.
	 * @param ctx the parse tree
	 */
	void enterWeight(DLVResultParser.WeightContext ctx);
	/**
	 * Exit a parse tree produced by {@link DLVResultParser#weight}.
	 * @param ctx the parse tree
	 */
	void exitWeight(DLVResultParser.WeightContext ctx);
	/**
	 * Enter a parse tree produced by {@link DLVResultParser#weighted_answer_set}.
	 * @param ctx the parse tree
	 */
	void enterWeighted_answer_set(DLVResultParser.Weighted_answer_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link DLVResultParser#weighted_answer_set}.
	 * @param ctx the parse tree
	 */
	void exitWeighted_answer_set(DLVResultParser.Weighted_answer_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link DLVResultParser#possible_worlds}.
	 * @param ctx the parse tree
	 */
	void enterPossible_worlds(DLVResultParser.Possible_worldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DLVResultParser#possible_worlds}.
	 * @param ctx the parse tree
	 */
	void exitPossible_worlds(DLVResultParser.Possible_worldsContext ctx);
}