// Generated from LPMLN.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.lpmln.util.syntax.lpmln;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LPMLNLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NAF_NOT=1, STRING=2, FULLSTOP=3, POSITIVE_INT=4, DECIMAL=5, ZERO=6, CONSTANT=7, 
		VAR=8, PLUS=9, MINUS=10, STAR=11, SLASH=12, LPAREN=13, RPAREN=14, LSBRACK=15, 
		RSBRACK=16, LCBRACK=17, RCBRACK=18, RANGE=19, COMMA=20, DISJUNCTION=21, 
		CONDITION=22, ASSIGN=23, WEAK_ASSIGN=24, SEMICOLON=25, LESS_THAN=26, LEQ=27, 
		GREATER_THAN=28, GEQ=29, EQUAL=30, DOUBLE_EQUAL=31, NEQ=32, AGGREGATE_OP=33, 
		META_OP=34, LINE_COMMENT=35, WS=36;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"NAF_NOT", "STRING", "FULLSTOP", "POSITIVE_INT", "DECIMAL", "ZERO", "CONSTANT", 
		"VAR", "PLUS", "MINUS", "STAR", "SLASH", "LPAREN", "RPAREN", "LSBRACK", 
		"RSBRACK", "LCBRACK", "RCBRACK", "RANGE", "COMMA", "DISJUNCTION", "CONDITION", 
		"ASSIGN", "WEAK_ASSIGN", "SEMICOLON", "LESS_THAN", "LEQ", "GREATER_THAN", 
		"GEQ", "EQUAL", "DOUBLE_EQUAL", "NEQ", "AGGREGATE_OP", "META_OP", "LINE_COMMENT", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'not '", null, "'.'", null, null, "'0'", null, null, "'+'", "'-'", 
		"'*'", "'/'", "'('", "')'", "'['", "']'", "'{'", "'}'", "'..'", "','", 
		"'|'", "':'", "':-'", "':~'", "';'", "'<'", "'<='", "'>'", "'>='", "'='", 
		"'=='", "'!='", null, "'#show '"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "NAF_NOT", "STRING", "FULLSTOP", "POSITIVE_INT", "DECIMAL", "ZERO", 
		"CONSTANT", "VAR", "PLUS", "MINUS", "STAR", "SLASH", "LPAREN", "RPAREN", 
		"LSBRACK", "RSBRACK", "LCBRACK", "RCBRACK", "RANGE", "COMMA", "DISJUNCTION", 
		"CONDITION", "ASSIGN", "WEAK_ASSIGN", "SEMICOLON", "LESS_THAN", "LEQ", 
		"GREATER_THAN", "GEQ", "EQUAL", "DOUBLE_EQUAL", "NEQ", "AGGREGATE_OP", 
		"META_OP", "LINE_COMMENT", "WS"
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


	public LPMLNLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LPMLN.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2&\u00f5\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3"+
		"U\n\3\f\3\16\3X\13\3\3\3\3\3\3\4\3\4\3\5\3\5\7\5`\n\5\f\5\16\5c\13\5\3"+
		"\6\5\6f\n\6\3\6\7\6i\n\6\f\6\16\6l\13\6\3\6\5\6o\n\6\3\6\3\6\3\6\7\6t"+
		"\n\6\f\6\16\6w\13\6\3\6\7\6z\n\6\f\6\16\6}\13\6\3\7\3\7\3\b\3\b\7\b\u0083"+
		"\n\b\f\b\16\b\u0086\13\b\3\t\3\t\7\t\u008a\n\t\f\t\16\t\u008d\13\t\3\n"+
		"\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21"+
		"\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35"+
		"\3\36\3\36\3\36\3\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u00d8\n\"\3#\3#\3#"+
		"\3#\3#\3#\3#\3$\3$\7$\u00e3\n$\f$\16$\u00e6\13$\3$\5$\u00e9\n$\3$\3$\3"+
		"$\3$\3%\6%\u00f0\n%\r%\16%\u00f1\3%\3%\2\2&\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&\3\2\n\3\2$"+
		"$\3\2\63;\3\2\62;\3\2c|\6\2\62;C\\aac|\3\2C\\\4\2\f\f\17\17\5\2\13\f\17"+
		"\17\"\"\u0104\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\3K\3\2\2\2\5P\3\2\2\2\7[\3\2\2\2\t]\3\2\2\2\13"+
		"e\3\2\2\2\r~\3\2\2\2\17\u0080\3\2\2\2\21\u0087\3\2\2\2\23\u008e\3\2\2"+
		"\2\25\u0090\3\2\2\2\27\u0092\3\2\2\2\31\u0094\3\2\2\2\33\u0096\3\2\2\2"+
		"\35\u0098\3\2\2\2\37\u009a\3\2\2\2!\u009c\3\2\2\2#\u009e\3\2\2\2%\u00a0"+
		"\3\2\2\2\'\u00a2\3\2\2\2)\u00a5\3\2\2\2+\u00a7\3\2\2\2-\u00a9\3\2\2\2"+
		"/\u00ab\3\2\2\2\61\u00ae\3\2\2\2\63\u00b1\3\2\2\2\65\u00b3\3\2\2\2\67"+
		"\u00b5\3\2\2\29\u00b8\3\2\2\2;\u00ba\3\2\2\2=\u00bd\3\2\2\2?\u00bf\3\2"+
		"\2\2A\u00c2\3\2\2\2C\u00d7\3\2\2\2E\u00d9\3\2\2\2G\u00e0\3\2\2\2I\u00ef"+
		"\3\2\2\2KL\7p\2\2LM\7q\2\2MN\7v\2\2NO\7\"\2\2O\4\3\2\2\2PV\7$\2\2QR\7"+
		"^\2\2RU\7$\2\2SU\n\2\2\2TQ\3\2\2\2TS\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2"+
		"\2\2WY\3\2\2\2XV\3\2\2\2YZ\7$\2\2Z\6\3\2\2\2[\\\7\60\2\2\\\b\3\2\2\2]"+
		"a\t\3\2\2^`\t\4\2\2_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2b\n\3\2\2\2"+
		"ca\3\2\2\2df\5\25\13\2ed\3\2\2\2ef\3\2\2\2fn\3\2\2\2gi\5\t\5\2hg\3\2\2"+
		"\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2ko\3\2\2\2lj\3\2\2\2mo\5\r\7\2nj\3\2\2"+
		"\2nm\3\2\2\2op\3\2\2\2pq\5\7\4\2qu\t\4\2\2rt\5\r\7\2sr\3\2\2\2tw\3\2\2"+
		"\2us\3\2\2\2uv\3\2\2\2v{\3\2\2\2wu\3\2\2\2xz\t\3\2\2yx\3\2\2\2z}\3\2\2"+
		"\2{y\3\2\2\2{|\3\2\2\2|\f\3\2\2\2}{\3\2\2\2~\177\7\62\2\2\177\16\3\2\2"+
		"\2\u0080\u0084\t\5\2\2\u0081\u0083\t\6\2\2\u0082\u0081\3\2\2\2\u0083\u0086"+
		"\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\20\3\2\2\2\u0086"+
		"\u0084\3\2\2\2\u0087\u008b\t\7\2\2\u0088\u008a\t\6\2\2\u0089\u0088\3\2"+
		"\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c"+
		"\22\3\2\2\2\u008d\u008b\3\2\2\2\u008e\u008f\7-\2\2\u008f\24\3\2\2\2\u0090"+
		"\u0091\7/\2\2\u0091\26\3\2\2\2\u0092\u0093\7,\2\2\u0093\30\3\2\2\2\u0094"+
		"\u0095\7\61\2\2\u0095\32\3\2\2\2\u0096\u0097\7*\2\2\u0097\34\3\2\2\2\u0098"+
		"\u0099\7+\2\2\u0099\36\3\2\2\2\u009a\u009b\7]\2\2\u009b \3\2\2\2\u009c"+
		"\u009d\7_\2\2\u009d\"\3\2\2\2\u009e\u009f\7}\2\2\u009f$\3\2\2\2\u00a0"+
		"\u00a1\7\177\2\2\u00a1&\3\2\2\2\u00a2\u00a3\7\60\2\2\u00a3\u00a4\7\60"+
		"\2\2\u00a4(\3\2\2\2\u00a5\u00a6\7.\2\2\u00a6*\3\2\2\2\u00a7\u00a8\7~\2"+
		"\2\u00a8,\3\2\2\2\u00a9\u00aa\7<\2\2\u00aa.\3\2\2\2\u00ab\u00ac\7<\2\2"+
		"\u00ac\u00ad\7/\2\2\u00ad\60\3\2\2\2\u00ae\u00af\7<\2\2\u00af\u00b0\7"+
		"\u0080\2\2\u00b0\62\3\2\2\2\u00b1\u00b2\7=\2\2\u00b2\64\3\2\2\2\u00b3"+
		"\u00b4\7>\2\2\u00b4\66\3\2\2\2\u00b5\u00b6\7>\2\2\u00b6\u00b7\7?\2\2\u00b7"+
		"8\3\2\2\2\u00b8\u00b9\7@\2\2\u00b9:\3\2\2\2\u00ba\u00bb\7@\2\2\u00bb\u00bc"+
		"\7?\2\2\u00bc<\3\2\2\2\u00bd\u00be\7?\2\2\u00be>\3\2\2\2\u00bf\u00c0\7"+
		"?\2\2\u00c0\u00c1\7?\2\2\u00c1@\3\2\2\2\u00c2\u00c3\7#\2\2\u00c3\u00c4"+
		"\7?\2\2\u00c4B\3\2\2\2\u00c5\u00c6\7%\2\2\u00c6\u00c7\7e\2\2\u00c7\u00c8"+
		"\7q\2\2\u00c8\u00c9\7w\2\2\u00c9\u00ca\7p\2\2\u00ca\u00d8\7v\2\2\u00cb"+
		"\u00cc\7%\2\2\u00cc\u00cd\7u\2\2\u00cd\u00ce\7w\2\2\u00ce\u00d8\7o\2\2"+
		"\u00cf\u00d0\7%\2\2\u00d0\u00d1\7o\2\2\u00d1\u00d2\7c\2\2\u00d2\u00d8"+
		"\7z\2\2\u00d3\u00d4\7%\2\2\u00d4\u00d5\7o\2\2\u00d5\u00d6\7k\2\2\u00d6"+
		"\u00d8\7p\2\2\u00d7\u00c5\3\2\2\2\u00d7\u00cb\3\2\2\2\u00d7\u00cf\3\2"+
		"\2\2\u00d7\u00d3\3\2\2\2\u00d8D\3\2\2\2\u00d9\u00da\7%\2\2\u00da\u00db"+
		"\7u\2\2\u00db\u00dc\7j\2\2\u00dc\u00dd\7q\2\2\u00dd\u00de\7y\2\2\u00de"+
		"\u00df\7\"\2\2\u00dfF\3\2\2\2\u00e0\u00e4\7\'\2\2\u00e1\u00e3\n\b\2\2"+
		"\u00e2\u00e1\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5"+
		"\3\2\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00e9\7\17\2\2"+
		"\u00e8\u00e7\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00eb"+
		"\7\f\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ed\b$\2\2\u00edH\3\2\2\2\u00ee\u00f0"+
		"\t\t\2\2\u00ef\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1"+
		"\u00f2\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f4\b%\2\2\u00f4J\3\2\2\2\21"+
		"\2TVaejnu{\u0084\u008b\u00d7\u00e4\u00e8\u00f1\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}