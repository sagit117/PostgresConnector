package ru.axel.connector;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Интерфейс для описания лямбды, которую необходимо передать в метод use класса PostgresConnector
 * @see PostgresConnector
 */
public interface UseBlock<T> {
    T invoke(Connection connection) throws SQLException;
}
