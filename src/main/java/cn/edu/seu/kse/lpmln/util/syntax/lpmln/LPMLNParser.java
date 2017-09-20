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
		NAF_NOT=1, STRING=2, FULLSTOP=3, POSITIVE_INT=4, DECIMAL=5, ZERO=6, IDENTIFIER=7, 
		VAR=8, EXPONENITIATION=9, BITWISE_AND=10, BITWISE_OR=11, BITWISE_EXCLUSIVE_OR=12, 
		BITWISE_COMPLEMENT=13, PLUS=14, MINUS=15, STAR=16, SLASH=17, BACKSLASH=18, 
		LPAREN=19, RPAREN=20, LSBRACK=21, RSBRACK=22, LCBRACK=23, RCBRACK=24, 
		RANGE=25, COMMA=26, DISJUNCTION=27, CONDITION=28, ASSIGN=29, WEAK_ASSIGN=30, 
		SEMICOLON=31, LESS_THAN=32, LEQ=33, GREATER_THAN=34, GEQ=35, EQUAL=36, 
		DOUBLE_EQUAL=37, NEQ=38, AGGREGATE_OP=39, META_OP=40, LINE_COMMENT=41, 
		WS=42, BOOL_CONSTANTS=43;
	public static final int
		RULE_constant = 0, RULE_negative_int = 1, RULE_integer = 2, RULE_natural_number = 3, 
		RULE_arithmetic_op = 4, RULE_bitwise_op = 5, RULE_binary_op = 6, RULE_unary_op = 7, 
		RULE_bit_number = 8, RULE_relation_op = 9, RULE_simple_arithmetic_expr = 10, 
		RULE_simple_arithmetic_expr2 = 11, RULE_arithmethic_expr = 12, RULE_function = 13, 
		RULE_simpleterm = 14, RULE_tuple = 15, RULE_pooling = 16, RULE_term = 17, 
		RULE_atom = 18, RULE_literal = 19, RULE_term_tuple = 20, RULE_literal_tuple = 21, 
		RULE_aggregate_elements = 22, RULE_aggregate_elements_condition = 23, 
		RULE_body_aggregate = 24, RULE_head_aggregate = 25, RULE_comparison_literal = 26, 
		RULE_head = 27, RULE_head_literal = 28, RULE_condition_literal = 29, RULE_body = 30, 
		RULE_body_literal = 31, RULE_fact = 32, RULE_constraint = 33, RULE_full_rule = 34, 
		RULE_hard_rule = 35, RULE_soft_rule = 36, RULE_meta_rule = 37, RULE_lpmln_rule = 38;
	public static final String[] ruleNames = {
		"constant", "negative_int", "integer", "natural_number", "arithmetic_op", 
		"bitwise_op", "binary_op", "unary_op", "bit_number", "relation_op", "simple_arithmetic_expr", 
		"simple_arithmetic_expr2", "arithmethic_expr", "function", "simpleterm", 
		"tuple", "pooling", "term", "atom", "literal", "term_tuple", "literal_tuple", 
		"aggregate_elements", "aggregate_elements_condition", "body_aggregate", 
		"head_aggregate", "comparison_literal", "head", "head_literal", "condition_literal", 
		"body", "body_literal", "fact", "constraint", "full_rule", "hard_rule", 
		"soft_rule", "meta_rule", "lpmln_rule"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'not '", null, "'.'", null, null, "'0'", null, null, "'**'", "'&'", 
		"'?'", "'^'", "'~'", "'+'", "'-'", "'*'", "'/'", "'\\'", "'('", "')'", 
		"'['", "']'", "'{'", "'}'", "'..'", "','", "'|'", "':'", "':-'", "':~'", 
		"';'", "'<'", "'<='", "'>'", "'>='", "'='", "'=='", "'!='", null, "'#show '"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "NAF_NOT", "STRING", "FULLSTOP", "POSITIVE_INT", "DECIMAL", "ZERO", 
		"IDENTIFIER", "VAR", "EXPONENITIATION", "BITWISE_AND", "BITWISE_OR", "BITWISE_EXCLUSIVE_OR", 
		"BITWISE_COMPLEMENT", "PLUS", "MINUS", "STAR", "SLASH", "BACKSLASH", "LPAREN", 
		"RPAREN", "LSBRACK", "RSBRACK", "LCBRACK", "RCBRACK", "RANGE", "COMMA", 
		"DISJUNCTION", "CONDITION", "ASSIGN", "WEAK_ASSIGN", "SEMICOLON", "LESS_THAN", 
		"LEQ", "GREATER_THAN", "GEQ", "EQUAL", "DOUBLE_EQUAL", "NEQ", "AGGREGATE_OP", 
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
	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LPMLNParser.IDENTIFIER, 0); }
		public TerminalNode BOOL_CONSTANTS() { return getToken(LPMLNParser.BOOL_CONSTANTS, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			_la = _input.LA(1);
			if ( !(_la==IDENTIFIER || _la==BOOL_CONSTANTS) ) {
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
		enterRule(_localctx, 2, RULE_negative_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(MINUS);
			setState(81);
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
		enterRule(_localctx, 4, RULE_integer);
		try {
			setState(86);
			switch (_input.LA(1)) {
			case POSITIVE_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				match(POSITIVE_INT);
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				negative_int();
				}
				break;
			case ZERO:
				enterOuterAlt(_localctx, 3);
				{
				setState(85);
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
		enterRule(_localctx, 6, RULE_natural_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
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
		public TerminalNode BACKSLASH() { return getToken(LPMLNParser.BACKSLASH, 0); }
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
		enterRule(_localctx, 8, RULE_arithmetic_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << BACKSLASH))) != 0)) ) {
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
		enterRule(_localctx, 10, RULE_bitwise_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
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
		public TerminalNode RANGE() { return getToken(LPMLNParser.RANGE, 0); }
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
		enterRule(_localctx, 12, RULE_binary_op);
		try {
			setState(98);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case STAR:
			case SLASH:
			case BACKSLASH:
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				arithmetic_op();
				}
				break;
			case BITWISE_AND:
			case BITWISE_OR:
			case BITWISE_EXCLUSIVE_OR:
				enterOuterAlt(_localctx, 2);
				{
				setState(95);
				bitwise_op();
				}
				break;
			case EXPONENITIATION:
				enterOuterAlt(_localctx, 3);
				{
				setState(96);
				match(EXPONENITIATION);
				}
				break;
			case RANGE:
				enterOuterAlt(_localctx, 4);
				{
				setState(97);
				match(RANGE);
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
		enterRule(_localctx, 14, RULE_unary_op);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
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
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
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
		enterRule(_localctx, 16, RULE_bit_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			_la = _input.LA(1);
			if (_la==BITWISE_COMPLEMENT) {
				{
				setState(102);
				unary_op();
				}
			}

			setState(105);
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

	public static class Relation_opContext extends ParserRuleContext {
		public TerminalNode LESS_THAN() { return getToken(LPMLNParser.LESS_THAN, 0); }
		public TerminalNode LEQ() { return getToken(LPMLNParser.LEQ, 0); }
		public TerminalNode GREATER_THAN() { return getToken(LPMLNParser.GREATER_THAN, 0); }
		public TerminalNode GEQ() { return getToken(LPMLNParser.GEQ, 0); }
		public TerminalNode EQUAL() { return getToken(LPMLNParser.EQUAL, 0); }
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
		enterRule(_localctx, 18, RULE_relation_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS_THAN) | (1L << LEQ) | (1L << GREATER_THAN) | (1L << GEQ) | (1L << EQUAL) | (1L << NEQ))) != 0)) ) {
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
		public List<IntegerContext> integer() {
			return getRuleContexts(IntegerContext.class);
		}
		public IntegerContext integer(int i) {
			return getRuleContext(IntegerContext.class,i);
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
		public Unary_opContext unary_op() {
			return getRuleContext(Unary_opContext.class,0);
		}
		public Simple_arithmetic_exprContext simple_arithmetic_expr() {
			return getRuleContext(Simple_arithmetic_exprContext.class,0);
		}
		public List<TerminalNode> DISJUNCTION() { return getTokens(LPMLNParser.DISJUNCTION); }
		public TerminalNode DISJUNCTION(int i) {
			return getToken(LPMLNParser.DISJUNCTION, i);
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
		enterRule(_localctx, 20, RULE_simple_arithmetic_expr);
		int _la;
		try {
			int _alt;
			setState(138);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				integer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				_la = _input.LA(1);
				if (_la==MINUS) {
					{
					setState(110);
					match(MINUS);
					}
				}

				setState(113);
				match(VAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(119);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(114);
					integer();
					}
					break;
				case 2:
					{
					setState(116);
					_la = _input.LA(1);
					if (_la==MINUS) {
						{
						setState(115);
						match(MINUS);
						}
					}

					setState(118);
					match(VAR);
					}
					break;
				}
				setState(128);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(121);
						binary_op();
						setState(124);
						switch (_input.LA(1)) {
						case POSITIVE_INT:
						case ZERO:
						case MINUS:
							{
							setState(122);
							integer();
							}
							break;
						case VAR:
							{
							setState(123);
							match(VAR);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						} 
					}
					setState(130);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(131);
				unary_op();
				setState(132);
				simple_arithmetic_expr();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(134);
				match(DISJUNCTION);
				setState(135);
				simple_arithmetic_expr();
				setState(136);
				match(DISJUNCTION);
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
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_simple_arithmetic_expr2, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			switch (_input.LA(1)) {
			case POSITIVE_INT:
			case ZERO:
			case VAR:
			case BITWISE_COMPLEMENT:
			case MINUS:
			case DISJUNCTION:
				{
				setState(141);
				simple_arithmetic_expr();
				}
				break;
			case LPAREN:
				{
				{
				setState(142);
				match(LPAREN);
				setState(143);
				simple_arithmetic_expr2(0);
				setState(144);
				match(RPAREN);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(154);
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
					setState(148);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(149);
					arithmetic_op();
					setState(150);
					simple_arithmetic_expr2(2);
					}
					} 
				}
				setState(156);
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
		enterRule(_localctx, 24, RULE_arithmethic_expr);
		try {
			setState(163);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				simple_arithmetic_expr2(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				match(MINUS);
				setState(159);
				match(LPAREN);
				setState(160);
				simple_arithmetic_expr2(0);
				setState(161);
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
		public TerminalNode IDENTIFIER() { return getToken(LPMLNParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(LPMLNParser.LPAREN, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LPMLNParser.RPAREN, 0); }
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
		enterRule(_localctx, 26, RULE_function);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			match(IDENTIFIER);
			setState(166);
			match(LPAREN);
			setState(167);
			term();
			setState(168);
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
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
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
		enterRule(_localctx, 28, RULE_simpleterm);
		try {
			setState(174);
			switch (_input.LA(1)) {
			case POSITIVE_INT:
			case ZERO:
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(170);
				integer();
				}
				break;
			case IDENTIFIER:
			case BOOL_CONSTANTS:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				constant();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(172);
				match(STRING);
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(173);
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
		public List<TerminalNode> COMMA() { return getTokens(LPMLNParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LPMLNParser.COMMA, i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
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
		enterRule(_localctx, 30, RULE_tuple);
		try {
			int _alt;
			setState(184);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				match(COMMA);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(180); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(178);
						match(COMMA);
						setState(179);
						term();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(182); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class PoolingContext extends ParserRuleContext {
		public List<TerminalNode> SEMICOLON() { return getTokens(LPMLNParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(LPMLNParser.SEMICOLON, i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public PoolingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pooling; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterPooling(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitPooling(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitPooling(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PoolingContext pooling() throws RecognitionException {
		PoolingContext _localctx = new PoolingContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_pooling);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(188); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(186);
					match(SEMICOLON);
					setState(187);
					term();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(190); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
		public Arithmethic_exprContext arithmethic_expr() {
			return getRuleContext(Arithmethic_exprContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(LPMLNParser.LPAREN, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LPMLNParser.RPAREN, 0); }
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public PoolingContext pooling() {
			return getRuleContext(PoolingContext.class,0);
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
		enterRule(_localctx, 34, RULE_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(192);
				simpleterm();
				}
				break;
			case 2:
				{
				setState(193);
				function();
				}
				break;
			case 3:
				{
				setState(194);
				arithmethic_expr();
				}
				break;
			case 4:
				{
				setState(195);
				match(LPAREN);
				setState(196);
				term();
				setState(197);
				match(RPAREN);
				}
				break;
			}
			setState(203);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(201);
				tuple();
				}
				break;
			case 2:
				{
				setState(202);
				pooling();
				}
				break;
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

	public static class AtomContext extends ParserRuleContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(LPMLNParser.IDENTIFIER, 0); }
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
		enterRule(_localctx, 36, RULE_atom);
		int _la;
		try {
			setState(218);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(205);
				constant();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(206);
				match(IDENTIFIER);
				setState(207);
				match(LPAREN);
				setState(208);
				term();
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(209);
					match(COMMA);
					setState(210);
					term();
					}
					}
					setState(215);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(216);
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
		public TerminalNode MINUS() { return getToken(LPMLNParser.MINUS, 0); }
		public TerminalNode NAF_NOT() { return getToken(LPMLNParser.NAF_NOT, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Comparison_literalContext comparison_literal() {
			return getRuleContext(Comparison_literalContext.class,0);
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
		enterRule(_localctx, 38, RULE_literal);
		try {
			setState(226);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(220);
				atom();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(221);
				match(MINUS);
				setState(222);
				atom();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(223);
				match(NAF_NOT);
				setState(224);
				literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(225);
				comparison_literal();
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
		enterRule(_localctx, 40, RULE_term_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			term();
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(229);
				match(COMMA);
				setState(230);
				term();
				}
				}
				setState(235);
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
		enterRule(_localctx, 42, RULE_literal_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			literal();
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(237);
				match(COMMA);
				setState(238);
				literal();
				}
				}
				setState(243);
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
		enterRule(_localctx, 44, RULE_aggregate_elements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(244);
				term_tuple();
				setState(245);
				match(CONDITION);
				}
				break;
			}
			setState(249);
			literal_tuple();
			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(250);
				match(SEMICOLON);
				setState(254);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(251);
					term_tuple();
					setState(252);
					match(CONDITION);
					}
					break;
				}
				setState(256);
				literal_tuple();
				}
				}
				setState(261);
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
		enterRule(_localctx, 46, RULE_aggregate_elements_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(262);
				term_tuple();
				setState(263);
				match(CONDITION);
				}
				break;
			}
			setState(267);
			literal_tuple();
			setState(268);
			match(CONDITION);
			setState(269);
			literal_tuple();
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(270);
				match(SEMICOLON);
				setState(274);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(271);
					term_tuple();
					setState(272);
					match(CONDITION);
					}
					break;
				}
				setState(276);
				literal_tuple();
				setState(277);
				match(CONDITION);
				setState(278);
				literal_tuple();
				}
				}
				setState(284);
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
		enterRule(_localctx, 48, RULE_body_aggregate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << POSITIVE_INT) | (1L << ZERO) | (1L << IDENTIFIER) | (1L << VAR) | (1L << BITWISE_COMPLEMENT) | (1L << MINUS) | (1L << LPAREN) | (1L << DISJUNCTION) | (1L << BOOL_CONSTANTS))) != 0)) {
				{
				setState(285);
				term();
				setState(287);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS_THAN) | (1L << LEQ) | (1L << GREATER_THAN) | (1L << GEQ) | (1L << EQUAL) | (1L << NEQ))) != 0)) {
					{
					setState(286);
					relation_op();
					}
				}

				}
			}

			setState(292);
			_la = _input.LA(1);
			if (_la==AGGREGATE_OP) {
				{
				setState(291);
				match(AGGREGATE_OP);
				}
			}

			setState(294);
			match(LCBRACK);
			setState(295);
			aggregate_elements();
			setState(296);
			match(RCBRACK);
			setState(301);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << POSITIVE_INT) | (1L << ZERO) | (1L << IDENTIFIER) | (1L << VAR) | (1L << BITWISE_COMPLEMENT) | (1L << MINUS) | (1L << LPAREN) | (1L << DISJUNCTION) | (1L << LESS_THAN) | (1L << LEQ) | (1L << GREATER_THAN) | (1L << GEQ) | (1L << EQUAL) | (1L << NEQ) | (1L << BOOL_CONSTANTS))) != 0)) {
				{
				setState(298);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS_THAN) | (1L << LEQ) | (1L << GREATER_THAN) | (1L << GEQ) | (1L << EQUAL) | (1L << NEQ))) != 0)) {
					{
					setState(297);
					relation_op();
					}
				}

				setState(300);
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
		enterRule(_localctx, 50, RULE_head_aggregate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << POSITIVE_INT) | (1L << ZERO) | (1L << IDENTIFIER) | (1L << VAR) | (1L << BITWISE_COMPLEMENT) | (1L << MINUS) | (1L << LPAREN) | (1L << DISJUNCTION) | (1L << BOOL_CONSTANTS))) != 0)) {
				{
				setState(303);
				term();
				setState(305);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS_THAN) | (1L << LEQ) | (1L << GREATER_THAN) | (1L << GEQ) | (1L << EQUAL) | (1L << NEQ))) != 0)) {
					{
					setState(304);
					relation_op();
					}
				}

				}
			}

			setState(310);
			_la = _input.LA(1);
			if (_la==AGGREGATE_OP) {
				{
				setState(309);
				match(AGGREGATE_OP);
				}
			}

			setState(312);
			match(LCBRACK);
			setState(313);
			aggregate_elements_condition();
			setState(314);
			match(RCBRACK);
			setState(319);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(316);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS_THAN) | (1L << LEQ) | (1L << GREATER_THAN) | (1L << GEQ) | (1L << EQUAL) | (1L << NEQ))) != 0)) {
					{
					setState(315);
					relation_op();
					}
				}

				setState(318);
				term();
				}
				break;
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

	public static class Comparison_literalContext extends ParserRuleContext {
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
		public Comparison_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterComparison_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitComparison_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitComparison_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Comparison_literalContext comparison_literal() throws RecognitionException {
		Comparison_literalContext _localctx = new Comparison_literalContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_comparison_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(322);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
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
			setState(326);
			relation_op();
			{
			setState(328);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(327);
				match(MINUS);
				}
				break;
			}
			setState(330);
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
		enterRule(_localctx, 54, RULE_head);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(337);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(332);
					head_literal();
					setState(333);
					match(DISJUNCTION);
					}
					} 
				}
				setState(339);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			}
			setState(340);
			head_literal();
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
		public Condition_literalContext condition_literal() {
			return getRuleContext(Condition_literalContext.class,0);
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
		enterRule(_localctx, 56, RULE_head_literal);
		try {
			setState(347);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(342);
				literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(343);
				head_aggregate();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(344);
				match(NAF_NOT);
				setState(345);
				head_literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(346);
				condition_literal();
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

	public static class Condition_literalContext extends ParserRuleContext {
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public TerminalNode CONDITION() { return getToken(LPMLNParser.CONDITION, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LPMLNParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LPMLNParser.COMMA, i);
		}
		public Condition_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).enterCondition_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPMLNListener ) ((LPMLNListener)listener).exitCondition_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPMLNVisitor ) return ((LPMLNVisitor<? extends T>)visitor).visitCondition_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Condition_literalContext condition_literal() throws RecognitionException {
		Condition_literalContext _localctx = new Condition_literalContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_condition_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			literal();
			setState(350);
			match(CONDITION);
			setState(351);
			literal();
			setState(356);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(352);
				match(COMMA);
				setState(353);
				literal();
				}
				}
				setState(358);
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
		public List<Body_literalContext> body_literal() {
			return getRuleContexts(Body_literalContext.class);
		}
		public Body_literalContext body_literal(int i) {
			return getRuleContext(Body_literalContext.class,i);
		}
		public List<Condition_literalContext> condition_literal() {
			return getRuleContexts(Condition_literalContext.class);
		}
		public Condition_literalContext condition_literal(int i) {
			return getRuleContext(Condition_literalContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LPMLNParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LPMLNParser.COMMA, i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(LPMLNParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(LPMLNParser.SEMICOLON, i);
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
		enterRule(_localctx, 60, RULE_body);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(367);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(365);
					switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
					case 1:
						{
						setState(359);
						body_literal();
						setState(360);
						match(COMMA);
						}
						break;
					case 2:
						{
						setState(362);
						condition_literal();
						setState(363);
						match(SEMICOLON);
						}
						break;
					}
					} 
				}
				setState(369);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			}
			setState(372);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(370);
				body_literal();
				}
				break;
			case 2:
				{
				setState(371);
				condition_literal();
				}
				break;
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
		enterRule(_localctx, 62, RULE_body_literal);
		try {
			setState(378);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(374);
				literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(375);
				body_aggregate();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(376);
				match(NAF_NOT);
				setState(377);
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
		public HeadContext head() {
			return getRuleContext(HeadContext.class,0);
		}
		public TerminalNode FULLSTOP() { return getToken(LPMLNParser.FULLSTOP, 0); }
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
		enterRule(_localctx, 64, RULE_fact);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
			head();
			setState(381);
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
		enterRule(_localctx, 66, RULE_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			match(ASSIGN);
			setState(384);
			body();
			setState(385);
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
		enterRule(_localctx, 68, RULE_full_rule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			head();
			setState(388);
			match(ASSIGN);
			setState(389);
			body();
			setState(390);
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
		enterRule(_localctx, 70, RULE_hard_rule);
		try {
			setState(395);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(392);
				fact();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(393);
				constraint();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(394);
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
		enterRule(_localctx, 72, RULE_soft_rule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
			switch (_input.LA(1)) {
			case DECIMAL:
				{
				setState(397);
				match(DECIMAL);
				}
				break;
			case POSITIVE_INT:
			case ZERO:
			case MINUS:
				{
				setState(398);
				integer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(401);
			match(CONDITION);
			setState(402);
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
		public TerminalNode IDENTIFIER() { return getToken(LPMLNParser.IDENTIFIER, 0); }
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
		enterRule(_localctx, 74, RULE_meta_rule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
			match(META_OP);
			setState(406);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(405);
				match(MINUS);
				}
			}

			setState(408);
			match(IDENTIFIER);
			setState(409);
			match(SLASH);
			setState(410);
			natural_number();
			setState(411);
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
		enterRule(_localctx, 76, RULE_lpmln_rule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NAF_NOT) | (1L << STRING) | (1L << POSITIVE_INT) | (1L << DECIMAL) | (1L << ZERO) | (1L << IDENTIFIER) | (1L << VAR) | (1L << BITWISE_COMPLEMENT) | (1L << MINUS) | (1L << LPAREN) | (1L << LCBRACK) | (1L << DISJUNCTION) | (1L << ASSIGN) | (1L << AGGREGATE_OP) | (1L << META_OP) | (1L << BOOL_CONSTANTS))) != 0)) {
				{
				setState(416);
				switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
				case 1:
					{
					setState(413);
					hard_rule();
					}
					break;
				case 2:
					{
					setState(414);
					soft_rule();
					}
					break;
				case 3:
					{
					setState(415);
					meta_rule();
					}
					break;
				}
				}
				setState(420);
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
		case 11:
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3-\u01a8\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\3\2\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\5\4Y\n\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\5\be\n\b"+
		"\3\t\3\t\3\n\5\nj\n\n\3\n\3\n\3\13\3\13\3\f\3\f\5\fr\n\f\3\f\3\f\3\f\5"+
		"\fw\n\f\3\f\5\fz\n\f\3\f\3\f\3\f\5\f\177\n\f\7\f\u0081\n\f\f\f\16\f\u0084"+
		"\13\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u008d\n\f\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\5\r\u0095\n\r\3\r\3\r\3\r\3\r\7\r\u009b\n\r\f\r\16\r\u009e\13\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\5\16\u00a6\n\16\3\17\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\5\20\u00b1\n\20\3\21\3\21\3\21\3\21\6\21\u00b7\n\21\r"+
		"\21\16\21\u00b8\5\21\u00bb\n\21\3\22\3\22\6\22\u00bf\n\22\r\22\16\22\u00c0"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00ca\n\23\3\23\3\23\5\23\u00ce"+
		"\n\23\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u00d6\n\24\f\24\16\24\u00d9\13"+
		"\24\3\24\3\24\5\24\u00dd\n\24\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u00e5"+
		"\n\25\3\26\3\26\3\26\7\26\u00ea\n\26\f\26\16\26\u00ed\13\26\3\27\3\27"+
		"\3\27\7\27\u00f2\n\27\f\27\16\27\u00f5\13\27\3\30\3\30\3\30\5\30\u00fa"+
		"\n\30\3\30\3\30\3\30\3\30\3\30\5\30\u0101\n\30\3\30\7\30\u0104\n\30\f"+
		"\30\16\30\u0107\13\30\3\31\3\31\3\31\5\31\u010c\n\31\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\3\31\5\31\u0115\n\31\3\31\3\31\3\31\3\31\7\31\u011b\n\31"+
		"\f\31\16\31\u011e\13\31\3\32\3\32\5\32\u0122\n\32\5\32\u0124\n\32\3\32"+
		"\5\32\u0127\n\32\3\32\3\32\3\32\3\32\5\32\u012d\n\32\3\32\5\32\u0130\n"+
		"\32\3\33\3\33\5\33\u0134\n\33\5\33\u0136\n\33\3\33\5\33\u0139\n\33\3\33"+
		"\3\33\3\33\3\33\5\33\u013f\n\33\3\33\5\33\u0142\n\33\3\34\5\34\u0145\n"+
		"\34\3\34\3\34\3\34\3\34\5\34\u014b\n\34\3\34\3\34\3\35\3\35\3\35\7\35"+
		"\u0152\n\35\f\35\16\35\u0155\13\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36"+
		"\5\36\u015e\n\36\3\37\3\37\3\37\3\37\3\37\7\37\u0165\n\37\f\37\16\37\u0168"+
		"\13\37\3 \3 \3 \3 \3 \3 \7 \u0170\n \f \16 \u0173\13 \3 \3 \5 \u0177\n"+
		" \3!\3!\3!\3!\5!\u017d\n!\3\"\3\"\3\"\3#\3#\3#\3#\3$\3$\3$\3$\3$\3%\3"+
		"%\3%\5%\u018e\n%\3&\3&\5&\u0192\n&\3&\3&\3&\3\'\3\'\5\'\u0199\n\'\3\'"+
		"\3\'\3\'\3\'\3\'\3(\3(\3(\7(\u01a3\n(\f(\16(\u01a6\13(\3(\2\3\30)\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLN\2"+
		"\7\4\2\t\t--\4\2\6\6\b\b\3\2\20\24\3\2\f\16\4\2\"&((\u01c7\2P\3\2\2\2"+
		"\4R\3\2\2\2\6X\3\2\2\2\bZ\3\2\2\2\n\\\3\2\2\2\f^\3\2\2\2\16d\3\2\2\2\20"+
		"f\3\2\2\2\22i\3\2\2\2\24m\3\2\2\2\26\u008c\3\2\2\2\30\u0094\3\2\2\2\32"+
		"\u00a5\3\2\2\2\34\u00a7\3\2\2\2\36\u00b0\3\2\2\2 \u00ba\3\2\2\2\"\u00be"+
		"\3\2\2\2$\u00c9\3\2\2\2&\u00dc\3\2\2\2(\u00e4\3\2\2\2*\u00e6\3\2\2\2,"+
		"\u00ee\3\2\2\2.\u00f9\3\2\2\2\60\u010b\3\2\2\2\62\u0123\3\2\2\2\64\u0135"+
		"\3\2\2\2\66\u0144\3\2\2\28\u0153\3\2\2\2:\u015d\3\2\2\2<\u015f\3\2\2\2"+
		">\u0171\3\2\2\2@\u017c\3\2\2\2B\u017e\3\2\2\2D\u0181\3\2\2\2F\u0185\3"+
		"\2\2\2H\u018d\3\2\2\2J\u0191\3\2\2\2L\u0196\3\2\2\2N\u01a4\3\2\2\2PQ\t"+
		"\2\2\2Q\3\3\2\2\2RS\7\21\2\2ST\7\6\2\2T\5\3\2\2\2UY\7\6\2\2VY\5\4\3\2"+
		"WY\7\b\2\2XU\3\2\2\2XV\3\2\2\2XW\3\2\2\2Y\7\3\2\2\2Z[\t\3\2\2[\t\3\2\2"+
		"\2\\]\t\4\2\2]\13\3\2\2\2^_\t\5\2\2_\r\3\2\2\2`e\5\n\6\2ae\5\f\7\2be\7"+
		"\13\2\2ce\7\33\2\2d`\3\2\2\2da\3\2\2\2db\3\2\2\2dc\3\2\2\2e\17\3\2\2\2"+
		"fg\7\17\2\2g\21\3\2\2\2hj\5\20\t\2ih\3\2\2\2ij\3\2\2\2jk\3\2\2\2kl\5\6"+
		"\4\2l\23\3\2\2\2mn\t\6\2\2n\25\3\2\2\2o\u008d\5\6\4\2pr\7\21\2\2qp\3\2"+
		"\2\2qr\3\2\2\2rs\3\2\2\2s\u008d\7\n\2\2tz\5\6\4\2uw\7\21\2\2vu\3\2\2\2"+
		"vw\3\2\2\2wx\3\2\2\2xz\7\n\2\2yt\3\2\2\2yv\3\2\2\2z\u0082\3\2\2\2{~\5"+
		"\16\b\2|\177\5\6\4\2}\177\7\n\2\2~|\3\2\2\2~}\3\2\2\2\177\u0081\3\2\2"+
		"\2\u0080{\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083"+
		"\3\2\2\2\u0083\u008d\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0086\5\20\t\2"+
		"\u0086\u0087\5\26\f\2\u0087\u008d\3\2\2\2\u0088\u0089\7\35\2\2\u0089\u008a"+
		"\5\26\f\2\u008a\u008b\7\35\2\2\u008b\u008d\3\2\2\2\u008co\3\2\2\2\u008c"+
		"q\3\2\2\2\u008cy\3\2\2\2\u008c\u0085\3\2\2\2\u008c\u0088\3\2\2\2\u008d"+
		"\27\3\2\2\2\u008e\u008f\b\r\1\2\u008f\u0095\5\26\f\2\u0090\u0091\7\25"+
		"\2\2\u0091\u0092\5\30\r\2\u0092\u0093\7\26\2\2\u0093\u0095\3\2\2\2\u0094"+
		"\u008e\3\2\2\2\u0094\u0090\3\2\2\2\u0095\u009c\3\2\2\2\u0096\u0097\f\3"+
		"\2\2\u0097\u0098\5\n\6\2\u0098\u0099\5\30\r\4\u0099\u009b\3\2\2\2\u009a"+
		"\u0096\3\2\2\2\u009b\u009e\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2"+
		"\2\2\u009d\31\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00a6\5\30\r\2\u00a0\u00a1"+
		"\7\21\2\2\u00a1\u00a2\7\25\2\2\u00a2\u00a3\5\30\r\2\u00a3\u00a4\7\26\2"+
		"\2\u00a4\u00a6\3\2\2\2\u00a5\u009f\3\2\2\2\u00a5\u00a0\3\2\2\2\u00a6\33"+
		"\3\2\2\2\u00a7\u00a8\7\t\2\2\u00a8\u00a9\7\25\2\2\u00a9\u00aa\5$\23\2"+
		"\u00aa\u00ab\7\26\2\2\u00ab\35\3\2\2\2\u00ac\u00b1\5\6\4\2\u00ad\u00b1"+
		"\5\2\2\2\u00ae\u00b1\7\4\2\2\u00af\u00b1\7\n\2\2\u00b0\u00ac\3\2\2\2\u00b0"+
		"\u00ad\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00af\3\2\2\2\u00b1\37\3\2\2"+
		"\2\u00b2\u00bb\3\2\2\2\u00b3\u00bb\7\34\2\2\u00b4\u00b5\7\34\2\2\u00b5"+
		"\u00b7\5$\23\2\u00b6\u00b4\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b6\3\2"+
		"\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bb\3\2\2\2\u00ba\u00b2\3\2\2\2\u00ba"+
		"\u00b3\3\2\2\2\u00ba\u00b6\3\2\2\2\u00bb!\3\2\2\2\u00bc\u00bd\7!\2\2\u00bd"+
		"\u00bf\5$\23\2\u00be\u00bc\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00be\3\2"+
		"\2\2\u00c0\u00c1\3\2\2\2\u00c1#\3\2\2\2\u00c2\u00ca\5\36\20\2\u00c3\u00ca"+
		"\5\34\17\2\u00c4\u00ca\5\32\16\2\u00c5\u00c6\7\25\2\2\u00c6\u00c7\5$\23"+
		"\2\u00c7\u00c8\7\26\2\2\u00c8\u00ca\3\2\2\2\u00c9\u00c2\3\2\2\2\u00c9"+
		"\u00c3\3\2\2\2\u00c9\u00c4\3\2\2\2\u00c9\u00c5\3\2\2\2\u00ca\u00cd\3\2"+
		"\2\2\u00cb\u00ce\5 \21\2\u00cc\u00ce\5\"\22\2\u00cd\u00cb\3\2\2\2\u00cd"+
		"\u00cc\3\2\2\2\u00ce%\3\2\2\2\u00cf\u00dd\5\2\2\2\u00d0\u00d1\7\t\2\2"+
		"\u00d1\u00d2\7\25\2\2\u00d2\u00d7\5$\23\2\u00d3\u00d4\7\34\2\2\u00d4\u00d6"+
		"\5$\23\2\u00d5\u00d3\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7"+
		"\u00d8\3\2\2\2\u00d8\u00da\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00db\7\26"+
		"\2\2\u00db\u00dd\3\2\2\2\u00dc\u00cf\3\2\2\2\u00dc\u00d0\3\2\2\2\u00dd"+
		"\'\3\2\2\2\u00de\u00e5\5&\24\2\u00df\u00e0\7\21\2\2\u00e0\u00e5\5&\24"+
		"\2\u00e1\u00e2\7\3\2\2\u00e2\u00e5\5(\25\2\u00e3\u00e5\5\66\34\2\u00e4"+
		"\u00de\3\2\2\2\u00e4\u00df\3\2\2\2\u00e4\u00e1\3\2\2\2\u00e4\u00e3\3\2"+
		"\2\2\u00e5)\3\2\2\2\u00e6\u00eb\5$\23\2\u00e7\u00e8\7\34\2\2\u00e8\u00ea"+
		"\5$\23\2\u00e9\u00e7\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb"+
		"\u00ec\3\2\2\2\u00ec+\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00f3\5(\25\2"+
		"\u00ef\u00f0\7\34\2\2\u00f0\u00f2\5(\25\2\u00f1\u00ef\3\2\2\2\u00f2\u00f5"+
		"\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4-\3\2\2\2\u00f5"+
		"\u00f3\3\2\2\2\u00f6\u00f7\5*\26\2\u00f7\u00f8\7\36\2\2\u00f8\u00fa\3"+
		"\2\2\2\u00f9\u00f6\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb"+
		"\u0105\5,\27\2\u00fc\u0100\7!\2\2\u00fd\u00fe\5*\26\2\u00fe\u00ff\7\36"+
		"\2\2\u00ff\u0101\3\2\2\2\u0100\u00fd\3\2\2\2\u0100\u0101\3\2\2\2\u0101"+
		"\u0102\3\2\2\2\u0102\u0104\5,\27\2\u0103\u00fc\3\2\2\2\u0104\u0107\3\2"+
		"\2\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106/\3\2\2\2\u0107\u0105"+
		"\3\2\2\2\u0108\u0109\5*\26\2\u0109\u010a\7\36\2\2\u010a\u010c\3\2\2\2"+
		"\u010b\u0108\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010e"+
		"\5,\27\2\u010e\u010f\7\36\2\2\u010f\u011c\5,\27\2\u0110\u0114\7!\2\2\u0111"+
		"\u0112\5*\26\2\u0112\u0113\7\36\2\2\u0113\u0115\3\2\2\2\u0114\u0111\3"+
		"\2\2\2\u0114\u0115\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0117\5,\27\2\u0117"+
		"\u0118\7\36\2\2\u0118\u0119\5,\27\2\u0119\u011b\3\2\2\2\u011a\u0110\3"+
		"\2\2\2\u011b\u011e\3\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d"+
		"\61\3\2\2\2\u011e\u011c\3\2\2\2\u011f\u0121\5$\23\2\u0120\u0122\5\24\13"+
		"\2\u0121\u0120\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0124\3\2\2\2\u0123\u011f"+
		"\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0126\3\2\2\2\u0125\u0127\7)\2\2\u0126"+
		"\u0125\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0129\7\31"+
		"\2\2\u0129\u012a\5.\30\2\u012a\u012f\7\32\2\2\u012b\u012d\5\24\13\2\u012c"+
		"\u012b\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u0130\5$"+
		"\23\2\u012f\u012c\3\2\2\2\u012f\u0130\3\2\2\2\u0130\63\3\2\2\2\u0131\u0133"+
		"\5$\23\2\u0132\u0134\5\24\13\2\u0133\u0132\3\2\2\2\u0133\u0134\3\2\2\2"+
		"\u0134\u0136\3\2\2\2\u0135\u0131\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0138"+
		"\3\2\2\2\u0137\u0139\7)\2\2\u0138\u0137\3\2\2\2\u0138\u0139\3\2\2\2\u0139"+
		"\u013a\3\2\2\2\u013a\u013b\7\31\2\2\u013b\u013c\5\60\31\2\u013c\u0141"+
		"\7\32\2\2\u013d\u013f\5\24\13\2\u013e\u013d\3\2\2\2\u013e\u013f\3\2\2"+
		"\2\u013f\u0140\3\2\2\2\u0140\u0142\5$\23\2\u0141\u013e\3\2\2\2\u0141\u0142"+
		"\3\2\2\2\u0142\65\3\2\2\2\u0143\u0145\7\21\2\2\u0144\u0143\3\2\2\2\u0144"+
		"\u0145\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147\5$\23\2\u0147\u0148\3\2"+
		"\2\2\u0148\u014a\5\24\13\2\u0149\u014b\7\21\2\2\u014a\u0149\3\2\2\2\u014a"+
		"\u014b\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d\5$\23\2\u014d\67\3\2\2"+
		"\2\u014e\u014f\5:\36\2\u014f\u0150\7\35\2\2\u0150\u0152\3\2\2\2\u0151"+
		"\u014e\3\2\2\2\u0152\u0155\3\2\2\2\u0153\u0151\3\2\2\2\u0153\u0154\3\2"+
		"\2\2\u0154\u0156\3\2\2\2\u0155\u0153\3\2\2\2\u0156\u0157\5:\36\2\u0157"+
		"9\3\2\2\2\u0158\u015e\5(\25\2\u0159\u015e\5\64\33\2\u015a\u015b\7\3\2"+
		"\2\u015b\u015e\5:\36\2\u015c\u015e\5<\37\2\u015d\u0158\3\2\2\2\u015d\u0159"+
		"\3\2\2\2\u015d\u015a\3\2\2\2\u015d\u015c\3\2\2\2\u015e;\3\2\2\2\u015f"+
		"\u0160\5(\25\2\u0160\u0161\7\36\2\2\u0161\u0166\5(\25\2\u0162\u0163\7"+
		"\34\2\2\u0163\u0165\5(\25\2\u0164\u0162\3\2\2\2\u0165\u0168\3\2\2\2\u0166"+
		"\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167=\3\2\2\2\u0168\u0166\3\2\2\2"+
		"\u0169\u016a\5@!\2\u016a\u016b\7\34\2\2\u016b\u0170\3\2\2\2\u016c\u016d"+
		"\5<\37\2\u016d\u016e\7!\2\2\u016e\u0170\3\2\2\2\u016f\u0169\3\2\2\2\u016f"+
		"\u016c\3\2\2\2\u0170\u0173\3\2\2\2\u0171\u016f\3\2\2\2\u0171\u0172\3\2"+
		"\2\2\u0172\u0176\3\2\2\2\u0173\u0171\3\2\2\2\u0174\u0177\5@!\2\u0175\u0177"+
		"\5<\37\2\u0176\u0174\3\2\2\2\u0176\u0175\3\2\2\2\u0177?\3\2\2\2\u0178"+
		"\u017d\5(\25\2\u0179\u017d\5\62\32\2\u017a\u017b\7\3\2\2\u017b\u017d\5"+
		"@!\2\u017c\u0178\3\2\2\2\u017c\u0179\3\2\2\2\u017c\u017a\3\2\2\2\u017d"+
		"A\3\2\2\2\u017e\u017f\58\35\2\u017f\u0180\7\5\2\2\u0180C\3\2\2\2\u0181"+
		"\u0182\7\37\2\2\u0182\u0183\5> \2\u0183\u0184\7\5\2\2\u0184E\3\2\2\2\u0185"+
		"\u0186\58\35\2\u0186\u0187\7\37\2\2\u0187\u0188\5> \2\u0188\u0189\7\5"+
		"\2\2\u0189G\3\2\2\2\u018a\u018e\5B\"\2\u018b\u018e\5D#\2\u018c\u018e\5"+
		"F$\2\u018d\u018a\3\2\2\2\u018d\u018b\3\2\2\2\u018d\u018c\3\2\2\2\u018e"+
		"I\3\2\2\2\u018f\u0192\7\7\2\2\u0190\u0192\5\6\4\2\u0191\u018f\3\2\2\2"+
		"\u0191\u0190\3\2\2\2\u0192\u0193\3\2\2\2\u0193\u0194\7\36\2\2\u0194\u0195"+
		"\5H%\2\u0195K\3\2\2\2\u0196\u0198\7*\2\2\u0197\u0199\7\21\2\2\u0198\u0197"+
		"\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u019a\3\2\2\2\u019a\u019b\7\t\2\2\u019b"+
		"\u019c\7\23\2\2\u019c\u019d\5\b\5\2\u019d\u019e\7\5\2\2\u019eM\3\2\2\2"+
		"\u019f\u01a3\5H%\2\u01a0\u01a3\5J&\2\u01a1\u01a3\5L\'\2\u01a2\u019f\3"+
		"\2\2\2\u01a2\u01a0\3\2\2\2\u01a2\u01a1\3\2\2\2\u01a3\u01a6\3\2\2\2\u01a4"+
		"\u01a2\3\2\2\2\u01a4\u01a5\3\2\2\2\u01a5O\3\2\2\2\u01a6\u01a4\3\2\2\2"+
		"\67Xdiqvy~\u0082\u008c\u0094\u009c\u00a5\u00b0\u00b8\u00ba\u00c0\u00c9"+
		"\u00cd\u00d7\u00dc\u00e4\u00eb\u00f3\u00f9\u0100\u0105\u010b\u0114\u011c"+
		"\u0121\u0123\u0126\u012c\u012f\u0133\u0135\u0138\u013e\u0141\u0144\u014a"+
		"\u0153\u015d\u0166\u016f\u0171\u0176\u017c\u018d\u0191\u0198\u01a2\u01a4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}