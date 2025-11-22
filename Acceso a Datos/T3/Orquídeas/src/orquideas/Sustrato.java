/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orquideas;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class Sustrato {

    public static void crearTablaSustrato(Connection conexion) {
        String sql = "CREATE TABLE IF NOT EXISTS sustrato (\n"
                + "    id_sustrato INT PRIMARY KEY AUTO_INCREMENT,\n"
                + "    nombre VARCHAR(100) NOT NULL,\n"
                + "    descripcion TEXT);";
        try (Statement st = conexion.createStatement()) {
            st.execute(sql);
            System.out.println("Tabla creada con exito.");
        } catch (SQLException e) {
            if (e.getErrorCode() == 1050) {
                System.out.println("Tabla 'sustrato' ya existe.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public static void insertarSustrato(Connection conexion) {
        Scanner sc = new Scanner(System.in);
        String nombre, descripcion;

        System.out.println("== Nuevo Sustrato ==");
        System.out.println("Nombre: ");
        nombre = sc.nextLine();
        System.out.println("Descripcion: ");
        descripcion = sc.nextLine();

        String sql = "INSERT INTO sustrato (nombre, descripcion) VALUES (?, ?);";
        PreparedStatement ps = null;

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, descripcion);

            int filas = ps.executeUpdate();
            System.out.println("Sustrato '" + nombre + "' insertado; " + filas + " afectadas.");
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarSustratos(Connection conexion) {
        String sql = "SELECT * FROM sustrato;";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery(sql);
            int contador = 0;

            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Descripcion: " + rs.getString("descripcion"));
                System.out.println("----------------------");
                contador++;
            }
            if (contador == 0) {
                System.out.println("No hay sustratos registrados.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (st != null && rs != null) {
                try {
                    st.close();
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
