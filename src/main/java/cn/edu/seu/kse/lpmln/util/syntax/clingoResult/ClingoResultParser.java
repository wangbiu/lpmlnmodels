// Generated from ClingoResult.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.lpmln.util.syntax.clingoResult;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ClingoResultParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STRING=1, FULLSTOP=2, POSITIVE_INT=3, ZERO=4, CONSTANT=5, LPAREN=6, RPAREN=7, 
		LSBRACK=8, RSBRACK=9, LCBRACK=10, RCBRACK=11, LESS_THAN=12, MINUS=13, 
		GREATER_THAN=14, COMMA=15, ANSWER=16, OPT=17, WS=18;
	public static final int
		RULE_negative_int = 0, RULE_integer = 1, RULE_natural_number = 2, RULE_function = 3, 
		RULE_simpleterm = 4, RULE_tuple = 5, RULE_term = 6, RULE_atom = 7, RULE_literal = 8, 
		RULE_answer_set_flag = 9, RULE_answer_set = 10, RULE_weight = 11, RULE_weighted_answer_set = 12, 
		RULE_possible_worlds = 13;
	public static final String[] ruleNames = {
		"negative_int", "integer", "natural_number", "function", "simpleterm", 
		"tuple", "term", "atom", "literal", "answer_set_flag", "answer_set", "weight", 
		"weighted_answer_set", "possible_worlds"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'.'", null, "'0'", null, "'('", "')'", "'['", "']'", "'{'", 
		"'}'", "'<'", "'-'", "'>'", "','", "'Answer:'", "'Optimization:'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "STRING", "FULLSTOP", "POSITIVE_INT", "ZERO", "CONSTANT", "LPAREN", 
		"RPAREN", "LSBRACK", "RSBRACK", "LCBRACK", "RCBRACK", "LESS_THAN", "MINUS", 
		"GREATER_THAN", "COMMA", "ANSWER", "OPT", "WS"
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
	public String getGrammarFileName() { return "ClingoResult.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ClingoResultParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Negative_intContext extends ParserRuleContext {
		public TerminalNode MINUS() { return getToken(ClingoResultParser.MINUS, 0); }
		public TerminalNode POSITIVE_INT() { return getToken(ClingoResultParser.POSITIVE_INT, 0); }
		public Negative_intContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negative_int; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).enterNegative_int(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).exitNegative_int(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClingoResultVisitor ) return ((ClingoResultVisitor<? extends T>)visitor).visitNegative_int(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Negative_intContext negative_int() throws RecognitionException {
		Negative_intContext _localctx = new Negative_intContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_negative_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(MINUS);
			setState(29);
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
		public TerminalNode POSITIVE_INT() { return getToken(ClingoResultParser.POSITIVE_INT, 0); }
		public Negative_intContext negative_int() {
			return getRuleContext(Negative_intContext.class,0);
		}
		public TerminalNode ZERO() { return getToken(ClingoResultParser.ZERO, 0); }
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).enterInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).exitInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClingoResultVisitor ) return ((ClingoResultVisitor<? extends T>)visitor).visitInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_integer);
		try {
			setState(34);
			switch (_input.LA(1)) {
			case POSITIVE_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(31);
				match(POSITIVE_INT);
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
				negative_int();
				}
				break;
			case ZERO:
				enterOuterAlt(_localctx, 3);
				{
				setState(33);
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
		public TerminalNode POSITIVE_INT() { return getToken(ClingoResultParser.POSITIVE_INT, 0); }
		public TerminalNode ZERO() { return getToken(ClingoResultParser.ZERO, 0); }
		public Natural_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_natural_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).enterNatural_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).exitNatural_number(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClingoResultVisitor ) return ((ClingoResultVisitor<? extends T>)visitor).visitNatural_number(this);
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
			setState(36);
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
		public TerminalNode CONSTANT() { return getToken(ClingoResultParser.CONSTANT, 0); }
		public TerminalNode LPAREN() { return getToken(ClingoResultParser.LPAREN, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(ClingoResultParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ClingoResultParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ClingoResultParser.COMMA, i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClingoResultVisitor ) return ((ClingoResultVisitor<? extends T>)visitor).visitFunction(this);
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
			setState(38);
			match(CONSTANT);
			setState(39);
			match(LPAREN);
			setState(40);
			term();
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(41);
				match(COMMA);
				setState(42);
				term();
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(48);
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

	public static class SimpletermContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public TerminalNode CONSTANT() { return getToken(ClingoResultParser.CONSTANT, 0); }
		public TerminalNode STRING() { return getToken(ClingoResultParser.STRING, 0); }
		public SimpletermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleterm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).enterSimpleterm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).exitSimpleterm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClingoResultVisitor ) return ((ClingoResultVisitor<? extends T>)visitor).visitSimpleterm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpletermContext simpleterm() throws RecognitionException {
		SimpletermContext _localctx = new SimpletermContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_simpleterm);
		try {
			setState(53);
			switch (_input.LA(1)) {
			case POSITIVE_INT:
			case ZERO:
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				integer();
				}
				break;
			case CONSTANT:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				match(CONSTANT);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(52);
				match(STRING);
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

	public static class TupleContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ClingoResultParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ClingoResultParser.RPAREN, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ClingoResultParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ClingoResultParser.COMMA, i);
		}
		public TupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tuple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).enterTuple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).exitTuple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClingoResultVisitor ) return ((ClingoResultVisitor<? extends T>)visitor).visitTuple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TupleContext tuple() throws RecognitionException {
		TupleContext _localctx = new TupleContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(LPAREN);
			setState(69);
			switch (_input.LA(1)) {
			case RPAREN:
				{
				}
				break;
			case STRING:
			case POSITIVE_INT:
			case ZERO:
			case CONSTANT:
			case LPAREN:
			case MINUS:
				{
				setState(57);
				term();
				setState(67);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					}
					break;
				case 2:
					{
					setState(59);
					match(COMMA);
					}
					break;
				case 3:
					{
					setState(64);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(60);
						match(COMMA);
						setState(61);
						term();
						}
						}
						setState(66);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(71);
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
		public SimpletermContext simpleterm() {
			return getRuleContext(SimpletermContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClingoResultVisitor ) return ((ClingoResultVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_term);
		try {
			setState(76);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(73);
				simpleterm();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				function();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				tuple();
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
		public TerminalNode CONSTANT() { return getToken(ClingoResultParser.CONSTANT, 0); }
		public TerminalNode LPAREN() { return getToken(ClingoResultParser.LPAREN, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(ClingoResultParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ClingoResultParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ClingoResultParser.COMMA, i);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClingoResultVisitor ) return ((ClingoResultVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_atom);
		int _la;
		try {
			setState(91);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				match(CONSTANT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				match(CONSTANT);
				setState(80);
				match(LPAREN);
				setState(81);
				term();
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(82);
					match(COMMA);
					setState(83);
					term();
					}
					}
					setState(88);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(89);
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
		public TerminalNode MINUS() { return getToken(ClingoResultParser.MINUS, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClingoResultVisitor ) return ((ClingoResultVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_literal);
		try {
			setState(96);
			switch (_input.LA(1)) {
			case CONSTANT:
				enterOuterAlt(_localctx, 1);
				{
				setState(93);
				atom();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				match(MINUS);
				setState(95);
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

	public static class Answer_set_flagContext extends ParserRuleContext {
		public TerminalNode ANSWER() { return getToken(ClingoResultParser.ANSWER, 0); }
		public TerminalNode POSITIVE_INT() { return getToken(ClingoResultParser.POSITIVE_INT, 0); }
		public Answer_set_flagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answer_set_flag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).enterAnswer_set_flag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).exitAnswer_set_flag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClingoResultVisitor ) return ((ClingoResultVisitor<? extends T>)visitor).visitAnswer_set_flag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Answer_set_flagContext answer_set_flag() throws RecognitionException {
		Answer_set_flagContext _localctx = new Answer_set_flagContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_answer_set_flag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(ANSWER);
			setState(99);
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

	public static class Answer_setContext extends ParserRuleContext {
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public Answer_setContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answer_set; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).enterAnswer_set(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).exitAnswer_set(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClingoResultVisitor ) return ((ClingoResultVisitor<? extends T>)visitor).visitAnswer_set(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Answer_setContext answer_set() throws RecognitionException {
		Answer_setContext _localctx = new Answer_setContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_answer_set);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CONSTANT || _la==MINUS) {
				{
				{
				setState(101);
				literal();
				}
				}
				setState(106);
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

	public static class WeightContext extends ParserRuleContext {
		public TerminalNode OPT() { return getToken(ClingoResultParser.OPT, 0); }
		public List<IntegerContext> integer() {
			return getRuleContexts(IntegerContext.class);
		}
		public IntegerContext integer(int i) {
			return getRuleContext(IntegerContext.class,i);
		}
		public WeightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_weight; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).enterWeight(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).exitWeight(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClingoResultVisitor ) return ((ClingoResultVisitor<? extends T>)visitor).visitWeight(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WeightContext weight() throws RecognitionException {
		WeightContext _localctx = new WeightContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_weight);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(OPT);
			setState(108);
			integer();
			setState(109);
			integer();
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
		public Answer_set_flagContext answer_set_flag() {
			return getRuleContext(Answer_set_flagContext.class,0);
		}
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
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).enterWeighted_answer_set(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).exitWeighted_answer_set(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClingoResultVisitor ) return ((ClingoResultVisitor<? extends T>)visitor).visitWeighted_answer_set(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Weighted_answer_setContext weighted_answer_set() throws RecognitionException {
		Weighted_answer_setContext _localctx = new Weighted_answer_setContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_weighted_answer_set);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			answer_set_flag();
			setState(112);
			answer_set();
			setState(113);
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
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).enterPossible_worlds(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClingoResultListener ) ((ClingoResultListener)listener).exitPossible_worlds(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClingoResultVisitor ) return ((ClingoResultVisitor<? extends T>)visitor).visitPossible_worlds(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Possible_worldsContext possible_worlds() throws RecognitionException {
		Possible_worldsContext _localctx = new Possible_worldsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_possible_worlds);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ANSWER) {
				{
				{
				setState(115);
				weighted_answer_set();
				}
				}
				setState(120);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\24|\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\3\3\3\3\3\5\3%\n\3\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\5\7\5.\n\5\f\5\16\5\61\13\5\3\5\3\5\3\6\3\6\3"+
		"\6\5\68\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7A\n\7\f\7\16\7D\13\7\5\7F\n"+
		"\7\5\7H\n\7\3\7\3\7\3\b\3\b\3\b\5\bO\n\b\3\t\3\t\3\t\3\t\3\t\3\t\7\tW"+
		"\n\t\f\t\16\tZ\13\t\3\t\3\t\5\t^\n\t\3\n\3\n\3\n\5\nc\n\n\3\13\3\13\3"+
		"\13\3\f\7\fi\n\f\f\f\16\fl\13\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\17\7\17w\n\17\f\17\16\17z\13\17\3\17\2\2\20\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\2\3\3\2\5\6}\2\36\3\2\2\2\4$\3\2\2\2\6&\3\2\2\2\b(\3\2\2\2\n"+
		"\67\3\2\2\2\f9\3\2\2\2\16N\3\2\2\2\20]\3\2\2\2\22b\3\2\2\2\24d\3\2\2\2"+
		"\26j\3\2\2\2\30m\3\2\2\2\32q\3\2\2\2\34x\3\2\2\2\36\37\7\17\2\2\37 \7"+
		"\5\2\2 \3\3\2\2\2!%\7\5\2\2\"%\5\2\2\2#%\7\6\2\2$!\3\2\2\2$\"\3\2\2\2"+
		"$#\3\2\2\2%\5\3\2\2\2&\'\t\2\2\2\'\7\3\2\2\2()\7\7\2\2)*\7\b\2\2*/\5\16"+
		"\b\2+,\7\21\2\2,.\5\16\b\2-+\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2"+
		"\60\62\3\2\2\2\61/\3\2\2\2\62\63\7\t\2\2\63\t\3\2\2\2\648\5\4\3\2\658"+
		"\7\7\2\2\668\7\3\2\2\67\64\3\2\2\2\67\65\3\2\2\2\67\66\3\2\2\28\13\3\2"+
		"\2\29G\7\b\2\2:H\3\2\2\2;E\5\16\b\2<F\3\2\2\2=F\7\21\2\2>?\7\21\2\2?A"+
		"\5\16\b\2@>\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2CF\3\2\2\2DB\3\2\2\2"+
		"E<\3\2\2\2E=\3\2\2\2EB\3\2\2\2FH\3\2\2\2G:\3\2\2\2G;\3\2\2\2HI\3\2\2\2"+
		"IJ\7\t\2\2J\r\3\2\2\2KO\5\n\6\2LO\5\b\5\2MO\5\f\7\2NK\3\2\2\2NL\3\2\2"+
		"\2NM\3\2\2\2O\17\3\2\2\2P^\7\7\2\2QR\7\7\2\2RS\7\b\2\2SX\5\16\b\2TU\7"+
		"\21\2\2UW\5\16\b\2VT\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y[\3\2\2\2Z"+
		"X\3\2\2\2[\\\7\t\2\2\\^\3\2\2\2]P\3\2\2\2]Q\3\2\2\2^\21\3\2\2\2_c\5\20"+
		"\t\2`a\7\17\2\2ac\5\20\t\2b_\3\2\2\2b`\3\2\2\2c\23\3\2\2\2de\7\22\2\2"+
		"ef\7\5\2\2f\25\3\2\2\2gi\5\22\n\2hg\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2"+
		"\2\2k\27\3\2\2\2lj\3\2\2\2mn\7\23\2\2no\5\4\3\2op\5\4\3\2p\31\3\2\2\2"+
		"qr\5\24\13\2rs\5\26\f\2st\5\30\r\2t\33\3\2\2\2uw\5\32\16\2vu\3\2\2\2w"+
		"z\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\35\3\2\2\2zx\3\2\2\2\16$/\67BEGNX]bjx";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}