# PostgresConnector - Класс для соединения с БД postgres

```java
var connector = new PostgresConnector(
    "jdbc:postgresql://localhost:5432/cookbook",
    "admin-postgres",
    "PasSW0rd"
);

connector.use((connection) -> {
    String stringQueryCreateTable = "SELECT * FROM \"Users\" WHERE \"ID\" = 1;";

    var query = connection.prepareStatement(stringQueryCreateTable);
    var result = query.executeQuery();

    result.next();
    System.out.println(result.getInt("ID"));

    return true;
});
```
