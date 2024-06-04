package database.service.tools;

import database.api.DatabaseAPI;
import database.service.tools.grammar.SQLGrammarBaseListener;
import database.service.tools.grammar.SQLGrammarParser;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * SQLListener is an implementation of the SQLGrammarBaseListener that handles
 * various SQL commands such as ALTER, CREATE, DROP, INSERT, DELETE, SELECT, UPDATE,
 * BEGIN, COMMIT, and ROLLBACK. It logs the start and success of each command execution.
 */
public class SQLListener extends SQLGrammarBaseListener {
    private final Logger logger = LogManager.getLogger(SQLListener.class);
    private final DatabaseAPI databaseAPI;

    public SQLListener(DatabaseAPI databaseAPI) {
        this.databaseAPI = databaseAPI;
    }

//    /**
//     * Represents the definition of a column in a table.
//     */
//    private record ColumnDefinition(
//        String name,
//        String dataType,
//        String constraint
//    ){
//    }

//    /**
//     * Represents an expression in a SQL command.
//     */
//    private record Expression(
//        String columnName,
//        String operator,
//        String value
//    ){
//    }
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
            databaseAPI.create(dbName, Optional.empty());
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
                databaseAPI.alter(tableName, newTableName);
                logger.info("Success RENAME TABLE command");
            } catch (Exception e){
                logger.error(e.getMessage());
            }

        } else if(alterCtx.addColumnStatement() != null){
            String columnName = alterCtx.addColumnStatement().columnName.getText();
            //String columnType = alterCtx.addColumnStatement().dataType().getText();

            logger.info("Starting ADD COLUMN command...");
            try{
                List<String> alterColumns = List.of(columnName);
                databaseAPI.alter(tableName, alterColumns);
                logger.info("Success ADD COLUMN command");
            } catch (Exception e){
                logger.error(e.getMessage());
            }

        } else if(alterCtx.dropColumnStatement() != null){
            String columnName = alterCtx.dropColumnStatement().columnName.getText();

            logger.info("Starting DROP COLUMN command...");
            try{
                List<String> alterColumns = List.of(columnName);
                databaseAPI.alter(tableName, null, null, alterColumns);
                logger.info("Success DROP COLUMN command");
            } catch (Exception e){
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void exitCreateTableCommand(SQLGrammarParser.CreateTableCommandContext ctx) {
        String tableName = ctx.tableName.getText();

        List<String> columnsToAdd = new LinkedList<>();

        for(var i : ctx.createTableStatement().columnDefinition()) {
//            String constraint = null;
//            List<Expression> expressions = new ArrayList<>();
//            List<String> logicalOperator = new ArrayList<>();
//
//            if(i.columnConstraint() != null) {
//
//                if(i.columnConstraint().check() != null) {
//                    constraint = "CHECK";
//
//                    for(var j : i.columnConstraint().check().condition().expression()){
//                        expressions.add(new Expression(j.columnName.getText(),j.comparisonOperator().getText(),j.value.getText()));
//                    }
//                    for(var j : i.columnConstraint().check().condition().logicalOperator()){
//                        logicalOperator.add(j.getText());
//                    }
//
//                } else if(i.columnConstraint().foreignKey() != null) {
//                    constraint = "REFERENCES";
//                    String foreignTableName = i.columnConstraint().foreignKey().tableName.getText();
//                    String foreignColumnName = i.columnConstraint().foreignKey().columnName.getText();
//
//                } else {
//                    constraint = i.columnConstraint().getText();
//                }
//            }
            columnsToAdd.add(STR."\{i.columnName.getText()} \{i.dataType().getText()}");
        }

        logger.info("Starting CREATE TABLE command...");
        try{
            databaseAPI.create(tableName, columnsToAdd);
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
            databaseAPI.drop(tableName);
            logger.info("Success DROP TABLE command");
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }


    @Override
    public void exitInsertCommand(SQLGrammarParser.InsertCommandContext ctx) {
        String tableName = ctx.tableName.getText();
        List<String> columnsToAdd = new LinkedList<>();
        List<String> valuesToAdd = new LinkedList<>();

        for(var i : ctx.columnList().IDENTIFIER()){
            columnsToAdd.add(i.getText());
        }
        for(var i : ctx.valueList().literal()){
            valuesToAdd.add(i.getText());
        }

        logger.info("Starting INSERT command ...");
        try{
            List<Object[]> values = new LinkedList<>();
            values.add(valuesToAdd.toArray());
            databaseAPI.insert(tableName, columnsToAdd, values);
            logger.info("Success INSERT command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void exitDeleteCommand(SQLGrammarParser.DeleteCommandContext ctx) {
        String tableName = ctx.tableName.getText();
        String condition = "";

        if(ctx.condition() != null){
//            List<Expression> expressions = new ArrayList<>();
//            List<String> logicalOperator = new ArrayList<>();
//
//            for(var i : ctx.condition().expression()){
//                expressions.add(new Expression(i.columnName.getText(),i.comparisonOperator().getText(),i.value.getText()));
//            }
//
//            for(var i : ctx.condition().logicalOperator()){
//                logicalOperator.add(i.getText());
//            }
            var i = ctx.condition().expression().getFirst();
            condition = STR."\{i.columnName.getText()} \{i.comparisonOperator().getText()} \{i.value.getText()}";
        }

        logger.info("Starting DELETE command ...");
        try{
            databaseAPI.delete(tableName, condition);
            logger.info("Success DELETE command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }


    @Override
    public void exitSelectCommand(SQLGrammarParser.SelectCommandContext ctx) { //если *, то columnsToSelect пустой массив
        String tableName = ctx.tableName.getText();
        List<String> columnsToSelect = new LinkedList<>();
        String condition = "";

        for(var i : ctx.selectList.selectElement()){
            columnsToSelect.add(i.columnName.getText());
        }

        if(ctx.condition() != null){
//            List<Expression> expressions = new LinkedList<>();
//            List<String> logicalOperator = new LinkedList<>();
//
//            for(var i : ctx.condition().expression()){
//                expressions.add(new Expression(i.columnName.getText(),i.comparisonOperator().getText(),i.value.getText()));
//            }
//
//            for(var i : ctx.condition().logicalOperator()){
//                logicalOperator.add(i.getText());
//            }
            var i = ctx.condition().expression().getFirst();
            condition = STR."\{i.columnName.getText()} \{i.comparisonOperator().getText()} \{i.value.getText()}";
        }

        logger.info("Starting SELECT command ...");
        try{
            if(condition.isEmpty()){
                databaseAPI.select(tableName, columnsToSelect);
            } else {
                databaseAPI.select(tableName, columnsToSelect, condition);
            }
            logger.info("Success SELECT command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void exitUpdateCommand(SQLGrammarParser.UpdateCommandContext ctx) {
        String tableName = ctx.tableName.getText();
        //List<String> columnsToUpdate = new LinkedList<>();
        //List<String> values = new LinkedList<>();
        String condition = "";
        String value = ctx.updateList.literal().getFirst().getText();

//        for(var i : ctx.updateList.IDENTIFIER()){
//            columnsToUpdate.add(i.getText());
//        }
//
//        for(var i : ctx.updateList.literal()){
//            values.add(i.getText());
//        }

        if(ctx.condition() != null){
//            List<Expression> expressions = new ArrayList<>();
//            List<String> logicalOperator = new ArrayList<>();
//
//            for(var i : ctx.condition().expression()){
//                expressions.add(new Expression(i.columnName.getText(),i.comparisonOperator().getText(),i.value.getText()));
//            }
//
//            for(var i : ctx.condition().logicalOperator()){
//                logicalOperator.add(i.getText());
//            }
            var i = ctx.condition().expression().getFirst();
            condition = STR."\{i.columnName.getText()} \{i.comparisonOperator().getText()} \{i.value.getText()}";
        }

        logger.info("Starting UPDATE command ...");
        try{
            databaseAPI.update(tableName, value, condition);
            logger.info("Success UPDATE command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }




    @Override
    public void exitBeginCommand(SQLGrammarParser.BeginCommandContext ctx) {
        logger.info("Starting BEGIN command ...");
        try{
            databaseAPI.begin();
            logger.info("Success BEGIN command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void exitCommitCommand(SQLGrammarParser.CommitCommandContext ctx) {
        logger.info("Starting COMMIT command ...");
        try{
            databaseAPI.commit();
            logger.info("Success COMMIT command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void exitRollbackCommand(SQLGrammarParser.RollbackCommandContext ctx) {
        logger.info("Starting ROLLBACK command ...");
        try{
            databaseAPI.rollback();
            logger.info("Success ROLLBACK command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
