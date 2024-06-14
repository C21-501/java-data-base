grammar SQLGrammar;

/* Грамматика для SQL команд с синтаксисом PostgreSQL */

// Правило для старта
start: sqlCommands EOF;

// Правила для одиночной команды
sqlCommand: ddlCommand | dmlCommand | tclCommand | helpCommand;

// Правила для последовательности команд, разделенных ';'
sqlCommands: sqlCommand (';' sqlCommand)* ';'?;

// Правила для DDL команд
ddlCommand: alterCommand | createCommand | dropCommand;

alterCommand: alterDbCommand | alterTableCommand;
createCommand: createDbCommand | createTableCommand;
dropCommand: dropDbCommand | dropTableCommand;

alterDbCommand: 'ALTER' 'DATABASE' dbName=IDENTIFIER alterDatabaseStatement;
createDbCommand: 'CREATE' 'DATABASE' dbName=IDENTIFIER;
dropDbCommand: 'DROP' 'DATABASE' dbName=IDENTIFIER;

alterTableCommand: 'ALTER' 'TABLE' tableName=IDENTIFIER alterTableStatement;
createTableCommand: 'CREATE' 'TABLE' tableName=IDENTIFIER createTableStatement;
dropTableCommand: 'DROP' 'TABLE' tableName=IDENTIFIER;

// Правила для DML команд
dmlCommand: deleteCommand | insertCommand | selectCommand | updateCommand;

deleteCommand: 'DELETE FROM' tableName=IDENTIFIER ('WHERE' condition)?;
insertCommand: 'INSERT INTO' tableName=IDENTIFIER '(' columnList ')' 'VALUES' '(' valueList ')';
selectCommand: 'SELECT' selectList=selectElements 'FROM' tableName=IDENTIFIER ('WHERE' condition)?;
updateCommand: 'UPDATE' tableName=IDENTIFIER 'SET' updateList=updateElements ('WHERE' condition)?;

// Правила для TCL команд
tclCommand: beginCommand | commitCommand | rollbackCommand;

beginCommand: 'BEGIN';
commitCommand: 'COMMIT';
rollbackCommand: 'ROLLBACK';

// Правила для HELP команды
helpCommand: 'HELP';

// Правила для элементов запроса SELECT
selectElements: ('*' | selectElement (',' selectElement)*);
selectElement: columnName=IDENTIFIER;

// Правила для элементов запроса UPDATE
updateElements: (columnName=IDENTIFIER '=' value=literal (',' columnName=IDENTIFIER '=' value=literal)*);

// Правила для списков столбцов и значений
columnList: IDENTIFIER (',' IDENTIFIER)*;
valueList: literal (',' literal)*;

// Правила для условий
condition: expression (logicalOperator expression)*;
expression: columnName=IDENTIFIER comparisonOperator value=literal;

// Литералы
literal: INTEGER | STRING | REAL | 'TRUE' | 'FALSE' | 'NULL';

// Идентификаторы
IDENTIFIER: [a-zA-Z_] [a-zA-Z0-9_]*;

// Терминалы
INTEGER: '-'? NON_ZERO_DIGIT DIGIT* | '0';
STRING: '\'' .*? '\'';
REAL: '-'? NON_ZERO_DIGIT DIGIT* '.' DIGIT+ | '0' '.' DIGIT+;
DIGIT: [0-9];
NON_ZERO_DIGIT: [1-9];

logicalOperator: 'AND' | 'OR';
comparisonOperator: '=' | '<>' | '<' | '>' | '<=' | '>=';
WS: [ \t\r\n]+ -> skip;

// Реализация поддержки команд ALTER DATABASE
alterDatabaseStatement: 'RENAME TO' newName=IDENTIFIER;

// Реализация поддержки команд ALTER TABLE
alterTableStatement: renameTableStatement | addColumnStatement | dropColumnStatement;

renameTableStatement: 'RENAME TO' newName=IDENTIFIER;
addColumnStatement: 'ADD' 'COLUMN' columnName=IDENTIFIER dataType;
dropColumnStatement: 'DROP' 'COLUMN' columnName=IDENTIFIER;

// Реализация поддержки команд CREATE TABLE
createTableStatement: '(' columnDefinition (',' columnDefinition)* ')';
columnDefinition: columnName=IDENTIFIER dataType columnConstraint?;
dataType: 'INTEGER' | 'REAL' | 'STRING' | 'BOOLEAN';
columnConstraint: check | foreignKey |'PRIMARY KEY' | 'NOT NULL' | 'UNIQUE';
check: 'CHECK' '('condition')';
foreignKey: 'REFERENCES' tableName=IDENTIFIER '(' columnName=IDENTIFIER ')';

