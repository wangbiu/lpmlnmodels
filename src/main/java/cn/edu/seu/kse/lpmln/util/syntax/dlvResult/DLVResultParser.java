// Generated from DLVResult.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.lpmln.util.syntax.dlvResult;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

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
		RULE_term = 4, RULE_atom = 5, RULE_literal = 6, RULE_answer_set = 7, RULE_weight_level_flag = 8, 
		RULE_weight_level = 9, RULE_weight = 10, RULE_weighted_answer_set = 11, 
		RULE_possible_worlds = 12;
	public static final String[] ruleNames = {
		"negative_int", "integer", "natural_number", "function", "term", "atom", 
		"literal", "answer_set", "weight_level_flag", "weight_level", "weight", 
		"weighted_answer_set", "possible_worlds"
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
			setState(26);
			match(MINUS);
			setState(27);
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
			setState(32);
			switch (_input.LA(1)) {
			case POSITIVE_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(29);
				match(POSITIVE_INT);
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(30);
				negative_int();
				}
				break;
			case ZERO:
				enterOuterAlt(_localctx, 3);
				{
				setState(31);
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
			setState(34);
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
			setState(36);
			match(CONSTANT);
			setState(37);
			match(LPAREN);
			setState(38);
			term();
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(39);
				match(COMMA);
				setState(40);
				term();
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(46);
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
			setState(52);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				match(CONSTANT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(49);
				function();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(50);
				match(STRING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(51);
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
			setState(67);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				match(CONSTANT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				match(CONSTANT);
				setState(56);
				match(LPAREN);
				setState(57);
				term();
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(58);
					match(COMMA);
					setState(59);
					term();
					}
					}
					setState(64);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(65);
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
			setState(72);
			switch (_input.LA(1)) {
			case CONSTANT:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				atom();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				match(MINUS);
				setState(71);
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
			setState(74);
			match(LCBRACK);
			setState(83);
			_la = _input.LA(1);
			if (_la==CONSTANT || _la==MINUS) {
				{
				setState(75);
				literal();
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(76);
					match(COMMA);
					setState(77);
					literal();
					}
					}
					setState(82);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(85);
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

	public static class Weight_level_flagContext extends ParserRuleContext {
		public TerminalNode LSBRACK() { return getToken(DLVResultParser.LSBRACK, 0); }
		public TerminalNode WEIGHT() { return getToken(DLVResultParser.WEIGHT, 0); }
		public TerminalNode CONDITION() { return getToken(DLVResultParser.CONDITION, 0); }
		public TerminalNode LEVEL() { return getToken(DLVResultParser.LEVEL, 0); }
		public TerminalNode RSBRACK() { return getToken(DLVResultParser.RSBRACK, 0); }
		public Weight_level_flagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_weight_level_flag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).enterWeight_level_flag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DLVResultListener ) ((DLVResultListener)listener).exitWeight_level_flag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DLVResultVisitor ) return ((DLVResultVisitor<? extends T>)visitor).visitWeight_level_flag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Weight_level_flagContext weight_level_flag() throws RecognitionException {
		Weight_level_flagContext _localctx = new Weight_level_flagContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_weight_level_flag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(LSBRACK);
			setState(88);
			match(WEIGHT);
			setState(89);
			match(CONDITION);
			setState(90);
			match(LEVEL);
			setState(91);
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

	public static class Weight_levelContext extends ParserRuleContext {
		public TerminalNode LSBRACK() { return getToken(DLVResultParser.LSBRACK, 0); }
		public List<Natural_numberContext> natural_number() {
			return getRuleContexts(Natural_numberContext.class);
		}
		public Natural_numberContext natural_number(int i) {
			return getRuleContext(Natural_numberContext.class,i);
		}
		public TerminalNode CONDITION() { return getToken(DLVResultParser.CONDITION, 0); }
		public TerminalNode RSBRACK() { return getToken(DLVResultParser.RSBRACK, 0); }
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
		enterRule(_localctx, 18, RULE_weight_level);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(LSBRACK);
			setState(94);
			natural_number();
			setState(95);
			match(CONDITION);
			setState(96);
			natural_number();
			setState(97);
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
		public Weight_level_flagContext weight_level_flag() {
			return getRuleContext(Weight_level_flagContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(DLVResultParser.RPAREN, 0); }
		public TerminalNode CONDITION() { return getToken(DLVResultParser.CONDITION, 0); }
		public TerminalNode LESS_THAN() { return getToken(DLVResultParser.LESS_THAN, 0); }
		public List<Weight_levelContext> weight_level() {
			return getRuleContexts(Weight_levelContext.class);
		}
		public Weight_levelContext weight_level(int i) {
			return getRuleContext(Weight_levelContext.class,i);
		}
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
		enterRule(_localctx, 20, RULE_weight);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(WEIGHT_FLAG);
			setState(100);
			match(LPAREN);
			setState(101);
			weight_level_flag();
			setState(102);
			match(RPAREN);
			setState(103);
			match(CONDITION);
			setState(104);
			match(LESS_THAN);
			setState(105);
			weight_level();
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(106);
				match(COMMA);
				setState(107);
				weight_level();
				}
				}
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(113);
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
		enterRule(_localctx, 22, RULE_weighted_answer_set);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			answer_set();
			setState(116);
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
		enterRule(_localctx, 24, RULE_possible_worlds);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LCBRACK) {
				{
				{
				setState(118);
				weighted_answer_set();
				}
				}
				setState(123);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\26\177\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\3\3\3\3\3\5\3#\n\3\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\7\5,\n\5\f\5\16\5/\13\5\3\5\3\5\3\6\3\6\3\6\3\6\5"+
		"\6\67\n\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7?\n\7\f\7\16\7B\13\7\3\7\3\7\5\7"+
		"F\n\7\3\b\3\b\3\b\5\bK\n\b\3\t\3\t\3\t\3\t\7\tQ\n\t\f\t\16\tT\13\t\5\t"+
		"V\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\fo\n\f\f\f\16\fr\13\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\16\7\16z\n\16\f\16\16\16}\13\16\3\16\2\2\17\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\2\3\3\2\5\6~\2\34\3\2\2\2\4\"\3\2\2\2\6$\3\2\2\2\b"+
		"&\3\2\2\2\n\66\3\2\2\2\fE\3\2\2\2\16J\3\2\2\2\20L\3\2\2\2\22Y\3\2\2\2"+
		"\24_\3\2\2\2\26e\3\2\2\2\30u\3\2\2\2\32{\3\2\2\2\34\35\7\22\2\2\35\36"+
		"\7\5\2\2\36\3\3\2\2\2\37#\7\5\2\2 #\5\2\2\2!#\7\6\2\2\"\37\3\2\2\2\" "+
		"\3\2\2\2\"!\3\2\2\2#\5\3\2\2\2$%\t\2\2\2%\7\3\2\2\2&\'\7\7\2\2\'(\7\13"+
		"\2\2(-\5\n\6\2)*\7\24\2\2*,\5\n\6\2+)\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3"+
		"\2\2\2.\60\3\2\2\2/-\3\2\2\2\60\61\7\f\2\2\61\t\3\2\2\2\62\67\7\7\2\2"+
		"\63\67\5\b\5\2\64\67\7\3\2\2\65\67\5\4\3\2\66\62\3\2\2\2\66\63\3\2\2\2"+
		"\66\64\3\2\2\2\66\65\3\2\2\2\67\13\3\2\2\28F\7\7\2\29:\7\7\2\2:;\7\13"+
		"\2\2;@\5\n\6\2<=\7\24\2\2=?\5\n\6\2><\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3"+
		"\2\2\2AC\3\2\2\2B@\3\2\2\2CD\7\f\2\2DF\3\2\2\2E8\3\2\2\2E9\3\2\2\2F\r"+
		"\3\2\2\2GK\5\f\7\2HI\7\22\2\2IK\5\f\7\2JG\3\2\2\2JH\3\2\2\2K\17\3\2\2"+
		"\2LU\7\17\2\2MR\5\16\b\2NO\7\24\2\2OQ\5\16\b\2PN\3\2\2\2QT\3\2\2\2RP\3"+
		"\2\2\2RS\3\2\2\2SV\3\2\2\2TR\3\2\2\2UM\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\7"+
		"\20\2\2X\21\3\2\2\2YZ\7\r\2\2Z[\7\t\2\2[\\\7\25\2\2\\]\7\n\2\2]^\7\16"+
		"\2\2^\23\3\2\2\2_`\7\r\2\2`a\5\6\4\2ab\7\25\2\2bc\5\6\4\2cd\7\16\2\2d"+
		"\25\3\2\2\2ef\7\b\2\2fg\7\13\2\2gh\5\22\n\2hi\7\f\2\2ij\7\25\2\2jk\7\21"+
		"\2\2kp\5\24\13\2lm\7\24\2\2mo\5\24\13\2nl\3\2\2\2or\3\2\2\2pn\3\2\2\2"+
		"pq\3\2\2\2qs\3\2\2\2rp\3\2\2\2st\7\23\2\2t\27\3\2\2\2uv\5\20\t\2vw\5\26"+
		"\f\2w\31\3\2\2\2xz\5\30\r\2yx\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|\33"+
		"\3\2\2\2}{\3\2\2\2\f\"-\66@EJRUp{";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}