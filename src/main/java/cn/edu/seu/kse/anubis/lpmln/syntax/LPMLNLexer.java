// Generated from LPMLN.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.anubis.lpmln.syntax;
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
		NAF_NOT=1, STRING=2, FULLSTOP=3, DECIMAL=4, POSITIVE_INT=5, ZERO=6, CONSTANT=7, 
		VAR=8, PLUS=9, MINUS=10, STAR=11, SLASH=12, LPAREN=13, RPAREN=14, LSBRACK=15, 
		RSBRACK=16, LCBRACK=17, RCBRACK=18, RANGE=19, COMMA=20, DISJUNCTION=21, 
		CONDITION=22, ASSIGN=23, WEAK_ASSIGN=24, LESS_THAN=25, LEQ=26, GREATER_THAN=27, 
		GEQ=28, EQUAL=29, DOUBLE_EQUAL=30, NEQ=31, AGGREGATE_OP=32, META_OP=33, 
		LINE_COMMENT=34, WS=35;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"NAF_NOT", "STRING", "FULLSTOP", "DECIMAL", "POSITIVE_INT", "ZERO", "CONSTANT", 
		"VAR", "PLUS", "MINUS", "STAR", "SLASH", "LPAREN", "RPAREN", "LSBRACK", 
		"RSBRACK", "LCBRACK", "RCBRACK", "RANGE", "COMMA", "DISJUNCTION", "CONDITION", 
		"ASSIGN", "WEAK_ASSIGN", "LESS_THAN", "LEQ", "GREATER_THAN", "GEQ", "EQUAL", 
		"DOUBLE_EQUAL", "NEQ", "AGGREGATE_OP", "META_OP", "LINE_COMMENT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'not'", null, "'.'", null, null, "'0'", null, null, "'+'", "'-'", 
		"'*'", "'/'", "'('", "')'", "'['", "']'", "'{'", "'}'", "'..'", "','", 
		"'|'", "':'", "':-'", "':~'", "'<'", "'<='", "'>'", "'>='", "'='", "'=='", 
		"'!='", null, "'#show'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "NAF_NOT", "STRING", "FULLSTOP", "DECIMAL", "POSITIVE_INT", "ZERO", 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2%\u00ee\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3R\n\3\f\3\16"+
		"\3U\13\3\3\3\3\3\3\4\3\4\3\5\5\5\\\n\5\3\5\7\5_\n\5\f\5\16\5b\13\5\3\5"+
		"\5\5e\n\5\3\5\3\5\7\5i\n\5\f\5\16\5l\13\5\3\5\7\5o\n\5\f\5\16\5r\13\5"+
		"\3\6\3\6\7\6v\n\6\f\6\16\6y\13\6\3\7\3\7\3\b\3\b\7\b\177\n\b\f\b\16\b"+
		"\u0082\13\b\3\t\3\t\7\t\u0086\n\t\f\t\16\t\u0089\13\t\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23"+
		"\3\23\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31"+
		"\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36"+
		"\3\37\3\37\3\37\3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!"+
		"\3!\3!\3!\5!\u00d2\n!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\7#\u00dc\n#\f#\16"+
		"#\u00df\13#\3#\5#\u00e2\n#\3#\3#\3#\3#\3$\6$\u00e9\n$\r$\16$\u00ea\3$"+
		"\3$\2\2%\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35"+
		"9\36;\37= ?!A\"C#E$G%\3\2\n\3\2$$\3\2\62;\3\2\63;\3\2c|\6\2\62;C\\aac"+
		"|\3\2C\\\4\2\f\f\17\17\5\2\13\f\17\17\"\"\u00fd\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\3I\3\2\2\2\5M\3\2\2"+
		"\2\7X\3\2\2\2\t[\3\2\2\2\13s\3\2\2\2\rz\3\2\2\2\17|\3\2\2\2\21\u0083\3"+
		"\2\2\2\23\u008a\3\2\2\2\25\u008c\3\2\2\2\27\u008e\3\2\2\2\31\u0090\3\2"+
		"\2\2\33\u0092\3\2\2\2\35\u0094\3\2\2\2\37\u0096\3\2\2\2!\u0098\3\2\2\2"+
		"#\u009a\3\2\2\2%\u009c\3\2\2\2\'\u009e\3\2\2\2)\u00a1\3\2\2\2+\u00a3\3"+
		"\2\2\2-\u00a5\3\2\2\2/\u00a7\3\2\2\2\61\u00aa\3\2\2\2\63\u00ad\3\2\2\2"+
		"\65\u00af\3\2\2\2\67\u00b2\3\2\2\29\u00b4\3\2\2\2;\u00b7\3\2\2\2=\u00b9"+
		"\3\2\2\2?\u00bc\3\2\2\2A\u00d1\3\2\2\2C\u00d3\3\2\2\2E\u00d9\3\2\2\2G"+
		"\u00e8\3\2\2\2IJ\7p\2\2JK\7q\2\2KL\7v\2\2L\4\3\2\2\2MS\7$\2\2NO\7^\2\2"+
		"OR\7$\2\2PR\n\2\2\2QN\3\2\2\2QP\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2"+
		"TV\3\2\2\2US\3\2\2\2VW\7$\2\2W\6\3\2\2\2XY\7\60\2\2Y\b\3\2\2\2Z\\\5\25"+
		"\13\2[Z\3\2\2\2[\\\3\2\2\2\\d\3\2\2\2]_\5\13\6\2^]\3\2\2\2_b\3\2\2\2`"+
		"^\3\2\2\2`a\3\2\2\2ae\3\2\2\2b`\3\2\2\2ce\5\r\7\2d`\3\2\2\2dc\3\2\2\2"+
		"ef\3\2\2\2fj\5\7\4\2gi\5\r\7\2hg\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2"+
		"kp\3\2\2\2lj\3\2\2\2mo\t\3\2\2nm\3\2\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2"+
		"q\n\3\2\2\2rp\3\2\2\2sw\t\4\2\2tv\t\3\2\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2"+
		"\2wx\3\2\2\2x\f\3\2\2\2yw\3\2\2\2z{\7\62\2\2{\16\3\2\2\2|\u0080\t\5\2"+
		"\2}\177\t\6\2\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081"+
		"\3\2\2\2\u0081\20\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0087\t\7\2\2\u0084"+
		"\u0086\t\6\2\2\u0085\u0084\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3\2"+
		"\2\2\u0087\u0088\3\2\2\2\u0088\22\3\2\2\2\u0089\u0087\3\2\2\2\u008a\u008b"+
		"\7-\2\2\u008b\24\3\2\2\2\u008c\u008d\7/\2\2\u008d\26\3\2\2\2\u008e\u008f"+
		"\7,\2\2\u008f\30\3\2\2\2\u0090\u0091\7\61\2\2\u0091\32\3\2\2\2\u0092\u0093"+
		"\7*\2\2\u0093\34\3\2\2\2\u0094\u0095\7+\2\2\u0095\36\3\2\2\2\u0096\u0097"+
		"\7]\2\2\u0097 \3\2\2\2\u0098\u0099\7_\2\2\u0099\"\3\2\2\2\u009a\u009b"+
		"\7}\2\2\u009b$\3\2\2\2\u009c\u009d\7\177\2\2\u009d&\3\2\2\2\u009e\u009f"+
		"\7\60\2\2\u009f\u00a0\7\60\2\2\u00a0(\3\2\2\2\u00a1\u00a2\7.\2\2\u00a2"+
		"*\3\2\2\2\u00a3\u00a4\7~\2\2\u00a4,\3\2\2\2\u00a5\u00a6\7<\2\2\u00a6."+
		"\3\2\2\2\u00a7\u00a8\7<\2\2\u00a8\u00a9\7/\2\2\u00a9\60\3\2\2\2\u00aa"+
		"\u00ab\7<\2\2\u00ab\u00ac\7\u0080\2\2\u00ac\62\3\2\2\2\u00ad\u00ae\7>"+
		"\2\2\u00ae\64\3\2\2\2\u00af\u00b0\7>\2\2\u00b0\u00b1\7?\2\2\u00b1\66\3"+
		"\2\2\2\u00b2\u00b3\7@\2\2\u00b38\3\2\2\2\u00b4\u00b5\7@\2\2\u00b5\u00b6"+
		"\7?\2\2\u00b6:\3\2\2\2\u00b7\u00b8\7?\2\2\u00b8<\3\2\2\2\u00b9\u00ba\7"+
		"?\2\2\u00ba\u00bb\7?\2\2\u00bb>\3\2\2\2\u00bc\u00bd\7#\2\2\u00bd\u00be"+
		"\7?\2\2\u00be@\3\2\2\2\u00bf\u00c0\7%\2\2\u00c0\u00c1\7e\2\2\u00c1\u00c2"+
		"\7q\2\2\u00c2\u00c3\7w\2\2\u00c3\u00c4\7p\2\2\u00c4\u00d2\7v\2\2\u00c5"+
		"\u00c6\7%\2\2\u00c6\u00c7\7u\2\2\u00c7\u00c8\7w\2\2\u00c8\u00d2\7o\2\2"+
		"\u00c9\u00ca\7%\2\2\u00ca\u00cb\7o\2\2\u00cb\u00cc\7c\2\2\u00cc\u00d2"+
		"\7z\2\2\u00cd\u00ce\7%\2\2\u00ce\u00cf\7o\2\2\u00cf\u00d0\7k\2\2\u00d0"+
		"\u00d2\7p\2\2\u00d1\u00bf\3\2\2\2\u00d1\u00c5\3\2\2\2\u00d1\u00c9\3\2"+
		"\2\2\u00d1\u00cd\3\2\2\2\u00d2B\3\2\2\2\u00d3\u00d4\7%\2\2\u00d4\u00d5"+
		"\7u\2\2\u00d5\u00d6\7j\2\2\u00d6\u00d7\7q\2\2\u00d7\u00d8\7y\2\2\u00d8"+
		"D\3\2\2\2\u00d9\u00dd\7\'\2\2\u00da\u00dc\n\b\2\2\u00db\u00da\3\2\2\2"+
		"\u00dc\u00df\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00e1"+
		"\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0\u00e2\7\17\2\2\u00e1\u00e0\3\2\2\2"+
		"\u00e1\u00e2\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e4\7\f\2\2\u00e4\u00e5"+
		"\3\2\2\2\u00e5\u00e6\b#\2\2\u00e6F\3\2\2\2\u00e7\u00e9\t\t\2\2\u00e8\u00e7"+
		"\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb"+
		"\u00ec\3\2\2\2\u00ec\u00ed\b$\2\2\u00edH\3\2\2\2\21\2QS[`djpw\u0080\u0087"+
		"\u00d1\u00dd\u00e1\u00ea\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}