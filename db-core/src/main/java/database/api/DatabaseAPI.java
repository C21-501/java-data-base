package database.api;

import database.api.ddl.commands.AlterCommand;
import database.api.ddl.commands.CreateCommand;
import database.api.ddl.commands.DropCommand;
import database.api.dml.commands.DeleteCommand;
import database.api.dml.commands.InsertCommand;
import database.api.dml.commands.SelectCommand;
import database.api.dml.commands.UpdateCommand;
import database.api.tcl.commands.BeginCommand;
import database.api.tcl.commands.CommitCommand;
import database.api.tcl.commands.RollBackCommand;
import database.system.core.structures.Response;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static database.api.utils.Utils.parseStringToObjectArray;

/**
 * The DatabaseAPI class provides an interface for interacting with the database.
 * It allows executing Data Definition Language (DDL) and Data Manipulation Language (DML) commands,
 * managing command history, undoing commands, and handling Transaction Control Language (TCL) operations.
 *
 * <p>This class offers a synchronized way to interact with the database through various command objects.
 * It maintains a command history to support undo operations and provides methods to start, commit,
 * and roll back transactions.</p>
 *
 * <p>Supported Commands:</p>
 * <ul>
 *     <li>DDL Commands: create, alter, drop</li>
 *     <li>DML Commands: select, insert, update, delete</li>
 *     <li>TCL Commands: begin, commit, rollback</li>
 * </ul>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * DatabaseAPI dbApi = new DatabaseAPI();
 * dbApi.setActiveEditor(new DatabaseEditor());
 * dbApi.setCommandHistory(new CommandHistory());
 * dbApi.create("myTable", List.of("id INTEGER", "name STRING"));
 * dbApi.begin();
 * dbApi.insert("myTable", List.of("id", "name"), List.of(new Object[]{1, "John Doe"}));
 * dbApi.commit();
 * }</pre>
 */
@Data
public class DatabaseAPI {
    private List<DatabaseEditor> editors;
    private DatabaseEditor activeEditor;
    private CommandHistory history;
    private Response lastSelectResponse;

    /**
     * Creates a new database by executing the CreateCommand.
     *
     * @param databaseName the name of the database to be created
     * @param databasePath an optional path where the database will be stored; if not provided, a default location will be used
     * @throws IOException if an I/O error occurs during the execution
     *
     * <p>Example:</p>
     * <pre>{@code
     * 
     * dbApi.create("myDatabase", Optional.of("/path/to/database"));
     * }</pre>
     */
    final synchronized public void create(String databaseName, Optional<String> databasePath) throws IOException {
        executeCommand(new CreateCommand(this, activeEditor, databaseName, databasePath));
    }

    /**
     * Executes a CREATE command to create a new table in the database.
     *
     * @param tableName the name of the table to create
     * @param columns   the list of column names for the new table
     * @throws IOException if an I/O error occurs during the execution
     *
     * <p>Example:</p>
     * <pre>{@code
     *
     * dbApi.create("myTable", List.of("id INTEGER", "name STRING"));
     * }</pre>
     */
    final synchronized public void create(String tableName, List<String> columns) throws IOException {
        executeCommand(new CreateCommand(this, activeEditor, tableName, columns));
    }

    /**
     * Executes an ALTER command to modify an existing table in the database.
     *
     * @param tableName    the name of the table to modify
     * @param alterColumns an array of lists representing modifications to the table columns
     *                     <p>
     *                     <strong>Variations:</strong>
     *                     <ol>
     *                     <li>If one list is provided, it represents the new columns to be added to the table.</li>
     *                     <li>If two lists are provided:
     *                     <ul>
     *                     <li>The first list represents the modified columns.</li>
     *                     <li>The second list represents the new values for those columns.</li>
     *                     </ul>
     *                     </li>
     *                     <li>If three lists are provided:
     *                     <ul>
     *                     <li>The first list represents the modified columns.</li>
     *                     <li>The second list represents the new values for those columns.</li>
     *                     <li>The third list represents the columns to be deleted from the table.</li>
     *                     </ul>
     *                     </li>
     *                     </ol>
     * @throws IOException if an I/O error occurs during the execution
     *
     * <p>Example:</p>
     * <pre>{@code
     *
     * dbApi.alter("myTable", List.of("newColumn INTEGER"));
     * dbApi.alter("myTable", null, List.of("column1 STRING", "column2 BOOLEAN"));
     * dbApi.alter("myTable", null, null, List.of("column2"));
     * }</pre>
     */
    @SafeVarargs
    final synchronized public void alter(String tableName, List<String>... alterColumns) throws IOException {
        executeCommand(new AlterCommand(this, activeEditor, tableName, alterColumns));
    }

    /**
     * Executes an ALTER command to rename an existing table in the database.
     *
     * @param name    the current name of the table or database to be renamed
     * @param newName the new name for the table or database
     * @param isDatabase flag
     * @throws IOException if an I/O error occurs during the execution
     *
     *                     <p>Example:</p>
     *                     <pre>{@code
     *
     *                     dbApi.alter("oldTableName", "newTableName", false);
     *                     }</pre>
     */
    final synchronized public void alter(String name, String newName, boolean isDatabase) throws IOException {
        executeCommand(new AlterCommand(this, activeEditor, name, newName, isDatabase));
    }

    /**
     * Executes a DROP command to delete a table from the database.
     *
     * @param name         the name of the table or database to delete
     * @param isDatabase   the flag
     * @throws IOException if an I/O error occurs during the execution
     *
     *                     <p>Example:</p>
     *                     <pre>{@code
     *
     *                     dbApi.drop("myTable", false);
     *                     }</pre>
     */
    final synchronized public void drop(String name, boolean isDatabase) throws IOException {
        executeCommand(new DropCommand(this, activeEditor, name, isDatabase));
    }

    /**
     * Executes a DELETE command to remove records from a table in the database.
     *
     * @param tableName the name of the table from which records will be deleted
     * @param condition the condition to filter which records are deleted
     * @throws IOException if an I/O error occurs during the execution
     *
     * <p>Example:</p>
     * <pre>{@code
     *
     * dbApi.delete("myTable", "id = 1");
     * }</pre>
     */
    final synchronized public void delete(String tableName, String condition) throws IOException {
        executeCommand(new DeleteCommand(this, activeEditor, tableName, condition));
    }

    /**
     * Executes an INSERT command to add records to a table in the database.
     *
     * @param tableName the name of the table to insert records into
     * @param columns   the list of column names for the records
     * @param values    the list of values to be inserted into the columns
     * @throws IOException if an I/O error occurs during the execution
     *
     * <p>Example:</p>
     * <pre>{@code
     *
     * dbApi.insert("myTable", List.of("id", "name"), List.of(new Object[]{1, "John Doe"}));
     * }</pre>
     */
    final synchronized public void insert(String tableName, List<String> columns, List<Object[]> values) throws IOException {
        executeCommand(new InsertCommand(this, activeEditor, tableName, columns, values));
    }

    /**
     * Executes a SELECT command to retrieve all records from a table in the database.
     *
     * @param tableName the name of the table from which records will be retrieved
     * @throws IOException if an I/O error occurs during the execution
     *
     * <p>Example:</p>
     * <pre>{@code
     *
     * dbApi.select("myTable");
     * }</pre>
     */
    final synchronized public void select(String tableName) throws IOException {
        executeCommand(new SelectCommand(this, activeEditor, tableName));
    }

    /**
     * Executes a SELECT command to retrieve specific columns from a table in the database.
     *
     * @param tableName the name of the table from which records will be retrieved
     * @param columns   the list of column names to select from the table
     * @throws IOException if an I/O error occurs during the execution
     *
     * <p>Example:</p>
     * <pre>{@code
     *
     * dbApi.select("myTable", List.of("id", "name"));
     * }</pre>
     */
    final synchronized public void select(String tableName, List<String> columns) throws IOException {
        executeCommand(new SelectCommand(this, activeEditor, tableName, columns));
    }

    /**
     * Executes a SELECT command to retrieve specific columns from a table in the database based on a condition.
     *
     * @param tableName the name of the table from which records will be retrieved
     * @param columns   the list of column names to select from the table
     * @param condition the condition to filter which records are selected
     * @throws IOException if an I/O error occurs during the execution
     *
     * <p>Example:</p>
     * <pre>{@code
     *
     * dbApi.select("myTable", List.of("id", "name"), "id = 1");
     * }</pre>
     */
    final synchronized public void select(String tableName, List<String> columns, String condition) throws IOException {
        executeCommand(new SelectCommand(this, activeEditor, tableName, columns, condition));
    }

    /**
     * Executes an UPDATE command to modify records in a table in the database.
     *
     * @param tableName the name of the table in which records will be updated
     * @param values     the new value to be set in the records
     * @param condition the condition to determine which records to update
     * @throws IOException if an I/O error occurs during the execution
     *
     * <p>Example:</p>
     * <pre>{@code
     *
     * dbApi.update("table1", List.of("column1 = 20", "column2 = 'John'",), "id = 10");
     * }</pre>
     */
    final synchronized public void update(String tableName, List<String> values, String condition) throws IOException {
        executeCommand(new UpdateCommand(this, activeEditor, tableName, values, condition));
    }

    /**
     * Begins a new transaction by executing the BeginCommand.
     *
     * @throws IOException if an I/O error occurs during the execution
     *
     * <p>Example:</p>
     * <pre>{@code
     *
     * dbApi.begin();
     * }</pre>
     */
    final synchronized public void begin() throws IOException {
        executeCommand(new BeginCommand(this, activeEditor));
    }

    /**
     * Commits the current transaction by executing the CommitCommand.
     *
     * @throws IOException if an I/O error occurs during the execution
     *
     * <p>Example:</p>
     * <pre>{@code
     *
     * dbApi.commit();
     * }</pre>
     */
    final synchronized public void commit() throws IOException {
        executeCommand(new CommitCommand(this, activeEditor));
    }

    /**
     * Rolls back the current transaction by executing the RollBackCommand.
     *
     * @throws IOException if an I/O error occurs during the execution
     *
     * <p>Example:</p>
     * <pre>{@code
     *
     * dbApi.rollback();
     * }</pre>
     */
    final synchronized public void rollback() throws IOException {
        executeCommand(new RollBackCommand(this, activeEditor));
    }

    private void executeCommand(Command command) throws IOException {
        if (activeEditor.haveActiveTransactions() && !(command instanceof BeginCommand || command instanceof CommitCommand || command instanceof RollBackCommand)) {
            activeEditor.collectCommands(command);
        } else if (activeEditor.haveActiveTransactions() && (command instanceof CommitCommand || command instanceof RollBackCommand)) {
            if(command.execute()){
                history.push(command);
            }
        } else {
            if(command.execute()){
                history.push(command);
            }
        }
    }

    /**
     * Undoes the last executed command by popping it from the history and calling its undo method.
     *
     * @throws IOException            if an I/O error occurs during the execution
     * @throws ClassNotFoundException if the class of a serialized object could not be found while undoing
     *
     * <p>Example:</p>
     * <pre>{@code
     *
     * dbApi.undo();
     * }</pre>
     */
    synchronized public void undo() throws IOException, ClassNotFoundException {
        Command command = history.pop();
        if (command != null) {
            command.undo();
        }
    }




    public static void main(String[] args) throws IOException {
        // Создаем экземпляр DatabaseAPI
        DatabaseAPI databaseAPI = new DatabaseAPI();

        // Устанавливаем активного редактора и историю команд
        databaseAPI.setActiveEditor(new DatabaseEditor());
        databaseAPI.setHistory(new CommandHistory());

        // Создаем базу данных
        databaseAPI.create("test_database", Optional.empty());

        // Создаем таблицу
        databaseAPI.create("test_table", List.of("id INTEGER", "name STRING", "age INTEGER"));

        // Вставляем записи
        databaseAPI.insert("test_table",
                List.of("id", "name", "age"),
                List.of(
                        parseStringToObjectArray("1, 'Alice', 20"),
                        parseStringToObjectArray("2, 'Bob', 25")
                )
        );

        // Выбор всех записей
        databaseAPI.select("test_table");
        databaseAPI.getLastSelectResponse().printTable();

        // Начинаем транзакцию
        databaseAPI.begin();

        // Вставляем записи в рамках транзакции
        databaseAPI.insert("test_table",
                List.of("id", "name", "age"),
                List.of(
                        parseStringToObjectArray("3, 'Tom', 21"),
                        parseStringToObjectArray("4, 'Peter', 18")
                )
        );

        // Коммитим транзакцию
        databaseAPI.commit();

        // Выбор всех записей после коммита
        databaseAPI.select("test_table");
        databaseAPI.getLastSelectResponse().printTable();

        // Обновляем записи
        databaseAPI.update("test_table", List.of("name = 'Alice Smith'", "age = 31"), "id = 1");

        // Удаляем запись
        databaseAPI.delete("test_table", "id = 2");

        databaseAPI.insert("test_table",
                List.of("id", "name", "age"),
                List.of(
                        parseStringToObjectArray("5, 'Sara', 21"),
                        parseStringToObjectArray("6, 'Connor', 18")
                )
        );

        // Выбор всех записей после изменений
        databaseAPI.select("test_table");
        databaseAPI.getLastSelectResponse().printTable();

        // Добавляем новый столбец
        databaseAPI.alter("test_table", List.of("salary REAL"));

        // Выбор всех записей после добавления столбца
        databaseAPI.select("test_table");
        databaseAPI.getLastSelectResponse().printTable();
        // Начинаем транзакцию для демонстрации отката
        databaseAPI.begin();

        // Вставляем записи в рамках транзакции
        databaseAPI.insert("test_table",
                List.of("id", "name", "age", "salary"),
                List.of(
                        parseStringToObjectArray("5, 'Sam', 28, 50000.0"),
                        parseStringToObjectArray("6, 'Anna', 24, 60000.0")
                )
        );
        // Откатываем транзакцию
        databaseAPI.rollback();
        // Выбор всех записей после отката
        databaseAPI.select("test_table");
        databaseAPI.getLastSelectResponse().printTable();

        // Переименовываем таблицу
        databaseAPI.alter("test_table", "renamed_table", false);

        databaseAPI.insert("renamed_table",
                List.of("id", "name", "age", "salary"),
                List.of(
                        parseStringToObjectArray("5, 'Sam', null, 50000.0"),
                        parseStringToObjectArray("6, 'Anna', 24, 60000.0")
                )
        );
        // Выбор всех записей из переименованной таблицы
        databaseAPI.select("renamed_table");
        databaseAPI.getLastSelectResponse().printTable();

        // Удаляем таблицу
        databaseAPI.drop("renamed_table", false);

        // Удаляем базу данных
        databaseAPI.drop("test_database", true);
    }
}
