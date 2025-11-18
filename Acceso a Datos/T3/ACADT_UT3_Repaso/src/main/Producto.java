package main;

import java.sql.*;
import java.util.Scanner;

/**
 * 
 * @author alumno
 */
public class Producto {

    public static void insertarProducto(Connection conexion) {
        Scanner sc = new Scanner(System.in);

        System.out.print("ID del producto: ");
        int id = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

        System.out.print("Stock actual: ");
        int stockActual = sc.nextInt();

        System.out.print("Stock mínimo: ");
        int stockMinimo = sc.nextInt();

        System.out.print("PVP: ");
        double pvp = sc.nextDouble();

        String sql = "INSERT INTO PRODUCTOS (ID, DESCRIPCION, STOCKACTUAL, STOCKMINIMO, PVP) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, descripcion);
            ps.setInt(3, stockActual);
            ps.setInt(4, stockMinimo);
            ps.setDouble(5, pvp);

            int filas = ps.executeUpdate();
            System.out.println("✅ Producto insertado. Filas afectadas: " + filas);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void mostrarProductos(Connection conexion) {
        String sql = "SELECT * FROM PRODUCTOS";
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conexion.createStatement();
            rs = st.executeQuery(sql);

            int contador = 0;
            System.out.println("\nLista de productos:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ID") +
                                   " | Descripción: " + rs.getString("DESCRIPCION") +
                                   " | Stock actual: " + rs.getInt("STOCKACTUAL") +
                                   " | Stock mínimo: " + rs.getInt("STOCKMINIMO") +
                                   " | PVP: " + rs.getDouble("PVP"));
                contador++;
            }
            System.out.println("Total de productos: " + contador);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
