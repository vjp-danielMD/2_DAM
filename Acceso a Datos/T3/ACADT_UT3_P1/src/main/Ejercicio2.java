package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio2 {

    public static void main(String[] args) {
        selectMySQL();
    }

    public static void selectMySQL() {
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conexión con la BD
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/empresa",
                    "root",
                    "123456"
            );

            // Consulta
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT apellido, oficio, salario FROM empleados WHERE dept_no = 10";
            ResultSet resultado = sentencia.executeQuery(sql);

            // Mostrar resultados
            while (resultado.next()) {
                System.out.println(resultado.getString("apellido") + "\t"
                        + resultado.getString("oficio") + "\t"
                        + resultado.getDouble("salario"));
            }

            // Cierre de recursos
            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
