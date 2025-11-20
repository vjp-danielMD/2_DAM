package main;

import java.sql.*;

public class PruebaDerby {

    public static void main(String[] args) {
        String url = "jdbc:derby:ejemplo;create=true";

        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.println("Conectado a Derby");

            // Crear tabla departamentos
            String sqlDept = "CREATE TABLE departamentos (" +
                             "dept_no INT PRIMARY KEY, " +
                             "dnombre VARCHAR(15), loc VARCHAR(15))";
            conn.createStatement().executeUpdate(sqlDept);

            // Crear tabla empleados
            String sqlEmp = "CREATE TABLE empleados (" +
                            "emp_no INT PRIMARY KEY, " +
                            "apellido VARCHAR(10), oficio VARCHAR(10), dir INT, " +
                            "fecha_alt DATE, salario NUMERIC(10,2), comision NUMERIC(10,2), " +
                            "dept_no INT, CONSTRAINT fk_dept FOREIGN KEY(dept_no) REFERENCES departamentos(dept_no))";
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
