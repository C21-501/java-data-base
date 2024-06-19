package database.service.tools;

import database.api.DatabaseAPI;
import database.api.utils.OUTPUT_TYPE;
import database.service.tools.grammar.SQLGrammarBaseListener;
import database.service.tools.grammar.SQLGrammarParser;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static database.api.utils.Utils.parseValue;

/**
 * SQLListener is an implementation of the SQLGrammarBaseListener that handles
 * various SQL commands such as ALTER, CREATE, DROP, INSERT, DELETE, SELECT, UPDATE,
 * BEGIN, COMMIT, and ROLLBACK. It logs the start and success of each command execution.
 */
public class SQLListener extends SQLGrammarBaseListener {
    private final Logger logger = LogManager.getLogger(SQLListener.class);
    private final DatabaseAPI databaseAPI;
    private final OUTPUT_TYPE outputType;
    private final Optional<String> filePath;

    public SQLListener(DatabaseAPI databaseAPI, OUTPUT_TYPE outputType, Optional<String> filePath) {
        this.databaseAPI = databaseAPI;
        this.outputType = outputType;
        this.filePath = filePath;
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
            databaseAPI.alter(dbName, newDbName, true);
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
            databaseAPI.drop(dbName, true);
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
                databaseAPI.alter(tableName, newTableName, false);
                logger.info("Success RENAME TABLE command");
            } catch (Exception e){
                logger.error(e.getMessage());
            }

        } else if(alterCtx.addColumnStatement() != null){
            String column;
            if(alterCtx.addColumnStatement().columnDefinition().columnConstraint() != null){
                StringBuilder constraint = new StringBuilder();
                for(var i : alterCtx.addColumnStatement().columnDefinition().columnConstraint()){
                    if(i.check() != null){
                        var expression = i.check().condition().expression(0);
                        constraint.append("CHECK ( ")
                                .append(STR."\{expression.columnName.getText()} ")
                                .append(STR."\{expression.comparisonOperator().getText()} ")
                                .append(expression.value.getText())
                                .append(" ) ");
                    } else if(i.foreignKey() != null){
                        constraint.append("REFERENCES ")
                                .append(STR."\{i.foreignKey().tableName.getText()} ( ")
                                .append(STR."\{i.foreignKey().columnName.getText()} ) ");
                    } else {
                        constraint.append(STR."\{i.getText()} ");
                    }
                }
                column = STR."\{alterCtx.addColumnStatement().columnDefinition().columnName.getText()} \{alterCtx.addColumnStatement().columnDefinition().dataType().getText()} \{constraint.toString()}";
            } else {
                column = STR."\{alterCtx.addColumnStatement().columnDefinition().columnName.getText()} \{alterCtx.addColumnStatement().columnDefinition().dataType().getText()}";
            }

            logger.info("Starting ADD COLUMN command...");
            try{
                List<String> alterColumns = List.of(column);
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
            String column;
            if(i.columnConstraint() != null){
                StringBuilder constraint = new StringBuilder();
                for(var j : i.columnConstraint()){
                    if(j.check() != null){
                        var expression = j.check().condition().expression(0);
                        constraint.append("CHECK ( ")
                                .append(STR."\{expression.columnName.getText()} ")
                                .append(STR."\{expression.comparisonOperator().getText()} ")
                                .append(expression.value.getText())
                                .append(" ) ");
                    } else if(j.foreignKey() != null){
                        constraint.append("REFERENCES ")
                                .append(STR."\{j.foreignKey().tableName.getText()} ( ")
                                .append(STR."\{j.foreignKey().columnName.getText()} ) ");
                    } else {
                        constraint.append(STR."\{j.getText()} ");
                    }
                }
                column = STR."\{i.columnName.getText()} \{i.dataType().getText()} \{constraint.toString()}";
            } else {
                column = STR."\{i.columnName.getText()} \{i.dataType().getText()}";
            }
            columnsToAdd.add(column);
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
            databaseAPI.drop(tableName, false);
            logger.info("Success DROP TABLE command");
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }


    @Override
    public void exitInsertCommand(SQLGrammarParser.InsertCommandContext ctx) {
        String tableName = ctx.tableName.getText();
        List<String> columnsToAdd = new LinkedList<>();
        List<Object> valuesToAdd = new LinkedList<>();

        for(var i : ctx.columnList().IDENTIFIER()){
            columnsToAdd.add(i.getText());
        }
        for(var i : ctx.valueList().literal()){
            valuesToAdd.add(parseValue(i.getText()));
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
            if(columnsToSelect.isEmpty()) {
                databaseAPI.select(tableName);
            } else if(condition.isEmpty()){
                databaseAPI.select(tableName, columnsToSelect);
            } else {
                databaseAPI.select(tableName, columnsToSelect, condition);
            }
            databaseAPI.print(outputType, filePath);
            logger.info("Success SELECT command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void exitUpdateCommand(SQLGrammarParser.UpdateCommandContext ctx) {
        String tableName = ctx.tableName.getText();
        String condition = "";
        List<String> values = new LinkedList<>();
        int sz = Math.min(ctx.updateList.IDENTIFIER().size(), ctx.updateList.literal().size());
        var c = ctx.updateList;

        for (int i = 0; i < sz; i++) {
            values.add(STR."\{c.IDENTIFIER(i).getText()} = \{c.literal(i).getText()}");
        }

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
            databaseAPI.update(tableName, values, condition);
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

    @Override
    public void exitHelpCommand(SQLGrammarParser.HelpCommandContext ctx) {
        logger.info("Starting HELP command ...");
        try{
            Optional<String> commandName = Optional.empty();
            if(ctx.commandName() != null){
                commandName = Optional.of(ctx.commandName().getText());
            }
            databaseAPI.help(commandName);
            logger.info("Success HELP command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void exitShowDatabases(SQLGrammarParser.ShowDatabasesContext ctx) {
        logger.info("Starting SHOW DATABASES command ...");
        try{
            databaseAPI.show(Optional.empty());
            logger.info("Success SHOW DATABASES command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void exitShowTables(SQLGrammarParser.ShowTablesContext ctx) {
        logger.info("Starting SHOW TABLES command ...");
        try{
            databaseAPI.show();
            logger.info("Success SHOW TABLES command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void exitOpenCommand(SQLGrammarParser.OpenCommandContext ctx) {
        logger.info("Starting OPEN DATABASE command ...");
        try{
            String databaseName = ctx.dbName.getText();
            Optional<String> path = Optional.empty();
            if(ctx.path != null){
                path = Optional.of(ctx.path.getText());
            }
            databaseAPI.open(databaseName,path);
            logger.info("Success OPEN DATABASE command");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
