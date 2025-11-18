package main;

import java.sql.*;
import java.util.Scanner;

public class Cliente {

    public static void insertarCliente(Connection conexion) {
        Scanner sc = new Scanner(System.in);

        System.out.print("ID del cliente: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        System.out.print("Población: ");
        String poblacion = sc.nextLine();

        System.out.print("Teléfono: ");
        String telef = sc.nextLine();

        System.out.print("NIF: ");
        String nif = sc.nextLine();

        String sql = "INSERT INTO CLIENTES (ID, NOMBRE, DIRECCION, POBLACION, TELEF, NIF) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setString(3, direccion);
            ps.setString(4, poblacion);
            ps.setString(5, telef);
            ps.setString(6, nif);

            int filas = ps.executeUpdate();
            System.out.println("Cliente insertado. Filas afectadas: " + filas);
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

    public static void mostrarClientes(Connection conexion) {
        String sql = "SELECT * FROM CLIENTES";
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
