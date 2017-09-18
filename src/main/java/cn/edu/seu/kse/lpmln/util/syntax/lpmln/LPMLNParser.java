// Generated from LPMLN.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.lpmln.util.syntax.lpmln;
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
		VAR=8, EXPONENITIATION=9, BITWISE_AND=10, BITWISE_OR=11, BITWISE_EXCLUSIVE_OR=12, 
		BITWISE_COMPLEMENT=13, PLUS=14, MINUS=15, STAR=16, SLASH=17, LPAREN=18, 
		RPAREN=19, LSBRACK=20, RSBRACK=21, LCBRACK=22, RCBRACK=23, RANGE=24, COMMA=25, 
		DISJUNCTION=26, CONDITION=27, ASSIGN=28, WEAK_ASSIGN=29, SEMICOLON=30, 
		LESS_THAN=31, LEQ=32, GREATER_THAN=33, GEQ=34, EQUAL=35, DOUBLE_EQUAL=36, 
		NEQ=37, AGGREGATE_OP=38, META_OP=39, LINE_COMMENT=40, WS=41, BOOL_CONSTANTS=42;
	public static final int
		RULE_negative_int = 0, RULE_integer = 1, RULE_natural_number = 2, RULE_arithmetic_op = 3, 
		RULE_bitwise_op = 4, RULE_binary_op = 5, RULE_unary_op = 6, RULE_bit_number = 7, 
		RULE_relation_op = 8, RULE_simple_arithmetic_expr = 9, RULE_simple_arithmetic_expr2 = 10, 
		RULE_arithmethic_expr = 11, RULE_function = 12, RULE_simpleterm = 13, 
		RULE_tuple = 14, RULE_term = 15, RULE_atom = 16, RULE_range_atom = 17, 
		RULE_literal = 18, RULE_term_tuple = 19, RULE_literal_tuple = 20, RULE_aggregate_elements = 21, 
		RULE_aggregate_elements_condition = 22, RULE_body_aggregate = 23, RULE_head_aggregate = 24, 
		RULE_relation_expr = 25, RULE_head = 26, RULE_head_literal = 27, RULE_body = 28, 
		RULE_body_literal = 29, RULE_fact = 30, RULE_constraint = 31, RULE_full_rule = 32, 
		RULE_hard_rule = 33, RULE_soft_rule = 34, RULE_meta_rule = 35, RULE_lpmln_rule = 36;
	public static final String[] ruleNames = {
		"negative_int", "integer", "natural_number", "arithmetic_op", "bitwise_op", 
		"binary_op", "unary_op", "bit_number", "relation_op", "simple_arithmetic_expr", 
		"simple_arithmetic_expr2", "arithmethic_expr", "function", "simpleterm", 
		"tuple", "term", "atom", "range_atom", "literal", "term_tuple", "literal_tuple", 
		"aggregate_elements", "aggregate_elements_condition", "body_aggregate", 
		"head_aggregate", "relation_expr", "head", "head_literal", "body", "body_literal", 
		"fact", "constraint", "full_rule", "hard_rule", "soft_rule", "meta_rule", 
		"lpmln_rule"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'not '", null, "'.'", null, null, "'0'", null, null, "'**'", "'&'", 
		"'?'", "'^'", "'~'", "'+'", "'-'", "'*'", "'/'", "'('", "')'", "'['", 
		"']'", "'{'", "'}'", "'..'", "','", "'|'", "':'", "':-'", "':~'", "';'", 
		"'<'", "'<='", "'>'", "'>='", "'='", "'=='", "'!='", null, "'#show '"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "NAF_NOT", "STRING", "FULLSTOP", "POSITIVE_INT", "DECIMAL", "ZERO", 
		"CONSTANT", "VAR", "EXPONENITIATION", "BITWISE_AND", "BITWISE_OR", "BITWISE_EXCLUSIVE_OR", 
		"BITWISE_COMPLEMENT", "PLUS", "MINUS", "STAR", "SLASH", "LPAREN", "RPAREN", 
		"LSBRACK", "RSBRACK", "LCBRACK", "RCBRACK", "RANGE", "COMMA", "DISJUNCTION", 
		"CONDITION", "ASSIGN", "WEAK_ASSIGN", "SEMICOLON", "LESS_THAN", "LEQ", 
		"GREATER_THAN", "GEQ", "EQUAL", "DOUBLE_EQUAL", "NEQ", "AGGREGATE_OP", 
		"META_OP", "LINE_COMMENT", "WS", "BOOL_CONSTANTS"
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
			setState(74);
			match(MINUS);
			setState(75);
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
			setState(80);
			switch (_input.LA(1)) {
			case POSITIVE_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				match(POSITIVE_INT);
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				negative_int();
				}
				break;
			case ZERO:
				enterOuterAlt(_localctx, 3);
				{
				setState(79);
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
			setState(82);
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
			setState(84);
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

	public static class Bitwise_opContext extends ParserRuleContext {
		public TerminalNode BITWISE_AND() { return getToken(LPMLNParser.BITWISE_AND, 0); }
		public TerminalNode BITWISE_OR() { return getToken(LPMLNParser.BITWISE_OR, 0); }
		public TerminalNode BITWISE_EXCLUSIVE_OR() { return getToken(LPMLNParser.BITWISE_EXCLUSIVE_OR, 0); }
		public Bitwise_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitwise_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterBitwise_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitBitwise_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitBitwise_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bitwise_opContext bitwise_op() throws RecognitionException {
		Bitwise_opContext _localctx = new Bitwise_opContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bitwise_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BITWISE_AND) | (1L << BITWISE_OR) | (1L << BITWISE_EXCLUSIVE_OR))) != 0)) ) {
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

	public static class Binary_opContext extends ParserRuleContext {
		public Arithmetic_opContext arithmetic_op() {
			return getRuleContext(Arithmetic_opContext.class,0);
		}
		public Bitwise_opContext bitwise_op() {
			return getRuleContext(Bitwise_opContext.class,0);
		}
		public TerminalNode EXPONENITIATION() { return getToken(LPMLNParser.EXPONENITIATION, 0); }
		public Binary_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterBinary_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitBinary_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitBinary_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_opContext binary_op() throws RecognitionException {
		Binary_opContext _localctx = new Binary_opContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_binary_op);
		try {
			setState(91);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case STAR:
			case SLASH:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				arithmetic_op();
				}
				break;
			case BITWISE_AND:
			case BITWISE_OR:
			case BITWISE_EXCLUSIVE_OR:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				bitwise_op();
				}
				break;
			case EXPONENITIATION:
				enterOuterAlt(_localctx, 3);
				{
				setState(90);
				match(EXPONENITIATION);
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

	public static class Unary_opContext extends ParserRuleContext {
		public TerminalNode BITWISE_COMPLEMENT() { return getToken(LPMLNParser.BITWISE_COMPLEMENT, 0); }
		public Unary_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterUnary_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitUnary_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitUnary_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unary_opContext unary_op() throws RecognitionException {
		Unary_opContext _localctx = new Unary_opContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_unary_op);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(BITWISE_COMPLEMENT);
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

	public static class Bit_numberContext extends ParserRuleContext {
		public Natural_numberContext natural_number() {
			return getRuleContext(Natural_numberContext.class,0);
		}
		public Unary_opContext unary_op() {
			return getRuleContext(Unary_opContext.class,0);
		}
		public Bit_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bit_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterBit_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitBit_number(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitBit_number(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bit_numberContext bit_number() throws RecognitionException {
		Bit_numberContext _localctx = new Bit_numberContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_bit_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			_la = _input.LA(1);
			if (_la==BITWISE_COMPLEMENT) {
				{
				setState(95);
				unary_op();
				}
			}

			setState(98);
			natural_number();
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
		enterRule(_localctx, 16, RULE_relation_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
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
		public List<Binary_opContext> binary_op() {
			return getRuleContexts(Binary_opContext.class);
		}
		public Binary_opContext binary_op(int i) {
			return getRuleContext(Binary_opContext.class,i);
		}
		public List<Bit_numberContext> bit_number() {
			return getRuleContexts(Bit_numberContext.class);
		}
		public Bit_numberContext bit_number(int i) {
			return getRuleContext(Bit_numberContext.class,i);
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
		enterRule(_localctx, 18, RULE_simple_arithmetic_expr);
		int _la;
		try {
			int _alt;
			setState(124);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				integer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(104);
				_la = _input.LA(1);
				if (_la==MINUS) {
					{
					setState(103);
					match(MINUS);
					}
				}

				setState(106);
				match(VAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(112);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(107);
					integer();
					}
					break;
				case 2:
					{
					setState(109);
					_la = _input.LA(1);
					if (_la==MINUS) {
						{
						setState(108);
						match(MINUS);
						}
					}

					setState(111);
					match(VAR);
					}
					break;
				}
				setState(121);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(114);
						binary_op();
						setState(117);
						switch (_input.LA(1)) {
						case POSITIVE_INT:
						case ZERO:
						case BITWISE_COMPLEMENT:
							{
							setState(115);
							bit_number();
							}
							break;
						case VAR:
							{
							setState(116);
							match(VAR);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						} 
					}
					setState(123);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_simple_arithmetic_expr2, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			switch (_input.LA(1)) {
			case POSITIVE_INT:
			case ZERO:
			case VAR:
			case MINUS:
				{
				setState(127);
				simple_arithmetic_expr();
				}
				break;
			case LPAREN:
				{
				{
				setState(128);
				match(LPAREN);
				setState(129);
				simple_arithmetic_expr2(0);
				setState(130);
				match(RPAREN);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(140);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Simple_arithmetic_expr2Context(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_simple_arithmetic_expr2);
					setState(134);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(135);
					arithmetic_op();
					setState(136);
					simple_arithmetic_expr2(2);
					}
					} 
				}
				setState(142);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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
		enterRule(_localctx, 22, RULE_arithmethic_expr);
		try {
			setState(149);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(143);
				simple_arithmetic_expr2(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(144);
				match(MINUS);
				setState(145);
				match(LPAREN);
				setState(146);
				simple_arithmetic_expr2(0);
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
		enterRule(_localctx, 24, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(CONSTANT);
			setState(152);
			match(LPAREN);
			setState(153);
			term();
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(154);
				match(COMMA);
				setState(155);
				term();
				}
				}
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(161);
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
		public TerminalNode CONSTANT() { return getToken(LPMLNParser.CONSTANT, 0); }
		public TerminalNode STRING() { return getToken(LPMLNParser.STRING, 0); }
		public TerminalNode VAR() { return getToken(LPMLNParser.VAR, 0); }
		public SimpletermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleterm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterSimpleterm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitSimpleterm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitSimpleterm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpletermContext simpleterm() throws RecognitionException {
		SimpletermContext _localctx = new SimpletermContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_simpleterm);
		try {
			setState(167);
			switch (_input.LA(1)) {
			case POSITIVE_INT:
			case ZERO:
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(163);
				integer();
				}
				break;
			case CONSTANT:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
				match(CONSTANT);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(165);
				match(STRING);
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(166);
				match(VAR);
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
		public TerminalNode LPAREN() { return getToken(LPMLNParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(LPMLNParser.RPAREN, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LPMLNParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LPMLNParser.COMMA, i);
		}
		public TupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tuple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterTuple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitTuple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitTuple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TupleContext tuple() throws RecognitionException {
		TupleContext _localctx = new TupleContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(LPAREN);
			setState(183);
			switch (_input.LA(1)) {
			case RPAREN:
				{
				}
				break;
			case STRING:
			case POSITIVE_INT:
			case ZERO:
			case CONSTANT:
			case VAR:
			case MINUS:
			case LPAREN:
				{
				setState(171);
				term();
				setState(181);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					}
					break;
				case 2:
					{
					setState(173);
					match(COMMA);
					}
					break;
				case 3:
					{
					setState(178);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(174);
						match(COMMA);
						setState(175);
						term();
						}
						}
						setState(180);
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
			setState(185);
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
		public Arithmethic_exprContext arithmethic_expr() {
			return getRuleContext(Arithmethic_exprContext.class,0);
		}
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
		enterRule(_localctx, 30, RULE_term);
		try {
			setState(191);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(187);
				simpleterm();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(188);
				function();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(189);
				tuple();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(190);
				arithmethic_expr();
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
		enterRule(_localctx, 32, RULE_atom);
		int _la;
		try {
			setState(206);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				match(CONSTANT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				match(CONSTANT);
				setState(195);
				match(LPAREN);
				setState(196);
				term();
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(197);
					match(COMMA);
					setState(198);
					term();
					}
					}
					setState(203);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(204);
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
		enterRule(_localctx, 34, RULE_range_atom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(CONSTANT);
			setState(209);
			match(LPAREN);
			setState(210);
			integer();
			setState(211);
			match(RANGE);
			setState(212);
			integer();
			setState(213);
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
		public TerminalNode NAF_NOT() { return getToken(LPMLNParser.NAF_NOT, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
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
		enterRule(_localctx, 36, RULE_literal);
		try {
			setState(220);
			switch (_input.LA(1)) {
			case CONSTANT:
				enterOuterAlt(_localctx, 1);
				{
				setState(215);
				atom();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
				match(MINUS);
				setState(217);
				atom();
				}
				break;
			case NAF_NOT:
				enterOuterAlt(_localctx, 3);
				{
				setState(218);
				match(NAF_NOT);
				setState(219);
				literal();
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

	public static class Term_tupleContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LPMLNParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LPMLNParser.COMMA, i);
		}
		public Term_tupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term_tuple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterTerm_tuple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitTerm_tuple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitTerm_tuple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Term_tupleContext term_tuple() throws RecognitionException {
		Term_tupleContext _localctx = new Term_tupleContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_term_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			term();
			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(223);
				match(COMMA);
				setState(224);
				term();
				}
				}
				setState(229);
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

	public static class Literal_tupleContext extends ParserRuleContext {
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LPMLNParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LPMLNParser.COMMA, i);
		}
		public Literal_tupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_tuple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterLiteral_tuple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitLiteral_tuple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitLiteral_tuple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Literal_tupleContext literal_tuple() throws RecognitionException {
		Literal_tupleContext _localctx = new Literal_tupleContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_literal_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			literal();
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(231);
				match(COMMA);
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

	public static class Aggregate_elementsContext extends ParserRuleContext {
		public List<Literal_tupleContext> literal_tuple() {
			return getRuleContexts(Literal_tupleContext.class);
		}
		public Literal_tupleContext literal_tuple(int i) {
			return getRuleContext(Literal_tupleContext.class,i);
		}
		public List<Term_tupleContext> term_tuple() {
			return getRuleContexts(Term_tupleContext.class);
		}
		public Term_tupleContext term_tuple(int i) {
			return getRuleContext(Term_tupleContext.class,i);
		}
		public List<TerminalNode> CONDITION() { return getTokens(LPMLNParser.CONDITION); }
		public TerminalNode CONDITION(int i) {
			return getToken(LPMLNParser.CONDITION, i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(LPMLNParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(LPMLNParser.SEMICOLON, i);
		}
		public Aggregate_elementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregate_elements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterAggregate_elements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitAggregate_elements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitAggregate_elements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aggregate_elementsContext aggregate_elements() throws RecognitionException {
		Aggregate_elementsContext _localctx = new Aggregate_elementsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_aggregate_elements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(238);
				term_tuple();
				setState(239);
				match(CONDITION);
				}
				break;
			}
			setState(243);
			literal_tuple();
			setState(253);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(244);
				match(SEMICOLON);
				setState(248);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(245);
					term_tuple();
					setState(246);
					match(CONDITION);
					}
					break;
				}
				setState(250);
				literal_tuple();
				}
				}
				setState(255);
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

	public static class Aggregate_elements_conditionContext extends ParserRuleContext {
		public List<Literal_tupleContext> literal_tuple() {
			return getRuleContexts(Literal_tupleContext.class);
		}
		public Literal_tupleContext literal_tuple(int i) {
			return getRuleContext(Literal_tupleContext.class,i);
		}
		public List<TerminalNode> CONDITION() { return getTokens(LPMLNParser.CONDITION); }
		public TerminalNode CONDITION(int i) {
			return getToken(LPMLNParser.CONDITION, i);
		}
		public List<Term_tupleContext> term_tuple() {
			return getRuleContexts(Term_tupleContext.class);
		}
		public Term_tupleContext term_tuple(int i) {
			return getRuleContext(Term_tupleContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(LPMLNParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(LPMLNParser.SEMICOLON, i);
		}
		public Aggregate_elements_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregate_elements_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterAggregate_elements_condition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitAggregate_elements_condition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitAggregate_elements_condition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aggregate_elements_conditionContext aggregate_elements_condition() throws RecognitionException {
		Aggregate_elements_conditionContext _localctx = new Aggregate_elements_conditionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_aggregate_elements_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(256);
				term_tuple();
				setState(257);
				match(CONDITION);
				}
				break;
			}
			setState(261);
			literal_tuple();
			setState(262);
			match(CONDITION);
			setState(263);
			literal_tuple();
			setState(276);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(264);
				match(SEMICOLON);
				setState(268);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(265);
					term_tuple();
					setState(266);
					match(CONDITION);
					}
					break;
				}
				setState(270);
				literal_tuple();
				setState(271);
				match(CONDITION);
				setState(272);
				literal_tuple();
				}
				}
				setState(278);
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

	public static class Body_aggregateContext extends ParserRuleContext {
		public TerminalNode LCBRACK() { return getToken(LPMLNParser.LCBRACK, 0); }
		public Aggregate_elementsContext aggregate_elements() {
			return getRuleContext(Aggregate_elementsContext.class,0);
		}
		public TerminalNode RCBRACK() { return getToken(LPMLNParser.RCBRACK, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode AGGREGATE_OP() { return getToken(LPMLNParser.AGGREGATE_OP, 0); }
		public List<Relation_opContext> relation_op() {
			return getRuleContexts(Relation_opContext.class);
		}
		public Relation_opContext relation_op(int i) {
			return getRuleContext(Relation_opContext.class,i);
		}
		public Body_aggregateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body_aggregate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterBody_aggregate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitBody_aggregate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitBody_aggregate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Body_aggregateContext body_aggregate() throws RecognitionException {
		Body_aggregateContext _localctx = new Body_aggregateContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_body_aggregate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << POSITIVE_INT) | (1L << ZERO) | (1L << CONSTANT) | (1L << VAR) | (1L << MINUS) | (1L << LPAREN))) != 0)) {
				{
				setState(279);
				term();
				setState(281);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS_THAN) | (1L << LEQ) | (1L << GREATER_THAN) | (1L << GEQ) | (1L << EQUAL) | (1L << DOUBLE_EQUAL) | (1L << NEQ))) != 0)) {
					{
					setState(280);
					relation_op();
					}
				}

				}
			}

			setState(286);
			_la = _input.LA(1);
			if (_la==AGGREGATE_OP) {
				{
				setState(285);
				match(AGGREGATE_OP);
				}
			}

			setState(288);
			match(LCBRACK);
			setState(289);
			aggregate_elements();
			setState(290);
			match(RCBRACK);
			setState(295);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << POSITIVE_INT) | (1L << ZERO) | (1L << CONSTANT) | (1L << VAR) | (1L << MINUS) | (1L << LPAREN) | (1L << LESS_THAN) | (1L << LEQ) | (1L << GREATER_THAN) | (1L << GEQ) | (1L << EQUAL) | (1L << DOUBLE_EQUAL) | (1L << NEQ))) != 0)) {
				{
				setState(292);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS_THAN) | (1L << LEQ) | (1L << GREATER_THAN) | (1L << GEQ) | (1L << EQUAL) | (1L << DOUBLE_EQUAL) | (1L << NEQ))) != 0)) {
					{
					setState(291);
					relation_op();
					}
				}

				setState(294);
				term();
				}
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

	public static class Head_aggregateContext extends ParserRuleContext {
		public TerminalNode LCBRACK() { return getToken(LPMLNParser.LCBRACK, 0); }
		public Aggregate_elements_conditionContext aggregate_elements_condition() {
			return getRuleContext(Aggregate_elements_conditionContext.class,0);
		}
		public TerminalNode RCBRACK() { return getToken(LPMLNParser.RCBRACK, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode AGGREGATE_OP() { return getToken(LPMLNParser.AGGREGATE_OP, 0); }
		public List<Relation_opContext> relation_op() {
			return getRuleContexts(Relation_opContext.class);
		}
		public Relation_opContext relation_op(int i) {
			return getRuleContext(Relation_opContext.class,i);
		}
		public Head_aggregateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head_aggregate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterHead_aggregate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitHead_aggregate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitHead_aggregate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Head_aggregateContext head_aggregate() throws RecognitionException {
		Head_aggregateContext _localctx = new Head_aggregateContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_head_aggregate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << POSITIVE_INT) | (1L << ZERO) | (1L << CONSTANT) | (1L << VAR) | (1L << MINUS) | (1L << LPAREN))) != 0)) {
				{
				setState(297);
				term();
				setState(299);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS_THAN) | (1L << LEQ) | (1L << GREATER_THAN) | (1L << GEQ) | (1L << EQUAL) | (1L << DOUBLE_EQUAL) | (1L << NEQ))) != 0)) {
					{
					setState(298);
					relation_op();
					}
				}

				}
			}

			setState(304);
			_la = _input.LA(1);
			if (_la==AGGREGATE_OP) {
				{
				setState(303);
				match(AGGREGATE_OP);
				}
			}

			setState(306);
			match(LCBRACK);
			setState(307);
			aggregate_elements_condition();
			setState(308);
			match(RCBRACK);
			setState(313);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << POSITIVE_INT) | (1L << ZERO) | (1L << CONSTANT) | (1L << VAR) | (1L << MINUS) | (1L << LPAREN) | (1L << LESS_THAN) | (1L << LEQ) | (1L << GREATER_THAN) | (1L << GEQ) | (1L << EQUAL) | (1L << DOUBLE_EQUAL) | (1L << NEQ))) != 0)) {
				{
				setState(310);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS_THAN) | (1L << LEQ) | (1L << GREATER_THAN) | (1L << GEQ) | (1L << EQUAL) | (1L << DOUBLE_EQUAL) | (1L << NEQ))) != 0)) {
					{
					setState(309);
					relation_op();
					}
				}

				setState(312);
				term();
				}
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

	public static class Relation_exprContext extends ParserRuleContext {
		public Relation_opContext relation_op() {
			return getRuleContext(Relation_opContext.class,0);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> MINUS() { return getTokens(LPMLNParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(LPMLNParser.MINUS, i);
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
		enterRule(_localctx, 50, RULE_relation_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(316);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(315);
				match(MINUS);
				}
				break;
			}
			setState(318);
			term();
			}
			setState(320);
			relation_op();
			{
			setState(322);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(321);
				match(MINUS);
				}
				break;
			}
			setState(324);
			term();
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

	public static class HeadContext extends ParserRuleContext {
		public List<Head_literalContext> head_literal() {
			return getRuleContexts(Head_literalContext.class);
		}
		public Head_literalContext head_literal(int i) {
			return getRuleContext(Head_literalContext.class,i);
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
		enterRule(_localctx, 52, RULE_head);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			head_literal();
			setState(331);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DISJUNCTION) {
				{
				{
				setState(327);
				match(DISJUNCTION);
				setState(328);
				head_literal();
				}
				}
				setState(333);
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

	public static class Head_literalContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Head_aggregateContext head_aggregate() {
			return getRuleContext(Head_aggregateContext.class,0);
		}
		public TerminalNode NAF_NOT() { return getToken(LPMLNParser.NAF_NOT, 0); }
		public Head_literalContext head_literal() {
			return getRuleContext(Head_literalContext.class,0);
		}
		public Head_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterHead_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitHead_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitHead_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Head_literalContext head_literal() throws RecognitionException {
		Head_literalContext _localctx = new Head_literalContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_head_literal);
		try {
			setState(338);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(334);
				literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(335);
				head_aggregate();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(336);
				match(NAF_NOT);
				setState(337);
				head_literal();
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

	public static class BodyContext extends ParserRuleContext {
		public List<Body_literalContext> body_literal() {
			return getRuleContexts(Body_literalContext.class);
		}
		public Body_literalContext body_literal(int i) {
			return getRuleContext(Body_literalContext.class,i);
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
		enterRule(_localctx, 56, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			body_literal();
			setState(345);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(341);
				match(COMMA);
				setState(342);
				body_literal();
				}
				}
				setState(347);
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

	public static class Body_literalContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Relation_exprContext relation_expr() {
			return getRuleContext(Relation_exprContext.class,0);
		}
		public Body_aggregateContext body_aggregate() {
			return getRuleContext(Body_aggregateContext.class,0);
		}
		public TerminalNode NAF_NOT() { return getToken(LPMLNParser.NAF_NOT, 0); }
		public Body_literalContext body_literal() {
			return getRuleContext(Body_literalContext.class,0);
		}
		public Body_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterBody_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitBody_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitBody_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Body_literalContext body_literal() throws RecognitionException {
		Body_literalContext _localctx = new Body_literalContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_body_literal);
		try {
			setState(353);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(348);
				literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(349);
				relation_expr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(350);
				body_aggregate();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(351);
				match(NAF_NOT);
				setState(352);
				body_literal();
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
		enterRule(_localctx, 60, RULE_fact);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(355);
				head();
				}
				break;
			case 2:
				{
				setState(356);
				range_atom();
				}
				break;
			}
			setState(359);
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
		enterRule(_localctx, 62, RULE_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			match(ASSIGN);
			setState(362);
			body();
			setState(363);
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
		enterRule(_localctx, 64, RULE_full_rule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			head();
			setState(366);
			match(ASSIGN);
			setState(367);
			body();
			setState(368);
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
		enterRule(_localctx, 66, RULE_hard_rule);
		try {
			setState(373);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(370);
				fact();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(371);
				constraint();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(372);
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
		enterRule(_localctx, 68, RULE_soft_rule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(377);
			switch (_input.LA(1)) {
			case DECIMAL:
				{
				setState(375);
				match(DECIMAL);
				}
				break;
			case POSITIVE_INT:
			case ZERO:
			case MINUS:
				{
				setState(376);
				integer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(379);
			match(CONDITION);
			setState(380);
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
		enterRule(_localctx, 70, RULE_meta_rule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			match(META_OP);
			setState(384);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(383);
				match(MINUS);
				}
			}

			setState(386);
			match(CONSTANT);
			setState(387);
			match(SLASH);
			setState(388);
			natural_number();
			setState(389);
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
		enterRule(_localctx, 72, RULE_lpmln_rule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NAF_NOT) | (1L << STRING) | (1L << POSITIVE_INT) | (1L << DECIMAL) | (1L << ZERO) | (1L << CONSTANT) | (1L << VAR) | (1L << MINUS) | (1L << LPAREN) | (1L << LCBRACK) | (1L << ASSIGN) | (1L << AGGREGATE_OP) | (1L << META_OP))) != 0)) {
				{
				setState(394);
				switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
				case 1:
					{
					setState(391);
					hard_rule();
					}
					break;
				case 2:
					{
					setState(392);
					soft_rule();
					}
					break;
				case 3:
					{
					setState(393);
					meta_rule();
					}
					break;
				}
				}
				setState(398);
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
		case 10:
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3,\u0192\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\2\3\3\3\3\3\3\5\3S\n\3\3"+
		"\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\5\7^\n\7\3\b\3\b\3\t\5\tc\n\t\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\5\13k\n\13\3\13\3\13\3\13\5\13p\n\13\3\13\5\13s\n"+
		"\13\3\13\3\13\3\13\5\13x\n\13\7\13z\n\13\f\13\16\13}\13\13\5\13\177\n"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0087\n\f\3\f\3\f\3\f\3\f\7\f\u008d\n"+
		"\f\f\f\16\f\u0090\13\f\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0098\n\r\3\16\3\16"+
		"\3\16\3\16\3\16\7\16\u009f\n\16\f\16\16\16\u00a2\13\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\5\17\u00aa\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20"+
		"\u00b3\n\20\f\20\16\20\u00b6\13\20\5\20\u00b8\n\20\5\20\u00ba\n\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\5\21\u00c2\n\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\7\22\u00ca\n\22\f\22\16\22\u00cd\13\22\3\22\3\22\5\22\u00d1\n\22\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\5\24\u00df\n\24"+
		"\3\25\3\25\3\25\7\25\u00e4\n\25\f\25\16\25\u00e7\13\25\3\26\3\26\3\26"+
		"\7\26\u00ec\n\26\f\26\16\26\u00ef\13\26\3\27\3\27\3\27\5\27\u00f4\n\27"+
		"\3\27\3\27\3\27\3\27\3\27\5\27\u00fb\n\27\3\27\7\27\u00fe\n\27\f\27\16"+
		"\27\u0101\13\27\3\30\3\30\3\30\5\30\u0106\n\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\5\30\u010f\n\30\3\30\3\30\3\30\3\30\7\30\u0115\n\30\f\30\16"+
		"\30\u0118\13\30\3\31\3\31\5\31\u011c\n\31\5\31\u011e\n\31\3\31\5\31\u0121"+
		"\n\31\3\31\3\31\3\31\3\31\5\31\u0127\n\31\3\31\5\31\u012a\n\31\3\32\3"+
		"\32\5\32\u012e\n\32\5\32\u0130\n\32\3\32\5\32\u0133\n\32\3\32\3\32\3\32"+
		"\3\32\5\32\u0139\n\32\3\32\5\32\u013c\n\32\3\33\5\33\u013f\n\33\3\33\3"+
		"\33\3\33\3\33\5\33\u0145\n\33\3\33\3\33\3\34\3\34\3\34\7\34\u014c\n\34"+
		"\f\34\16\34\u014f\13\34\3\35\3\35\3\35\3\35\5\35\u0155\n\35\3\36\3\36"+
		"\3\36\7\36\u015a\n\36\f\36\16\36\u015d\13\36\3\37\3\37\3\37\3\37\3\37"+
		"\5\37\u0164\n\37\3 \3 \5 \u0168\n \3 \3 \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3"+
		"\"\3#\3#\3#\5#\u0178\n#\3$\3$\5$\u017c\n$\3$\3$\3$\3%\3%\5%\u0183\n%\3"+
		"%\3%\3%\3%\3%\3&\3&\3&\7&\u018d\n&\f&\16&\u0190\13&\3&\2\3\26\'\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJ\2\6\4"+
		"\2\6\6\b\b\3\2\20\23\3\2\f\16\3\2!\'\u01ad\2L\3\2\2\2\4R\3\2\2\2\6T\3"+
		"\2\2\2\bV\3\2\2\2\nX\3\2\2\2\f]\3\2\2\2\16_\3\2\2\2\20b\3\2\2\2\22f\3"+
		"\2\2\2\24~\3\2\2\2\26\u0086\3\2\2\2\30\u0097\3\2\2\2\32\u0099\3\2\2\2"+
		"\34\u00a9\3\2\2\2\36\u00ab\3\2\2\2 \u00c1\3\2\2\2\"\u00d0\3\2\2\2$\u00d2"+
		"\3\2\2\2&\u00de\3\2\2\2(\u00e0\3\2\2\2*\u00e8\3\2\2\2,\u00f3\3\2\2\2."+
		"\u0105\3\2\2\2\60\u011d\3\2\2\2\62\u012f\3\2\2\2\64\u013e\3\2\2\2\66\u0148"+
		"\3\2\2\28\u0154\3\2\2\2:\u0156\3\2\2\2<\u0163\3\2\2\2>\u0167\3\2\2\2@"+
		"\u016b\3\2\2\2B\u016f\3\2\2\2D\u0177\3\2\2\2F\u017b\3\2\2\2H\u0180\3\2"+
		"\2\2J\u018e\3\2\2\2LM\7\21\2\2MN\7\6\2\2N\3\3\2\2\2OS\7\6\2\2PS\5\2\2"+
		"\2QS\7\b\2\2RO\3\2\2\2RP\3\2\2\2RQ\3\2\2\2S\5\3\2\2\2TU\t\2\2\2U\7\3\2"+
		"\2\2VW\t\3\2\2W\t\3\2\2\2XY\t\4\2\2Y\13\3\2\2\2Z^\5\b\5\2[^\5\n\6\2\\"+
		"^\7\13\2\2]Z\3\2\2\2][\3\2\2\2]\\\3\2\2\2^\r\3\2\2\2_`\7\17\2\2`\17\3"+
		"\2\2\2ac\5\16\b\2ba\3\2\2\2bc\3\2\2\2cd\3\2\2\2de\5\6\4\2e\21\3\2\2\2"+
		"fg\t\5\2\2g\23\3\2\2\2h\177\5\4\3\2ik\7\21\2\2ji\3\2\2\2jk\3\2\2\2kl\3"+
		"\2\2\2l\177\7\n\2\2ms\5\4\3\2np\7\21\2\2on\3\2\2\2op\3\2\2\2pq\3\2\2\2"+
		"qs\7\n\2\2rm\3\2\2\2ro\3\2\2\2s{\3\2\2\2tw\5\f\7\2ux\5\20\t\2vx\7\n\2"+
		"\2wu\3\2\2\2wv\3\2\2\2xz\3\2\2\2yt\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2"+
		"\2|\177\3\2\2\2}{\3\2\2\2~h\3\2\2\2~j\3\2\2\2~r\3\2\2\2\177\25\3\2\2\2"+
		"\u0080\u0081\b\f\1\2\u0081\u0087\5\24\13\2\u0082\u0083\7\24\2\2\u0083"+
		"\u0084\5\26\f\2\u0084\u0085\7\25\2\2\u0085\u0087\3\2\2\2\u0086\u0080\3"+
		"\2\2\2\u0086\u0082\3\2\2\2\u0087\u008e\3\2\2\2\u0088\u0089\f\3\2\2\u0089"+
		"\u008a\5\b\5\2\u008a\u008b\5\26\f\4\u008b\u008d\3\2\2\2\u008c\u0088\3"+
		"\2\2\2\u008d\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f"+
		"\27\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0098\5\26\f\2\u0092\u0093\7\21"+
		"\2\2\u0093\u0094\7\24\2\2\u0094\u0095\5\26\f\2\u0095\u0096\7\25\2\2\u0096"+
		"\u0098\3\2\2\2\u0097\u0091\3\2\2\2\u0097\u0092\3\2\2\2\u0098\31\3\2\2"+
		"\2\u0099\u009a\7\t\2\2\u009a\u009b\7\24\2\2\u009b\u00a0\5 \21\2\u009c"+
		"\u009d\7\33\2\2\u009d\u009f\5 \21\2\u009e\u009c\3\2\2\2\u009f\u00a2\3"+
		"\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a3\3\2\2\2\u00a2"+
		"\u00a0\3\2\2\2\u00a3\u00a4\7\25\2\2\u00a4\33\3\2\2\2\u00a5\u00aa\5\4\3"+
		"\2\u00a6\u00aa\7\t\2\2\u00a7\u00aa\7\4\2\2\u00a8\u00aa\7\n\2\2\u00a9\u00a5"+
		"\3\2\2\2\u00a9\u00a6\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00a8\3\2\2\2\u00aa"+
		"\35\3\2\2\2\u00ab\u00b9\7\24\2\2\u00ac\u00ba\3\2\2\2\u00ad\u00b7\5 \21"+
		"\2\u00ae\u00b8\3\2\2\2\u00af\u00b8\7\33\2\2\u00b0\u00b1\7\33\2\2\u00b1"+
		"\u00b3\5 \21\2\u00b2\u00b0\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2"+
		"\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7"+
		"\u00ae\3\2\2\2\u00b7\u00af\3\2\2\2\u00b7\u00b4\3\2\2\2\u00b8\u00ba\3\2"+
		"\2\2\u00b9\u00ac\3\2\2\2\u00b9\u00ad\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb"+
		"\u00bc\7\25\2\2\u00bc\37\3\2\2\2\u00bd\u00c2\5\34\17\2\u00be\u00c2\5\32"+
		"\16\2\u00bf\u00c2\5\36\20\2\u00c0\u00c2\5\30\r\2\u00c1\u00bd\3\2\2\2\u00c1"+
		"\u00be\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c0\3\2\2\2\u00c2!\3\2\2\2"+
		"\u00c3\u00d1\7\t\2\2\u00c4\u00c5\7\t\2\2\u00c5\u00c6\7\24\2\2\u00c6\u00cb"+
		"\5 \21\2\u00c7\u00c8\7\33\2\2\u00c8\u00ca\5 \21\2\u00c9\u00c7\3\2\2\2"+
		"\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ce"+
		"\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00cf\7\25\2\2\u00cf\u00d1\3\2\2\2"+
		"\u00d0\u00c3\3\2\2\2\u00d0\u00c4\3\2\2\2\u00d1#\3\2\2\2\u00d2\u00d3\7"+
		"\t\2\2\u00d3\u00d4\7\24\2\2\u00d4\u00d5\5\4\3\2\u00d5\u00d6\7\32\2\2\u00d6"+
		"\u00d7\5\4\3\2\u00d7\u00d8\7\25\2\2\u00d8%\3\2\2\2\u00d9\u00df\5\"\22"+
		"\2\u00da\u00db\7\21\2\2\u00db\u00df\5\"\22\2\u00dc\u00dd\7\3\2\2\u00dd"+
		"\u00df\5&\24\2\u00de\u00d9\3\2\2\2\u00de\u00da\3\2\2\2\u00de\u00dc\3\2"+
		"\2\2\u00df\'\3\2\2\2\u00e0\u00e5\5 \21\2\u00e1\u00e2\7\33\2\2\u00e2\u00e4"+
		"\5 \21\2\u00e3\u00e1\3\2\2\2\u00e4\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5"+
		"\u00e6\3\2\2\2\u00e6)\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8\u00ed\5&\24\2"+
		"\u00e9\u00ea\7\33\2\2\u00ea\u00ec\5&\24\2\u00eb\u00e9\3\2\2\2\u00ec\u00ef"+
		"\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee+\3\2\2\2\u00ef"+
		"\u00ed\3\2\2\2\u00f0\u00f1\5(\25\2\u00f1\u00f2\7\35\2\2\u00f2\u00f4\3"+
		"\2\2\2\u00f3\u00f0\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5"+
		"\u00ff\5*\26\2\u00f6\u00fa\7 \2\2\u00f7\u00f8\5(\25\2\u00f8\u00f9\7\35"+
		"\2\2\u00f9\u00fb\3\2\2\2\u00fa\u00f7\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb"+
		"\u00fc\3\2\2\2\u00fc\u00fe\5*\26\2\u00fd\u00f6\3\2\2\2\u00fe\u0101\3\2"+
		"\2\2\u00ff\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100-\3\2\2\2\u0101\u00ff"+
		"\3\2\2\2\u0102\u0103\5(\25\2\u0103\u0104\7\35\2\2\u0104\u0106\3\2\2\2"+
		"\u0105\u0102\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0108"+
		"\5*\26\2\u0108\u0109\7\35\2\2\u0109\u0116\5*\26\2\u010a\u010e\7 \2\2\u010b"+
		"\u010c\5(\25\2\u010c\u010d\7\35\2\2\u010d\u010f\3\2\2\2\u010e\u010b\3"+
		"\2\2\2\u010e\u010f\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0111\5*\26\2\u0111"+
		"\u0112\7\35\2\2\u0112\u0113\5*\26\2\u0113\u0115\3\2\2\2\u0114\u010a\3"+
		"\2\2\2\u0115\u0118\3\2\2\2\u0116\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117"+
		"/\3\2\2\2\u0118\u0116\3\2\2\2\u0119\u011b\5 \21\2\u011a\u011c\5\22\n\2"+
		"\u011b\u011a\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011e\3\2\2\2\u011d\u0119"+
		"\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u0120\3\2\2\2\u011f\u0121\7(\2\2\u0120"+
		"\u011f\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0123\7\30"+
		"\2\2\u0123\u0124\5,\27\2\u0124\u0129\7\31\2\2\u0125\u0127\5\22\n\2\u0126"+
		"\u0125\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u012a\5 "+
		"\21\2\u0129\u0126\3\2\2\2\u0129\u012a\3\2\2\2\u012a\61\3\2\2\2\u012b\u012d"+
		"\5 \21\2\u012c\u012e\5\22\n\2\u012d\u012c\3\2\2\2\u012d\u012e\3\2\2\2"+
		"\u012e\u0130\3\2\2\2\u012f\u012b\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0132"+
		"\3\2\2\2\u0131\u0133\7(\2\2\u0132\u0131\3\2\2\2\u0132\u0133\3\2\2\2\u0133"+
		"\u0134\3\2\2\2\u0134\u0135\7\30\2\2\u0135\u0136\5.\30\2\u0136\u013b\7"+
		"\31\2\2\u0137\u0139\5\22\n\2\u0138\u0137\3\2\2\2\u0138\u0139\3\2\2\2\u0139"+
		"\u013a\3\2\2\2\u013a\u013c\5 \21\2\u013b\u0138\3\2\2\2\u013b\u013c\3\2"+
		"\2\2\u013c\63\3\2\2\2\u013d\u013f\7\21\2\2\u013e\u013d\3\2\2\2\u013e\u013f"+
		"\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0141\5 \21\2\u0141\u0142\3\2\2\2\u0142"+
		"\u0144\5\22\n\2\u0143\u0145\7\21\2\2\u0144\u0143\3\2\2\2\u0144\u0145\3"+
		"\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147\5 \21\2\u0147\65\3\2\2\2\u0148"+
		"\u014d\58\35\2\u0149\u014a\7\34\2\2\u014a\u014c\58\35\2\u014b\u0149\3"+
		"\2\2\2\u014c\u014f\3\2\2\2\u014d\u014b\3\2\2\2\u014d\u014e\3\2\2\2\u014e"+
		"\67\3\2\2\2\u014f\u014d\3\2\2\2\u0150\u0155\5&\24\2\u0151\u0155\5\62\32"+
		"\2\u0152\u0153\7\3\2\2\u0153\u0155\58\35\2\u0154\u0150\3\2\2\2\u0154\u0151"+
		"\3\2\2\2\u0154\u0152\3\2\2\2\u01559\3\2\2\2\u0156\u015b\5<\37\2\u0157"+
		"\u0158\7\33\2\2\u0158\u015a\5<\37\2\u0159\u0157\3\2\2\2\u015a\u015d\3"+
		"\2\2\2\u015b\u0159\3\2\2\2\u015b\u015c\3\2\2\2\u015c;\3\2\2\2\u015d\u015b"+
		"\3\2\2\2\u015e\u0164\5&\24\2\u015f\u0164\5\64\33\2\u0160\u0164\5\60\31"+
		"\2\u0161\u0162\7\3\2\2\u0162\u0164\5<\37\2\u0163\u015e\3\2\2\2\u0163\u015f"+
		"\3\2\2\2\u0163\u0160\3\2\2\2\u0163\u0161\3\2\2\2\u0164=\3\2\2\2\u0165"+
		"\u0168\5\66\34\2\u0166\u0168\5$\23\2\u0167\u0165\3\2\2\2\u0167\u0166\3"+
		"\2\2\2\u0168\u0169\3\2\2\2\u0169\u016a\7\5\2\2\u016a?\3\2\2\2\u016b\u016c"+
		"\7\36\2\2\u016c\u016d\5:\36\2\u016d\u016e\7\5\2\2\u016eA\3\2\2\2\u016f"+
		"\u0170\5\66\34\2\u0170\u0171\7\36\2\2\u0171\u0172\5:\36\2\u0172\u0173"+
		"\7\5\2\2\u0173C\3\2\2\2\u0174\u0178\5> \2\u0175\u0178\5@!\2\u0176\u0178"+
		"\5B\"\2\u0177\u0174\3\2\2\2\u0177\u0175\3\2\2\2\u0177\u0176\3\2\2\2\u0178"+
		"E\3\2\2\2\u0179\u017c\7\7\2\2\u017a\u017c\5\4\3\2\u017b\u0179\3\2\2\2"+
		"\u017b\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017e\7\35\2\2\u017e\u017f"+
		"\5D#\2\u017fG\3\2\2\2\u0180\u0182\7)\2\2\u0181\u0183\7\21\2\2\u0182\u0181"+
		"\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u0184\3\2\2\2\u0184\u0185\7\t\2\2\u0185"+
		"\u0186\7\23\2\2\u0186\u0187\5\6\4\2\u0187\u0188\7\5\2\2\u0188I\3\2\2\2"+
		"\u0189\u018d\5D#\2\u018a\u018d\5F$\2\u018b\u018d\5H%\2\u018c\u0189\3\2"+
		"\2\2\u018c\u018a\3\2\2\2\u018c\u018b\3\2\2\2\u018d\u0190\3\2\2\2\u018e"+
		"\u018c\3\2\2\2\u018e\u018f\3\2\2\2\u018fK\3\2\2\2\u0190\u018e\3\2\2\2"+
		"\65R]bjorw{~\u0086\u008e\u0097\u00a0\u00a9\u00b4\u00b7\u00b9\u00c1\u00cb"+
		"\u00d0\u00de\u00e5\u00ed\u00f3\u00fa\u00ff\u0105\u010e\u0116\u011b\u011d"+
		"\u0120\u0126\u0129\u012d\u012f\u0132\u0138\u013b\u013e\u0144\u014d\u0154"+
		"\u015b\u0163\u0167\u0177\u017b\u0182\u018c\u018e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}