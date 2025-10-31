/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class Ejercicio5 {

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("""
                               1. todos los empleados
                               2. empleado por numero
                               3. empleados con salario superior al valor introducido
                               4. empleados con salario inferior o igual al valor introducido
                               0. salir
                               Selecciones una opcion:""");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1 ->
                    consultarTodos();
                case 2 ->
                    consultarPorNumero();
                case 3 ->
                    consultarSalarioSuperior();
                case 4 ->
                    consultarSalarioInferior();
                case 0 ->
                    System.out.println("Saliendo...");
                default ->
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/empresa",
                "root",
                "123456"
        );
    }

    public static void consultarTodos() {
        String sql = "SELECT emp_no, apellido, oficio, salario, dept_no FROM empleados";
        try {
            Connection con = getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("LISTA DE EMPLEADOS");
            while (rs.next()) {
                System.out.println(
                        "Emp_no: " + rs.getInt("emp_no")
                        + ", Apellido: " + rs.getString("apellido")
                        + ", Oficio: " + rs.getString("oficio")
                        + ", Salario: " + rs.getDouble("salario")
                        + ", Dept: " + rs.getInt("dept_no")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void consultarPorNumero() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el número del empleado (emp_no): ");
        int empNo = sc.nextInt();

        String sql = "SELECT emp_no, apellido, oficio, salario, dept_no FROM empleados WHERE emp_no = " + empNo;
        try {
            Connection con = getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                System.out.println(
                        "Empleado encontrado: Emp_no: " + rs.getInt("emp_no")
                        + ", Apellido: " + rs.getString("apellido")
                        + ", Oficio: " + rs.getString("oficio")
                        + ", Salario: " + rs.getDouble("salario")
                        + ", Dept: " + rs.getInt("dept_no")
                );
            } else {
                System.out.println("No existe ningún empleado con ese numero.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void consultarSalarioSuperior() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce un salario mínimo: ");
        double salario = sc.nextDouble();

        String sql = "SELECT emp_no, apellido, oficio, salario FROM empleados WHERE salario > " + salario;
        try (Connection con = getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n--- EMPLEADOS CON SALARIO SUPERIOR A " + salario + " ---");
            boolean hayResultados = false;
            while (rs.next()) {
                hayResultados = true;
                System.out.println(
                        "Emp_no: " + rs.getInt("emp_no")
                        + ", Apellido: " + rs.getString("apellido")
                        + ", Oficio: " + rs.getString("oficio")
                        + ", Salario: " + rs.getDouble("salario")
                );
            }
            if (!hayResultados) {
                System.out.println("No se encontraron empleados con salario superior a " + salario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void consultarSalarioInferior() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce un salario máximo: ");
        double salario = sc.nextDouble();

        String sql = "SELECT emp_no, apellido, oficio, salario FROM empleados WHERE salario <= " + salario;
        try (Connection con = getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n--- EMPLEADOS CON SALARIO <= " + salario + " ---");
            boolean hayResultados = false;
            while (rs.next()) {
                hayResultados = true;
                System.out.println(
                        "Emp_no: " + rs.getInt("emp_no")
                        + ", Apellido: " + rs.getString("apellido")
                        + ", Oficio: " + rs.getString("oficio")
                        + ", Salario: " + rs.getDouble("salario")
                );
            }
            if (!hayResultados) {
                System.out.println("No se encontraron empleados con salario <= " + salario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
