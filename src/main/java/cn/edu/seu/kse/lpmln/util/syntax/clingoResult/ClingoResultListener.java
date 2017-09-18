// Generated from ClingoResult.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.lpmln.util.syntax.clingoResult;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ClingoResultParser}.
 */
public interface ClingoResultListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ClingoResultParser#negative_int}.
	 * @param ctx the parse tree
	 */
	void enterNegative_int(ClingoResultParser.Negative_intContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClingoResultParser#negative_int}.
	 * @param ctx the parse tree
	 */
	void exitNegative_int(ClingoResultParser.Negative_intContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClingoResultParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(ClingoResultParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClingoResultParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(ClingoResultParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClingoResultParser#natural_number}.
	 * @param ctx the parse tree
	 */
	void enterNatural_number(ClingoResultParser.Natural_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClingoResultParser#natural_number}.
	 * @param ctx the parse tree
	 */
	void exitNatural_number(ClingoResultParser.Natural_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClingoResultParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(ClingoResultParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClingoResultParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(ClingoResultParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClingoResultParser#simpleterm}.
	 * @param ctx the parse tree
	 */
	void enterSimpleterm(ClingoResultParser.SimpletermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClingoResultParser#simpleterm}.
	 * @param ctx the parse tree
	 */
	void exitSimpleterm(ClingoResultParser.SimpletermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClingoResultParser#tuple}.
	 * @param ctx the parse tree
	 */
	void enterTuple(ClingoResultParser.TupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClingoResultParser#tuple}.
	 * @param ctx the parse tree
	 */
	void exitTuple(ClingoResultParser.TupleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClingoResultParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(ClingoResultParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClingoResultParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(ClingoResultParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClingoResultParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(ClingoResultParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClingoResultParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(ClingoResultParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClingoResultParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(ClingoResultParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClingoResultParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(ClingoResultParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClingoResultParser#answer_set_flag}.
	 * @param ctx the parse tree
	 */
	void enterAnswer_set_flag(ClingoResultParser.Answer_set_flagContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClingoResultParser#answer_set_flag}.
	 * @param ctx the parse tree
	 */
	void exitAnswer_set_flag(ClingoResultParser.Answer_set_flagContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClingoResultParser#answer_set}.
	 * @param ctx the parse tree
	 */
	void enterAnswer_set(ClingoResultParser.Answer_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClingoResultParser#answer_set}.
	 * @param ctx the parse tree
	 */
	void exitAnswer_set(ClingoResultParser.Answer_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClingoResultParser#weight}.
	 * @param ctx the parse tree
	 */
	void enterWeight(ClingoResultParser.WeightContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClingoResultParser#weight}.
	 * @param ctx the parse tree
	 */
	void exitWeight(ClingoResultParser.WeightContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClingoResultParser#weighted_answer_set}.
	 * @param ctx the parse tree
	 */
	void enterWeighted_answer_set(ClingoResultParser.Weighted_answer_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClingoResultParser#weighted_answer_set}.
	 * @param ctx the parse tree
	 */
	void exitWeighted_answer_set(ClingoResultParser.Weighted_answer_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClingoResultParser#possible_worlds}.
	 * @param ctx the parse tree
	 */
	void enterPossible_worlds(ClingoResultParser.Possible_worldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClingoResultParser#possible_worlds}.
	 * @param ctx the parse tree
	 */
	void exitPossible_worlds(ClingoResultParser.Possible_worldsContext ctx);
}