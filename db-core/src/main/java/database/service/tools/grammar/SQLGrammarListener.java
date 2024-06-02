// Generated from C:/Users/Alexandr/Desktop/Project/.git/db-core/src/main/java/database/service/tools/grammar/SQLGrammar.g4 by ANTLR 4.13.1
package database.service.tools.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SQLGrammarParser}.
 */
public interface SQLGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(SQLGrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(SQLGrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#sqlCommand}.
	 * @param ctx the parse tree
	 */
	void enterSqlCommand(SQLGrammarParser.SqlCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#sqlCommand}.
	 * @param ctx the parse tree
	 */
	void exitSqlCommand(SQLGrammarParser.SqlCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#sqlCommands}.
	 * @param ctx the parse tree
	 */
	void enterSqlCommands(SQLGrammarParser.SqlCommandsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#sqlCommands}.
	 * @param ctx the parse tree
	 */
	void exitSqlCommands(SQLGrammarParser.SqlCommandsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#ddlCommand}.
	 * @param ctx the parse tree
	 */
	void enterDdlCommand(SQLGrammarParser.DdlCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#ddlCommand}.
	 * @param ctx the parse tree
	 */
	void exitDdlCommand(SQLGrammarParser.DdlCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#alterCommand}.
	 * @param ctx the parse tree
	 */
	void enterAlterCommand(SQLGrammarParser.AlterCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#alterCommand}.
	 * @param ctx the parse tree
	 */
	void exitAlterCommand(SQLGrammarParser.AlterCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#createCommand}.
	 * @param ctx the parse tree
	 */
	void enterCreateCommand(SQLGrammarParser.CreateCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#createCommand}.
	 * @param ctx the parse tree
	 */
	void exitCreateCommand(SQLGrammarParser.CreateCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#dropCommand}.
	 * @param ctx the parse tree
	 */
	void enterDropCommand(SQLGrammarParser.DropCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#dropCommand}.
	 * @param ctx the parse tree
	 */
	void exitDropCommand(SQLGrammarParser.DropCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#alterDbCommand}.
	 * @param ctx the parse tree
	 */
	void enterAlterDbCommand(SQLGrammarParser.AlterDbCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#alterDbCommand}.
	 * @param ctx the parse tree
	 */
	void exitAlterDbCommand(SQLGrammarParser.AlterDbCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#createDbCommand}.
	 * @param ctx the parse tree
	 */
	void enterCreateDbCommand(SQLGrammarParser.CreateDbCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#createDbCommand}.
	 * @param ctx the parse tree
	 */
	void exitCreateDbCommand(SQLGrammarParser.CreateDbCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#dropDbCommand}.
	 * @param ctx the parse tree
	 */
	void enterDropDbCommand(SQLGrammarParser.DropDbCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#dropDbCommand}.
	 * @param ctx the parse tree
	 */
	void exitDropDbCommand(SQLGrammarParser.DropDbCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#alterTableCommand}.
	 * @param ctx the parse tree
	 */
	void enterAlterTableCommand(SQLGrammarParser.AlterTableCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#alterTableCommand}.
	 * @param ctx the parse tree
	 */
	void exitAlterTableCommand(SQLGrammarParser.AlterTableCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#createTableCommand}.
	 * @param ctx the parse tree
	 */
	void enterCreateTableCommand(SQLGrammarParser.CreateTableCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#createTableCommand}.
	 * @param ctx the parse tree
	 */
	void exitCreateTableCommand(SQLGrammarParser.CreateTableCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#dropTableCommand}.
	 * @param ctx the parse tree
	 */
	void enterDropTableCommand(SQLGrammarParser.DropTableCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#dropTableCommand}.
	 * @param ctx the parse tree
	 */
	void exitDropTableCommand(SQLGrammarParser.DropTableCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#dmlCommand}.
	 * @param ctx the parse tree
	 */
	void enterDmlCommand(SQLGrammarParser.DmlCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#dmlCommand}.
	 * @param ctx the parse tree
	 */
	void exitDmlCommand(SQLGrammarParser.DmlCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#deleteCommand}.
	 * @param ctx the parse tree
	 */
	void enterDeleteCommand(SQLGrammarParser.DeleteCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#deleteCommand}.
	 * @param ctx the parse tree
	 */
	void exitDeleteCommand(SQLGrammarParser.DeleteCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#insertCommand}.
	 * @param ctx the parse tree
	 */
	void enterInsertCommand(SQLGrammarParser.InsertCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#insertCommand}.
	 * @param ctx the parse tree
	 */
	void exitInsertCommand(SQLGrammarParser.InsertCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#selectCommand}.
	 * @param ctx the parse tree
	 */
	void enterSelectCommand(SQLGrammarParser.SelectCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#selectCommand}.
	 * @param ctx the parse tree
	 */
	void exitSelectCommand(SQLGrammarParser.SelectCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#updateCommand}.
	 * @param ctx the parse tree
	 */
	void enterUpdateCommand(SQLGrammarParser.UpdateCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#updateCommand}.
	 * @param ctx the parse tree
	 */
	void exitUpdateCommand(SQLGrammarParser.UpdateCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#tclCommand}.
	 * @param ctx the parse tree
	 */
	void enterTclCommand(SQLGrammarParser.TclCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#tclCommand}.
	 * @param ctx the parse tree
	 */
	void exitTclCommand(SQLGrammarParser.TclCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#beginCommand}.
	 * @param ctx the parse tree
	 */
	void enterBeginCommand(SQLGrammarParser.BeginCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#beginCommand}.
	 * @param ctx the parse tree
	 */
	void exitBeginCommand(SQLGrammarParser.BeginCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#commitCommand}.
	 * @param ctx the parse tree
	 */
	void enterCommitCommand(SQLGrammarParser.CommitCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#commitCommand}.
	 * @param ctx the parse tree
	 */
	void exitCommitCommand(SQLGrammarParser.CommitCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#rollbackCommand}.
	 * @param ctx the parse tree
	 */
	void enterRollbackCommand(SQLGrammarParser.RollbackCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#rollbackCommand}.
	 * @param ctx the parse tree
	 */
	void exitRollbackCommand(SQLGrammarParser.RollbackCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void enterSelectElements(SQLGrammarParser.SelectElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void exitSelectElements(SQLGrammarParser.SelectElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectElement(SQLGrammarParser.SelectElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectElement(SQLGrammarParser.SelectElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#updateElements}.
	 * @param ctx the parse tree
	 */
	void enterUpdateElements(SQLGrammarParser.UpdateElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#updateElements}.
	 * @param ctx the parse tree
	 */
	void exitUpdateElements(SQLGrammarParser.UpdateElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#columnList}.
	 * @param ctx the parse tree
	 */
	void enterColumnList(SQLGrammarParser.ColumnListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#columnList}.
	 * @param ctx the parse tree
	 */
	void exitColumnList(SQLGrammarParser.ColumnListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#valueList}.
	 * @param ctx the parse tree
	 */
	void enterValueList(SQLGrammarParser.ValueListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#valueList}.
	 * @param ctx the parse tree
	 */
	void exitValueList(SQLGrammarParser.ValueListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(SQLGrammarParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(SQLGrammarParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(SQLGrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(SQLGrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(SQLGrammarParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(SQLGrammarParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperator(SQLGrammarParser.LogicalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperator(SQLGrammarParser.LogicalOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(SQLGrammarParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(SQLGrammarParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#alterDatabaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterAlterDatabaseStatement(SQLGrammarParser.AlterDatabaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#alterDatabaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitAlterDatabaseStatement(SQLGrammarParser.AlterDatabaseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#alterTableStatement}.
	 * @param ctx the parse tree
	 */
	void enterAlterTableStatement(SQLGrammarParser.AlterTableStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#alterTableStatement}.
	 * @param ctx the parse tree
	 */
	void exitAlterTableStatement(SQLGrammarParser.AlterTableStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#renameTableStatement}.
	 * @param ctx the parse tree
	 */
	void enterRenameTableStatement(SQLGrammarParser.RenameTableStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#renameTableStatement}.
	 * @param ctx the parse tree
	 */
	void exitRenameTableStatement(SQLGrammarParser.RenameTableStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#addColumnStatement}.
	 * @param ctx the parse tree
	 */
	void enterAddColumnStatement(SQLGrammarParser.AddColumnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#addColumnStatement}.
	 * @param ctx the parse tree
	 */
	void exitAddColumnStatement(SQLGrammarParser.AddColumnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#dropColumnStatement}.
	 * @param ctx the parse tree
	 */
	void enterDropColumnStatement(SQLGrammarParser.DropColumnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#dropColumnStatement}.
	 * @param ctx the parse tree
	 */
	void exitDropColumnStatement(SQLGrammarParser.DropColumnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#createTableStatement}.
	 * @param ctx the parse tree
	 */
	void enterCreateTableStatement(SQLGrammarParser.CreateTableStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#createTableStatement}.
	 * @param ctx the parse tree
	 */
	void exitCreateTableStatement(SQLGrammarParser.CreateTableStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void enterColumnDefinition(SQLGrammarParser.ColumnDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void exitColumnDefinition(SQLGrammarParser.ColumnDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterDataType(SQLGrammarParser.DataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitDataType(SQLGrammarParser.DataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterColumnConstraint(SQLGrammarParser.ColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitColumnConstraint(SQLGrammarParser.ColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#check}.
	 * @param ctx the parse tree
	 */
	void enterCheck(SQLGrammarParser.CheckContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#check}.
	 * @param ctx the parse tree
	 */
	void exitCheck(SQLGrammarParser.CheckContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLGrammarParser#foreignKey}.
	 * @param ctx the parse tree
	 */
	void enterForeignKey(SQLGrammarParser.ForeignKeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLGrammarParser#foreignKey}.
	 * @param ctx the parse tree
	 */
	void exitForeignKey(SQLGrammarParser.ForeignKeyContext ctx);
}