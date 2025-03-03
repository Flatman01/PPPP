package jm.task.core.jdbc.util;

import java.io.IOException;
import java.sql.*;

public class Util {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DRIVER = "org.postgresql.Driver";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(USER_NAME, PASSWORD, URL);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed");
        }
        return connection;
    }

    // реализуйте настройку соеденения с БД
}
