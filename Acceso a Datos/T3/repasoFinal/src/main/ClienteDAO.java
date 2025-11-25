/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author danie
 */
public class ClienteDAO {

    public static void crearTablaCliente(Connection con) {
        Statement st = null;
        String sql = """
                     CREATE TABLE cliente(
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     nombre VARCHAR(50),
                     direccion VARCHAR(100),
                     poblacion VARCHAR(50),
                     telefono VARCHAR(9),
                     nif VARCHAR(9));
                     """;
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("Tabla 'cliente' cerada con exito.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public static void insertarClietne(Connection con) {
        Cliente c = Cliente.getCliente();
        String sql = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getPoblacion());
            ps.setString(5, c.getTelefono());
            ps.setString(6, c.getNIF());
            int filas = ps.executeUpdate();
            System.out.println("Clinete " + c.getNombre() + " insertado.");
        } catch (Exception e) {
        }
    }

    public static void mostrarClientes(Connection conexion) {
        String sql = "SELECT * FROM cliente";
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conexion.createStatement();
            rs = st.executeQuery(sql);

            int contador = 0;
            System.out.println("\nLista de clientes:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ID")
                        + " | Nombre: " + rs.getString("NOMBRE")
                        + " | Dirección: " + rs.getString("DIRECCION")
                        + " | Población: " + rs.getString("POBLACION")
                        + " | Teléfono: " + rs.getString("TELEF")
                        + " | NIF: " + rs.getString("NIF"));
                contador++;
            }
            System.out.println("Total de clientes: " + contador);
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
