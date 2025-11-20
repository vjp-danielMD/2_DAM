package main;

import java.sql.*;

public class PruebaSQLite {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:ejemplo.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.println("Conectado a SQLite");

            // Crear tabla departamentos
            String sqlDept = "CREATE TABLE IF NOT EXISTS departamentos (" +
                             "dept_no INTEGER PRIMARY KEY, " +
                             "dnombre TEXT, loc TEXT)";
            conn.createStatement().executeUpdate(sqlDept);

            // Crear tabla empleados
            String sqlEmp = "CREATE TABLE IF NOT EXISTS empleados (" +
                            "emp_no INTEGER PRIMARY KEY, " +
                            "apellido TEXT, oficio TEXT, dir INTEGER, " +
                            "fecha_alt DATE, salario REAL, comision REAL, " +
                            "dept_no INTEGER, FOREIGN KEY(dept_no) REFERENCES departamentos(dept_no))";
            conn.createStatement().executeUpdate(sqlEmp);

            // Insertar datos
            conn.createStatement().executeUpdate("INSERT INTO departamentos VALUES (10,'CONTABILIDAD','SEVILLA')");
            conn.createStatement().executeUpdate("INSERT INTO departamentos VALUES (20,'INVESTIGACIÓN','MADRID')");

            // Consultar
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM departamentos");
            while (rs.next()) {
                System.out.println(rs.getInt("dept_no") + " - " +
                                   rs.getString("dnombre") + " - " +
                                   rs.getString("loc"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
