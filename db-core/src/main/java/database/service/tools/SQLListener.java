package database.service.tools;

import database.service.tools.grammar.SQLGrammarBaseListener;
import database.service.tools.grammar.SQLGrammarParser;

import java.util.ArrayList;
import java.util.List;

public class SQLListener extends SQLGrammarBaseListener {

    private record ColumnDefinition(
        String name,
        String dataType,
        String constraint
    ){
    }

    private record Expression(
        String columnName,
        String operator,
        String value
    ){
    }

    private String currentTableName;

    @Override
    public void enterStart(SQLGrammarParser.StartContext ctx) {
        currentTableName = null;
    }

    @Override
    public void exitAlterDbCommand(SQLGrammarParser.AlterDbCommandContext ctx) {
        String dbName = ctx.dbName.getText();
        String newDbName = ctx.alterDatabaseStatement().newName.getText();

        //вызов функции
        System.out.println("ALTER DB command");
    }

    @Override
    public void exitCreateDbCommand(SQLGrammarParser.CreateDbCommandContext ctx) {
        String dbName = ctx.dbName.getText();

        //вызов функции
        System.out.println("CREATE DB command");
    }

    @Override
    public void exitDropDbCommand(SQLGrammarParser.DropDbCommandContext ctx) {
        String dbName = ctx.dbName.getText();

        //вызов функции
        System.out.println("DROP DB command");
    }

    @Override
    public void enterAlterTableCommand(SQLGrammarParser.AlterTableCommandContext ctx) {
        currentTableName = ctx.tableName.getText();
    }

    @Override
    public void exitAlterTableCommand(SQLGrammarParser.AlterTableCommandContext ctx) { //??? и нужна ли проверка на null
        currentTableName = null;
    }

    @Override
    public void exitRenameTableStatement(SQLGrammarParser.RenameTableStatementContext ctx) {
        String tableName = currentTableName;
        String newTableName = ctx.newName.getText();

        //вызов функции
        System.out.println("RENAME TABLE command");
    }

    @Override public void exitAddColumnStatement(SQLGrammarParser.AddColumnStatementContext ctx) {
        String tableName = currentTableName;
        String columnName = ctx.columnName.getText();
        String columnType = ctx.dataType().getText();

        //вызов функции
        System.out.println("ADD COLUMN command");
    }

    @Override public void exitDropColumnStatement(SQLGrammarParser.DropColumnStatementContext ctx) {
        String tableName = currentTableName;
        String columnName = ctx.columnName.getText();

        //вызов функции
        System.out.println("DROP COLUMN command");
    }

    @Override
    public void exitCreateTableCommand(SQLGrammarParser.CreateTableCommandContext ctx) {
        String tableName = ctx.tableName.getText();

        List<ColumnDefinition> columnsToAdd = new ArrayList<>();

        for(var i : ctx.createTableStatement().columnDefinition()){
            columnsToAdd.add(new ColumnDefinition(i.columnName.getText(),i.dataType().getText(),i.columnConstraint().getText()));
        }

        //вызов функции
        System.out.println("CREATE TABLE command");
    }


    @Override
    public void exitDropTableCommand(SQLGrammarParser.DropTableCommandContext ctx) {
        String tableName = ctx.tableName.getText();

        //вызов функции
        System.out.println("DROP TABLE command");
    }


    @Override
    public void exitInsertCommand(SQLGrammarParser.InsertCommandContext ctx) {
        String tableName = ctx.tableName.getText();
        List<String> columnsToAdd = new ArrayList<>();
        List<String> valuesToAdd = new ArrayList<>();  //нужно добавить определение типа данных значения

        for(var i : ctx.columnList().IDENTIFIER()){
            columnsToAdd.add(i.getText());
        }
        for(var i : ctx.valueList().literal()){
            valuesToAdd.add(i.getText());
        }

        //вызов функции
        System.out.println("INSERT command");
    }

    @Override
    public void exitDeleteCommand(SQLGrammarParser.DeleteCommandContext ctx) {
        String tableName = ctx.tableName.getText();

        List<Expression> expressions = new ArrayList<>();
        List<String> logicalOperator = new ArrayList<>();

        for(var i : ctx.condition().expression()){
            expressions.add(new Expression(i.columnName.getText(),i.comparisonOperator().getText(),i.value.getText()));
        }

        for(var i : ctx.condition().logicalOperator()){
            logicalOperator.add(i.getText());
        }

        //вызов функции
        System.out.println("DELETE command");
    }


    @Override
    public void exitSelectCommand(SQLGrammarParser.SelectCommandContext ctx) { //* не будет работать скорее всего
        String tableName = ctx.tableName.getText();
        List<String> columnsToSelect = new ArrayList<>();

        for(var i : ctx.selectList.selectElement()){
            columnsToSelect.add(i.columnName.getText());
        }

        List<Expression> expressions = new ArrayList<>();
        List<String> logicalOperator = new ArrayList<>();

        for(var i : ctx.condition().expression()){
            expressions.add(new Expression(i.columnName.getText(),i.comparisonOperator().getText(),i.value.getText()));
        }

        for(var i : ctx.condition().logicalOperator()){
            logicalOperator.add(i.getText());
        }

        //вызов функции
        System.out.println("SELECT command");
    }

    @Override
    public void exitUpdateCommand(SQLGrammarParser.UpdateCommandContext ctx) {
        String tableName = ctx.tableName.getText();
        List<String> columnsToUpdate = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for(var i : ctx.updateList.IDENTIFIER()){
            columnsToUpdate.add(i.getText());
        }

        for(var i : ctx.updateList.literal()){
            values.add(i.getText());
        }

        List<Expression> expressions = new ArrayList<>();
        List<String> logicalOperator = new ArrayList<>();

        for(var i : ctx.condition().expression()){
            expressions.add(new Expression(i.columnName.getText(),i.comparisonOperator().getText(),i.value.getText()));
        }

        for(var i : ctx.condition().logicalOperator()){
            logicalOperator.add(i.getText());
        }

        //вызов функции
        System.out.println("UPDATE command");
    }




    @Override
    public void exitBeginCommand(SQLGrammarParser.BeginCommandContext ctx) {
        //вызов функции
        System.out.println("BEGIN command");
    }

    @Override
    public void exitCommitCommand(SQLGrammarParser.CommitCommandContext ctx) {
        //вызов функции
        System.out.println("COMMIT command");
    }

    @Override
    public void exitRollbackCommand(SQLGrammarParser.RollbackCommandContext ctx) {
        //вызов функции
        System.out.println("ROLLBACK command");
    }
}
