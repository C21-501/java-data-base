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

    @Override
    public void exitAlterDbCommand(SQLGrammarParser.AlterDbCommandContext ctx) {
        String dbName = ctx.dbName.getText();
        String newDbName = ctx.alterDatabaseStatement().newName.getText();

        //вызов функции
        System.out.println("ALTER DB command");
        System.out.println(dbName);
        System.out.println(newDbName);
    }

    @Override
    public void exitCreateDbCommand(SQLGrammarParser.CreateDbCommandContext ctx) {
        String dbName = ctx.dbName.getText();

        //вызов функции
        System.out.println("CREATE DB command");
        System.out.println(dbName);
    }

    @Override
    public void exitDropDbCommand(SQLGrammarParser.DropDbCommandContext ctx) {
        String dbName = ctx.dbName.getText();

        //вызов функции
        System.out.println("DROP DB command");
        System.out.println(dbName);
    }

    @Override
    public void exitAlterTableCommand(SQLGrammarParser.AlterTableCommandContext ctx) {
        String tableName = ctx.tableName.getText();
        var alterCtx = ctx.alterTableStatement();

        if(alterCtx.renameTableStatement() != null){
            String newTableName = alterCtx.renameTableStatement().newName.getText();

            //вызов функции
            System.out.println("RENAME TABLE command");
            System.out.println(tableName);
            System.out.println(newTableName);

        } else if(alterCtx.addColumnStatement() != null){
            String columnName = alterCtx.addColumnStatement().columnName.getText();
            String columnType = alterCtx.addColumnStatement().dataType().getText();

            //вызов функции
            System.out.println("ADD COLUMN command");
            System.out.println(tableName);
            System.out.println(columnName);
            System.out.println(columnType);

        } else if(alterCtx.dropColumnStatement() != null){
            String columnName = alterCtx.dropColumnStatement().columnName.getText();

            //вызов функции
            System.out.println("DROP COLUMN command");
            System.out.println(tableName);
            System.out.println(columnName);
        }
    }

    @Override
    public void exitCreateTableCommand(SQLGrammarParser.CreateTableCommandContext ctx) {
        String tableName = ctx.tableName.getText();

        List<ColumnDefinition> columnsToAdd = new ArrayList<>();

        for(var i : ctx.createTableStatement().columnDefinition()){
            String constraint = null;
            if(i.columnConstraint() != null){
                constraint = i.columnConstraint().getText();
            }
            columnsToAdd.add(new ColumnDefinition(i.columnName.getText(),i.dataType().getText(),constraint));
        }

        //вызов функции
        System.out.println("CREATE TABLE command");
        System.out.println(tableName);
        for(ColumnDefinition o : columnsToAdd){
            System.out.println(o.name +" "+ o.dataType +" "+ o.constraint);
        }
    }


    @Override
    public void exitDropTableCommand(SQLGrammarParser.DropTableCommandContext ctx) {
        String tableName = ctx.tableName.getText();

        //вызов функции
        System.out.println("DROP TABLE command");
        System.out.println(tableName);
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
        System.out.println(tableName);
        for (int i = 0; i < columnsToAdd.size(); i++) {
            System.out.println(columnsToAdd.get(i) +" = "+ valuesToAdd.get(i));
        }
    }

    @Override
    public void exitDeleteCommand(SQLGrammarParser.DeleteCommandContext ctx) {
        String tableName = ctx.tableName.getText();

        if(ctx.condition() != null){
            List<Expression> expressions = new ArrayList<>();
            List<String> logicalOperator = new ArrayList<>();

            for(var i : ctx.condition().expression()){
                expressions.add(new Expression(i.columnName.getText(),i.comparisonOperator().getText(),i.value.getText()));
            }

            for(var i : ctx.condition().logicalOperator()){
                logicalOperator.add(i.getText());
            }

            //-------------------------------------------------
            for (Expression o : expressions){
                System.out.println(o.columnName +" "+ o.operator +" "+ o.value);
            }
            for(String s : logicalOperator){
                System.out.println(s);
            }
            //-------------------------------------------------
        }

        //вызов функции
        System.out.println("DELETE command");
        System.out.println(tableName);
    }


    @Override
    public void exitSelectCommand(SQLGrammarParser.SelectCommandContext ctx) { //если *, то columnsToSelect пустой массив
        String tableName = ctx.tableName.getText();
        List<String> columnsToSelect = new ArrayList<>();

        for(var i : ctx.selectList.selectElement()){
            columnsToSelect.add(i.columnName.getText());
        }

        if(ctx.condition() != null){
            List<Expression> expressions = new ArrayList<>();
            List<String> logicalOperator = new ArrayList<>();

            for(var i : ctx.condition().expression()){
                expressions.add(new Expression(i.columnName.getText(),i.comparisonOperator().getText(),i.value.getText()));
            }

            for(var i : ctx.condition().logicalOperator()){
                logicalOperator.add(i.getText());
            }

            //-------------------------------------------------
            for (Expression o : expressions){
                System.out.println(o.columnName +" "+ o.operator +" "+ o.value);
            }
            for(String s : logicalOperator){
                System.out.println(s);
            }
            //-------------------------------------------------
        }

        //вызов функции
        System.out.println("SELECT command");
        System.out.println(tableName);
        for(String s : columnsToSelect){
            System.out.println(s);
        }
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

        if(ctx.condition() != null){
            List<Expression> expressions = new ArrayList<>();
            List<String> logicalOperator = new ArrayList<>();

            for(var i : ctx.condition().expression()){
                expressions.add(new Expression(i.columnName.getText(),i.comparisonOperator().getText(),i.value.getText()));
            }

            for(var i : ctx.condition().logicalOperator()){
                logicalOperator.add(i.getText());
            }

            //-------------------------------------------------
            for (Expression o : expressions){
                System.out.println(o.columnName +" "+ o.operator +" "+ o.value);
            }
            for(String s : logicalOperator){
                System.out.println(s);
            }
            //-------------------------------------------------
        }

        //вызов функции
        System.out.println("UPDATE command");
        System.out.println(tableName);
        for (int i = 0; i < columnsToUpdate.size(); i++) {
            System.out.println(columnsToUpdate.get(i) +" = "+ values.get(i));
        }
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
