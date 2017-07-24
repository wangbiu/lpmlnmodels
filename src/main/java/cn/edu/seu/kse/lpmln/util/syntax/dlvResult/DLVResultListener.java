// Generated from DLVResult.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.lpmln.util.syntax.dlvResult;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parseLPMLN tree produced by
 * {@link DLVResultParser}.
 */
public interface DLVResultListener extends ParseTreeListener {
	/**
	 * Enter a parseLPMLN tree produced by {@link DLVResultParser#negative_int}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterNegative_int(DLVResultParser.Negative_intContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link DLVResultParser#negative_int}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitNegative_int(DLVResultParser.Negative_intContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link DLVResultParser#integer}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterInteger(DLVResultParser.IntegerContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link DLVResultParser#integer}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitInteger(DLVResultParser.IntegerContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link DLVResultParser#natural_number}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterNatural_number(DLVResultParser.Natural_numberContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link DLVResultParser#natural_number}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitNatural_number(DLVResultParser.Natural_numberContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link DLVResultParser#function}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterFunction(DLVResultParser.FunctionContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link DLVResultParser#function}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitFunction(DLVResultParser.FunctionContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link DLVResultParser#term}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterTerm(DLVResultParser.TermContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link DLVResultParser#term}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitTerm(DLVResultParser.TermContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link DLVResultParser#atom}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterAtom(DLVResultParser.AtomContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link DLVResultParser#atom}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitAtom(DLVResultParser.AtomContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link DLVResultParser#literal}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterLiteral(DLVResultParser.LiteralContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link DLVResultParser#literal}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitLiteral(DLVResultParser.LiteralContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link DLVResultParser#answer_set}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterAnswer_set(DLVResultParser.Answer_setContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link DLVResultParser#answer_set}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitAnswer_set(DLVResultParser.Answer_setContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link DLVResultParser#weight_level_flag}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterWeight_level_flag(DLVResultParser.Weight_level_flagContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link DLVResultParser#weight_level_flag}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitWeight_level_flag(DLVResultParser.Weight_level_flagContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link DLVResultParser#weight_level}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterWeight_level(DLVResultParser.Weight_levelContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link DLVResultParser#weight_level}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitWeight_level(DLVResultParser.Weight_levelContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link DLVResultParser#weight}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterWeight(DLVResultParser.WeightContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link DLVResultParser#weight}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitWeight(DLVResultParser.WeightContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link DLVResultParser#weighted_answer_set}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterWeighted_answer_set(DLVResultParser.Weighted_answer_setContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link DLVResultParser#weighted_answer_set}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitWeighted_answer_set(DLVResultParser.Weighted_answer_setContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link DLVResultParser#possible_worlds}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterPossible_worlds(DLVResultParser.Possible_worldsContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link DLVResultParser#possible_worlds}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitPossible_worlds(DLVResultParser.Possible_worldsContext ctx);
}