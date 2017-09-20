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
		NAF_NOT=1, STRING=2, FULLSTOP=3, POSITIVE_INT=4, DECIMAL=5, ZERO=6, IDENTIFIER=7, 
		VAR=8, EXPONENITIATION=9, BITWISE_AND=10, BITWISE_OR=11, BITWISE_EXCLUSIVE_OR=12, 
		BITWISE_COMPLEMENT=13, PLUS=14, MINUS=15, STAR=16, SLASH=17, BACKSLASH=18, 
		LPAREN=19, RPAREN=20, LSBRACK=21, RSBRACK=22, LCBRACK=23, RCBRACK=24, 
		RANGE=25, COMMA=26, DISJUNCTION=27, CONDITION=28, ASSIGN=29, WEAK_ASSIGN=30, 
		SEMICOLON=31, LESS_THAN=32, LEQ=33, GREATER_THAN=34, GEQ=35, EQUAL=36, 
		DOUBLE_EQUAL=37, NEQ=38, AGGREGATE_OP=39, META_OP=40, LINE_COMMENT=41, 
		WS=42, BOOL_CONSTANTS=43;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"NAF_NOT", "STRING", "FULLSTOP", "POSITIVE_INT", "DECIMAL", "ZERO", "IDENTIFIER", 
		"VAR", "EXPONENITIATION", "BITWISE_AND", "BITWISE_OR", "BITWISE_EXCLUSIVE_OR", 
		"BITWISE_COMPLEMENT", "PLUS", "MINUS", "STAR", "SLASH", "BACKSLASH", "LPAREN", 
		"RPAREN", "LSBRACK", "RSBRACK", "LCBRACK", "RCBRACK", "RANGE", "COMMA", 
		"DISJUNCTION", "CONDITION", "ASSIGN", "WEAK_ASSIGN", "SEMICOLON", "LESS_THAN", 
		"LEQ", "GREATER_THAN", "GEQ", "EQUAL", "DOUBLE_EQUAL", "NEQ", "AGGREGATE_OP", 
		"META_OP", "LINE_COMMENT", "WS", "BOOL_CONSTANTS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2-\u0129\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3c\n\3\f\3\16\3f\13\3\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\7\5n\n\5\f\5\16\5q\13\5\3\6\5\6t\n\6\3\6\7\6w\n\6\f"+
		"\6\16\6z\13\6\3\6\5\6}\n\6\3\6\3\6\3\6\7\6\u0082\n\6\f\6\16\6\u0085\13"+
		"\6\3\6\7\6\u0088\n\6\f\6\16\6\u008b\13\6\3\7\3\7\3\b\7\b\u0090\n\b\f\b"+
		"\16\b\u0093\13\b\3\b\3\b\7\b\u0097\n\b\f\b\16\b\u009a\13\b\3\t\7\t\u009d"+
		"\n\t\f\t\16\t\u00a0\13\t\3\t\3\t\7\t\u00a4\n\t\f\t\16\t\u00a7\13\t\3\n"+
		"\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36"+
		"\3\36\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3$\3$\3$\3%\3%\3&\3"+
		"&\3&\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3"+
		"(\5(\u00ff\n(\3)\3)\3)\3)\3)\3)\3)\3*\3*\7*\u010a\n*\f*\16*\u010d\13*"+
		"\3*\5*\u0110\n*\3*\3*\3*\3*\3+\6+\u0117\n+\r+\16+\u0118\3+\3+\3,\3,\3"+
		",\3,\3,\3,\3,\3,\3,\3,\3,\5,\u0128\n,\2\2-\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+"+
		"U,W-\3\2\13\3\2$$\3\2\63;\3\2\62;\3\2aa\3\2c|\7\2))\62;C\\aac|\3\2C\\"+
		"\4\2\f\f\17\17\5\2\13\f\17\17\"\"\u013b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2"+
		"\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2"+
		"A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3"+
		"\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\3Y\3\2\2"+
		"\2\5^\3\2\2\2\7i\3\2\2\2\tk\3\2\2\2\13s\3\2\2\2\r\u008c\3\2\2\2\17\u0091"+
		"\3\2\2\2\21\u009e\3\2\2\2\23\u00a8\3\2\2\2\25\u00ab\3\2\2\2\27\u00ad\3"+
		"\2\2\2\31\u00af\3\2\2\2\33\u00b1\3\2\2\2\35\u00b3\3\2\2\2\37\u00b5\3\2"+
		"\2\2!\u00b7\3\2\2\2#\u00b9\3\2\2\2%\u00bb\3\2\2\2\'\u00bd\3\2\2\2)\u00bf"+
		"\3\2\2\2+\u00c1\3\2\2\2-\u00c3\3\2\2\2/\u00c5\3\2\2\2\61\u00c7\3\2\2\2"+
		"\63\u00c9\3\2\2\2\65\u00cc\3\2\2\2\67\u00ce\3\2\2\29\u00d0\3\2\2\2;\u00d2"+
		"\3\2\2\2=\u00d5\3\2\2\2?\u00d8\3\2\2\2A\u00da\3\2\2\2C\u00dc\3\2\2\2E"+
		"\u00df\3\2\2\2G\u00e1\3\2\2\2I\u00e4\3\2\2\2K\u00e6\3\2\2\2M\u00e9\3\2"+
		"\2\2O\u00fe\3\2\2\2Q\u0100\3\2\2\2S\u0107\3\2\2\2U\u0116\3\2\2\2W\u0127"+
		"\3\2\2\2YZ\7p\2\2Z[\7q\2\2[\\\7v\2\2\\]\7\"\2\2]\4\3\2\2\2^d\7$\2\2_`"+
		"\7^\2\2`c\7$\2\2ac\n\2\2\2b_\3\2\2\2ba\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3"+
		"\2\2\2eg\3\2\2\2fd\3\2\2\2gh\7$\2\2h\6\3\2\2\2ij\7\60\2\2j\b\3\2\2\2k"+
		"o\t\3\2\2ln\t\4\2\2ml\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2\2p\n\3\2\2\2"+
		"qo\3\2\2\2rt\5\37\20\2sr\3\2\2\2st\3\2\2\2t|\3\2\2\2uw\5\t\5\2vu\3\2\2"+
		"\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y}\3\2\2\2zx\3\2\2\2{}\5\r\7\2|x\3\2\2"+
		"\2|{\3\2\2\2}~\3\2\2\2~\177\5\7\4\2\177\u0083\t\4\2\2\u0080\u0082\5\r"+
		"\7\2\u0081\u0080\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083"+
		"\u0084\3\2\2\2\u0084\u0089\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0088\t\3"+
		"\2\2\u0087\u0086\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u0089"+
		"\u008a\3\2\2\2\u008a\f\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u008d\7\62\2"+
		"\2\u008d\16\3\2\2\2\u008e\u0090\t\5\2\2\u008f\u008e\3\2\2\2\u0090\u0093"+
		"\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093"+
		"\u0091\3\2\2\2\u0094\u0098\t\6\2\2\u0095\u0097\t\7\2\2\u0096\u0095\3\2"+
		"\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099"+
		"\20\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u009d\t\5\2\2\u009c\u009b\3\2\2"+
		"\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a1"+
		"\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a5\t\b\2\2\u00a2\u00a4\t\7\2\2\u00a3"+
		"\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2"+
		"\2\2\u00a6\22\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00a9\7,\2\2\u00a9\u00aa"+
		"\7,\2\2\u00aa\24\3\2\2\2\u00ab\u00ac\7(\2\2\u00ac\26\3\2\2\2\u00ad\u00ae"+
		"\7A\2\2\u00ae\30\3\2\2\2\u00af\u00b0\7`\2\2\u00b0\32\3\2\2\2\u00b1\u00b2"+
		"\7\u0080\2\2\u00b2\34\3\2\2\2\u00b3\u00b4\7-\2\2\u00b4\36\3\2\2\2\u00b5"+
		"\u00b6\7/\2\2\u00b6 \3\2\2\2\u00b7\u00b8\7,\2\2\u00b8\"\3\2\2\2\u00b9"+
		"\u00ba\7\61\2\2\u00ba$\3\2\2\2\u00bb\u00bc\7^\2\2\u00bc&\3\2\2\2\u00bd"+
		"\u00be\7*\2\2\u00be(\3\2\2\2\u00bf\u00c0\7+\2\2\u00c0*\3\2\2\2\u00c1\u00c2"+
		"\7]\2\2\u00c2,\3\2\2\2\u00c3\u00c4\7_\2\2\u00c4.\3\2\2\2\u00c5\u00c6\7"+
		"}\2\2\u00c6\60\3\2\2\2\u00c7\u00c8\7\177\2\2\u00c8\62\3\2\2\2\u00c9\u00ca"+
		"\7\60\2\2\u00ca\u00cb\7\60\2\2\u00cb\64\3\2\2\2\u00cc\u00cd\7.\2\2\u00cd"+
		"\66\3\2\2\2\u00ce\u00cf\7~\2\2\u00cf8\3\2\2\2\u00d0\u00d1\7<\2\2\u00d1"+
		":\3\2\2\2\u00d2\u00d3\7<\2\2\u00d3\u00d4\7/\2\2\u00d4<\3\2\2\2\u00d5\u00d6"+
		"\7<\2\2\u00d6\u00d7\7\u0080\2\2\u00d7>\3\2\2\2\u00d8\u00d9\7=\2\2\u00d9"+
		"@\3\2\2\2\u00da\u00db\7>\2\2\u00dbB\3\2\2\2\u00dc\u00dd\7>\2\2\u00dd\u00de"+
		"\7?\2\2\u00deD\3\2\2\2\u00df\u00e0\7@\2\2\u00e0F\3\2\2\2\u00e1\u00e2\7"+
		"@\2\2\u00e2\u00e3\7?\2\2\u00e3H\3\2\2\2\u00e4\u00e5\7?\2\2\u00e5J\3\2"+
		"\2\2\u00e6\u00e7\7?\2\2\u00e7\u00e8\7?\2\2\u00e8L\3\2\2\2\u00e9\u00ea"+
		"\7#\2\2\u00ea\u00eb\7?\2\2\u00ebN\3\2\2\2\u00ec\u00ed\7%\2\2\u00ed\u00ee"+
		"\7e\2\2\u00ee\u00ef\7q\2\2\u00ef\u00f0\7w\2\2\u00f0\u00f1\7p\2\2\u00f1"+
		"\u00ff\7v\2\2\u00f2\u00f3\7%\2\2\u00f3\u00f4\7u\2\2\u00f4\u00f5\7w\2\2"+
		"\u00f5\u00ff\7o\2\2\u00f6\u00f7\7%\2\2\u00f7\u00f8\7o\2\2\u00f8\u00f9"+
		"\7c\2\2\u00f9\u00ff\7z\2\2\u00fa\u00fb\7%\2\2\u00fb\u00fc\7o\2\2\u00fc"+
		"\u00fd\7k\2\2\u00fd\u00ff\7p\2\2\u00fe\u00ec\3\2\2\2\u00fe\u00f2\3\2\2"+
		"\2\u00fe\u00f6\3\2\2\2\u00fe\u00fa\3\2\2\2\u00ffP\3\2\2\2\u0100\u0101"+
		"\7%\2\2\u0101\u0102\7u\2\2\u0102\u0103\7j\2\2\u0103\u0104\7q\2\2\u0104"+
		"\u0105\7y\2\2\u0105\u0106\7\"\2\2\u0106R\3\2\2\2\u0107\u010b\7\'\2\2\u0108"+
		"\u010a\n\t\2\2\u0109\u0108\3\2\2\2\u010a\u010d\3\2\2\2\u010b\u0109\3\2"+
		"\2\2\u010b\u010c\3\2\2\2\u010c\u010f\3\2\2\2\u010d\u010b\3\2\2\2\u010e"+
		"\u0110\7\17\2\2\u010f\u010e\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0111\3"+
		"\2\2\2\u0111\u0112\7\f\2\2\u0112\u0113\3\2\2\2\u0113\u0114\b*\2\2\u0114"+
		"T\3\2\2\2\u0115\u0117\t\n\2\2\u0116\u0115\3\2\2\2\u0117\u0118\3\2\2\2"+
		"\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011b"+
		"\b+\2\2\u011bV\3\2\2\2\u011c\u011d\7%\2\2\u011d\u011e\7v\2\2\u011e\u011f"+
		"\7t\2\2\u011f\u0120\7w\2\2\u0120\u0128\7g\2\2\u0121\u0122\7%\2\2\u0122"+
		"\u0123\7h\2\2\u0123\u0124\7c\2\2\u0124\u0125\7n\2\2\u0125\u0126\7u\2\2"+
		"\u0126\u0128\7g\2\2\u0127\u011c\3\2\2\2\u0127\u0121\3\2\2\2\u0128X\3\2"+
		"\2\2\24\2bdosx|\u0083\u0089\u0091\u0098\u009e\u00a5\u00fe\u010b\u010f"+
		"\u0118\u0127\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}