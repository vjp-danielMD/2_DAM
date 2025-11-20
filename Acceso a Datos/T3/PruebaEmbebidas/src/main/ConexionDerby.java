package main;

import java.sql.*;

public class ConexionDerby {
    private static final String URL = "jdbc:derby:ejemplo;create=true";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
