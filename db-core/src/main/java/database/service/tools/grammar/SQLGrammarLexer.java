// Generated from /Users/dmitriykosolobov/university_project/java-data-base/db-core/src/main/java/database/service/tools/grammar/SQLGrammar.g4 by ANTLR 4.13.1
package database.service.tools.grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class SQLGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, IDENTIFIER=49, INTEGER=50, STRING=51, REAL=52, 
		DIGIT=53, NON_ZERO_DIGIT=54, WS=55;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
			"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "T__39", "T__40", 
			"T__41", "T__42", "T__43", "T__44", "T__45", "T__46", "T__47", "IDENTIFIER", 
			"INTEGER", "STRING", "REAL", "DIGIT", "NON_ZERO_DIGIT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'ALTER'", "'DATABASE'", "'CREATE'", "'DROP'", "'TABLE'", 
			"'DELETE FROM'", "'WHERE'", "'INSERT INTO'", "'('", "')'", "'VALUES'", 
			"'SELECT'", "'FROM'", "'UPDATE'", "'SET'", "'BEGIN'", "'COMMIT'", "'ROLLBACK'", 
			"'HELP'", "'INSERT'", "'DELETE'", "'CONSTRAINTS'", "'*'", "','", "'='", 
			"'TRUE'", "'FALSE'", "'NULL'", "'AND'", "'OR'", "'<>'", "'<'", "'>'", 
			"'<='", "'>='", "'RENAME TO'", "'ADD'", "'COLUMN'", "'INTEGER'", "'REAL'", 
			"'STRING'", "'BOOLEAN'", "'PRIMARY KEY'", "'NOT NULL'", "'UNIQUE'", "'CHECK'", 
			"'REFERENCES'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "IDENTIFIER", "INTEGER", "STRING", "REAL", "DIGIT", "NON_ZERO_DIGIT", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public SQLGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SQLGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u00007\u01d0\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u00026\u00076\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001"+
		"\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001!\u0001!\u0001\"\u0001"+
		"\"\u0001\"\u0001#\u0001#\u0001#\u0001$\u0001$\u0001$\u0001$\u0001$\u0001"+
		"$\u0001$\u0001$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001%\u0001&\u0001"+
		"&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0001(\u0001(\u0001(\u0001(\u0001(\u0001)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0001)\u0001*\u0001*\u0001*\u0001*\u0001"+
		"*\u0001*\u0001*\u0001*\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001"+
		"+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001,\u0001,\u0001,\u0001,\u0001"+
		",\u0001,\u0001,\u0001,\u0001,\u0001-\u0001-\u0001-\u0001-\u0001-\u0001"+
		"-\u0001-\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001/\u0001/\u0001"+
		"/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u00010\u0001"+
		"0\u00050\u0192\b0\n0\f0\u0195\t0\u00011\u00031\u0198\b1\u00011\u00011"+
		"\u00051\u019c\b1\n1\f1\u019f\t1\u00011\u00031\u01a2\b1\u00012\u00012\u0005"+
		"2\u01a6\b2\n2\f2\u01a9\t2\u00012\u00012\u00013\u00033\u01ae\b3\u00013"+
		"\u00013\u00053\u01b2\b3\n3\f3\u01b5\t3\u00013\u00013\u00043\u01b9\b3\u000b"+
		"3\f3\u01ba\u00013\u00013\u00013\u00043\u01c0\b3\u000b3\f3\u01c1\u0003"+
		"3\u01c4\b3\u00014\u00014\u00015\u00015\u00016\u00046\u01cb\b6\u000b6\f"+
		"6\u01cc\u00016\u00016\u0001\u01a7\u00007\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n"+
		"\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b"+
		"7\u001c9\u001d;\u001e=\u001f? A!C\"E#G$I%K&M\'O(Q)S*U+W,Y-[.]/_0a1c2e"+
		"3g4i5k6m7\u0001\u0000\u0005\u0003\u0000AZ__az\u0004\u000009AZ__az\u0001"+
		"\u000009\u0001\u000019\u0003\u0000\t\n\r\r  \u01da\u0000\u0001\u0001\u0000"+
		"\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000"+
		"\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000"+
		"\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000"+
		"\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000"+
		"\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000"+
		"#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001"+
		"\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000"+
		"\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u0000"+
		"1\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001"+
		"\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000"+
		"\u0000\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000"+
		"?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C\u0001"+
		"\u0000\u0000\u0000\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001\u0000\u0000"+
		"\u0000\u0000I\u0001\u0000\u0000\u0000\u0000K\u0001\u0000\u0000\u0000\u0000"+
		"M\u0001\u0000\u0000\u0000\u0000O\u0001\u0000\u0000\u0000\u0000Q\u0001"+
		"\u0000\u0000\u0000\u0000S\u0001\u0000\u0000\u0000\u0000U\u0001\u0000\u0000"+
		"\u0000\u0000W\u0001\u0000\u0000\u0000\u0000Y\u0001\u0000\u0000\u0000\u0000"+
		"[\u0001\u0000\u0000\u0000\u0000]\u0001\u0000\u0000\u0000\u0000_\u0001"+
		"\u0000\u0000\u0000\u0000a\u0001\u0000\u0000\u0000\u0000c\u0001\u0000\u0000"+
		"\u0000\u0000e\u0001\u0000\u0000\u0000\u0000g\u0001\u0000\u0000\u0000\u0000"+
		"i\u0001\u0000\u0000\u0000\u0000k\u0001\u0000\u0000\u0000\u0000m\u0001"+
		"\u0000\u0000\u0000\u0001o\u0001\u0000\u0000\u0000\u0003q\u0001\u0000\u0000"+
		"\u0000\u0005w\u0001\u0000\u0000\u0000\u0007\u0080\u0001\u0000\u0000\u0000"+
		"\t\u0087\u0001\u0000\u0000\u0000\u000b\u008c\u0001\u0000\u0000\u0000\r"+
		"\u0092\u0001\u0000\u0000\u0000\u000f\u009e\u0001\u0000\u0000\u0000\u0011"+
		"\u00a4\u0001\u0000\u0000\u0000\u0013\u00b0\u0001\u0000\u0000\u0000\u0015"+
		"\u00b2\u0001\u0000\u0000\u0000\u0017\u00b4\u0001\u0000\u0000\u0000\u0019"+
		"\u00bb\u0001\u0000\u0000\u0000\u001b\u00c2\u0001\u0000\u0000\u0000\u001d"+
		"\u00c7\u0001\u0000\u0000\u0000\u001f\u00ce\u0001\u0000\u0000\u0000!\u00d2"+
		"\u0001\u0000\u0000\u0000#\u00d8\u0001\u0000\u0000\u0000%\u00df\u0001\u0000"+
		"\u0000\u0000\'\u00e8\u0001\u0000\u0000\u0000)\u00ed\u0001\u0000\u0000"+
		"\u0000+\u00f4\u0001\u0000\u0000\u0000-\u00fb\u0001\u0000\u0000\u0000/"+
		"\u0107\u0001\u0000\u0000\u00001\u0109\u0001\u0000\u0000\u00003\u010b\u0001"+
		"\u0000\u0000\u00005\u010d\u0001\u0000\u0000\u00007\u0112\u0001\u0000\u0000"+
		"\u00009\u0118\u0001\u0000\u0000\u0000;\u011d\u0001\u0000\u0000\u0000="+
		"\u0121\u0001\u0000\u0000\u0000?\u0124\u0001\u0000\u0000\u0000A\u0127\u0001"+
		"\u0000\u0000\u0000C\u0129\u0001\u0000\u0000\u0000E\u012b\u0001\u0000\u0000"+
		"\u0000G\u012e\u0001\u0000\u0000\u0000I\u0131\u0001\u0000\u0000\u0000K"+
		"\u013b\u0001\u0000\u0000\u0000M\u013f\u0001\u0000\u0000\u0000O\u0146\u0001"+
		"\u0000\u0000\u0000Q\u014e\u0001\u0000\u0000\u0000S\u0153\u0001\u0000\u0000"+
		"\u0000U\u015a\u0001\u0000\u0000\u0000W\u0162\u0001\u0000\u0000\u0000Y"+
		"\u016e\u0001\u0000\u0000\u0000[\u0177\u0001\u0000\u0000\u0000]\u017e\u0001"+
		"\u0000\u0000\u0000_\u0184\u0001\u0000\u0000\u0000a\u018f\u0001\u0000\u0000"+
		"\u0000c\u01a1\u0001\u0000\u0000\u0000e\u01a3\u0001\u0000\u0000\u0000g"+
		"\u01c3\u0001\u0000\u0000\u0000i\u01c5\u0001\u0000\u0000\u0000k\u01c7\u0001"+
		"\u0000\u0000\u0000m\u01ca\u0001\u0000\u0000\u0000op\u0005;\u0000\u0000"+
		"p\u0002\u0001\u0000\u0000\u0000qr\u0005A\u0000\u0000rs\u0005L\u0000\u0000"+
		"st\u0005T\u0000\u0000tu\u0005E\u0000\u0000uv\u0005R\u0000\u0000v\u0004"+
		"\u0001\u0000\u0000\u0000wx\u0005D\u0000\u0000xy\u0005A\u0000\u0000yz\u0005"+
		"T\u0000\u0000z{\u0005A\u0000\u0000{|\u0005B\u0000\u0000|}\u0005A\u0000"+
		"\u0000}~\u0005S\u0000\u0000~\u007f\u0005E\u0000\u0000\u007f\u0006\u0001"+
		"\u0000\u0000\u0000\u0080\u0081\u0005C\u0000\u0000\u0081\u0082\u0005R\u0000"+
		"\u0000\u0082\u0083\u0005E\u0000\u0000\u0083\u0084\u0005A\u0000\u0000\u0084"+
		"\u0085\u0005T\u0000\u0000\u0085\u0086\u0005E\u0000\u0000\u0086\b\u0001"+
		"\u0000\u0000\u0000\u0087\u0088\u0005D\u0000\u0000\u0088\u0089\u0005R\u0000"+
		"\u0000\u0089\u008a\u0005O\u0000\u0000\u008a\u008b\u0005P\u0000\u0000\u008b"+
		"\n\u0001\u0000\u0000\u0000\u008c\u008d\u0005T\u0000\u0000\u008d\u008e"+
		"\u0005A\u0000\u0000\u008e\u008f\u0005B\u0000\u0000\u008f\u0090\u0005L"+
		"\u0000\u0000\u0090\u0091\u0005E\u0000\u0000\u0091\f\u0001\u0000\u0000"+
		"\u0000\u0092\u0093\u0005D\u0000\u0000\u0093\u0094\u0005E\u0000\u0000\u0094"+
		"\u0095\u0005L\u0000\u0000\u0095\u0096\u0005E\u0000\u0000\u0096\u0097\u0005"+
		"T\u0000\u0000\u0097\u0098\u0005E\u0000\u0000\u0098\u0099\u0005 \u0000"+
		"\u0000\u0099\u009a\u0005F\u0000\u0000\u009a\u009b\u0005R\u0000\u0000\u009b"+
		"\u009c\u0005O\u0000\u0000\u009c\u009d\u0005M\u0000\u0000\u009d\u000e\u0001"+
		"\u0000\u0000\u0000\u009e\u009f\u0005W\u0000\u0000\u009f\u00a0\u0005H\u0000"+
		"\u0000\u00a0\u00a1\u0005E\u0000\u0000\u00a1\u00a2\u0005R\u0000\u0000\u00a2"+
		"\u00a3\u0005E\u0000\u0000\u00a3\u0010\u0001\u0000\u0000\u0000\u00a4\u00a5"+
		"\u0005I\u0000\u0000\u00a5\u00a6\u0005N\u0000\u0000\u00a6\u00a7\u0005S"+
		"\u0000\u0000\u00a7\u00a8\u0005E\u0000\u0000\u00a8\u00a9\u0005R\u0000\u0000"+
		"\u00a9\u00aa\u0005T\u0000\u0000\u00aa\u00ab\u0005 \u0000\u0000\u00ab\u00ac"+
		"\u0005I\u0000\u0000\u00ac\u00ad\u0005N\u0000\u0000\u00ad\u00ae\u0005T"+
		"\u0000\u0000\u00ae\u00af\u0005O\u0000\u0000\u00af\u0012\u0001\u0000\u0000"+
		"\u0000\u00b0\u00b1\u0005(\u0000\u0000\u00b1\u0014\u0001\u0000\u0000\u0000"+
		"\u00b2\u00b3\u0005)\u0000\u0000\u00b3\u0016\u0001\u0000\u0000\u0000\u00b4"+
		"\u00b5\u0005V\u0000\u0000\u00b5\u00b6\u0005A\u0000\u0000\u00b6\u00b7\u0005"+
		"L\u0000\u0000\u00b7\u00b8\u0005U\u0000\u0000\u00b8\u00b9\u0005E\u0000"+
		"\u0000\u00b9\u00ba\u0005S\u0000\u0000\u00ba\u0018\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bc\u0005S\u0000\u0000\u00bc\u00bd\u0005E\u0000\u0000\u00bd\u00be"+
		"\u0005L\u0000\u0000\u00be\u00bf\u0005E\u0000\u0000\u00bf\u00c0\u0005C"+
		"\u0000\u0000\u00c0\u00c1\u0005T\u0000\u0000\u00c1\u001a\u0001\u0000\u0000"+
		"\u0000\u00c2\u00c3\u0005F\u0000\u0000\u00c3\u00c4\u0005R\u0000\u0000\u00c4"+
		"\u00c5\u0005O\u0000\u0000\u00c5\u00c6\u0005M\u0000\u0000\u00c6\u001c\u0001"+
		"\u0000\u0000\u0000\u00c7\u00c8\u0005U\u0000\u0000\u00c8\u00c9\u0005P\u0000"+
		"\u0000\u00c9\u00ca\u0005D\u0000\u0000\u00ca\u00cb\u0005A\u0000\u0000\u00cb"+
		"\u00cc\u0005T\u0000\u0000\u00cc\u00cd\u0005E\u0000\u0000\u00cd\u001e\u0001"+
		"\u0000\u0000\u0000\u00ce\u00cf\u0005S\u0000\u0000\u00cf\u00d0\u0005E\u0000"+
		"\u0000\u00d0\u00d1\u0005T\u0000\u0000\u00d1 \u0001\u0000\u0000\u0000\u00d2"+
		"\u00d3\u0005B\u0000\u0000\u00d3\u00d4\u0005E\u0000\u0000\u00d4\u00d5\u0005"+
		"G\u0000\u0000\u00d5\u00d6\u0005I\u0000\u0000\u00d6\u00d7\u0005N\u0000"+
		"\u0000\u00d7\"\u0001\u0000\u0000\u0000\u00d8\u00d9\u0005C\u0000\u0000"+
		"\u00d9\u00da\u0005O\u0000\u0000\u00da\u00db\u0005M\u0000\u0000\u00db\u00dc"+
		"\u0005M\u0000\u0000\u00dc\u00dd\u0005I\u0000\u0000\u00dd\u00de\u0005T"+
		"\u0000\u0000\u00de$\u0001\u0000\u0000\u0000\u00df\u00e0\u0005R\u0000\u0000"+
		"\u00e0\u00e1\u0005O\u0000\u0000\u00e1\u00e2\u0005L\u0000\u0000\u00e2\u00e3"+
		"\u0005L\u0000\u0000\u00e3\u00e4\u0005B\u0000\u0000\u00e4\u00e5\u0005A"+
		"\u0000\u0000\u00e5\u00e6\u0005C\u0000\u0000\u00e6\u00e7\u0005K\u0000\u0000"+
		"\u00e7&\u0001\u0000\u0000\u0000\u00e8\u00e9\u0005H\u0000\u0000\u00e9\u00ea"+
		"\u0005E\u0000\u0000\u00ea\u00eb\u0005L\u0000\u0000\u00eb\u00ec\u0005P"+
		"\u0000\u0000\u00ec(\u0001\u0000\u0000\u0000\u00ed\u00ee\u0005I\u0000\u0000"+
		"\u00ee\u00ef\u0005N\u0000\u0000\u00ef\u00f0\u0005S\u0000\u0000\u00f0\u00f1"+
		"\u0005E\u0000\u0000\u00f1\u00f2\u0005R\u0000\u0000\u00f2\u00f3\u0005T"+
		"\u0000\u0000\u00f3*\u0001\u0000\u0000\u0000\u00f4\u00f5\u0005D\u0000\u0000"+
		"\u00f5\u00f6\u0005E\u0000\u0000\u00f6\u00f7\u0005L\u0000\u0000\u00f7\u00f8"+
		"\u0005E\u0000\u0000\u00f8\u00f9\u0005T\u0000\u0000\u00f9\u00fa\u0005E"+
		"\u0000\u0000\u00fa,\u0001\u0000\u0000\u0000\u00fb\u00fc\u0005C\u0000\u0000"+
		"\u00fc\u00fd\u0005O\u0000\u0000\u00fd\u00fe\u0005N\u0000\u0000\u00fe\u00ff"+
		"\u0005S\u0000\u0000\u00ff\u0100\u0005T\u0000\u0000\u0100\u0101\u0005R"+
		"\u0000\u0000\u0101\u0102\u0005A\u0000\u0000\u0102\u0103\u0005I\u0000\u0000"+
		"\u0103\u0104\u0005N\u0000\u0000\u0104\u0105\u0005T\u0000\u0000\u0105\u0106"+
		"\u0005S\u0000\u0000\u0106.\u0001\u0000\u0000\u0000\u0107\u0108\u0005*"+
		"\u0000\u0000\u01080\u0001\u0000\u0000\u0000\u0109\u010a\u0005,\u0000\u0000"+
		"\u010a2\u0001\u0000\u0000\u0000\u010b\u010c\u0005=\u0000\u0000\u010c4"+
		"\u0001\u0000\u0000\u0000\u010d\u010e\u0005T\u0000\u0000\u010e\u010f\u0005"+
		"R\u0000\u0000\u010f\u0110\u0005U\u0000\u0000\u0110\u0111\u0005E\u0000"+
		"\u0000\u01116\u0001\u0000\u0000\u0000\u0112\u0113\u0005F\u0000\u0000\u0113"+
		"\u0114\u0005A\u0000\u0000\u0114\u0115\u0005L\u0000\u0000\u0115\u0116\u0005"+
		"S\u0000\u0000\u0116\u0117\u0005E\u0000\u0000\u01178\u0001\u0000\u0000"+
		"\u0000\u0118\u0119\u0005N\u0000\u0000\u0119\u011a\u0005U\u0000\u0000\u011a"+
		"\u011b\u0005L\u0000\u0000\u011b\u011c\u0005L\u0000\u0000\u011c:\u0001"+
		"\u0000\u0000\u0000\u011d\u011e\u0005A\u0000\u0000\u011e\u011f\u0005N\u0000"+
		"\u0000\u011f\u0120\u0005D\u0000\u0000\u0120<\u0001\u0000\u0000\u0000\u0121"+
		"\u0122\u0005O\u0000\u0000\u0122\u0123\u0005R\u0000\u0000\u0123>\u0001"+
		"\u0000\u0000\u0000\u0124\u0125\u0005<\u0000\u0000\u0125\u0126\u0005>\u0000"+
		"\u0000\u0126@\u0001\u0000\u0000\u0000\u0127\u0128\u0005<\u0000\u0000\u0128"+
		"B\u0001\u0000\u0000\u0000\u0129\u012a\u0005>\u0000\u0000\u012aD\u0001"+
		"\u0000\u0000\u0000\u012b\u012c\u0005<\u0000\u0000\u012c\u012d\u0005=\u0000"+
		"\u0000\u012dF\u0001\u0000\u0000\u0000\u012e\u012f\u0005>\u0000\u0000\u012f"+
		"\u0130\u0005=\u0000\u0000\u0130H\u0001\u0000\u0000\u0000\u0131\u0132\u0005"+
		"R\u0000\u0000\u0132\u0133\u0005E\u0000\u0000\u0133\u0134\u0005N\u0000"+
		"\u0000\u0134\u0135\u0005A\u0000\u0000\u0135\u0136\u0005M\u0000\u0000\u0136"+
		"\u0137\u0005E\u0000\u0000\u0137\u0138\u0005 \u0000\u0000\u0138\u0139\u0005"+
		"T\u0000\u0000\u0139\u013a\u0005O\u0000\u0000\u013aJ\u0001\u0000\u0000"+
		"\u0000\u013b\u013c\u0005A\u0000\u0000\u013c\u013d\u0005D\u0000\u0000\u013d"+
		"\u013e\u0005D\u0000\u0000\u013eL\u0001\u0000\u0000\u0000\u013f\u0140\u0005"+
		"C\u0000\u0000\u0140\u0141\u0005O\u0000\u0000\u0141\u0142\u0005L\u0000"+
		"\u0000\u0142\u0143\u0005U\u0000\u0000\u0143\u0144\u0005M\u0000\u0000\u0144"+
		"\u0145\u0005N\u0000\u0000\u0145N\u0001\u0000\u0000\u0000\u0146\u0147\u0005"+
		"I\u0000\u0000\u0147\u0148\u0005N\u0000\u0000\u0148\u0149\u0005T\u0000"+
		"\u0000\u0149\u014a\u0005E\u0000\u0000\u014a\u014b\u0005G\u0000\u0000\u014b"+
		"\u014c\u0005E\u0000\u0000\u014c\u014d\u0005R\u0000\u0000\u014dP\u0001"+
		"\u0000\u0000\u0000\u014e\u014f\u0005R\u0000\u0000\u014f\u0150\u0005E\u0000"+
		"\u0000\u0150\u0151\u0005A\u0000\u0000\u0151\u0152\u0005L\u0000\u0000\u0152"+
		"R\u0001\u0000\u0000\u0000\u0153\u0154\u0005S\u0000\u0000\u0154\u0155\u0005"+
		"T\u0000\u0000\u0155\u0156\u0005R\u0000\u0000\u0156\u0157\u0005I\u0000"+
		"\u0000\u0157\u0158\u0005N\u0000\u0000\u0158\u0159\u0005G\u0000\u0000\u0159"+
		"T\u0001\u0000\u0000\u0000\u015a\u015b\u0005B\u0000\u0000\u015b\u015c\u0005"+
		"O\u0000\u0000\u015c\u015d\u0005O\u0000\u0000\u015d\u015e\u0005L\u0000"+
		"\u0000\u015e\u015f\u0005E\u0000\u0000\u015f\u0160\u0005A\u0000\u0000\u0160"+
		"\u0161\u0005N\u0000\u0000\u0161V\u0001\u0000\u0000\u0000\u0162\u0163\u0005"+
		"P\u0000\u0000\u0163\u0164\u0005R\u0000\u0000\u0164\u0165\u0005I\u0000"+
		"\u0000\u0165\u0166\u0005M\u0000\u0000\u0166\u0167\u0005A\u0000\u0000\u0167"+
		"\u0168\u0005R\u0000\u0000\u0168\u0169\u0005Y\u0000\u0000\u0169\u016a\u0005"+
		" \u0000\u0000\u016a\u016b\u0005K\u0000\u0000\u016b\u016c\u0005E\u0000"+
		"\u0000\u016c\u016d\u0005Y\u0000\u0000\u016dX\u0001\u0000\u0000\u0000\u016e"+
		"\u016f\u0005N\u0000\u0000\u016f\u0170\u0005O\u0000\u0000\u0170\u0171\u0005"+
		"T\u0000\u0000\u0171\u0172\u0005 \u0000\u0000\u0172\u0173\u0005N\u0000"+
		"\u0000\u0173\u0174\u0005U\u0000\u0000\u0174\u0175\u0005L\u0000\u0000\u0175"+
		"\u0176\u0005L\u0000\u0000\u0176Z\u0001\u0000\u0000\u0000\u0177\u0178\u0005"+
		"U\u0000\u0000\u0178\u0179\u0005N\u0000\u0000\u0179\u017a\u0005I\u0000"+
		"\u0000\u017a\u017b\u0005Q\u0000\u0000\u017b\u017c\u0005U\u0000\u0000\u017c"+
		"\u017d\u0005E\u0000\u0000\u017d\\\u0001\u0000\u0000\u0000\u017e\u017f"+
		"\u0005C\u0000\u0000\u017f\u0180\u0005H\u0000\u0000\u0180\u0181\u0005E"+
		"\u0000\u0000\u0181\u0182\u0005C\u0000\u0000\u0182\u0183\u0005K\u0000\u0000"+
		"\u0183^\u0001\u0000\u0000\u0000\u0184\u0185\u0005R\u0000\u0000\u0185\u0186"+
		"\u0005E\u0000\u0000\u0186\u0187\u0005F\u0000\u0000\u0187\u0188\u0005E"+
		"\u0000\u0000\u0188\u0189\u0005R\u0000\u0000\u0189\u018a\u0005E\u0000\u0000"+
		"\u018a\u018b\u0005N\u0000\u0000\u018b\u018c\u0005C\u0000\u0000\u018c\u018d"+
		"\u0005E\u0000\u0000\u018d\u018e\u0005S\u0000\u0000\u018e`\u0001\u0000"+
		"\u0000\u0000\u018f\u0193\u0007\u0000\u0000\u0000\u0190\u0192\u0007\u0001"+
		"\u0000\u0000\u0191\u0190\u0001\u0000\u0000\u0000\u0192\u0195\u0001\u0000"+
		"\u0000\u0000\u0193\u0191\u0001\u0000\u0000\u0000\u0193\u0194\u0001\u0000"+
		"\u0000\u0000\u0194b\u0001\u0000\u0000\u0000\u0195\u0193\u0001\u0000\u0000"+
		"\u0000\u0196\u0198\u0005-\u0000\u0000\u0197\u0196\u0001\u0000\u0000\u0000"+
		"\u0197\u0198\u0001\u0000\u0000\u0000\u0198\u0199\u0001\u0000\u0000\u0000"+
		"\u0199\u019d\u0003k5\u0000\u019a\u019c\u0003i4\u0000\u019b\u019a\u0001"+
		"\u0000\u0000\u0000\u019c\u019f\u0001\u0000\u0000\u0000\u019d\u019b\u0001"+
		"\u0000\u0000\u0000\u019d\u019e\u0001\u0000\u0000\u0000\u019e\u01a2\u0001"+
		"\u0000\u0000\u0000\u019f\u019d\u0001\u0000\u0000\u0000\u01a0\u01a2\u0005"+
		"0\u0000\u0000\u01a1\u0197\u0001\u0000\u0000\u0000\u01a1\u01a0\u0001\u0000"+
		"\u0000\u0000\u01a2d\u0001\u0000\u0000\u0000\u01a3\u01a7\u0005\'\u0000"+
		"\u0000\u01a4\u01a6\t\u0000\u0000\u0000\u01a5\u01a4\u0001\u0000\u0000\u0000"+
		"\u01a6\u01a9\u0001\u0000\u0000\u0000\u01a7\u01a8\u0001\u0000\u0000\u0000"+
		"\u01a7\u01a5\u0001\u0000\u0000\u0000\u01a8\u01aa\u0001\u0000\u0000\u0000"+
		"\u01a9\u01a7\u0001\u0000\u0000\u0000\u01aa\u01ab\u0005\'\u0000\u0000\u01ab"+
		"f\u0001\u0000\u0000\u0000\u01ac\u01ae\u0005-\u0000\u0000\u01ad\u01ac\u0001"+
		"\u0000\u0000\u0000\u01ad\u01ae\u0001\u0000\u0000\u0000\u01ae\u01af\u0001"+
		"\u0000\u0000\u0000\u01af\u01b3\u0003k5\u0000\u01b0\u01b2\u0003i4\u0000"+
		"\u01b1\u01b0\u0001\u0000\u0000\u0000\u01b2\u01b5\u0001\u0000\u0000\u0000"+
		"\u01b3\u01b1\u0001\u0000\u0000\u0000\u01b3\u01b4\u0001\u0000\u0000\u0000"+
		"\u01b4\u01b6\u0001\u0000\u0000\u0000\u01b5\u01b3\u0001\u0000\u0000\u0000"+
		"\u01b6\u01b8\u0005.\u0000\u0000\u01b7\u01b9\u0003i4\u0000\u01b8\u01b7"+
		"\u0001\u0000\u0000\u0000\u01b9\u01ba\u0001\u0000\u0000\u0000\u01ba\u01b8"+
		"\u0001\u0000\u0000\u0000\u01ba\u01bb\u0001\u0000\u0000\u0000\u01bb\u01c4"+
		"\u0001\u0000\u0000\u0000\u01bc\u01bd\u00050\u0000\u0000\u01bd\u01bf\u0005"+
		".\u0000\u0000\u01be\u01c0\u0003i4\u0000\u01bf\u01be\u0001\u0000\u0000"+
		"\u0000\u01c0\u01c1\u0001\u0000\u0000\u0000\u01c1\u01bf\u0001\u0000\u0000"+
		"\u0000\u01c1\u01c2\u0001\u0000\u0000\u0000\u01c2\u01c4\u0001\u0000\u0000"+
		"\u0000\u01c3\u01ad\u0001\u0000\u0000\u0000\u01c3\u01bc\u0001\u0000\u0000"+
		"\u0000\u01c4h\u0001\u0000\u0000\u0000\u01c5\u01c6\u0007\u0002\u0000\u0000"+
		"\u01c6j\u0001\u0000\u0000\u0000\u01c7\u01c8\u0007\u0003\u0000\u0000\u01c8"+
		"l\u0001\u0000\u0000\u0000\u01c9\u01cb\u0007\u0004\u0000\u0000\u01ca\u01c9"+
		"\u0001\u0000\u0000\u0000\u01cb\u01cc\u0001\u0000\u0000\u0000\u01cc\u01ca"+
		"\u0001\u0000\u0000\u0000\u01cc\u01cd\u0001\u0000\u0000\u0000\u01cd\u01ce"+
		"\u0001\u0000\u0000\u0000\u01ce\u01cf\u00066\u0000\u0000\u01cfn\u0001\u0000"+
		"\u0000\u0000\f\u0000\u0193\u0197\u019d\u01a1\u01a7\u01ad\u01b3\u01ba\u01c1"+
		"\u01c3\u01cc\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}