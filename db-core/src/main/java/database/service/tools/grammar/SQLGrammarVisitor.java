// Generated from /Users/dmitriykosolobov/university_project/java-data-base/db-core/src/main/java/database/service/tools/grammar/SQLGrammar.g4 by ANTLR 4.13.1
package database.service.tools.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SQLGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SQLGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(SQLGrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#sqlCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlCommand(SQLGrammarParser.SqlCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#sqlCommands}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlCommands(SQLGrammarParser.SqlCommandsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#ddlCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDdlCommand(SQLGrammarParser.DdlCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#alterCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterCommand(SQLGrammarParser.AlterCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#createCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateCommand(SQLGrammarParser.CreateCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#dropCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropCommand(SQLGrammarParser.DropCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#alterDbCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterDbCommand(SQLGrammarParser.AlterDbCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#createDbCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateDbCommand(SQLGrammarParser.CreateDbCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#dropDbCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropDbCommand(SQLGrammarParser.DropDbCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#alterTableCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterTableCommand(SQLGrammarParser.AlterTableCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#createTableCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTableCommand(SQLGrammarParser.CreateTableCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#dropTableCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropTableCommand(SQLGrammarParser.DropTableCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#dmlCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDmlCommand(SQLGrammarParser.DmlCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#deleteCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteCommand(SQLGrammarParser.DeleteCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#insertCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertCommand(SQLGrammarParser.InsertCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#selectCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectCommand(SQLGrammarParser.SelectCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#updateCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateCommand(SQLGrammarParser.UpdateCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#tclCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTclCommand(SQLGrammarParser.TclCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#beginCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeginCommand(SQLGrammarParser.BeginCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#commitCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommitCommand(SQLGrammarParser.CommitCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#rollbackCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRollbackCommand(SQLGrammarParser.RollbackCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#helpCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHelpCommand(SQLGrammarParser.HelpCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#commandName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommandName(SQLGrammarParser.CommandNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#selectElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectElements(SQLGrammarParser.SelectElementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectElement(SQLGrammarParser.SelectElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#updateElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateElements(SQLGrammarParser.UpdateElementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#columnList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnList(SQLGrammarParser.ColumnListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#valueList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueList(SQLGrammarParser.ValueListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(SQLGrammarParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(SQLGrammarParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(SQLGrammarParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#logicalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOperator(SQLGrammarParser.LogicalOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(SQLGrammarParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#alterDatabaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterDatabaseStatement(SQLGrammarParser.AlterDatabaseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#alterTableStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterTableStatement(SQLGrammarParser.AlterTableStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#renameTableStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameTableStatement(SQLGrammarParser.RenameTableStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#addColumnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddColumnStatement(SQLGrammarParser.AddColumnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#dropColumnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropColumnStatement(SQLGrammarParser.DropColumnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#createTableStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTableStatement(SQLGrammarParser.CreateTableStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#columnDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnDefinition(SQLGrammarParser.ColumnDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataType(SQLGrammarParser.DataTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnConstraint(SQLGrammarParser.ColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#check}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheck(SQLGrammarParser.CheckContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLGrammarParser#foreignKey}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForeignKey(SQLGrammarParser.ForeignKeyContext ctx);
}