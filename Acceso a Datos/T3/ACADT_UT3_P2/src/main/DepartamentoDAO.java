package main;

import java.sql.*;
import java.util.Scanner;

public class DepartamentoDAO {

    public static void insertarDepartamento() {
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(
                "INSERT INTO departamentos (dept_no, dnombre, loc) VALUES (?, ?, ?)")) {

            Scanner sc = new Scanner(System.in);
            System.out.print("Número de departamento: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Localización: ");
            String loc = sc.nextLine();

            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setString(3, loc);

            ps.executeUpdate();
            System.out.println("Departamento insertado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void eliminarDepartamento() {
        try (Connection con = Conexion.getConnection()) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Número de departamento a eliminar: ");
            int id = sc.nextInt();

            // Verificar si tiene empleados
            PreparedStatement check = con.prepareStatement("SELECT COUNT(*) FROM empleados WHERE dept_no=?");
            check.setInt(1, id);
            ResultSet rs = check.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                System.out.println("No se puede eliminar: el departamento tiene empleados asignados.");
                return;
            }

            PreparedStatement ps = con.prepareStatement("DELETE FROM departamentos WHERE dept_no=?");
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Departamento eliminado.");
            } else {
                System.out.println("Departamento no encontrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modificarDepartamento() {
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(
                "UPDATE departamentos SET dnombre=?, loc=? WHERE dept_no=?")) {

            Scanner sc = new Scanner(System.in);
            System.out.print("Número de departamento: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Nuevo nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Nueva localización: ");
            String loc = sc.nextLine();

            ps.setString(1, nombre);
            ps.setString(2, loc);
            ps.setInt(3, id);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Departamento actualizado.");
            } else {
                System.out.println("No existe el departamento.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listarDepartamentos() {
        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM departamentos")) {

            System.out.println("\n--- LISTA DE DEPARTAMENTOS ---");
            while (rs.next()) {
                System.out.printf("%d - %s - %s%n",
                        rs.getInt("dept_no"),
                        rs.getString("dnombre"),
                        rs.getString("loc"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
