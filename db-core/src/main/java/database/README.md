

```
DatabaseProject/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── databaseScheme/
│   │   │   │   ├── core/
│   │   │   │   │   ├── Database.java        // Singleton для управления экземпляром базы данных
│   │   │   │   │   ├── Table.java          // Composite для управления структурой таблиц
│   │   │   │   │   ├── Record.java         // Data Transfer Object для представления записей
│   │   │   │   │   └── Column.java         // Data Transfer Object для столбцов
│   │   │   │   │
│   │   │   │   ├── parser/
│   │   │   │   │   ├── SQLParser.java      // Interpreter для разбора SQL запросов
│   │   │   │   │   ├── Query.java          // Command для представления выполнимых запросов
│   │   │   │   │   └── Tokenizer.java      // Strategy для различных методов токенизации
│   │   │   │   │
│   │   │   │   ├── api/
│   │   │   │   │   ├── DatabaseAPI.java    // Facade для предоставления простого API
│   │   │   │   │   └── CLI.java            // Command Line Interface использующий Facade
│   │   │   │   │
│   │   │   │   ├── storage/
│   │   │   │   │   ├── DataManager.java    // Singleton для управления файлами данных
│   │   │   │   │   ├── Serializer.java     // Strategy для сериализации объектов
│   │   │   │   │   └── Deserializer.java   // Strategy для десериализации данных
│   │   │   │   │
│   │   │   │   ├── transaction/
│   │   │   │   │   ├── TransactionManager.java // State для управления состояниями транзакций
│   │   │   │   │   └── TransactionLog.java     // Observer для логирования изменений
│   │   │   │   │
│   │   │   │   ├── indexing/
│   │   │   │   │   ├── IndexManager.java   // Singleton для управления индексами
│   │   │   │   │   └── BTreeIndex.java     // Adapter для адаптации B-дерева под нужды индексации
│   │   │   │   │
│   │   │   │   └── utils/
│   │   │   │       ├── ConfigLoader.java   // Singleton для загрузки конфигураций
│   │   │   │       └── Logger.java         // Singleton для логирования
│   │   │   │
│   │   ├── resources/
│   │   │   └── config.properties           // Файл конфигурации
│   │   │
│   ├── test/
│   │   ├── java/
│   │   │   ├── databaseScheme/
│   │   │   │   ├── core/
│   │   │   │   │   ├── DatabaseTest.java
│   │   │   │   │   ├── TableTest.java
│   │   │   │   │   └── RecordTest.java
│   │   │   │   │
│   │   │   │   ├── parser/
│   │   │   │   │   ├── SQLParserTest.java
│   │   │   │   │   └── TokenizerTest.java
│   │   │   │   │
│   │   │   │   ├── api/
│   │   │   │   │   └── DatabaseAPITest.java
│   │   │   │   │
│   │   │   │   ├── storage/
│   │   │   │   │   ├── DataManagerTest.java
│   │   │   │   │   ├── SerializerTest.java
│   │   │   │   │   └── DeserializerTest.java
│   │   │   │   │
│   │   │   │   ├── transaction/
│   │   │   │   │   ├── TransactionManagerTest.java
│   │   │   │   │   └── TransactionLogTest.java
│   │   │   │   │
│   │   │   │   ├── indexing/
│   │   │   │   │   ├── IndexManagerTest.java
│   │   │   │   │   └── BTreeIndexTest.java
│   │   │   │   │
│   │   │   │   └── utils/
│   │   │   │       ├── ConfigLoaderTest.java
│   │   │   │       └── LoggerTest.java
│   │   │   │
│   │   ├── resources/
│   │   │   └── testdata/
│   │   │
├── docs/
│   ├── README.md
│   └── API.md
```