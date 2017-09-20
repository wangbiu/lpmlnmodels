// Generated from ClingoResult.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.lpmln.util.syntax.clingoResult;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ClingoResultLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STRING=1, FULLSTOP=2, POSITIVE_INT=3, ZERO=4, CONSTANT=5, LPAREN=6, RPAREN=7, 
		LSBRACK=8, RSBRACK=9, LCBRACK=10, RCBRACK=11, LESS_THAN=12, MINUS=13, 
		GREATER_THAN=14, COMMA=15, ANSWER=16, OPT=17, WS=18;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"STRING", "FULLSTOP", "POSITIVE_INT", "ZERO", "CONSTANT", "LPAREN", "RPAREN", 
		"LSBRACK", "RSBRACK", "LCBRACK", "RCBRACK", "LESS_THAN", "MINUS", "GREATER_THAN", 
		"COMMA", "ANSWER", "OPT", "WS"
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


	public ClingoResultLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ClingoResult.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\24{\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\7\2,\n\2\f\2\16\2/\13\2\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\7\4\67\n\4\f\4\16\4:\13\4\3\5\3\5\3\6\7\6?\n\6\f\6\16\6B\13\6\3\6"+
		"\3\6\7\6F\n\6\f\6\16\6I\13\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\23\6\23v\n\23\r\23\16\23w\3\23\3\23\2\2\24\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\3\2\t\3\2$$\3\2\63;\3\2\62;\3\2aa\3\2c|\7\2))\62;C\\aac|\5\2"+
		"\13\f\17\17\"\"\u0080\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\3\'\3\2\2\2\5\62\3\2\2\2\7\64\3"+
		"\2\2\2\t;\3\2\2\2\13@\3\2\2\2\rJ\3\2\2\2\17L\3\2\2\2\21N\3\2\2\2\23P\3"+
		"\2\2\2\25R\3\2\2\2\27T\3\2\2\2\31V\3\2\2\2\33X\3\2\2\2\35Z\3\2\2\2\37"+
		"\\\3\2\2\2!^\3\2\2\2#f\3\2\2\2%u\3\2\2\2\'-\7$\2\2()\7^\2\2),\7$\2\2*"+
		",\n\2\2\2+(\3\2\2\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\60\3\2\2"+
		"\2/-\3\2\2\2\60\61\7$\2\2\61\4\3\2\2\2\62\63\7\60\2\2\63\6\3\2\2\2\64"+
		"8\t\3\2\2\65\67\t\4\2\2\66\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2"+
		"\29\b\3\2\2\2:8\3\2\2\2;<\7\62\2\2<\n\3\2\2\2=?\t\5\2\2>=\3\2\2\2?B\3"+
		"\2\2\2@>\3\2\2\2@A\3\2\2\2AC\3\2\2\2B@\3\2\2\2CG\t\6\2\2DF\t\7\2\2ED\3"+
		"\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2H\f\3\2\2\2IG\3\2\2\2JK\7*\2\2K\16"+
		"\3\2\2\2LM\7+\2\2M\20\3\2\2\2NO\7]\2\2O\22\3\2\2\2PQ\7_\2\2Q\24\3\2\2"+
		"\2RS\7}\2\2S\26\3\2\2\2TU\7\177\2\2U\30\3\2\2\2VW\7>\2\2W\32\3\2\2\2X"+
		"Y\7/\2\2Y\34\3\2\2\2Z[\7@\2\2[\36\3\2\2\2\\]\7.\2\2] \3\2\2\2^_\7C\2\2"+
		"_`\7p\2\2`a\7u\2\2ab\7y\2\2bc\7g\2\2cd\7t\2\2de\7<\2\2e\"\3\2\2\2fg\7"+
		"Q\2\2gh\7r\2\2hi\7v\2\2ij\7k\2\2jk\7o\2\2kl\7k\2\2lm\7|\2\2mn\7c\2\2n"+
		"o\7v\2\2op\7k\2\2pq\7q\2\2qr\7p\2\2rs\7<\2\2s$\3\2\2\2tv\t\b\2\2ut\3\2"+
		"\2\2vw\3\2\2\2wu\3\2\2\2wx\3\2\2\2xy\3\2\2\2yz\b\23\2\2z&\3\2\2\2\t\2"+
		"+-8@Gw\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}