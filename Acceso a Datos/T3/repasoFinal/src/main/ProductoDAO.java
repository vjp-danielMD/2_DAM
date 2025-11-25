/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.sql.*;

/**
 *
 * @author danie
 */
public class ProductoDAO {

    public static void crearTablaProducto(Connection con) {
        String sql = """
                     CREATE TABLE producto(
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     stock_actual INT,
                     stocl_minimo INT,
                     PVP DECIMAL(10,2)
                     );
                     """;
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("Tabla 'produto' creada exitosamente!");
        } catch (Exception e) {
        }
    }

    public static void insertarProducto(Connection conexion) {
        Producto p = Producto.getProducto();
        String sql = "INSERT INTO producto VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(sql);

            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getStockActual());
            ps.setInt(4, p.getStockMinimo());
            ps.setDouble(5, p.getPvp());

            int filas = ps.executeUpdate();
            System.out.println("Producto insertado. Filas afectadas: " + filas);
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
                System.out.println("ID: " + rs.getInt("id")
                        + " | Descripción: " + rs.getString("descripcion")
                        + " | Stock actual: " + rs.getInt("stock_actual")
                        + " | Stock mínimo: " + rs.getInt("stock_minimo")
                        + " | PVP: " + rs.getDouble("pvp"));
                contador++;
            }
            System.out.println("Total de productos: " + contador);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null && st != null) {
                try {
                    rs.close();
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
