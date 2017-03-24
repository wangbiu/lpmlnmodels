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
		NAF_NOT=1, STRING=2, FULLSTOP=3, POSITIVE_INT=4, DECIMAL=5, ZERO=6, CONSTANT=7, 
		VAR=8, PLUS=9, MINUS=10, STAR=11, SLASH=12, LPAREN=13, RPAREN=14, LSBRACK=15, 
		RSBRACK=16, LCBRACK=17, RCBRACK=18, RANGE=19, COMMA=20, DISJUNCTION=21, 
		CONDITION=22, ASSIGN=23, WEAK_ASSIGN=24, LESS_THAN=25, LEQ=26, GREATER_THAN=27, 
		GEQ=28, EQUAL=29, DOUBLE_EQUAL=30, NEQ=31, AGGREGATE_OP=32, META_OP=33, 
		LINE_COMMENT=34, WS=35;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"NAF_NOT", "STRING", "FULLSTOP", "POSITIVE_INT", "DECIMAL", "ZERO", "CONSTANT", 
		"VAR", "PLUS", "MINUS", "STAR", "SLASH", "LPAREN", "RPAREN", "LSBRACK", 
		"RSBRACK", "LCBRACK", "RCBRACK", "RANGE", "COMMA", "DISJUNCTION", "CONDITION", 
		"ASSIGN", "WEAK_ASSIGN", "LESS_THAN", "LEQ", "GREATER_THAN", "GEQ", "EQUAL", 
		"DOUBLE_EQUAL", "NEQ", "AGGREGATE_OP", "META_OP", "LINE_COMMENT", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2%\u00f0\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3S\n\3\f"+
		"\3\16\3V\13\3\3\3\3\3\3\4\3\4\3\5\3\5\7\5^\n\5\f\5\16\5a\13\5\3\6\5\6"+
		"d\n\6\3\6\7\6g\n\6\f\6\16\6j\13\6\3\6\5\6m\n\6\3\6\3\6\3\6\7\6r\n\6\f"+
		"\6\16\6u\13\6\3\6\7\6x\n\6\f\6\16\6{\13\6\3\7\3\7\3\b\3\b\7\b\u0081\n"+
		"\b\f\b\16\b\u0084\13\b\3\t\3\t\7\t\u0088\n\t\f\t\16\t\u008b\13\t\3\n\3"+
		"\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3"+
		"\30\3\30\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3"+
		"\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3"+
		"!\3!\3!\3!\3!\3!\3!\3!\5!\u00d4\n!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\7#\u00de"+
		"\n#\f#\16#\u00e1\13#\3#\5#\u00e4\n#\3#\3#\3#\3#\3$\6$\u00eb\n$\r$\16$"+
		"\u00ec\3$\3$\2\2%\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%\3\2\n\3\2$$\3\2\63;\3\2\62;\3\2c|\6\2\62"+
		";C\\aac|\3\2C\\\4\2\f\f\17\17\5\2\13\f\17\17\"\"\u00ff\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\3I\3\2\2\2\5N"+
		"\3\2\2\2\7Y\3\2\2\2\t[\3\2\2\2\13c\3\2\2\2\r|\3\2\2\2\17~\3\2\2\2\21\u0085"+
		"\3\2\2\2\23\u008c\3\2\2\2\25\u008e\3\2\2\2\27\u0090\3\2\2\2\31\u0092\3"+
		"\2\2\2\33\u0094\3\2\2\2\35\u0096\3\2\2\2\37\u0098\3\2\2\2!\u009a\3\2\2"+
		"\2#\u009c\3\2\2\2%\u009e\3\2\2\2\'\u00a0\3\2\2\2)\u00a3\3\2\2\2+\u00a5"+
		"\3\2\2\2-\u00a7\3\2\2\2/\u00a9\3\2\2\2\61\u00ac\3\2\2\2\63\u00af\3\2\2"+
		"\2\65\u00b1\3\2\2\2\67\u00b4\3\2\2\29\u00b6\3\2\2\2;\u00b9\3\2\2\2=\u00bb"+
		"\3\2\2\2?\u00be\3\2\2\2A\u00d3\3\2\2\2C\u00d5\3\2\2\2E\u00db\3\2\2\2G"+
		"\u00ea\3\2\2\2IJ\7p\2\2JK\7q\2\2KL\7v\2\2LM\7\"\2\2M\4\3\2\2\2NT\7$\2"+
		"\2OP\7^\2\2PS\7$\2\2QS\n\2\2\2RO\3\2\2\2RQ\3\2\2\2SV\3\2\2\2TR\3\2\2\2"+
		"TU\3\2\2\2UW\3\2\2\2VT\3\2\2\2WX\7$\2\2X\6\3\2\2\2YZ\7\60\2\2Z\b\3\2\2"+
		"\2[_\t\3\2\2\\^\t\4\2\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`\n\3"+
		"\2\2\2a_\3\2\2\2bd\5\25\13\2cb\3\2\2\2cd\3\2\2\2dl\3\2\2\2eg\5\t\5\2f"+
		"e\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2im\3\2\2\2jh\3\2\2\2km\5\r\7\2"+
		"lh\3\2\2\2lk\3\2\2\2mn\3\2\2\2no\5\7\4\2os\t\4\2\2pr\5\r\7\2qp\3\2\2\2"+
		"ru\3\2\2\2sq\3\2\2\2st\3\2\2\2ty\3\2\2\2us\3\2\2\2vx\t\3\2\2wv\3\2\2\2"+
		"x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z\f\3\2\2\2{y\3\2\2\2|}\7\62\2\2}\16\3\2"+
		"\2\2~\u0082\t\5\2\2\177\u0081\t\6\2\2\u0080\177\3\2\2\2\u0081\u0084\3"+
		"\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\20\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0085\u0089\t\7\2\2\u0086\u0088\t\6\2\2\u0087\u0086\3\2"+
		"\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a"+
		"\22\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u008d\7-\2\2\u008d\24\3\2\2\2\u008e"+
		"\u008f\7/\2\2\u008f\26\3\2\2\2\u0090\u0091\7,\2\2\u0091\30\3\2\2\2\u0092"+
		"\u0093\7\61\2\2\u0093\32\3\2\2\2\u0094\u0095\7*\2\2\u0095\34\3\2\2\2\u0096"+
		"\u0097\7+\2\2\u0097\36\3\2\2\2\u0098\u0099\7]\2\2\u0099 \3\2\2\2\u009a"+
		"\u009b\7_\2\2\u009b\"\3\2\2\2\u009c\u009d\7}\2\2\u009d$\3\2\2\2\u009e"+
		"\u009f\7\177\2\2\u009f&\3\2\2\2\u00a0\u00a1\7\60\2\2\u00a1\u00a2\7\60"+
		"\2\2\u00a2(\3\2\2\2\u00a3\u00a4\7.\2\2\u00a4*\3\2\2\2\u00a5\u00a6\7~\2"+
		"\2\u00a6,\3\2\2\2\u00a7\u00a8\7<\2\2\u00a8.\3\2\2\2\u00a9\u00aa\7<\2\2"+
		"\u00aa\u00ab\7/\2\2\u00ab\60\3\2\2\2\u00ac\u00ad\7<\2\2\u00ad\u00ae\7"+
		"\u0080\2\2\u00ae\62\3\2\2\2\u00af\u00b0\7>\2\2\u00b0\64\3\2\2\2\u00b1"+
		"\u00b2\7>\2\2\u00b2\u00b3\7?\2\2\u00b3\66\3\2\2\2\u00b4\u00b5\7@\2\2\u00b5"+
		"8\3\2\2\2\u00b6\u00b7\7@\2\2\u00b7\u00b8\7?\2\2\u00b8:\3\2\2\2\u00b9\u00ba"+
		"\7?\2\2\u00ba<\3\2\2\2\u00bb\u00bc\7?\2\2\u00bc\u00bd\7?\2\2\u00bd>\3"+
		"\2\2\2\u00be\u00bf\7#\2\2\u00bf\u00c0\7?\2\2\u00c0@\3\2\2\2\u00c1\u00c2"+
		"\7%\2\2\u00c2\u00c3\7e\2\2\u00c3\u00c4\7q\2\2\u00c4\u00c5\7w\2\2\u00c5"+
		"\u00c6\7p\2\2\u00c6\u00d4\7v\2\2\u00c7\u00c8\7%\2\2\u00c8\u00c9\7u\2\2"+
		"\u00c9\u00ca\7w\2\2\u00ca\u00d4\7o\2\2\u00cb\u00cc\7%\2\2\u00cc\u00cd"+
		"\7o\2\2\u00cd\u00ce\7c\2\2\u00ce\u00d4\7z\2\2\u00cf\u00d0\7%\2\2\u00d0"+
		"\u00d1\7o\2\2\u00d1\u00d2\7k\2\2\u00d2\u00d4\7p\2\2\u00d3\u00c1\3\2\2"+
		"\2\u00d3\u00c7\3\2\2\2\u00d3\u00cb\3\2\2\2\u00d3\u00cf\3\2\2\2\u00d4B"+
		"\3\2\2\2\u00d5\u00d6\7%\2\2\u00d6\u00d7\7u\2\2\u00d7\u00d8\7j\2\2\u00d8"+
		"\u00d9\7q\2\2\u00d9\u00da\7y\2\2\u00daD\3\2\2\2\u00db\u00df\7\'\2\2\u00dc"+
		"\u00de\n\b\2\2\u00dd\u00dc\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2"+
		"\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2"+
		"\u00e4\7\17\2\2\u00e3\u00e2\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e5\3"+
		"\2\2\2\u00e5\u00e6\7\f\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8\b#\2\2\u00e8"+
		"F\3\2\2\2\u00e9\u00eb\t\t\2\2\u00ea\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2"+
		"\u00ec\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef"+
		"\b$\2\2\u00efH\3\2\2\2\21\2RT_chlsy\u0082\u0089\u00d3\u00df\u00e3\u00ec"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}