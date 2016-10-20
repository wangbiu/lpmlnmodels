// Generated from LPMLN.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.anubis.lpmln.syntax;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LPMLNParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NAF_NOT=1, STRING=2, FULLSTOP=3, POSITIVE_INT=4, DECIMAL=5, ZERO=6, CONSTANT=7, 
		VAR=8, PLUS=9, MINUS=10, STAR=11, SLASH=12, LPAREN=13, RPAREN=14, LSBRACK=15, 
		RSBRACK=16, LCBRACK=17, RCBRACK=18, RANGE=19, COMMA=20, DISJUNCTION=21, 
		CONDITION=22, ASSIGN=23, WEAK_ASSIGN=24, LESS_THAN=25, LEQ=26, GREATER_THAN=27, 
		GEQ=28, EQUAL=29, DOUBLE_EQUAL=30, NEQ=31, AGGREGATE_OP=32, META_OP=33, 
		LINE_COMMENT=34, WS=35;
	public static final int
		RULE_negative_int = 0, RULE_integer = 1, RULE_natural_number = 2, RULE_arithmetic_op = 3, 
		RULE_relation_op = 4, RULE_simple_arithmetic_expr = 5, RULE_simple_arithmetic_expr2 = 6, 
		RULE_arithmethic_expr = 7, RULE_function = 8, RULE_term = 9, RULE_atom = 10, 
		RULE_range_atom = 11, RULE_literal = 12, RULE_default_literal = 13, RULE_extended_literal = 14, 
		RULE_aggregate_atom = 15, RULE_aggregate_expr = 16, RULE_relation_expr = 17, 
		RULE_head = 18, RULE_body = 19, RULE_fact = 20, RULE_constraint = 21, 
		RULE_full_rule = 22, RULE_hard_rule = 23, RULE_soft_rule = 24, RULE_meta_rule = 25, 
		RULE_lpmln_rule = 26;
	public static final String[] ruleNames = {
		"negative_int", "integer", "natural_number", "arithmetic_op", "relation_op", 
		"simple_arithmetic_expr", "simple_arithmetic_expr2", "arithmethic_expr", 
		"function", "term", "atom", "range_atom", "literal", "default_literal", 
		"extended_literal", "aggregate_atom", "aggregate_expr", "relation_expr", 
		"head", "body", "fact", "constraint", "full_rule", "hard_rule", "soft_rule", 
		"meta_rule", "lpmln_rule"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'not '", null, "'.'", null, null, "'0'", null, null, "'+'", "'-'", 
		"'*'", "'/'", "'('", "')'", "'['", "']'", "'{'", "'}'", "'..'", "','", 
		"'|'", "':'", "':-'", "':~'", "'<'", "'<='", "'>'", "'>='", "'='", "'=='", 
		"'!='", null, "'#show'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "NAF_NOT", "STRING", "FULLSTOP", "POSITIVE_INT", "DECIMAL", "ZERO", 
		"CONSTANT", "VAR", "PLUS", "MINUS", "STAR", "SLASH", "LPAREN", "RPAREN", 
		"LSBRACK", "RSBRACK", "LCBRACK", "RCBRACK", "RANGE", "COMMA", "DISJUNCTION", 
		"CONDITION", "ASSIGN", "WEAK_ASSIGN", "LESS_THAN", "LEQ", "GREATER_THAN", 
		"GEQ", "EQUAL", "DOUBLE_EQUAL", "NEQ", "AGGREGATE_OP", "META_OP", "LINE_COMMENT", 
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
	public String getGrammarFileName() { return "LPMLN.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LPMLNParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Negative_intContext extends ParserRuleContext {
		public TerminalNode MINUS() { return getToken(LPMLNParser.MINUS, 0); }
		public TerminalNode POSITIVE_INT() { return getToken(LPMLNParser.POSITIVE_INT, 0); }
		public Negative_intContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negative_int; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterNegative_int(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitNegative_int(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitNegative_int(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Negative_intContext negative_int() throws RecognitionException {
		Negative_intContext _localctx = new Negative_intContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_negative_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(MINUS);
			setState(55);
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
		public TerminalNode POSITIVE_INT() { return getToken(LPMLNParser.POSITIVE_INT, 0); }
		public Negative_intContext negative_int() {
			return getRuleContext(Negative_intContext.class,0);
		}
		public TerminalNode ZERO() { return getToken(LPMLNParser.ZERO, 0); }
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_integer);
		try {
			setState(60);
			switch (_input.LA(1)) {
			case POSITIVE_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				match(POSITIVE_INT);
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				negative_int();
				}
				break;
			case ZERO:
				enterOuterAlt(_localctx, 3);
				{
				setState(59);
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
		public TerminalNode POSITIVE_INT() { return getToken(LPMLNParser.POSITIVE_INT, 0); }
		public TerminalNode ZERO() { return getToken(LPMLNParser.ZERO, 0); }
		public Natural_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_natural_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterNatural_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitNatural_number(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitNatural_number(this);
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
			setState(62);
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

	public static class Arithmetic_opContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(LPMLNParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(LPMLNParser.MINUS, 0); }
		public TerminalNode STAR() { return getToken(LPMLNParser.STAR, 0); }
		public TerminalNode SLASH() { return getToken(LPMLNParser.SLASH, 0); }
		public Arithmetic_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterArithmetic_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitArithmetic_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitArithmetic_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arithmetic_opContext arithmetic_op() throws RecognitionException {
		Arithmetic_opContext _localctx = new Arithmetic_opContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_arithmetic_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH))) != 0)) ) {
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

	public static class Relation_opContext extends ParserRuleContext {
		public TerminalNode LESS_THAN() { return getToken(LPMLNParser.LESS_THAN, 0); }
		public TerminalNode LEQ() { return getToken(LPMLNParser.LEQ, 0); }
		public TerminalNode GREATER_THAN() { return getToken(LPMLNParser.GREATER_THAN, 0); }
		public TerminalNode GEQ() { return getToken(LPMLNParser.GEQ, 0); }
		public TerminalNode EQUAL() { return getToken(LPMLNParser.EQUAL, 0); }
		public TerminalNode DOUBLE_EQUAL() { return getToken(LPMLNParser.DOUBLE_EQUAL, 0); }
		public TerminalNode NEQ() { return getToken(LPMLNParser.NEQ, 0); }
		public Relation_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterRelation_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitRelation_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitRelation_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Relation_opContext relation_op() throws RecognitionException {
		Relation_opContext _localctx = new Relation_opContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_relation_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS_THAN) | (1L << LEQ) | (1L << GREATER_THAN) | (1L << GEQ) | (1L << EQUAL) | (1L << DOUBLE_EQUAL) | (1L << NEQ))) != 0)) ) {
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

	public static class Simple_arithmetic_exprContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public List<TerminalNode> VAR() { return getTokens(LPMLNParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(LPMLNParser.VAR, i);
		}
		public TerminalNode MINUS() { return getToken(LPMLNParser.MINUS, 0); }
		public List<Arithmetic_opContext> arithmetic_op() {
			return getRuleContexts(Arithmetic_opContext.class);
		}
		public Arithmetic_opContext arithmetic_op(int i) {
			return getRuleContext(Arithmetic_opContext.class,i);
		}
		public List<Natural_numberContext> natural_number() {
			return getRuleContexts(Natural_numberContext.class);
		}
		public Natural_numberContext natural_number(int i) {
			return getRuleContext(Natural_numberContext.class,i);
		}
		public Simple_arithmetic_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_arithmetic_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterSimple_arithmetic_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitSimple_arithmetic_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitSimple_arithmetic_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_arithmetic_exprContext simple_arithmetic_expr() throws RecognitionException {
		Simple_arithmetic_exprContext _localctx = new Simple_arithmetic_exprContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_simple_arithmetic_expr);
		int _la;
		try {
			int _alt;
			setState(90);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				integer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				_la = _input.LA(1);
				if (_la==MINUS) {
					{
					setState(69);
					match(MINUS);
					}
				}

				setState(72);
				match(VAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(73);
					integer();
					}
					break;
				case 2:
					{
					setState(75);
					_la = _input.LA(1);
					if (_la==MINUS) {
						{
						setState(74);
						match(MINUS);
						}
					}

					setState(77);
					match(VAR);
					}
					break;
				}
				setState(87);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(80);
						arithmetic_op();
						setState(83);
						switch (_input.LA(1)) {
						case POSITIVE_INT:
						case ZERO:
							{
							setState(81);
							natural_number();
							}
							break;
						case VAR:
							{
							setState(82);
							match(VAR);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						} 
					}
					setState(89);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				}
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

	public static class Simple_arithmetic_expr2Context extends ParserRuleContext {
		public Simple_arithmetic_exprContext simple_arithmetic_expr() {
			return getRuleContext(Simple_arithmetic_exprContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(LPMLNParser.LPAREN, 0); }
		public List<Simple_arithmetic_expr2Context> simple_arithmetic_expr2() {
			return getRuleContexts(Simple_arithmetic_expr2Context.class);
		}
		public Simple_arithmetic_expr2Context simple_arithmetic_expr2(int i) {
			return getRuleContext(Simple_arithmetic_expr2Context.class,i);
		}
		public TerminalNode RPAREN() { return getToken(LPMLNParser.RPAREN, 0); }
		public Arithmetic_opContext arithmetic_op() {
			return getRuleContext(Arithmetic_opContext.class,0);
		}
		public Simple_arithmetic_expr2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_arithmetic_expr2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterSimple_arithmetic_expr2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitSimple_arithmetic_expr2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitSimple_arithmetic_expr2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_arithmetic_expr2Context simple_arithmetic_expr2() throws RecognitionException {
		return simple_arithmetic_expr2(0);
	}

	private Simple_arithmetic_expr2Context simple_arithmetic_expr2(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Simple_arithmetic_expr2Context _localctx = new Simple_arithmetic_expr2Context(_ctx, _parentState);
		Simple_arithmetic_expr2Context _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_simple_arithmetic_expr2, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			switch (_input.LA(1)) {
			case POSITIVE_INT:
			case ZERO:
			case VAR:
			case MINUS:
				{
				setState(93);
				simple_arithmetic_expr();
				}
				break;
			case LPAREN:
				{
				{
				setState(94);
				match(LPAREN);
				setState(95);
				simple_arithmetic_expr2(0);
				setState(96);
				match(RPAREN);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(106);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Simple_arithmetic_expr2Context(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_simple_arithmetic_expr2);
					setState(100);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(101);
					arithmetic_op();
					setState(102);
					simple_arithmetic_expr2(2);
					}
					} 
				}
				setState(108);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Arithmethic_exprContext extends ParserRuleContext {
		public Simple_arithmetic_expr2Context simple_arithmetic_expr2() {
			return getRuleContext(Simple_arithmetic_expr2Context.class,0);
		}
		public TerminalNode MINUS() { return getToken(LPMLNParser.MINUS, 0); }
		public TerminalNode LPAREN() { return getToken(LPMLNParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(LPMLNParser.RPAREN, 0); }
		public Arithmethic_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmethic_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterArithmethic_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitArithmethic_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitArithmethic_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arithmethic_exprContext arithmethic_expr() throws RecognitionException {
		Arithmethic_exprContext _localctx = new Arithmethic_exprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_arithmethic_expr);
		try {
			setState(115);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				simple_arithmetic_expr2(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(110);
				match(MINUS);
				setState(111);
				match(LPAREN);
				setState(112);
				simple_arithmetic_expr2(0);
				setState(113);
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

	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode CONSTANT() { return getToken(LPMLNParser.CONSTANT, 0); }
		public TerminalNode LPAREN() { return getToken(LPMLNParser.LPAREN, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(LPMLNParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LPMLNParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LPMLNParser.COMMA, i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(CONSTANT);
			setState(118);
			match(LPAREN);
			setState(119);
			term();
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(120);
				match(COMMA);
				setState(121);
				term();
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(127);
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
		public TerminalNode VAR() { return getToken(LPMLNParser.VAR, 0); }
		public TerminalNode CONSTANT() { return getToken(LPMLNParser.CONSTANT, 0); }
		public Arithmethic_exprContext arithmethic_expr() {
			return getRuleContext(Arithmethic_exprContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public TerminalNode STRING() { return getToken(LPMLNParser.STRING, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_term);
		try {
			setState(134);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(129);
				match(VAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				match(CONSTANT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(131);
				arithmethic_expr();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(132);
				function();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(133);
				match(STRING);
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
		public TerminalNode CONSTANT() { return getToken(LPMLNParser.CONSTANT, 0); }
		public TerminalNode LPAREN() { return getToken(LPMLNParser.LPAREN, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(LPMLNParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LPMLNParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LPMLNParser.COMMA, i);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_atom);
		int _la;
		try {
			setState(149);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				match(CONSTANT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				match(CONSTANT);
				setState(138);
				match(LPAREN);
				setState(139);
				term();
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(140);
					match(COMMA);
					setState(141);
					term();
					}
					}
					setState(146);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(147);
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

	public static class Range_atomContext extends ParserRuleContext {
		public TerminalNode CONSTANT() { return getToken(LPMLNParser.CONSTANT, 0); }
		public TerminalNode LPAREN() { return getToken(LPMLNParser.LPAREN, 0); }
		public List<IntegerContext> integer() {
			return getRuleContexts(IntegerContext.class);
		}
		public IntegerContext integer(int i) {
			return getRuleContext(IntegerContext.class,i);
		}
		public TerminalNode RANGE() { return getToken(LPMLNParser.RANGE, 0); }
		public TerminalNode RPAREN() { return getToken(LPMLNParser.RPAREN, 0); }
		public Range_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterRange_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitRange_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitRange_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Range_atomContext range_atom() throws RecognitionException {
		Range_atomContext _localctx = new Range_atomContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_range_atom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(CONSTANT);
			setState(152);
			match(LPAREN);
			setState(153);
			integer();
			setState(154);
			match(RANGE);
			setState(155);
			integer();
			setState(156);
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

	public static class LiteralContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(LPMLNParser.MINUS, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_literal);
		try {
			setState(161);
			switch (_input.LA(1)) {
			case CONSTANT:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				atom();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				match(MINUS);
				setState(160);
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

	public static class Default_literalContext extends ParserRuleContext {
		public TerminalNode NAF_NOT() { return getToken(LPMLNParser.NAF_NOT, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Default_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_default_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterDefault_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitDefault_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitDefault_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Default_literalContext default_literal() throws RecognitionException {
		Default_literalContext _localctx = new Default_literalContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_default_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(NAF_NOT);
			setState(164);
			literal();
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

	public static class Extended_literalContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Default_literalContext default_literal() {
			return getRuleContext(Default_literalContext.class,0);
		}
		public Extended_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extended_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterExtended_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitExtended_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitExtended_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Extended_literalContext extended_literal() throws RecognitionException {
		Extended_literalContext _localctx = new Extended_literalContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_extended_literal);
		try {
			setState(168);
			switch (_input.LA(1)) {
			case CONSTANT:
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(166);
				literal();
				}
				break;
			case NAF_NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(167);
				default_literal();
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

	public static class Aggregate_atomContext extends ParserRuleContext {
		public TerminalNode AGGREGATE_OP() { return getToken(LPMLNParser.AGGREGATE_OP, 0); }
		public TerminalNode LCBRACK() { return getToken(LPMLNParser.LCBRACK, 0); }
		public TerminalNode CONDITION() { return getToken(LPMLNParser.CONDITION, 0); }
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public TerminalNode RCBRACK() { return getToken(LPMLNParser.RCBRACK, 0); }
		public TerminalNode VAR() { return getToken(LPMLNParser.VAR, 0); }
		public Aggregate_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregate_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterAggregate_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitAggregate_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitAggregate_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aggregate_atomContext aggregate_atom() throws RecognitionException {
		Aggregate_atomContext _localctx = new Aggregate_atomContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_aggregate_atom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(AGGREGATE_OP);
			setState(171);
			match(LCBRACK);
			setState(174);
			switch (_input.LA(1)) {
			case CONSTANT:
			case MINUS:
				{
				setState(172);
				literal();
				}
				break;
			case VAR:
				{
				setState(173);
				match(VAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(176);
			match(CONDITION);
			setState(177);
			literal();
			setState(178);
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

	public static class Aggregate_exprContext extends ParserRuleContext {
		public Relation_opContext relation_op() {
			return getRuleContext(Relation_opContext.class,0);
		}
		public List<Aggregate_atomContext> aggregate_atom() {
			return getRuleContexts(Aggregate_atomContext.class);
		}
		public Aggregate_atomContext aggregate_atom(int i) {
			return getRuleContext(Aggregate_atomContext.class,i);
		}
		public TerminalNode VAR() { return getToken(LPMLNParser.VAR, 0); }
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(LPMLNParser.EQUAL, 0); }
		public Aggregate_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregate_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterAggregate_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitAggregate_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitAggregate_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aggregate_exprContext aggregate_expr() throws RecognitionException {
		Aggregate_exprContext _localctx = new Aggregate_exprContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_aggregate_expr);
		try {
			setState(197);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(183);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(180);
					match(VAR);
					}
					break;
				case AGGREGATE_OP:
					{
					setState(181);
					aggregate_atom();
					}
					break;
				case POSITIVE_INT:
				case ZERO:
				case MINUS:
					{
					setState(182);
					integer();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(185);
				relation_op();
				setState(186);
				aggregate_atom();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(188);
				aggregate_atom();
				setState(189);
				relation_op();
				setState(192);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(190);
					match(VAR);
					}
					break;
				case POSITIVE_INT:
				case ZERO:
				case MINUS:
					{
					setState(191);
					integer();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(194);
				match(VAR);
				setState(195);
				match(EQUAL);
				setState(196);
				aggregate_atom();
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

	public static class Relation_exprContext extends ParserRuleContext {
		public List<TerminalNode> VAR() { return getTokens(LPMLNParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(LPMLNParser.VAR, i);
		}
		public Relation_opContext relation_op() {
			return getRuleContext(Relation_opContext.class,0);
		}
		public List<TerminalNode> MINUS() { return getTokens(LPMLNParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(LPMLNParser.MINUS, i);
		}
		public TerminalNode STRING() { return getToken(LPMLNParser.STRING, 0); }
		public List<Arithmethic_exprContext> arithmethic_expr() {
			return getRuleContexts(Arithmethic_exprContext.class);
		}
		public Arithmethic_exprContext arithmethic_expr(int i) {
			return getRuleContext(Arithmethic_exprContext.class,i);
		}
		public Relation_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterRelation_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitRelation_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitRelation_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Relation_exprContext relation_expr() throws RecognitionException {
		Relation_exprContext _localctx = new Relation_exprContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_relation_expr);
		int _la;
		try {
			setState(228);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				_la = _input.LA(1);
				if (_la==MINUS) {
					{
					setState(199);
					match(MINUS);
					}
				}

				setState(202);
				match(VAR);
				setState(203);
				relation_op();
				setState(205);
				_la = _input.LA(1);
				if (_la==MINUS) {
					{
					setState(204);
					match(MINUS);
					}
				}

				setState(207);
				match(VAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(209);
				match(VAR);
				setState(210);
				relation_op();
				setState(211);
				match(STRING);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(218);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(214);
					_la = _input.LA(1);
					if (_la==MINUS) {
						{
						setState(213);
						match(MINUS);
						}
					}

					setState(216);
					match(VAR);
					}
					break;
				case 2:
					{
					setState(217);
					arithmethic_expr();
					}
					break;
				}
				setState(220);
				relation_op();
				setState(226);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(222);
					_la = _input.LA(1);
					if (_la==MINUS) {
						{
						setState(221);
						match(MINUS);
						}
					}

					setState(224);
					match(VAR);
					}
					break;
				case 2:
					{
					setState(225);
					arithmethic_expr();
					}
					break;
				}
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

	public static class HeadContext extends ParserRuleContext {
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public List<TerminalNode> DISJUNCTION() { return getTokens(LPMLNParser.DISJUNCTION); }
		public TerminalNode DISJUNCTION(int i) {
			return getToken(LPMLNParser.DISJUNCTION, i);
		}
		public HeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitHead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitHead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadContext head() throws RecognitionException {
		HeadContext _localctx = new HeadContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_head);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			literal();
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DISJUNCTION) {
				{
				{
				setState(231);
				match(DISJUNCTION);
				setState(232);
				literal();
				}
				}
				setState(237);
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

	public static class BodyContext extends ParserRuleContext {
		public List<Extended_literalContext> extended_literal() {
			return getRuleContexts(Extended_literalContext.class);
		}
		public Extended_literalContext extended_literal(int i) {
			return getRuleContext(Extended_literalContext.class,i);
		}
		public List<Relation_exprContext> relation_expr() {
			return getRuleContexts(Relation_exprContext.class);
		}
		public Relation_exprContext relation_expr(int i) {
			return getRuleContext(Relation_exprContext.class,i);
		}
		public List<Aggregate_exprContext> aggregate_expr() {
			return getRuleContexts(Aggregate_exprContext.class);
		}
		public Aggregate_exprContext aggregate_expr(int i) {
			return getRuleContext(Aggregate_exprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LPMLNParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LPMLNParser.COMMA, i);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(238);
				extended_literal();
				}
				break;
			case 2:
				{
				setState(239);
				relation_expr();
				}
				break;
			case 3:
				{
				setState(240);
				aggregate_expr();
				}
				break;
			}
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(243);
				match(COMMA);
				setState(247);
				switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
				case 1:
					{
					setState(244);
					extended_literal();
					}
					break;
				case 2:
					{
					setState(245);
					relation_expr();
					}
					break;
				case 3:
					{
					setState(246);
					aggregate_expr();
					}
					break;
				}
				}
				}
				setState(253);
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

	public static class FactContext extends ParserRuleContext {
		public TerminalNode FULLSTOP() { return getToken(LPMLNParser.FULLSTOP, 0); }
		public HeadContext head() {
			return getRuleContext(HeadContext.class,0);
		}
		public Range_atomContext range_atom() {
			return getRuleContext(Range_atomContext.class,0);
		}
		public FactContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fact; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterFact(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitFact(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitFact(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactContext fact() throws RecognitionException {
		FactContext _localctx = new FactContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_fact);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				{
				setState(254);
				head();
				}
				break;
			case 2:
				{
				setState(255);
				range_atom();
				}
				break;
			}
			setState(258);
			match(FULLSTOP);
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

	public static class ConstraintContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(LPMLNParser.ASSIGN, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode FULLSTOP() { return getToken(LPMLNParser.FULLSTOP, 0); }
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(ASSIGN);
			setState(261);
			body();
			setState(262);
			match(FULLSTOP);
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

	public static class Full_ruleContext extends ParserRuleContext {
		public HeadContext head() {
			return getRuleContext(HeadContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(LPMLNParser.ASSIGN, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode FULLSTOP() { return getToken(LPMLNParser.FULLSTOP, 0); }
		public Full_ruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_full_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterFull_rule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitFull_rule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitFull_rule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Full_ruleContext full_rule() throws RecognitionException {
		Full_ruleContext _localctx = new Full_ruleContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_full_rule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			head();
			setState(265);
			match(ASSIGN);
			setState(266);
			body();
			setState(267);
			match(FULLSTOP);
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

	public static class Hard_ruleContext extends ParserRuleContext {
		public FactContext fact() {
			return getRuleContext(FactContext.class,0);
		}
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
		public Full_ruleContext full_rule() {
			return getRuleContext(Full_ruleContext.class,0);
		}
		public Hard_ruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hard_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterHard_rule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitHard_rule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitHard_rule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Hard_ruleContext hard_rule() throws RecognitionException {
		Hard_ruleContext _localctx = new Hard_ruleContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_hard_rule);
		try {
			setState(272);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(269);
				fact();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(270);
				constraint();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(271);
				full_rule();
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

	public static class Soft_ruleContext extends ParserRuleContext {
		public TerminalNode CONDITION() { return getToken(LPMLNParser.CONDITION, 0); }
		public Hard_ruleContext hard_rule() {
			return getRuleContext(Hard_ruleContext.class,0);
		}
		public TerminalNode DECIMAL() { return getToken(LPMLNParser.DECIMAL, 0); }
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public Soft_ruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_soft_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterSoft_rule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitSoft_rule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitSoft_rule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Soft_ruleContext soft_rule() throws RecognitionException {
		Soft_ruleContext _localctx = new Soft_ruleContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_soft_rule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			switch (_input.LA(1)) {
			case DECIMAL:
				{
				setState(274);
				match(DECIMAL);
				}
				break;
			case POSITIVE_INT:
			case ZERO:
			case MINUS:
				{
				setState(275);
				integer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(278);
			match(CONDITION);
			setState(279);
			hard_rule();
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

	public static class Meta_ruleContext extends ParserRuleContext {
		public TerminalNode META_OP() { return getToken(LPMLNParser.META_OP, 0); }
		public TerminalNode CONSTANT() { return getToken(LPMLNParser.CONSTANT, 0); }
		public TerminalNode SLASH() { return getToken(LPMLNParser.SLASH, 0); }
		public Natural_numberContext natural_number() {
			return getRuleContext(Natural_numberContext.class,0);
		}
		public TerminalNode FULLSTOP() { return getToken(LPMLNParser.FULLSTOP, 0); }
		public TerminalNode MINUS() { return getToken(LPMLNParser.MINUS, 0); }
		public Meta_ruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_meta_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterMeta_rule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitMeta_rule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitMeta_rule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Meta_ruleContext meta_rule() throws RecognitionException {
		Meta_ruleContext _localctx = new Meta_ruleContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_meta_rule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			match(META_OP);
			setState(283);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(282);
				match(MINUS);
				}
			}

			setState(285);
			match(CONSTANT);
			setState(286);
			match(SLASH);
			setState(287);
			natural_number();
			setState(288);
			match(FULLSTOP);
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

	public static class Lpmln_ruleContext extends ParserRuleContext {
		public List<Hard_ruleContext> hard_rule() {
			return getRuleContexts(Hard_ruleContext.class);
		}
		public Hard_ruleContext hard_rule(int i) {
			return getRuleContext(Hard_ruleContext.class,i);
		}
		public List<Soft_ruleContext> soft_rule() {
			return getRuleContexts(Soft_ruleContext.class);
		}
		public Soft_ruleContext soft_rule(int i) {
			return getRuleContext(Soft_ruleContext.class,i);
		}
		public List<Meta_ruleContext> meta_rule() {
			return getRuleContexts(Meta_ruleContext.class);
		}
		public Meta_ruleContext meta_rule(int i) {
			return getRuleContext(Meta_ruleContext.class,i);
		}
		public Lpmln_ruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lpmln_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterLpmln_rule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitLpmln_rule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitLpmln_rule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lpmln_ruleContext lpmln_rule() throws RecognitionException {
		Lpmln_ruleContext _localctx = new Lpmln_ruleContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_lpmln_rule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << POSITIVE_INT) | (1L << DECIMAL) | (1L << ZERO) | (1L << CONSTANT) | (1L << MINUS) | (1L << ASSIGN) | (1L << META_OP))) != 0)) {
				{
				setState(293);
				switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(290);
					hard_rule();
					}
					break;
				case 2:
					{
					setState(291);
					soft_rule();
					}
					break;
				case 3:
					{
					setState(292);
					meta_rule();
					}
					break;
				}
				}
				setState(297);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return simple_arithmetic_expr2_sempred((Simple_arithmetic_expr2Context)_localctx, predIndex);
		}
		return true;
	}
	private boolean simple_arithmetic_expr2_sempred(Simple_arithmetic_expr2Context _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3%\u012d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\3\3\3\3\3\5\3?\n\3\3\4\3"+
		"\4\3\5\3\5\3\6\3\6\3\7\3\7\5\7I\n\7\3\7\3\7\3\7\5\7N\n\7\3\7\5\7Q\n\7"+
		"\3\7\3\7\3\7\5\7V\n\7\7\7X\n\7\f\7\16\7[\13\7\5\7]\n\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\5\be\n\b\3\b\3\b\3\b\3\b\7\bk\n\b\f\b\16\bn\13\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\5\tv\n\t\3\n\3\n\3\n\3\n\3\n\7\n}\n\n\f\n\16\n\u0080\13\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\5\13\u0089\n\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\7\f\u0091\n\f\f\f\16\f\u0094\13\f\3\f\3\f\5\f\u0098\n\f\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\5\16\u00a4\n\16\3\17\3\17\3\17\3\20"+
		"\3\20\5\20\u00ab\n\20\3\21\3\21\3\21\3\21\5\21\u00b1\n\21\3\21\3\21\3"+
		"\21\3\21\3\22\3\22\3\22\5\22\u00ba\n\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\5\22\u00c3\n\22\3\22\3\22\3\22\5\22\u00c8\n\22\3\23\5\23\u00cb\n"+
		"\23\3\23\3\23\3\23\5\23\u00d0\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\5\23\u00d9\n\23\3\23\3\23\5\23\u00dd\n\23\3\23\3\23\5\23\u00e1\n\23\3"+
		"\23\3\23\5\23\u00e5\n\23\5\23\u00e7\n\23\3\24\3\24\3\24\7\24\u00ec\n\24"+
		"\f\24\16\24\u00ef\13\24\3\25\3\25\3\25\5\25\u00f4\n\25\3\25\3\25\3\25"+
		"\3\25\5\25\u00fa\n\25\7\25\u00fc\n\25\f\25\16\25\u00ff\13\25\3\26\3\26"+
		"\5\26\u0103\n\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\5\31\u0113\n\31\3\32\3\32\5\32\u0117\n\32\3\32\3\32\3"+
		"\32\3\33\3\33\5\33\u011e\n\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\7\34\u0128\n\34\f\34\16\34\u012b\13\34\3\34\2\3\16\35\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66\2\5\4\2\6\6\b\b\3\2\13\16"+
		"\3\2\33!\u0142\28\3\2\2\2\4>\3\2\2\2\6@\3\2\2\2\bB\3\2\2\2\nD\3\2\2\2"+
		"\f\\\3\2\2\2\16d\3\2\2\2\20u\3\2\2\2\22w\3\2\2\2\24\u0088\3\2\2\2\26\u0097"+
		"\3\2\2\2\30\u0099\3\2\2\2\32\u00a3\3\2\2\2\34\u00a5\3\2\2\2\36\u00aa\3"+
		"\2\2\2 \u00ac\3\2\2\2\"\u00c7\3\2\2\2$\u00e6\3\2\2\2&\u00e8\3\2\2\2(\u00f3"+
		"\3\2\2\2*\u0102\3\2\2\2,\u0106\3\2\2\2.\u010a\3\2\2\2\60\u0112\3\2\2\2"+
		"\62\u0116\3\2\2\2\64\u011b\3\2\2\2\66\u0129\3\2\2\289\7\f\2\29:\7\6\2"+
		"\2:\3\3\2\2\2;?\7\6\2\2<?\5\2\2\2=?\7\b\2\2>;\3\2\2\2><\3\2\2\2>=\3\2"+
		"\2\2?\5\3\2\2\2@A\t\2\2\2A\7\3\2\2\2BC\t\3\2\2C\t\3\2\2\2DE\t\4\2\2E\13"+
		"\3\2\2\2F]\5\4\3\2GI\7\f\2\2HG\3\2\2\2HI\3\2\2\2IJ\3\2\2\2J]\7\n\2\2K"+
		"Q\5\4\3\2LN\7\f\2\2ML\3\2\2\2MN\3\2\2\2NO\3\2\2\2OQ\7\n\2\2PK\3\2\2\2"+
		"PM\3\2\2\2QY\3\2\2\2RU\5\b\5\2SV\5\6\4\2TV\7\n\2\2US\3\2\2\2UT\3\2\2\2"+
		"VX\3\2\2\2WR\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2"+
		"\\F\3\2\2\2\\H\3\2\2\2\\P\3\2\2\2]\r\3\2\2\2^_\b\b\1\2_e\5\f\7\2`a\7\17"+
		"\2\2ab\5\16\b\2bc\7\20\2\2ce\3\2\2\2d^\3\2\2\2d`\3\2\2\2el\3\2\2\2fg\f"+
		"\3\2\2gh\5\b\5\2hi\5\16\b\4ik\3\2\2\2jf\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm"+
		"\3\2\2\2m\17\3\2\2\2nl\3\2\2\2ov\5\16\b\2pq\7\f\2\2qr\7\17\2\2rs\5\16"+
		"\b\2st\7\20\2\2tv\3\2\2\2uo\3\2\2\2up\3\2\2\2v\21\3\2\2\2wx\7\t\2\2xy"+
		"\7\17\2\2y~\5\24\13\2z{\7\26\2\2{}\5\24\13\2|z\3\2\2\2}\u0080\3\2\2\2"+
		"~|\3\2\2\2~\177\3\2\2\2\177\u0081\3\2\2\2\u0080~\3\2\2\2\u0081\u0082\7"+
		"\20\2\2\u0082\23\3\2\2\2\u0083\u0089\7\n\2\2\u0084\u0089\7\t\2\2\u0085"+
		"\u0089\5\20\t\2\u0086\u0089\5\22\n\2\u0087\u0089\7\4\2\2\u0088\u0083\3"+
		"\2\2\2\u0088\u0084\3\2\2\2\u0088\u0085\3\2\2\2\u0088\u0086\3\2\2\2\u0088"+
		"\u0087\3\2\2\2\u0089\25\3\2\2\2\u008a\u0098\7\t\2\2\u008b\u008c\7\t\2"+
		"\2\u008c\u008d\7\17\2\2\u008d\u0092\5\24\13\2\u008e\u008f\7\26\2\2\u008f"+
		"\u0091\5\24\13\2\u0090\u008e\3\2\2\2\u0091\u0094\3\2\2\2\u0092\u0090\3"+
		"\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2\2\2\u0094\u0092\3\2\2\2\u0095"+
		"\u0096\7\20\2\2\u0096\u0098\3\2\2\2\u0097\u008a\3\2\2\2\u0097\u008b\3"+
		"\2\2\2\u0098\27\3\2\2\2\u0099\u009a\7\t\2\2\u009a\u009b\7\17\2\2\u009b"+
		"\u009c\5\4\3\2\u009c\u009d\7\25\2\2\u009d\u009e\5\4\3\2\u009e\u009f\7"+
		"\20\2\2\u009f\31\3\2\2\2\u00a0\u00a4\5\26\f\2\u00a1\u00a2\7\f\2\2\u00a2"+
		"\u00a4\5\26\f\2\u00a3\u00a0\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\33\3\2\2"+
		"\2\u00a5\u00a6\7\3\2\2\u00a6\u00a7\5\32\16\2\u00a7\35\3\2\2\2\u00a8\u00ab"+
		"\5\32\16\2\u00a9\u00ab\5\34\17\2\u00aa\u00a8\3\2\2\2\u00aa\u00a9\3\2\2"+
		"\2\u00ab\37\3\2\2\2\u00ac\u00ad\7\"\2\2\u00ad\u00b0\7\23\2\2\u00ae\u00b1"+
		"\5\32\16\2\u00af\u00b1\7\n\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00af\3\2\2\2"+
		"\u00b1\u00b2\3\2\2\2\u00b2\u00b3\7\30\2\2\u00b3\u00b4\5\32\16\2\u00b4"+
		"\u00b5\7\24\2\2\u00b5!\3\2\2\2\u00b6\u00ba\7\n\2\2\u00b7\u00ba\5 \21\2"+
		"\u00b8\u00ba\5\4\3\2\u00b9\u00b6\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00b8"+
		"\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\5\n\6\2\u00bc\u00bd\5 \21\2\u00bd"+
		"\u00c8\3\2\2\2\u00be\u00bf\5 \21\2\u00bf\u00c2\5\n\6\2\u00c0\u00c3\7\n"+
		"\2\2\u00c1\u00c3\5\4\3\2\u00c2\u00c0\3\2\2\2\u00c2\u00c1\3\2\2\2\u00c3"+
		"\u00c8\3\2\2\2\u00c4\u00c5\7\n\2\2\u00c5\u00c6\7\37\2\2\u00c6\u00c8\5"+
		" \21\2\u00c7\u00b9\3\2\2\2\u00c7\u00be\3\2\2\2\u00c7\u00c4\3\2\2\2\u00c8"+
		"#\3\2\2\2\u00c9\u00cb\7\f\2\2\u00ca\u00c9\3\2\2\2\u00ca\u00cb\3\2\2\2"+
		"\u00cb\u00cc\3\2\2\2\u00cc\u00cd\7\n\2\2\u00cd\u00cf\5\n\6\2\u00ce\u00d0"+
		"\7\f\2\2\u00cf\u00ce\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00d2\7\n\2\2\u00d2\u00e7\3\2\2\2\u00d3\u00d4\7\n\2\2\u00d4\u00d5\5\n"+
		"\6\2\u00d5\u00d6\7\4\2\2\u00d6\u00e7\3\2\2\2\u00d7\u00d9\7\f\2\2\u00d8"+
		"\u00d7\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00dd\7\n"+
		"\2\2\u00db\u00dd\5\20\t\2\u00dc\u00d8\3\2\2\2\u00dc\u00db\3\2\2\2\u00dd"+
		"\u00de\3\2\2\2\u00de\u00e4\5\n\6\2\u00df\u00e1\7\f\2\2\u00e0\u00df\3\2"+
		"\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e5\7\n\2\2\u00e3"+
		"\u00e5\5\20\t\2\u00e4\u00e0\3\2\2\2\u00e4\u00e3\3\2\2\2\u00e5\u00e7\3"+
		"\2\2\2\u00e6\u00ca\3\2\2\2\u00e6\u00d3\3\2\2\2\u00e6\u00dc\3\2\2\2\u00e7"+
		"%\3\2\2\2\u00e8\u00ed\5\32\16\2\u00e9\u00ea\7\27\2\2\u00ea\u00ec\5\32"+
		"\16\2\u00eb\u00e9\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed"+
		"\u00ee\3\2\2\2\u00ee\'\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f4\5\36\20"+
		"\2\u00f1\u00f4\5$\23\2\u00f2\u00f4\5\"\22\2\u00f3\u00f0\3\2\2\2\u00f3"+
		"\u00f1\3\2\2\2\u00f3\u00f2\3\2\2\2\u00f4\u00fd\3\2\2\2\u00f5\u00f9\7\26"+
		"\2\2\u00f6\u00fa\5\36\20\2\u00f7\u00fa\5$\23\2\u00f8\u00fa\5\"\22\2\u00f9"+
		"\u00f6\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00f8\3\2\2\2\u00fa\u00fc\3\2"+
		"\2\2\u00fb\u00f5\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd"+
		"\u00fe\3\2\2\2\u00fe)\3\2\2\2\u00ff\u00fd\3\2\2\2\u0100\u0103\5&\24\2"+
		"\u0101\u0103\5\30\r\2\u0102\u0100\3\2\2\2\u0102\u0101\3\2\2\2\u0103\u0104"+
		"\3\2\2\2\u0104\u0105\7\5\2\2\u0105+\3\2\2\2\u0106\u0107\7\31\2\2\u0107"+
		"\u0108\5(\25\2\u0108\u0109\7\5\2\2\u0109-\3\2\2\2\u010a\u010b\5&\24\2"+
		"\u010b\u010c\7\31\2\2\u010c\u010d\5(\25\2\u010d\u010e\7\5\2\2\u010e/\3"+
		"\2\2\2\u010f\u0113\5*\26\2\u0110\u0113\5,\27\2\u0111\u0113\5.\30\2\u0112"+
		"\u010f\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0111\3\2\2\2\u0113\61\3\2\2"+
		"\2\u0114\u0117\7\7\2\2\u0115\u0117\5\4\3\2\u0116\u0114\3\2\2\2\u0116\u0115"+
		"\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0119\7\30\2\2\u0119\u011a\5\60\31"+
		"\2\u011a\63\3\2\2\2\u011b\u011d\7#\2\2\u011c\u011e\7\f\2\2\u011d\u011c"+
		"\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0120\7\t\2\2\u0120"+
		"\u0121\7\16\2\2\u0121\u0122\5\6\4\2\u0122\u0123\7\5\2\2\u0123\65\3\2\2"+
		"\2\u0124\u0128\5\60\31\2\u0125\u0128\5\62\32\2\u0126\u0128\5\64\33\2\u0127"+
		"\u0124\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0126\3\2\2\2\u0128\u012b\3\2"+
		"\2\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a\67\3\2\2\2\u012b\u0129"+
		"\3\2\2\2\'>HMPUY\\dlu~\u0088\u0092\u0097\u00a3\u00aa\u00b0\u00b9\u00c2"+
		"\u00c7\u00ca\u00cf\u00d8\u00dc\u00e0\u00e4\u00e6\u00ed\u00f3\u00f9\u00fd"+
		"\u0102\u0112\u0116\u011d\u0127\u0129";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}