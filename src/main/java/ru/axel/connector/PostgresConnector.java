package ru.axel.connector;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;

/** Класс для соединения с БД postgres */
public class PostgresConnector {
    private final HikariDataSource dataSource = new HikariDataSource();

    public PostgresConnector(String url, String user, String password) {
        this.dataSource.setJdbcUrl(url);
        this.dataSource.setUsername(user);
        this.dataSource.setPassword(password);
    }

    /**
     * Метод запустит блок кода для обращения к БД и вернет результат, предварительно закрыв соединение.
     * @param block блок кода для запроса к БД.
     * @return результат.
     * @param <T> тип результата.
     * @throws SQLException если произошла ошибка при исполнении запроса к БД.
     */
    public <T> T use(UseBlock<T> block) throws SQLException {
        var connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        T result;

        try {
            result = block.invoke(connection);
        } catch (Throwable error) {
            connection.rollback();
            connection.close();

            throw error;
        }

        connection.commit();
        connection.close();

        return result;
    }
}

