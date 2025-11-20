package main;

import java.sql.*;

public class ConexionSQLite {
    private static final String URL = "jdbc:sqlite:ejemplo.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
