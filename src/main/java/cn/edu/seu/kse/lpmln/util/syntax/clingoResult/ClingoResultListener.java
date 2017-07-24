// Generated from ClingoResult.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.lpmln.util.syntax.clingoResult;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parseLPMLN tree produced by
 * {@link ClingoResultParser}.
 */
public interface ClingoResultListener extends ParseTreeListener {
	/**
	 * Enter a parseLPMLN tree produced by {@link ClingoResultParser#negative_int}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterNegative_int(ClingoResultParser.Negative_intContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link ClingoResultParser#negative_int}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitNegative_int(ClingoResultParser.Negative_intContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link ClingoResultParser#integer}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterInteger(ClingoResultParser.IntegerContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link ClingoResultParser#integer}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitInteger(ClingoResultParser.IntegerContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link ClingoResultParser#natural_number}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterNatural_number(ClingoResultParser.Natural_numberContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link ClingoResultParser#natural_number}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitNatural_number(ClingoResultParser.Natural_numberContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link ClingoResultParser#function}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterFunction(ClingoResultParser.FunctionContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link ClingoResultParser#function}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitFunction(ClingoResultParser.FunctionContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link ClingoResultParser#term}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterTerm(ClingoResultParser.TermContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link ClingoResultParser#term}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitTerm(ClingoResultParser.TermContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link ClingoResultParser#atom}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterAtom(ClingoResultParser.AtomContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link ClingoResultParser#atom}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitAtom(ClingoResultParser.AtomContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link ClingoResultParser#literal}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterLiteral(ClingoResultParser.LiteralContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link ClingoResultParser#literal}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitLiteral(ClingoResultParser.LiteralContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link ClingoResultParser#answer_set_flag}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterAnswer_set_flag(ClingoResultParser.Answer_set_flagContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link ClingoResultParser#answer_set_flag}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitAnswer_set_flag(ClingoResultParser.Answer_set_flagContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link ClingoResultParser#answer_set}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterAnswer_set(ClingoResultParser.Answer_setContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link ClingoResultParser#answer_set}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitAnswer_set(ClingoResultParser.Answer_setContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link ClingoResultParser#weight}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterWeight(ClingoResultParser.WeightContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link ClingoResultParser#weight}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitWeight(ClingoResultParser.WeightContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link ClingoResultParser#weighted_answer_set}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterWeighted_answer_set(ClingoResultParser.Weighted_answer_setContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link ClingoResultParser#weighted_answer_set}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitWeighted_answer_set(ClingoResultParser.Weighted_answer_setContext ctx);
	/**
	 * Enter a parseLPMLN tree produced by {@link ClingoResultParser#possible_worlds}.
	 * @param ctx the parseLPMLN tree
	 */
	void enterPossible_worlds(ClingoResultParser.Possible_worldsContext ctx);
	/**
	 * Exit a parseLPMLN tree produced by {@link ClingoResultParser#possible_worlds}.
	 * @param ctx the parseLPMLN tree
	 */
	void exitPossible_worlds(ClingoResultParser.Possible_worldsContext ctx);
}