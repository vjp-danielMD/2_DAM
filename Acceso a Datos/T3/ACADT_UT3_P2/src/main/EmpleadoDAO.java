package main;

import java.sql.*;
import java.util.Scanner;

public class EmpleadoDAO {

    public static void insertarEmpleado() {
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(
                "INSERT INTO empleados (emp_no, apellido, oficio, salario, dept_no) VALUES (?, ?, ?, ?, ?)")) {

            Scanner sc = new Scanner(System.in);
            System.out.print("Número de empleado: ");
            int empNo = sc.nextInt();
            sc.nextLine();
            System.out.print("Apellido: ");
            String apellido = sc.nextLine();
            System.out.print("Oficio: ");
            String oficio = sc.nextLine();
            System.out.print("Salario: ");
            double salario = sc.nextDouble();
            System.out.print("Departamento (dept_no): ");
            int deptNo = sc.nextInt();

            ps.setInt(1, empNo);
            ps.setString(2, apellido);
            ps.setString(3, oficio);
            ps.setDouble(4, salario);
            ps.setInt(5, deptNo);

            ps.executeUpdate();
            System.out.println("Empleado insertado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modificarEmpleado() {
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(
                "UPDATE empleados SET apellido=?, oficio=?, salario=?, dept_no=? WHERE emp_no=?")) {

            Scanner sc = new Scanner(System.in);
            System.out.print("Número de empleado a modificar: ");
            int empNo = sc.nextInt();
            sc.nextLine();
            System.out.print("Nuevo apellido: ");
            String apellido = sc.nextLine();
            System.out.print("Nuevo oficio: ");
            String oficio = sc.nextLine();
            System.out.print("Nuevo salario: ");
            double salario = sc.nextDouble();
            System.out.print("Nuevo dept_no: ");
            int deptNo = sc.nextInt();

            ps.setString(1, apellido);
            ps.setString(2, oficio);
            ps.setDouble(3, salario);
            ps.setInt(4, deptNo);
            ps.setInt(5, empNo);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Empleado actualizado.");
            } else {
                System.out.println("No se encontró el empleado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void eliminarEmpleado() {
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(
                "DELETE FROM empleados WHERE emp_no=?")) {

            Scanner sc = new Scanner(System.in);
            System.out.print("Número de empleado a eliminar: ");
            int empNo = sc.nextInt();

            ps.setInt(1, empNo);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Empleado eliminado correctamente.");
            } else {
                System.out.println("No se encontró el empleado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listarEmpleados() {
        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM empleados")) {

            System.out.println("\n--- LISTA DE EMPLEADOS ---");
            while (rs.next()) {
                System.out.printf("%d - %s - %s - %.2f - Dept:%d%n",
                        rs.getInt("emp_no"),
                        rs.getString("apellido"),
                        rs.getString("oficio"),
                        rs.getDouble("salario"),
                        rs.getInt("dept_no"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listarPorDepartamento() {
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM empleados WHERE dept_no = ?")) {

            Scanner sc = new Scanner(System.in);
            System.out.print("Número de departamento: ");
            int dept = sc.nextInt();
            ps.setInt(1, dept);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.printf("%d - %s - %s - %.2f%n",
                        rs.getInt("emp_no"),
                        rs.getString("apellido"),
                        rs.getString("oficio"),
                        rs.getDouble("salario"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void subirSueldoDepartamento() {
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(
                "UPDATE empleados SET salario = salario + ? WHERE dept_no = ?")) {

            Scanner sc = new Scanner(System.in);
            System.out.print("Número de departamento: ");
            int dept = sc.nextInt();
            System.out.print("Cantidad a subir: ");
            double cantidad = sc.nextDouble();

            ps.setDouble(1, cantidad);
            ps.setInt(2, dept);
            int filas = ps.executeUpdate();
            System.out.println("Sueldo actualizado para " + filas + " empleados.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
