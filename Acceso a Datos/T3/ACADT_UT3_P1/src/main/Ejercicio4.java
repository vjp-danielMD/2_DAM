/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alumno
 */
public class Ejercicio4 {

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

            // Crear la sentencia
            Statement sentencia = conexion.createStatement();

            // Consulta: empleados con salario máximo
            String sql = """
                SELECT apellido, salario
                FROM empleados
                WHERE salario = (SELECT MAX(salario) FROM empleados)
            """;

            ResultSet resultado = sentencia.executeQuery(sql);

            System.out.println("""
                               Empleado(s) con salario m\u00e1ximo:
                               --------------------------------""");
            while (resultado.next()) {
                System.out.println("Apellido: " + resultado.getString("apellido")
                        + "\tSalario: " + resultado.getDouble("salario"));
            }

            // Cerrar recursos
            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
