package database.service.tools;

import database.service.tools.grammar.SQLGrammarBaseListener;
import database.service.tools.grammar.SQLGrammarParser;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SQLListener extends SQLGrammarBaseListener {
    private static final Logger logger = LogManager.getLogger(SQLListener.class);

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

        logger.info("Starting ALTER DB command...");
        try{
            //вызов функции
            System.out.println(dbName);
            System.out.println(newDbName);

            logger.info("Success ALTER DB command");
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void exitCreateDbCommand(SQLGrammarParser.CreateDbCommandContext ctx) {
        String dbName = ctx.dbName.getText();

        logger.info("Starting CREATE DB command...");
        try{
            //вызов функции
            System.out.println(dbName);

            logger.info("Success CREATE DB command");
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void exitDropDbCommand(SQLGrammarParser.DropDbCommandContext ctx) {
        String dbName = ctx.dbName.getText();

        logger.info("Starting DROP DB command...");
        try{
            //вызов функции
            System.out.println(dbName);

            logger.info("Success DROP DB command");
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void exitAlterTableCommand(SQLGrammarParser.AlterTableCommandContext ctx) {
        String tableName = ctx.tableName.getText();
        var alterCtx = ctx.alterTableStatement();

        if(alterCtx.renameTableStatement() != null){
            String newTableName = alterCtx.renameTableStatement().newName.getText();

            logger.info("Starting RENAME TABLE command...");
            try{
                //вызов функции
                System.out.println(tableName);
                System.out.println(newTableName);

                logger.info("Success RENAME TABLE command");
            } catch (Exception e){
                logger.error(e.getMessage());
            }

        } else if(alterCtx.addColumnStatement() != null){
            String columnName = alterCtx.addColumnStatement().columnName.getText();
            String columnType = alterCtx.addColumnStatement().dataType().getText();

            logger.info("Starting ADD COLUMN command...");
            try{
                //вызов функции
                System.out.println(tableName);
                System.out.println(columnName);
                System.out.println(columnType);

                logger.info("Success ADD COLUMN command");
            } catch (Exception e){
                logger.error(e.getMessage());
            }

        } else if(alterCtx.dropColumnStatement() != null){
            String columnName = alterCtx.dropColumnStatement().columnName.getText();

            logger.info("Starting DROP COLUMN command...");
            try{
                //вызов функции
                System.out.println(tableName);
                System.out.println(columnName);

                logger.info("Success DROP COLUMN command");
            } catch (Exception e){
                logger.error(e.getMessage());
            }
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

        logger.info("Starting CREATE TABLE command...");
        try{
            //вызов функции
            System.out.println(tableName);
            for(ColumnDefinition o : columnsToAdd){
                System.out.println(o.name +" "+ o.dataType +" "+ o.constraint);
            }

            logger.info("Success CREATE TABLE command");
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }


    @Override
    public void exitDropTableCommand(SQLGrammarParser.DropTableCommandContext ctx) {
        String tableName = ctx.tableName.getText();
        logger.info("Starting DROP TABLE command...");
        try{
            //вызов функции
            System.out.println(tableName);

            logger.info("Success DROP TABLE command");
        } catch (Exception e){
            logger.error(e.getMessage());
        }
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

        logger.info("Starting INSERT command ...");
        try{
            //вызов функции
            System.out.println(tableName);
            for (int i = 0; i < columnsToAdd.size(); i++) {
                System.out.println(columnsToAdd.get(i) +" = "+ valuesToAdd.get(i));
            }
            logger.info("Success INSERT command");
        }catch (Exception e){
            logger.error(e.getMessage());
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

        logger.info("Starting DELETE command ...");
        try{
            //вызов функции
            System.out.println(tableName);
            logger.info("Success DELETE command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
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
        logger.info("Starting SELECT command ...");
        try{
            //вызов функции
            System.out.println(tableName);
            for(String s : columnsToSelect){
                System.out.println(s);
            }
            logger.info("Success SELECT command");
        }catch (Exception e){
            logger.error(e.getMessage());
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
        logger.info("Starting UPDATE command ...");
        try{
            //вызов функции
            System.out.println(tableName);
            for (int i = 0; i < columnsToUpdate.size(); i++) {
                System.out.println(columnsToUpdate.get(i) +" = "+ values.get(i));
            }
            logger.info("Success UPDATE command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }




    @Override
    public void exitBeginCommand(SQLGrammarParser.BeginCommandContext ctx) {
        logger.info("Starting BEGIN command ...");
        try{
            //вызов функции
            logger.info("Success BEGIN command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void exitCommitCommand(SQLGrammarParser.CommitCommandContext ctx) {
        logger.info("Starting COMMIT command ...");
        try{
            //вызов функции
            logger.info("Success COMMIT command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void exitRollbackCommand(SQLGrammarParser.RollbackCommandContext ctx) {
        logger.info("Starting ROLLBACK command ...");
        try{
            //вызов функции
            logger.info("Success ROLLBACK command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
