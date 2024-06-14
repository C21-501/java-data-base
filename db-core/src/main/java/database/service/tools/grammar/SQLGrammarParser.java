// Generated from /Users/dmitriykosolobov/university_project/java-data-base/db-core/src/main/java/database/service/tools/grammar/SQLGrammar.g4 by ANTLR 4.13.1
package database.service.tools.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SQLGrammarParser extends Parser {
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
		IDENTIFIER=46, INTEGER=47, STRING=48, REAL=49, DIGIT=50, NON_ZERO_DIGIT=51, 
		WS=52;
	public static final int
		RULE_start = 0, RULE_sqlCommand = 1, RULE_sqlCommands = 2, RULE_ddlCommand = 3, 
		RULE_alterCommand = 4, RULE_createCommand = 5, RULE_dropCommand = 6, RULE_alterDbCommand = 7, 
		RULE_createDbCommand = 8, RULE_dropDbCommand = 9, RULE_alterTableCommand = 10, 
		RULE_createTableCommand = 11, RULE_dropTableCommand = 12, RULE_dmlCommand = 13, 
		RULE_deleteCommand = 14, RULE_insertCommand = 15, RULE_selectCommand = 16, 
		RULE_updateCommand = 17, RULE_tclCommand = 18, RULE_beginCommand = 19, 
		RULE_commitCommand = 20, RULE_rollbackCommand = 21, RULE_helpCommand = 22, 
		RULE_selectElements = 23, RULE_selectElement = 24, RULE_updateElements = 25, 
		RULE_columnList = 26, RULE_valueList = 27, RULE_condition = 28, RULE_expression = 29, 
		RULE_literal = 30, RULE_logicalOperator = 31, RULE_comparisonOperator = 32, 
		RULE_alterDatabaseStatement = 33, RULE_alterTableStatement = 34, RULE_renameTableStatement = 35, 
		RULE_addColumnStatement = 36, RULE_dropColumnStatement = 37, RULE_createTableStatement = 38, 
		RULE_columnDefinition = 39, RULE_dataType = 40, RULE_columnConstraint = 41, 
		RULE_check = 42, RULE_foreignKey = 43;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "sqlCommand", "sqlCommands", "ddlCommand", "alterCommand", "createCommand", 
			"dropCommand", "alterDbCommand", "createDbCommand", "dropDbCommand", 
			"alterTableCommand", "createTableCommand", "dropTableCommand", "dmlCommand", 
			"deleteCommand", "insertCommand", "selectCommand", "updateCommand", "tclCommand", 
			"beginCommand", "commitCommand", "rollbackCommand", "helpCommand", "selectElements", 
			"selectElement", "updateElements", "columnList", "valueList", "condition", 
			"expression", "literal", "logicalOperator", "comparisonOperator", "alterDatabaseStatement", 
			"alterTableStatement", "renameTableStatement", "addColumnStatement", 
			"dropColumnStatement", "createTableStatement", "columnDefinition", "dataType", 
			"columnConstraint", "check", "foreignKey"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'ALTER'", "'DATABASE'", "'CREATE'", "'DROP'", "'TABLE'", 
			"'DELETE FROM'", "'WHERE'", "'INSERT INTO'", "'('", "')'", "'VALUES'", 
			"'SELECT'", "'FROM'", "'UPDATE'", "'SET'", "'BEGIN'", "'COMMIT'", "'ROLLBACK'", 
			"'HELP'", "'*'", "','", "'='", "'TRUE'", "'FALSE'", "'NULL'", "'AND'", 
			"'OR'", "'<>'", "'<'", "'>'", "'<='", "'>='", "'RENAME TO'", "'ADD'", 
			"'COLUMN'", "'INTEGER'", "'REAL'", "'STRING'", "'BOOLEAN'", "'PRIMARY KEY'", 
			"'NOT NULL'", "'UNIQUE'", "'CHECK'", "'REFERENCES'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "IDENTIFIER", 
			"INTEGER", "STRING", "REAL", "DIGIT", "NON_ZERO_DIGIT", "WS"
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

	@Override
	public String getGrammarFileName() { return "SQLGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SQLGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public SqlCommandsContext sqlCommands() {
			return getRuleContext(SqlCommandsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(SQLGrammarParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			sqlCommands();
			setState(89);
			match(EOF);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SqlCommandContext extends ParserRuleContext {
		public DdlCommandContext ddlCommand() {
			return getRuleContext(DdlCommandContext.class,0);
		}
		public DmlCommandContext dmlCommand() {
			return getRuleContext(DmlCommandContext.class,0);
		}
		public TclCommandContext tclCommand() {
			return getRuleContext(TclCommandContext.class,0);
		}
		public HelpCommandContext helpCommand() {
			return getRuleContext(HelpCommandContext.class,0);
		}
		public SqlCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterSqlCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitSqlCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitSqlCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlCommandContext sqlCommand() throws RecognitionException {
		SqlCommandContext _localctx = new SqlCommandContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sqlCommand);
		try {
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__3:
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				ddlCommand();
				}
				break;
			case T__6:
			case T__8:
			case T__12:
			case T__14:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				dmlCommand();
				}
				break;
			case T__16:
			case T__17:
			case T__18:
				enterOuterAlt(_localctx, 3);
				{
				setState(93);
				tclCommand();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 4);
				{
				setState(94);
				helpCommand();
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

	@SuppressWarnings("CheckReturnValue")
	public static class SqlCommandsContext extends ParserRuleContext {
		public List<SqlCommandContext> sqlCommand() {
			return getRuleContexts(SqlCommandContext.class);
		}
		public SqlCommandContext sqlCommand(int i) {
			return getRuleContext(SqlCommandContext.class,i);
		}
		public SqlCommandsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlCommands; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterSqlCommands(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitSqlCommands(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitSqlCommands(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlCommandsContext sqlCommands() throws RecognitionException {
		SqlCommandsContext _localctx = new SqlCommandsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sqlCommands);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			sqlCommand();
			setState(102);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(98);
					match(T__0);
					setState(99);
					sqlCommand();
					}
					} 
				}
				setState(104);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(105);
				match(T__0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DdlCommandContext extends ParserRuleContext {
		public AlterCommandContext alterCommand() {
			return getRuleContext(AlterCommandContext.class,0);
		}
		public CreateCommandContext createCommand() {
			return getRuleContext(CreateCommandContext.class,0);
		}
		public DropCommandContext dropCommand() {
			return getRuleContext(DropCommandContext.class,0);
		}
		public DdlCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ddlCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterDdlCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitDdlCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitDdlCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DdlCommandContext ddlCommand() throws RecognitionException {
		DdlCommandContext _localctx = new DdlCommandContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ddlCommand);
		try {
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				alterCommand();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				createCommand();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(110);
				dropCommand();
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

	@SuppressWarnings("CheckReturnValue")
	public static class AlterCommandContext extends ParserRuleContext {
		public AlterDbCommandContext alterDbCommand() {
			return getRuleContext(AlterDbCommandContext.class,0);
		}
		public AlterTableCommandContext alterTableCommand() {
			return getRuleContext(AlterTableCommandContext.class,0);
		}
		public AlterCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alterCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterAlterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitAlterCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitAlterCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlterCommandContext alterCommand() throws RecognitionException {
		AlterCommandContext _localctx = new AlterCommandContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_alterCommand);
		try {
			setState(115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				alterDbCommand();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				alterTableCommand();
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

	@SuppressWarnings("CheckReturnValue")
	public static class CreateCommandContext extends ParserRuleContext {
		public CreateDbCommandContext createDbCommand() {
			return getRuleContext(CreateDbCommandContext.class,0);
		}
		public CreateTableCommandContext createTableCommand() {
			return getRuleContext(CreateTableCommandContext.class,0);
		}
		public CreateCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterCreateCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitCreateCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitCreateCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateCommandContext createCommand() throws RecognitionException {
		CreateCommandContext _localctx = new CreateCommandContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_createCommand);
		try {
			setState(119);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				createDbCommand();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				createTableCommand();
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

	@SuppressWarnings("CheckReturnValue")
	public static class DropCommandContext extends ParserRuleContext {
		public DropDbCommandContext dropDbCommand() {
			return getRuleContext(DropDbCommandContext.class,0);
		}
		public DropTableCommandContext dropTableCommand() {
			return getRuleContext(DropTableCommandContext.class,0);
		}
		public DropCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterDropCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitDropCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitDropCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropCommandContext dropCommand() throws RecognitionException {
		DropCommandContext _localctx = new DropCommandContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_dropCommand);
		try {
			setState(123);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				dropDbCommand();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				dropTableCommand();
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

	@SuppressWarnings("CheckReturnValue")
	public static class AlterDbCommandContext extends ParserRuleContext {
		public Token dbName;
		public AlterDatabaseStatementContext alterDatabaseStatement() {
			return getRuleContext(AlterDatabaseStatementContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public AlterDbCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alterDbCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterAlterDbCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitAlterDbCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitAlterDbCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlterDbCommandContext alterDbCommand() throws RecognitionException {
		AlterDbCommandContext _localctx = new AlterDbCommandContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_alterDbCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(T__1);
			setState(126);
			match(T__2);
			setState(127);
			((AlterDbCommandContext)_localctx).dbName = match(IDENTIFIER);
			setState(128);
			alterDatabaseStatement();
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

	@SuppressWarnings("CheckReturnValue")
	public static class CreateDbCommandContext extends ParserRuleContext {
		public Token dbName;
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public CreateDbCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createDbCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterCreateDbCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitCreateDbCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitCreateDbCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateDbCommandContext createDbCommand() throws RecognitionException {
		CreateDbCommandContext _localctx = new CreateDbCommandContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_createDbCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(T__3);
			setState(131);
			match(T__2);
			setState(132);
			((CreateDbCommandContext)_localctx).dbName = match(IDENTIFIER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DropDbCommandContext extends ParserRuleContext {
		public Token dbName;
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public DropDbCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropDbCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterDropDbCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitDropDbCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitDropDbCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropDbCommandContext dropDbCommand() throws RecognitionException {
		DropDbCommandContext _localctx = new DropDbCommandContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_dropDbCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(T__4);
			setState(135);
			match(T__2);
			setState(136);
			((DropDbCommandContext)_localctx).dbName = match(IDENTIFIER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class AlterTableCommandContext extends ParserRuleContext {
		public Token tableName;
		public AlterTableStatementContext alterTableStatement() {
			return getRuleContext(AlterTableStatementContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public AlterTableCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alterTableCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterAlterTableCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitAlterTableCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitAlterTableCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlterTableCommandContext alterTableCommand() throws RecognitionException {
		AlterTableCommandContext _localctx = new AlterTableCommandContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_alterTableCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(T__1);
			setState(139);
			match(T__5);
			setState(140);
			((AlterTableCommandContext)_localctx).tableName = match(IDENTIFIER);
			setState(141);
			alterTableStatement();
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

	@SuppressWarnings("CheckReturnValue")
	public static class CreateTableCommandContext extends ParserRuleContext {
		public Token tableName;
		public CreateTableStatementContext createTableStatement() {
			return getRuleContext(CreateTableStatementContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public CreateTableCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createTableCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterCreateTableCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitCreateTableCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitCreateTableCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateTableCommandContext createTableCommand() throws RecognitionException {
		CreateTableCommandContext _localctx = new CreateTableCommandContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_createTableCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(T__3);
			setState(144);
			match(T__5);
			setState(145);
			((CreateTableCommandContext)_localctx).tableName = match(IDENTIFIER);
			setState(146);
			createTableStatement();
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

	@SuppressWarnings("CheckReturnValue")
	public static class DropTableCommandContext extends ParserRuleContext {
		public Token tableName;
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public DropTableCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropTableCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterDropTableCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitDropTableCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitDropTableCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropTableCommandContext dropTableCommand() throws RecognitionException {
		DropTableCommandContext _localctx = new DropTableCommandContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_dropTableCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(T__4);
			setState(149);
			match(T__5);
			setState(150);
			((DropTableCommandContext)_localctx).tableName = match(IDENTIFIER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DmlCommandContext extends ParserRuleContext {
		public DeleteCommandContext deleteCommand() {
			return getRuleContext(DeleteCommandContext.class,0);
		}
		public InsertCommandContext insertCommand() {
			return getRuleContext(InsertCommandContext.class,0);
		}
		public SelectCommandContext selectCommand() {
			return getRuleContext(SelectCommandContext.class,0);
		}
		public UpdateCommandContext updateCommand() {
			return getRuleContext(UpdateCommandContext.class,0);
		}
		public DmlCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dmlCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterDmlCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitDmlCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitDmlCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DmlCommandContext dmlCommand() throws RecognitionException {
		DmlCommandContext _localctx = new DmlCommandContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_dmlCommand);
		try {
			setState(156);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				deleteCommand();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				insertCommand();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 3);
				{
				setState(154);
				selectCommand();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 4);
				{
				setState(155);
				updateCommand();
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

	@SuppressWarnings("CheckReturnValue")
	public static class DeleteCommandContext extends ParserRuleContext {
		public Token tableName;
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public DeleteCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterDeleteCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitDeleteCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitDeleteCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteCommandContext deleteCommand() throws RecognitionException {
		DeleteCommandContext _localctx = new DeleteCommandContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_deleteCommand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(T__6);
			setState(159);
			((DeleteCommandContext)_localctx).tableName = match(IDENTIFIER);
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(160);
				match(T__7);
				setState(161);
				condition();
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

	@SuppressWarnings("CheckReturnValue")
	public static class InsertCommandContext extends ParserRuleContext {
		public Token tableName;
		public ColumnListContext columnList() {
			return getRuleContext(ColumnListContext.class,0);
		}
		public ValueListContext valueList() {
			return getRuleContext(ValueListContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public InsertCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterInsertCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitInsertCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitInsertCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InsertCommandContext insertCommand() throws RecognitionException {
		InsertCommandContext _localctx = new InsertCommandContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_insertCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(T__8);
			setState(165);
			((InsertCommandContext)_localctx).tableName = match(IDENTIFIER);
			setState(166);
			match(T__9);
			setState(167);
			columnList();
			setState(168);
			match(T__10);
			setState(169);
			match(T__11);
			setState(170);
			match(T__9);
			setState(171);
			valueList();
			setState(172);
			match(T__10);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SelectCommandContext extends ParserRuleContext {
		public SelectElementsContext selectList;
		public Token tableName;
		public SelectElementsContext selectElements() {
			return getRuleContext(SelectElementsContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public SelectCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterSelectCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitSelectCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitSelectCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectCommandContext selectCommand() throws RecognitionException {
		SelectCommandContext _localctx = new SelectCommandContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_selectCommand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(T__12);
			setState(175);
			((SelectCommandContext)_localctx).selectList = selectElements();
			setState(176);
			match(T__13);
			setState(177);
			((SelectCommandContext)_localctx).tableName = match(IDENTIFIER);
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(178);
				match(T__7);
				setState(179);
				condition();
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

	@SuppressWarnings("CheckReturnValue")
	public static class UpdateCommandContext extends ParserRuleContext {
		public Token tableName;
		public UpdateElementsContext updateList;
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public UpdateElementsContext updateElements() {
			return getRuleContext(UpdateElementsContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public UpdateCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterUpdateCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitUpdateCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitUpdateCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateCommandContext updateCommand() throws RecognitionException {
		UpdateCommandContext _localctx = new UpdateCommandContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_updateCommand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			match(T__14);
			setState(183);
			((UpdateCommandContext)_localctx).tableName = match(IDENTIFIER);
			setState(184);
			match(T__15);
			setState(185);
			((UpdateCommandContext)_localctx).updateList = updateElements();
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(186);
				match(T__7);
				setState(187);
				condition();
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

	@SuppressWarnings("CheckReturnValue")
	public static class TclCommandContext extends ParserRuleContext {
		public BeginCommandContext beginCommand() {
			return getRuleContext(BeginCommandContext.class,0);
		}
		public CommitCommandContext commitCommand() {
			return getRuleContext(CommitCommandContext.class,0);
		}
		public RollbackCommandContext rollbackCommand() {
			return getRuleContext(RollbackCommandContext.class,0);
		}
		public TclCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tclCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterTclCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitTclCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitTclCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TclCommandContext tclCommand() throws RecognitionException {
		TclCommandContext _localctx = new TclCommandContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_tclCommand);
		try {
			setState(193);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
				enterOuterAlt(_localctx, 1);
				{
				setState(190);
				beginCommand();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(191);
				commitCommand();
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 3);
				{
				setState(192);
				rollbackCommand();
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

	@SuppressWarnings("CheckReturnValue")
	public static class BeginCommandContext extends ParserRuleContext {
		public BeginCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_beginCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterBeginCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitBeginCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitBeginCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BeginCommandContext beginCommand() throws RecognitionException {
		BeginCommandContext _localctx = new BeginCommandContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_beginCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			match(T__16);
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

	@SuppressWarnings("CheckReturnValue")
	public static class CommitCommandContext extends ParserRuleContext {
		public CommitCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commitCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterCommitCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitCommitCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitCommitCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommitCommandContext commitCommand() throws RecognitionException {
		CommitCommandContext _localctx = new CommitCommandContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_commitCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			match(T__17);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RollbackCommandContext extends ParserRuleContext {
		public RollbackCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rollbackCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterRollbackCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitRollbackCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitRollbackCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RollbackCommandContext rollbackCommand() throws RecognitionException {
		RollbackCommandContext _localctx = new RollbackCommandContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_rollbackCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(T__18);
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

	@SuppressWarnings("CheckReturnValue")
	public static class HelpCommandContext extends ParserRuleContext {
		public HelpCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_helpCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterHelpCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitHelpCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitHelpCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HelpCommandContext helpCommand() throws RecognitionException {
		HelpCommandContext _localctx = new HelpCommandContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_helpCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(T__19);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SelectElementsContext extends ParserRuleContext {
		public List<SelectElementContext> selectElement() {
			return getRuleContexts(SelectElementContext.class);
		}
		public SelectElementContext selectElement(int i) {
			return getRuleContext(SelectElementContext.class,i);
		}
		public SelectElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterSelectElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitSelectElements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitSelectElements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectElementsContext selectElements() throws RecognitionException {
		SelectElementsContext _localctx = new SelectElementsContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_selectElements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__20:
				{
				setState(203);
				match(T__20);
				}
				break;
			case IDENTIFIER:
				{
				setState(204);
				selectElement();
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__21) {
					{
					{
					setState(205);
					match(T__21);
					setState(206);
					selectElement();
					}
					}
					setState(211);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SelectElementContext extends ParserRuleContext {
		public Token columnName;
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public SelectElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterSelectElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitSelectElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitSelectElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectElementContext selectElement() throws RecognitionException {
		SelectElementContext _localctx = new SelectElementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_selectElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			((SelectElementContext)_localctx).columnName = match(IDENTIFIER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class UpdateElementsContext extends ParserRuleContext {
		public Token columnName;
		public LiteralContext value;
		public List<TerminalNode> IDENTIFIER() { return getTokens(SQLGrammarParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(SQLGrammarParser.IDENTIFIER, i);
		}
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public UpdateElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterUpdateElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitUpdateElements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitUpdateElements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateElementsContext updateElements() throws RecognitionException {
		UpdateElementsContext _localctx = new UpdateElementsContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_updateElements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(216);
			((UpdateElementsContext)_localctx).columnName = match(IDENTIFIER);
			setState(217);
			match(T__22);
			setState(218);
			((UpdateElementsContext)_localctx).value = literal();
			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__21) {
				{
				{
				setState(219);
				match(T__21);
				setState(220);
				((UpdateElementsContext)_localctx).columnName = match(IDENTIFIER);
				setState(221);
				match(T__22);
				setState(222);
				((UpdateElementsContext)_localctx).value = literal();
				}
				}
				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnListContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(SQLGrammarParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(SQLGrammarParser.IDENTIFIER, i);
		}
		public ColumnListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterColumnList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitColumnList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitColumnList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnListContext columnList() throws RecognitionException {
		ColumnListContext _localctx = new ColumnListContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_columnList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			match(IDENTIFIER);
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__21) {
				{
				{
				setState(229);
				match(T__21);
				setState(230);
				match(IDENTIFIER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ValueListContext extends ParserRuleContext {
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public ValueListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterValueList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitValueList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitValueList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueListContext valueList() throws RecognitionException {
		ValueListContext _localctx = new ValueListContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_valueList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			literal();
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__21) {
				{
				{
				setState(237);
				match(T__21);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<LogicalOperatorContext> logicalOperator() {
			return getRuleContexts(LogicalOperatorContext.class);
		}
		public LogicalOperatorContext logicalOperator(int i) {
			return getRuleContext(LogicalOperatorContext.class,i);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			expression();
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__26 || _la==T__27) {
				{
				{
				setState(245);
				logicalOperator();
				setState(246);
				expression();
				}
				}
				setState(252);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public Token columnName;
		public LiteralContext value;
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			((ExpressionContext)_localctx).columnName = match(IDENTIFIER);
			setState(254);
			comparisonOperator();
			setState(255);
			((ExpressionContext)_localctx).value = literal();
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

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(SQLGrammarParser.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(SQLGrammarParser.STRING, 0); }
		public TerminalNode REAL() { return getToken(SQLGrammarParser.REAL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 985162535927808L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class LogicalOperatorContext extends ParserRuleContext {
		public LogicalOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterLogicalOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitLogicalOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitLogicalOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalOperatorContext logicalOperator() throws RecognitionException {
		LogicalOperatorContext _localctx = new LogicalOperatorContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_logicalOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			_la = _input.LA(1);
			if ( !(_la==T__26 || _la==T__27) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonOperatorContext extends ParserRuleContext {
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitComparisonOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitComparisonOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 16651386880L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class AlterDatabaseStatementContext extends ParserRuleContext {
		public Token newName;
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public AlterDatabaseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alterDatabaseStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterAlterDatabaseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitAlterDatabaseStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitAlterDatabaseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlterDatabaseStatementContext alterDatabaseStatement() throws RecognitionException {
		AlterDatabaseStatementContext _localctx = new AlterDatabaseStatementContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_alterDatabaseStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			match(T__33);
			setState(264);
			((AlterDatabaseStatementContext)_localctx).newName = match(IDENTIFIER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class AlterTableStatementContext extends ParserRuleContext {
		public RenameTableStatementContext renameTableStatement() {
			return getRuleContext(RenameTableStatementContext.class,0);
		}
		public AddColumnStatementContext addColumnStatement() {
			return getRuleContext(AddColumnStatementContext.class,0);
		}
		public DropColumnStatementContext dropColumnStatement() {
			return getRuleContext(DropColumnStatementContext.class,0);
		}
		public AlterTableStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alterTableStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterAlterTableStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitAlterTableStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitAlterTableStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlterTableStatementContext alterTableStatement() throws RecognitionException {
		AlterTableStatementContext _localctx = new AlterTableStatementContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_alterTableStatement);
		try {
			setState(269);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__33:
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				renameTableStatement();
				}
				break;
			case T__34:
				enterOuterAlt(_localctx, 2);
				{
				setState(267);
				addColumnStatement();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(268);
				dropColumnStatement();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RenameTableStatementContext extends ParserRuleContext {
		public Token newName;
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public RenameTableStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_renameTableStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterRenameTableStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitRenameTableStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitRenameTableStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RenameTableStatementContext renameTableStatement() throws RecognitionException {
		RenameTableStatementContext _localctx = new RenameTableStatementContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_renameTableStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			match(T__33);
			setState(272);
			((RenameTableStatementContext)_localctx).newName = match(IDENTIFIER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class AddColumnStatementContext extends ParserRuleContext {
		public Token columnName;
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public AddColumnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addColumnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterAddColumnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitAddColumnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitAddColumnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddColumnStatementContext addColumnStatement() throws RecognitionException {
		AddColumnStatementContext _localctx = new AddColumnStatementContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_addColumnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			match(T__34);
			setState(275);
			match(T__35);
			setState(276);
			((AddColumnStatementContext)_localctx).columnName = match(IDENTIFIER);
			setState(277);
			dataType();
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

	@SuppressWarnings("CheckReturnValue")
	public static class DropColumnStatementContext extends ParserRuleContext {
		public Token columnName;
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public DropColumnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropColumnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterDropColumnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitDropColumnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitDropColumnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropColumnStatementContext dropColumnStatement() throws RecognitionException {
		DropColumnStatementContext _localctx = new DropColumnStatementContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_dropColumnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			match(T__4);
			setState(280);
			match(T__35);
			setState(281);
			((DropColumnStatementContext)_localctx).columnName = match(IDENTIFIER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class CreateTableStatementContext extends ParserRuleContext {
		public List<ColumnDefinitionContext> columnDefinition() {
			return getRuleContexts(ColumnDefinitionContext.class);
		}
		public ColumnDefinitionContext columnDefinition(int i) {
			return getRuleContext(ColumnDefinitionContext.class,i);
		}
		public CreateTableStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createTableStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterCreateTableStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitCreateTableStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitCreateTableStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateTableStatementContext createTableStatement() throws RecognitionException {
		CreateTableStatementContext _localctx = new CreateTableStatementContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_createTableStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			match(T__9);
			setState(284);
			columnDefinition();
			setState(289);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__21) {
				{
				{
				setState(285);
				match(T__21);
				setState(286);
				columnDefinition();
				}
				}
				setState(291);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(292);
			match(T__10);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnDefinitionContext extends ParserRuleContext {
		public Token columnName;
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(SQLGrammarParser.IDENTIFIER, 0); }
		public ColumnConstraintContext columnConstraint() {
			return getRuleContext(ColumnConstraintContext.class,0);
		}
		public ColumnDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterColumnDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitColumnDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitColumnDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnDefinitionContext columnDefinition() throws RecognitionException {
		ColumnDefinitionContext _localctx = new ColumnDefinitionContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_columnDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			((ColumnDefinitionContext)_localctx).columnName = match(IDENTIFIER);
			setState(295);
			dataType();
			setState(297);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 68169720922112L) != 0)) {
				{
				setState(296);
				columnConstraint();
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

	@SuppressWarnings("CheckReturnValue")
	public static class DataTypeContext extends ParserRuleContext {
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitDataType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitDataType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_dataType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2061584302080L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnConstraintContext extends ParserRuleContext {
		public CheckContext check() {
			return getRuleContext(CheckContext.class,0);
		}
		public ForeignKeyContext foreignKey() {
			return getRuleContext(ForeignKeyContext.class,0);
		}
		public ColumnConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterColumnConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitColumnConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitColumnConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnConstraintContext columnConstraint() throws RecognitionException {
		ColumnConstraintContext _localctx = new ColumnConstraintContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_columnConstraint);
		try {
			setState(306);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__43:
				enterOuterAlt(_localctx, 1);
				{
				setState(301);
				check();
				}
				break;
			case T__44:
				enterOuterAlt(_localctx, 2);
				{
				setState(302);
				foreignKey();
				}
				break;
			case T__40:
				enterOuterAlt(_localctx, 3);
				{
				setState(303);
				match(T__40);
				}
				break;
			case T__41:
				enterOuterAlt(_localctx, 4);
				{
				setState(304);
				match(T__41);
				}
				break;
			case T__42:
				enterOuterAlt(_localctx, 5);
				{
				setState(305);
				match(T__42);
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

	@SuppressWarnings("CheckReturnValue")
	public static class CheckContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public CheckContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_check; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterCheck(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitCheck(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitCheck(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CheckContext check() throws RecognitionException {
		CheckContext _localctx = new CheckContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_check);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			match(T__43);
			setState(309);
			match(T__9);
			setState(310);
			condition();
			setState(311);
			match(T__10);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ForeignKeyContext extends ParserRuleContext {
		public Token tableName;
		public Token columnName;
		public List<TerminalNode> IDENTIFIER() { return getTokens(SQLGrammarParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(SQLGrammarParser.IDENTIFIER, i);
		}
		public ForeignKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreignKey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).enterForeignKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLGrammarListener ) ((SQLGrammarListener)listener).exitForeignKey(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLGrammarVisitor ) return ((SQLGrammarVisitor<? extends T>)visitor).visitForeignKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForeignKeyContext foreignKey() throws RecognitionException {
		ForeignKeyContext _localctx = new ForeignKeyContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_foreignKey);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			match(T__44);
			setState(314);
			((ForeignKeyContext)_localctx).tableName = match(IDENTIFIER);
			setState(315);
			match(T__9);
			setState(316);
			((ForeignKeyContext)_localctx).columnName = match(IDENTIFIER);
			setState(317);
			match(T__10);
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

	public static final String _serializedATN =
		"\u0004\u00014\u0140\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"`\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002e\b\u0002\n\u0002"+
		"\f\u0002h\t\u0002\u0001\u0002\u0003\u0002k\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003p\b\u0003\u0001\u0004\u0001\u0004\u0003\u0004"+
		"t\b\u0004\u0001\u0005\u0001\u0005\u0003\u0005x\b\u0005\u0001\u0006\u0001"+
		"\u0006\u0003\u0006|\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0003\r\u009d\b\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0003\u000e\u00a3\b\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0003\u0010\u00b5\b\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00bd\b\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00c2\b\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001"+
		"\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u00d0"+
		"\b\u0017\n\u0017\f\u0017\u00d3\t\u0017\u0003\u0017\u00d5\b\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u00e0\b\u0019\n\u0019\f\u0019"+
		"\u00e3\t\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0005\u001a\u00e8\b"+
		"\u001a\n\u001a\f\u001a\u00eb\t\u001a\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0005\u001b\u00f0\b\u001b\n\u001b\f\u001b\u00f3\t\u001b\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0005\u001c\u00f9\b\u001c\n\u001c\f\u001c"+
		"\u00fc\t\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e"+
		"\u0001\u001e\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001!\u0001!\u0001"+
		"!\u0001\"\u0001\"\u0001\"\u0003\"\u010e\b\"\u0001#\u0001#\u0001#\u0001"+
		"$\u0001$\u0001$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001%\u0001&\u0001"+
		"&\u0001&\u0001&\u0005&\u0120\b&\n&\f&\u0123\t&\u0001&\u0001&\u0001\'\u0001"+
		"\'\u0001\'\u0003\'\u012a\b\'\u0001(\u0001(\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0003)\u0133\b)\u0001*\u0001*\u0001*\u0001*\u0001*\u0001+\u0001"+
		"+\u0001+\u0001+\u0001+\u0001+\u0001+\u0000\u0000,\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,."+
		"02468:<>@BDFHJLNPRTV\u0000\u0004\u0002\u0000\u0018\u001a/1\u0001\u0000"+
		"\u001b\u001c\u0002\u0000\u0017\u0017\u001d!\u0001\u0000%(\u0133\u0000"+
		"X\u0001\u0000\u0000\u0000\u0002_\u0001\u0000\u0000\u0000\u0004a\u0001"+
		"\u0000\u0000\u0000\u0006o\u0001\u0000\u0000\u0000\bs\u0001\u0000\u0000"+
		"\u0000\nw\u0001\u0000\u0000\u0000\f{\u0001\u0000\u0000\u0000\u000e}\u0001"+
		"\u0000\u0000\u0000\u0010\u0082\u0001\u0000\u0000\u0000\u0012\u0086\u0001"+
		"\u0000\u0000\u0000\u0014\u008a\u0001\u0000\u0000\u0000\u0016\u008f\u0001"+
		"\u0000\u0000\u0000\u0018\u0094\u0001\u0000\u0000\u0000\u001a\u009c\u0001"+
		"\u0000\u0000\u0000\u001c\u009e\u0001\u0000\u0000\u0000\u001e\u00a4\u0001"+
		"\u0000\u0000\u0000 \u00ae\u0001\u0000\u0000\u0000\"\u00b6\u0001\u0000"+
		"\u0000\u0000$\u00c1\u0001\u0000\u0000\u0000&\u00c3\u0001\u0000\u0000\u0000"+
		"(\u00c5\u0001\u0000\u0000\u0000*\u00c7\u0001\u0000\u0000\u0000,\u00c9"+
		"\u0001\u0000\u0000\u0000.\u00d4\u0001\u0000\u0000\u00000\u00d6\u0001\u0000"+
		"\u0000\u00002\u00d8\u0001\u0000\u0000\u00004\u00e4\u0001\u0000\u0000\u0000"+
		"6\u00ec\u0001\u0000\u0000\u00008\u00f4\u0001\u0000\u0000\u0000:\u00fd"+
		"\u0001\u0000\u0000\u0000<\u0101\u0001\u0000\u0000\u0000>\u0103\u0001\u0000"+
		"\u0000\u0000@\u0105\u0001\u0000\u0000\u0000B\u0107\u0001\u0000\u0000\u0000"+
		"D\u010d\u0001\u0000\u0000\u0000F\u010f\u0001\u0000\u0000\u0000H\u0112"+
		"\u0001\u0000\u0000\u0000J\u0117\u0001\u0000\u0000\u0000L\u011b\u0001\u0000"+
		"\u0000\u0000N\u0126\u0001\u0000\u0000\u0000P\u012b\u0001\u0000\u0000\u0000"+
		"R\u0132\u0001\u0000\u0000\u0000T\u0134\u0001\u0000\u0000\u0000V\u0139"+
		"\u0001\u0000\u0000\u0000XY\u0003\u0004\u0002\u0000YZ\u0005\u0000\u0000"+
		"\u0001Z\u0001\u0001\u0000\u0000\u0000[`\u0003\u0006\u0003\u0000\\`\u0003"+
		"\u001a\r\u0000]`\u0003$\u0012\u0000^`\u0003,\u0016\u0000_[\u0001\u0000"+
		"\u0000\u0000_\\\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000_^\u0001"+
		"\u0000\u0000\u0000`\u0003\u0001\u0000\u0000\u0000af\u0003\u0002\u0001"+
		"\u0000bc\u0005\u0001\u0000\u0000ce\u0003\u0002\u0001\u0000db\u0001\u0000"+
		"\u0000\u0000eh\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001"+
		"\u0000\u0000\u0000gj\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000"+
		"ik\u0005\u0001\u0000\u0000ji\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000"+
		"\u0000k\u0005\u0001\u0000\u0000\u0000lp\u0003\b\u0004\u0000mp\u0003\n"+
		"\u0005\u0000np\u0003\f\u0006\u0000ol\u0001\u0000\u0000\u0000om\u0001\u0000"+
		"\u0000\u0000on\u0001\u0000\u0000\u0000p\u0007\u0001\u0000\u0000\u0000"+
		"qt\u0003\u000e\u0007\u0000rt\u0003\u0014\n\u0000sq\u0001\u0000\u0000\u0000"+
		"sr\u0001\u0000\u0000\u0000t\t\u0001\u0000\u0000\u0000ux\u0003\u0010\b"+
		"\u0000vx\u0003\u0016\u000b\u0000wu\u0001\u0000\u0000\u0000wv\u0001\u0000"+
		"\u0000\u0000x\u000b\u0001\u0000\u0000\u0000y|\u0003\u0012\t\u0000z|\u0003"+
		"\u0018\f\u0000{y\u0001\u0000\u0000\u0000{z\u0001\u0000\u0000\u0000|\r"+
		"\u0001\u0000\u0000\u0000}~\u0005\u0002\u0000\u0000~\u007f\u0005\u0003"+
		"\u0000\u0000\u007f\u0080\u0005.\u0000\u0000\u0080\u0081\u0003B!\u0000"+
		"\u0081\u000f\u0001\u0000\u0000\u0000\u0082\u0083\u0005\u0004\u0000\u0000"+
		"\u0083\u0084\u0005\u0003\u0000\u0000\u0084\u0085\u0005.\u0000\u0000\u0085"+
		"\u0011\u0001\u0000\u0000\u0000\u0086\u0087\u0005\u0005\u0000\u0000\u0087"+
		"\u0088\u0005\u0003\u0000\u0000\u0088\u0089\u0005.\u0000\u0000\u0089\u0013"+
		"\u0001\u0000\u0000\u0000\u008a\u008b\u0005\u0002\u0000\u0000\u008b\u008c"+
		"\u0005\u0006\u0000\u0000\u008c\u008d\u0005.\u0000\u0000\u008d\u008e\u0003"+
		"D\"\u0000\u008e\u0015\u0001\u0000\u0000\u0000\u008f\u0090\u0005\u0004"+
		"\u0000\u0000\u0090\u0091\u0005\u0006\u0000\u0000\u0091\u0092\u0005.\u0000"+
		"\u0000\u0092\u0093\u0003L&\u0000\u0093\u0017\u0001\u0000\u0000\u0000\u0094"+
		"\u0095\u0005\u0005\u0000\u0000\u0095\u0096\u0005\u0006\u0000\u0000\u0096"+
		"\u0097\u0005.\u0000\u0000\u0097\u0019\u0001\u0000\u0000\u0000\u0098\u009d"+
		"\u0003\u001c\u000e\u0000\u0099\u009d\u0003\u001e\u000f\u0000\u009a\u009d"+
		"\u0003 \u0010\u0000\u009b\u009d\u0003\"\u0011\u0000\u009c\u0098\u0001"+
		"\u0000\u0000\u0000\u009c\u0099\u0001\u0000\u0000\u0000\u009c\u009a\u0001"+
		"\u0000\u0000\u0000\u009c\u009b\u0001\u0000\u0000\u0000\u009d\u001b\u0001"+
		"\u0000\u0000\u0000\u009e\u009f\u0005\u0007\u0000\u0000\u009f\u00a2\u0005"+
		".\u0000\u0000\u00a0\u00a1\u0005\b\u0000\u0000\u00a1\u00a3\u00038\u001c"+
		"\u0000\u00a2\u00a0\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000"+
		"\u0000\u00a3\u001d\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005\t\u0000\u0000"+
		"\u00a5\u00a6\u0005.\u0000\u0000\u00a6\u00a7\u0005\n\u0000\u0000\u00a7"+
		"\u00a8\u00034\u001a\u0000\u00a8\u00a9\u0005\u000b\u0000\u0000\u00a9\u00aa"+
		"\u0005\f\u0000\u0000\u00aa\u00ab\u0005\n\u0000\u0000\u00ab\u00ac\u0003"+
		"6\u001b\u0000\u00ac\u00ad\u0005\u000b\u0000\u0000\u00ad\u001f\u0001\u0000"+
		"\u0000\u0000\u00ae\u00af\u0005\r\u0000\u0000\u00af\u00b0\u0003.\u0017"+
		"\u0000\u00b0\u00b1\u0005\u000e\u0000\u0000\u00b1\u00b4\u0005.\u0000\u0000"+
		"\u00b2\u00b3\u0005\b\u0000\u0000\u00b3\u00b5\u00038\u001c\u0000\u00b4"+
		"\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000\u00b5"+
		"!\u0001\u0000\u0000\u0000\u00b6\u00b7\u0005\u000f\u0000\u0000\u00b7\u00b8"+
		"\u0005.\u0000\u0000\u00b8\u00b9\u0005\u0010\u0000\u0000\u00b9\u00bc\u0003"+
		"2\u0019\u0000\u00ba\u00bb\u0005\b\u0000\u0000\u00bb\u00bd\u00038\u001c"+
		"\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001\u0000\u0000"+
		"\u0000\u00bd#\u0001\u0000\u0000\u0000\u00be\u00c2\u0003&\u0013\u0000\u00bf"+
		"\u00c2\u0003(\u0014\u0000\u00c0\u00c2\u0003*\u0015\u0000\u00c1\u00be\u0001"+
		"\u0000\u0000\u0000\u00c1\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c0\u0001"+
		"\u0000\u0000\u0000\u00c2%\u0001\u0000\u0000\u0000\u00c3\u00c4\u0005\u0011"+
		"\u0000\u0000\u00c4\'\u0001\u0000\u0000\u0000\u00c5\u00c6\u0005\u0012\u0000"+
		"\u0000\u00c6)\u0001\u0000\u0000\u0000\u00c7\u00c8\u0005\u0013\u0000\u0000"+
		"\u00c8+\u0001\u0000\u0000\u0000\u00c9\u00ca\u0005\u0014\u0000\u0000\u00ca"+
		"-\u0001\u0000\u0000\u0000\u00cb\u00d5\u0005\u0015\u0000\u0000\u00cc\u00d1"+
		"\u00030\u0018\u0000\u00cd\u00ce\u0005\u0016\u0000\u0000\u00ce\u00d0\u0003"+
		"0\u0018\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000\u00d0\u00d3\u0001\u0000"+
		"\u0000\u0000\u00d1\u00cf\u0001\u0000\u0000\u0000\u00d1\u00d2\u0001\u0000"+
		"\u0000\u0000\u00d2\u00d5\u0001\u0000\u0000\u0000\u00d3\u00d1\u0001\u0000"+
		"\u0000\u0000\u00d4\u00cb\u0001\u0000\u0000\u0000\u00d4\u00cc\u0001\u0000"+
		"\u0000\u0000\u00d5/\u0001\u0000\u0000\u0000\u00d6\u00d7\u0005.\u0000\u0000"+
		"\u00d71\u0001\u0000\u0000\u0000\u00d8\u00d9\u0005.\u0000\u0000\u00d9\u00da"+
		"\u0005\u0017\u0000\u0000\u00da\u00e1\u0003<\u001e\u0000\u00db\u00dc\u0005"+
		"\u0016\u0000\u0000\u00dc\u00dd\u0005.\u0000\u0000\u00dd\u00de\u0005\u0017"+
		"\u0000\u0000\u00de\u00e0\u0003<\u001e\u0000\u00df\u00db\u0001\u0000\u0000"+
		"\u0000\u00e0\u00e3\u0001\u0000\u0000\u0000\u00e1\u00df\u0001\u0000\u0000"+
		"\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e23\u0001\u0000\u0000\u0000"+
		"\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e4\u00e9\u0005.\u0000\u0000\u00e5"+
		"\u00e6\u0005\u0016\u0000\u0000\u00e6\u00e8\u0005.\u0000\u0000\u00e7\u00e5"+
		"\u0001\u0000\u0000\u0000\u00e8\u00eb\u0001\u0000\u0000\u0000\u00e9\u00e7"+
		"\u0001\u0000\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000\u0000\u00ea5\u0001"+
		"\u0000\u0000\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00ec\u00f1\u0003"+
		"<\u001e\u0000\u00ed\u00ee\u0005\u0016\u0000\u0000\u00ee\u00f0\u0003<\u001e"+
		"\u0000\u00ef\u00ed\u0001\u0000\u0000\u0000\u00f0\u00f3\u0001\u0000\u0000"+
		"\u0000\u00f1\u00ef\u0001\u0000\u0000\u0000\u00f1\u00f2\u0001\u0000\u0000"+
		"\u0000\u00f27\u0001\u0000\u0000\u0000\u00f3\u00f1\u0001\u0000\u0000\u0000"+
		"\u00f4\u00fa\u0003:\u001d\u0000\u00f5\u00f6\u0003>\u001f\u0000\u00f6\u00f7"+
		"\u0003:\u001d\u0000\u00f7\u00f9\u0001\u0000\u0000\u0000\u00f8\u00f5\u0001"+
		"\u0000\u0000\u0000\u00f9\u00fc\u0001\u0000\u0000\u0000\u00fa\u00f8\u0001"+
		"\u0000\u0000\u0000\u00fa\u00fb\u0001\u0000\u0000\u0000\u00fb9\u0001\u0000"+
		"\u0000\u0000\u00fc\u00fa\u0001\u0000\u0000\u0000\u00fd\u00fe\u0005.\u0000"+
		"\u0000\u00fe\u00ff\u0003@ \u0000\u00ff\u0100\u0003<\u001e\u0000\u0100"+
		";\u0001\u0000\u0000\u0000\u0101\u0102\u0007\u0000\u0000\u0000\u0102=\u0001"+
		"\u0000\u0000\u0000\u0103\u0104\u0007\u0001\u0000\u0000\u0104?\u0001\u0000"+
		"\u0000\u0000\u0105\u0106\u0007\u0002\u0000\u0000\u0106A\u0001\u0000\u0000"+
		"\u0000\u0107\u0108\u0005\"\u0000\u0000\u0108\u0109\u0005.\u0000\u0000"+
		"\u0109C\u0001\u0000\u0000\u0000\u010a\u010e\u0003F#\u0000\u010b\u010e"+
		"\u0003H$\u0000\u010c\u010e\u0003J%\u0000\u010d\u010a\u0001\u0000\u0000"+
		"\u0000\u010d\u010b\u0001\u0000\u0000\u0000\u010d\u010c\u0001\u0000\u0000"+
		"\u0000\u010eE\u0001\u0000\u0000\u0000\u010f\u0110\u0005\"\u0000\u0000"+
		"\u0110\u0111\u0005.\u0000\u0000\u0111G\u0001\u0000\u0000\u0000\u0112\u0113"+
		"\u0005#\u0000\u0000\u0113\u0114\u0005$\u0000\u0000\u0114\u0115\u0005."+
		"\u0000\u0000\u0115\u0116\u0003P(\u0000\u0116I\u0001\u0000\u0000\u0000"+
		"\u0117\u0118\u0005\u0005\u0000\u0000\u0118\u0119\u0005$\u0000\u0000\u0119"+
		"\u011a\u0005.\u0000\u0000\u011aK\u0001\u0000\u0000\u0000\u011b\u011c\u0005"+
		"\n\u0000\u0000\u011c\u0121\u0003N\'\u0000\u011d\u011e\u0005\u0016\u0000"+
		"\u0000\u011e\u0120\u0003N\'\u0000\u011f\u011d\u0001\u0000\u0000\u0000"+
		"\u0120\u0123\u0001\u0000\u0000\u0000\u0121\u011f\u0001\u0000\u0000\u0000"+
		"\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u0124\u0001\u0000\u0000\u0000"+
		"\u0123\u0121\u0001\u0000\u0000\u0000\u0124\u0125\u0005\u000b\u0000\u0000"+
		"\u0125M\u0001\u0000\u0000\u0000\u0126\u0127\u0005.\u0000\u0000\u0127\u0129"+
		"\u0003P(\u0000\u0128\u012a\u0003R)\u0000\u0129\u0128\u0001\u0000\u0000"+
		"\u0000\u0129\u012a\u0001\u0000\u0000\u0000\u012aO\u0001\u0000\u0000\u0000"+
		"\u012b\u012c\u0007\u0003\u0000\u0000\u012cQ\u0001\u0000\u0000\u0000\u012d"+
		"\u0133\u0003T*\u0000\u012e\u0133\u0003V+\u0000\u012f\u0133\u0005)\u0000"+
		"\u0000\u0130\u0133\u0005*\u0000\u0000\u0131\u0133\u0005+\u0000\u0000\u0132"+
		"\u012d\u0001\u0000\u0000\u0000\u0132\u012e\u0001\u0000\u0000\u0000\u0132"+
		"\u012f\u0001\u0000\u0000\u0000\u0132\u0130\u0001\u0000\u0000\u0000\u0132"+
		"\u0131\u0001\u0000\u0000\u0000\u0133S\u0001\u0000\u0000\u0000\u0134\u0135"+
		"\u0005,\u0000\u0000\u0135\u0136\u0005\n\u0000\u0000\u0136\u0137\u0003"+
		"8\u001c\u0000\u0137\u0138\u0005\u000b\u0000\u0000\u0138U\u0001\u0000\u0000"+
		"\u0000\u0139\u013a\u0005-\u0000\u0000\u013a\u013b\u0005.\u0000\u0000\u013b"+
		"\u013c\u0005\n\u0000\u0000\u013c\u013d\u0005.\u0000\u0000\u013d\u013e"+
		"\u0005\u000b\u0000\u0000\u013eW\u0001\u0000\u0000\u0000\u0016_fjosw{\u009c"+
		"\u00a2\u00b4\u00bc\u00c1\u00d1\u00d4\u00e1\u00e9\u00f1\u00fa\u010d\u0121"+
		"\u0129\u0132";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}