// Generated from DLVResult.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.anubis.lpmln.solver.syntax;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DLVResultParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STRING=1, FULLSTOP=2, POSITIVE_INT=3, ZERO=4, CONSTANT=5, WEIGHT_FLAG=6, 
		WEIGHT=7, LEVEL=8, LPAREN=9, RPAREN=10, LSBRACK=11, RSBRACK=12, LCBRACK=13, 
		RCBRACK=14, LESS_THAN=15, MINUS=16, GREATER_THAN=17, COMMA=18, CONDITION=19, 
		WS=20;
	public static final int
		RULE_negative_int = 0, RULE_integer = 1, RULE_natural_number = 2, RULE_function = 3, 
		RULE_term = 4, RULE_atom = 5, RULE_literal = 6, RULE_answer_set = 7, RULE_weight_level = 8, 
		RULE_weight = 9, RULE_weighted_answer_set = 10, RULE_possible_worlds = 11;
	public static final String[] ruleNames = {
		"negative_int", "integer", "natural_number", "function", "term", "atom", 
		"literal", "answer_set", "weight_level", "weight", "weighted_answer_set", 
		"possible_worlds"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'.'", null, "'0'", null, "'Cost'", "'Weight'", "'Level'", 
		"'('", "')'", "'['", "']'", "'{'", "'}'", "'<'", "'-'", "'>'", "','", 
		"':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "STRING", "FULLSTOP", "POSITIVE_INT", "ZERO", "CONSTANT", "WEIGHT_FLAG", 
		"WEIGHT", "LEVEL", "LPAREN", "RPAREN", "LSBRACK", "RSBRACK", "LCBRACK", 
		"RCBRACK", "LESS_THAN", "MINUS", "GREATER_THAN", "COMMA", "CONDITION", 
		"WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "DLVResult.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DLVResultParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Negative_intContext extends ParserRuleContext {
		public TerminalNode MINUS() { return getToken(DLVResultParser.MINUS, 0); }
		public TerminalNode POSITIVE_INT() { return getToken(DLVResultParser.POSITIVE_INT, 0); }
		public Negative_intContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negative_int; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).enterNegative_int(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).exitNegative_int(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DLVResultVisitor ) return ((DLVResultVisitor<? extends T>)visitor).visitNegative_int(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Negative_intContext negative_int() throws RecognitionException {
		Negative_intContext _localctx = new Negative_intContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_negative_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(MINUS);
			setState(25);
			match(POSITIVE_INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerContext extends ParserRuleContext {
		public TerminalNode POSITIVE_INT() { return getToken(DLVResultParser.POSITIVE_INT, 0); }
		public Negative_intContext negative_int() {
			return getRuleContext(Negative_intContext.class,0);
		}
		public TerminalNode ZERO() { return getToken(DLVResultParser.ZERO, 0); }
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).enterInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).exitInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DLVResultVisitor ) return ((DLVResultVisitor<? extends T>)visitor).visitInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_integer);
		try {
			setState(30);
			switch (_input.LA(1)) {
			case POSITIVE_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(27);
				match(POSITIVE_INT);
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(28);
				negative_int();
				}
				break;
			case ZERO:
				enterOuterAlt(_localctx, 3);
				{
				setState(29);
				match(ZERO);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Natural_numberContext extends ParserRuleContext {
		public TerminalNode POSITIVE_INT() { return getToken(DLVResultParser.POSITIVE_INT, 0); }
		public TerminalNode ZERO() { return getToken(DLVResultParser.ZERO, 0); }
		public Natural_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_natural_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).enterNatural_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).exitNatural_number(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DLVResultVisitor ) return ((DLVResultVisitor<? extends T>)visitor).visitNatural_number(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Natural_numberContext natural_number() throws RecognitionException {
		Natural_numberContext _localctx = new Natural_numberContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_natural_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			_la = _input.LA(1);
			if ( !(_la==POSITIVE_INT || _la==ZERO) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode CONSTANT() { return getToken(DLVResultParser.CONSTANT, 0); }
		public TerminalNode LPAREN() { return getToken(DLVResultParser.LPAREN, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(DLVResultParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DLVResultParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DLVResultParser.COMMA, i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DLVResultVisitor ) return ((DLVResultVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(CONSTANT);
			setState(35);
			match(LPAREN);
			setState(36);
			term();
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(37);
				match(COMMA);
				setState(38);
				term();
				}
				}
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(44);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public TerminalNode CONSTANT() { return getToken(DLVResultParser.CONSTANT, 0); }
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public TerminalNode STRING() { return getToken(DLVResultParser.STRING, 0); }
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DLVResultVisitor ) return ((DLVResultVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_term);
		try {
			setState(50);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				match(CONSTANT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				function();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(48);
				match(STRING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(49);
				integer();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public TerminalNode CONSTANT() { return getToken(DLVResultParser.CONSTANT, 0); }
		public TerminalNode LPAREN() { return getToken(DLVResultParser.LPAREN, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(DLVResultParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DLVResultParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DLVResultParser.COMMA, i);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DLVResultVisitor ) return ((DLVResultVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_atom);
		int _la;
		try {
			setState(65);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				match(CONSTANT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(53);
				match(CONSTANT);
				setState(54);
				match(LPAREN);
				setState(55);
				term();
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(56);
					match(COMMA);
					setState(57);
					term();
					}
					}
					setState(62);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(63);
				match(RPAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(DLVResultParser.MINUS, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DLVResultVisitor ) return ((DLVResultVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_literal);
		try {
			setState(70);
			switch (_input.LA(1)) {
			case CONSTANT:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				atom();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
				match(MINUS);
				setState(69);
				atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Answer_setContext extends ParserRuleContext {
		public TerminalNode LCBRACK() { return getToken(DLVResultParser.LCBRACK, 0); }
		public TerminalNode RCBRACK() { return getToken(DLVResultParser.RCBRACK, 0); }
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(DLVResultParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DLVResultParser.COMMA, i);
		}
		public Answer_setContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answer_set; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).enterAnswer_set(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).exitAnswer_set(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DLVResultVisitor ) return ((DLVResultVisitor<? extends T>)visitor).visitAnswer_set(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Answer_setContext answer_set() throws RecognitionException {
		Answer_setContext _localctx = new Answer_setContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_answer_set);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(LCBRACK);
			setState(81);
			_la = _input.LA(1);
			if (_la==CONSTANT || _la==MINUS) {
				{
				setState(73);
				literal();
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(74);
					match(COMMA);
					setState(75);
					literal();
					}
					}
					setState(80);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(83);
			match(RCBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Weight_levelContext extends ParserRuleContext {
		public TerminalNode LSBRACK() { return getToken(DLVResultParser.LSBRACK, 0); }
		public TerminalNode CONDITION() { return getToken(DLVResultParser.CONDITION, 0); }
		public TerminalNode RSBRACK() { return getToken(DLVResultParser.RSBRACK, 0); }
		public TerminalNode WEIGHT() { return getToken(DLVResultParser.WEIGHT, 0); }
		public List<Natural_numberContext> natural_number() {
			return getRuleContexts(Natural_numberContext.class);
		}
		public Natural_numberContext natural_number(int i) {
			return getRuleContext(Natural_numberContext.class,i);
		}
		public TerminalNode LEVEL() { return getToken(DLVResultParser.LEVEL, 0); }
		public Weight_levelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_weight_level; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).enterWeight_level(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).exitWeight_level(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DLVResultVisitor ) return ((DLVResultVisitor<? extends T>)visitor).visitWeight_level(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Weight_levelContext weight_level() throws RecognitionException {
		Weight_levelContext _localctx = new Weight_levelContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_weight_level);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(LSBRACK);
			setState(88);
			switch (_input.LA(1)) {
			case WEIGHT:
				{
				setState(86);
				match(WEIGHT);
				}
				break;
			case POSITIVE_INT:
			case ZERO:
				{
				setState(87);
				natural_number();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(90);
			match(CONDITION);
			setState(93);
			switch (_input.LA(1)) {
			case LEVEL:
				{
				setState(91);
				match(LEVEL);
				}
				break;
			case POSITIVE_INT:
			case ZERO:
				{
				setState(92);
				natural_number();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(95);
			match(RSBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WeightContext extends ParserRuleContext {
		public TerminalNode WEIGHT_FLAG() { return getToken(DLVResultParser.WEIGHT_FLAG, 0); }
		public TerminalNode LPAREN() { return getToken(DLVResultParser.LPAREN, 0); }
		public List<Weight_levelContext> weight_level() {
			return getRuleContexts(Weight_levelContext.class);
		}
		public Weight_levelContext weight_level(int i) {
			return getRuleContext(Weight_levelContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(DLVResultParser.RPAREN, 0); }
		public TerminalNode CONDITION() { return getToken(DLVResultParser.CONDITION, 0); }
		public TerminalNode LESS_THAN() { return getToken(DLVResultParser.LESS_THAN, 0); }
		public TerminalNode GREATER_THAN() { return getToken(DLVResultParser.GREATER_THAN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(DLVResultParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(DLVResultParser.COMMA, i);
		}
		public WeightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_weight; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).enterWeight(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).exitWeight(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DLVResultVisitor ) return ((DLVResultVisitor<? extends T>)visitor).visitWeight(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WeightContext weight() throws RecognitionException {
		WeightContext _localctx = new WeightContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_weight);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(WEIGHT_FLAG);
			setState(98);
			match(LPAREN);
			setState(99);
			weight_level();
			setState(100);
			match(RPAREN);
			setState(101);
			match(CONDITION);
			setState(102);
			match(LESS_THAN);
			setState(103);
			weight_level();
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(104);
				match(COMMA);
				setState(105);
				weight_level();
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(111);
			match(GREATER_THAN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Weighted_answer_setContext extends ParserRuleContext {
		public Answer_setContext answer_set() {
			return getRuleContext(Answer_setContext.class,0);
		}
		public WeightContext weight() {
			return getRuleContext(WeightContext.class,0);
		}
		public Weighted_answer_setContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_weighted_answer_set; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).enterWeighted_answer_set(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).exitWeighted_answer_set(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DLVResultVisitor ) return ((DLVResultVisitor<? extends T>)visitor).visitWeighted_answer_set(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Weighted_answer_setContext weighted_answer_set() throws RecognitionException {
		Weighted_answer_setContext _localctx = new Weighted_answer_setContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_weighted_answer_set);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			answer_set();
			setState(114);
			weight();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Possible_worldsContext extends ParserRuleContext {
		public List<Weighted_answer_setContext> weighted_answer_set() {
			return getRuleContexts(Weighted_answer_setContext.class);
		}
		public Weighted_answer_setContext weighted_answer_set(int i) {
			return getRuleContext(Weighted_answer_setContext.class,i);
		}
		public Possible_worldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_possible_worlds; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).enterPossible_worlds(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).exitPossible_worlds(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DLVResultVisitor ) return ((DLVResultVisitor<? extends T>)visitor).visitPossible_worlds(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Possible_worldsContext possible_worlds() throws RecognitionException {
		Possible_worldsContext _localctx = new Possible_worldsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_possible_worlds);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LCBRACK) {
				{
				{
				setState(116);
				weighted_answer_set();
				}
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\26}\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\3\2\3\2\3\3\3\3\3\3\5\3!\n\3\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\3\5\7\5*\n\5\f\5\16\5-\13\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6\65\n\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\7\7=\n\7\f\7\16\7@\13\7\3\7\3\7\5\7D\n\7\3\b\3\b"+
		"\3\b\5\bI\n\b\3\t\3\t\3\t\3\t\7\tO\n\t\f\t\16\tR\13\t\5\tT\n\t\3\t\3\t"+
		"\3\n\3\n\3\n\5\n[\n\n\3\n\3\n\3\n\5\n`\n\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\7\13m\n\13\f\13\16\13p\13\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\r\7\rx\n\r\f\r\16\r{\13\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\2\3\3\2\5\6\177\2\32\3\2\2\2\4 \3\2\2\2\6\"\3\2\2\2\b$\3\2\2\2"+
		"\n\64\3\2\2\2\fC\3\2\2\2\16H\3\2\2\2\20J\3\2\2\2\22W\3\2\2\2\24c\3\2\2"+
		"\2\26s\3\2\2\2\30y\3\2\2\2\32\33\7\22\2\2\33\34\7\5\2\2\34\3\3\2\2\2\35"+
		"!\7\5\2\2\36!\5\2\2\2\37!\7\6\2\2 \35\3\2\2\2 \36\3\2\2\2 \37\3\2\2\2"+
		"!\5\3\2\2\2\"#\t\2\2\2#\7\3\2\2\2$%\7\7\2\2%&\7\13\2\2&+\5\n\6\2\'(\7"+
		"\24\2\2(*\5\n\6\2)\'\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,.\3\2\2\2-"+
		"+\3\2\2\2./\7\f\2\2/\t\3\2\2\2\60\65\7\7\2\2\61\65\5\b\5\2\62\65\7\3\2"+
		"\2\63\65\5\4\3\2\64\60\3\2\2\2\64\61\3\2\2\2\64\62\3\2\2\2\64\63\3\2\2"+
		"\2\65\13\3\2\2\2\66D\7\7\2\2\678\7\7\2\289\7\13\2\29>\5\n\6\2:;\7\24\2"+
		"\2;=\5\n\6\2<:\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2\2\2@>\3\2\2"+
		"\2AB\7\f\2\2BD\3\2\2\2C\66\3\2\2\2C\67\3\2\2\2D\r\3\2\2\2EI\5\f\7\2FG"+
		"\7\22\2\2GI\5\f\7\2HE\3\2\2\2HF\3\2\2\2I\17\3\2\2\2JS\7\17\2\2KP\5\16"+
		"\b\2LM\7\24\2\2MO\5\16\b\2NL\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QT\3"+
		"\2\2\2RP\3\2\2\2SK\3\2\2\2ST\3\2\2\2TU\3\2\2\2UV\7\20\2\2V\21\3\2\2\2"+
		"WZ\7\r\2\2X[\7\t\2\2Y[\5\6\4\2ZX\3\2\2\2ZY\3\2\2\2[\\\3\2\2\2\\_\7\25"+
		"\2\2]`\7\n\2\2^`\5\6\4\2_]\3\2\2\2_^\3\2\2\2`a\3\2\2\2ab\7\16\2\2b\23"+
		"\3\2\2\2cd\7\b\2\2de\7\13\2\2ef\5\22\n\2fg\7\f\2\2gh\7\25\2\2hi\7\21\2"+
		"\2in\5\22\n\2jk\7\24\2\2km\5\22\n\2lj\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3"+
		"\2\2\2oq\3\2\2\2pn\3\2\2\2qr\7\23\2\2r\25\3\2\2\2st\5\20\t\2tu\5\24\13"+
		"\2u\27\3\2\2\2vx\5\26\f\2wv\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z\31"+
		"\3\2\2\2{y\3\2\2\2\16 +\64>CHPSZ_ny";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}