// Generated from DLVResult.g4 by ANTLR 4.5.1
package cn.edu.seu.kse.lpmln.util.syntax.dlvResult;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DLVResultLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STRING=1, FULLSTOP=2, POSITIVE_INT=3, ZERO=4, CONSTANT=5, WEIGHT_FLAG=6, 
		WEIGHT=7, LEVEL=8, LPAREN=9, RPAREN=10, LSBRACK=11, RSBRACK=12, LCBRACK=13, 
		RCBRACK=14, LESS_THAN=15, MINUS=16, GREATER_THAN=17, COMMA=18, CONDITION=19, 
		WS=20;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"STRING", "FULLSTOP", "POSITIVE_INT", "ZERO", "CONSTANT", "WEIGHT_FLAG", 
		"WEIGHT", "LEVEL", "LPAREN", "RPAREN", "LSBRACK", "RSBRACK", "LCBRACK", 
		"RCBRACK", "LESS_THAN", "MINUS", "GREATER_THAN", "COMMA", "CONDITION", 
		"WS"
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


	public DLVResultLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DLVResult.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\26w\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\7\2\60\n\2\f\2\16\2\63"+
		"\13\2\3\2\3\2\3\3\3\3\3\4\3\4\7\4;\n\4\f\4\16\4>\13\4\3\5\3\5\3\6\3\6"+
		"\7\6D\n\6\f\6\16\6G\13\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\6\25"+
		"r\n\25\r\25\16\25s\3\25\3\25\2\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26\3\2\b\3"+
		"\2$$\3\2\63;\3\2\62;\3\2c|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"{\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5\66\3\2\2\2\78\3\2\2\2\t?\3"+
		"\2\2\2\13A\3\2\2\2\rH\3\2\2\2\17M\3\2\2\2\21T\3\2\2\2\23Z\3\2\2\2\25\\"+
		"\3\2\2\2\27^\3\2\2\2\31`\3\2\2\2\33b\3\2\2\2\35d\3\2\2\2\37f\3\2\2\2!"+
		"h\3\2\2\2#j\3\2\2\2%l\3\2\2\2\'n\3\2\2\2)q\3\2\2\2+\61\7$\2\2,-\7^\2\2"+
		"-\60\7$\2\2.\60\n\2\2\2/,\3\2\2\2/.\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2"+
		"\61\62\3\2\2\2\62\64\3\2\2\2\63\61\3\2\2\2\64\65\7$\2\2\65\4\3\2\2\2\66"+
		"\67\7\60\2\2\67\6\3\2\2\28<\t\3\2\29;\t\4\2\2:9\3\2\2\2;>\3\2\2\2<:\3"+
		"\2\2\2<=\3\2\2\2=\b\3\2\2\2><\3\2\2\2?@\7\62\2\2@\n\3\2\2\2AE\t\5\2\2"+
		"BD\t\6\2\2CB\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\f\3\2\2\2GE\3\2\2"+
		"\2HI\7E\2\2IJ\7q\2\2JK\7u\2\2KL\7v\2\2L\16\3\2\2\2MN\7Y\2\2NO\7g\2\2O"+
		"P\7k\2\2PQ\7i\2\2QR\7j\2\2RS\7v\2\2S\20\3\2\2\2TU\7N\2\2UV\7g\2\2VW\7"+
		"x\2\2WX\7g\2\2XY\7n\2\2Y\22\3\2\2\2Z[\7*\2\2[\24\3\2\2\2\\]\7+\2\2]\26"+
		"\3\2\2\2^_\7]\2\2_\30\3\2\2\2`a\7_\2\2a\32\3\2\2\2bc\7}\2\2c\34\3\2\2"+
		"\2de\7\177\2\2e\36\3\2\2\2fg\7>\2\2g \3\2\2\2hi\7/\2\2i\"\3\2\2\2jk\7"+
		"@\2\2k$\3\2\2\2lm\7.\2\2m&\3\2\2\2no\7<\2\2o(\3\2\2\2pr\t\7\2\2qp\3\2"+
		"\2\2rs\3\2\2\2sq\3\2\2\2st\3\2\2\2tu\3\2\2\2uv\b\25\2\2v*\3\2\2\2\b\2"+
		"/\61<Es\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}