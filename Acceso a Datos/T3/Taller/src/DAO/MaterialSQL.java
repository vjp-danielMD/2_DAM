/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Objects.Material;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class MaterialSQL {

    public static Material getCategoria() {
        String nombre, descripcion;
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre:");
        nombre = sc.nextLine();
        System.out.println("Descripcion:");
        descripcion = sc.nextLine();
        return new Material(nombre, descripcion);
    }

    public static void crearTablaMaterial(Connection con) {
        String sql = """
                         CREATE TABLE Material (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             nombre VARCHAR(50) NOT NULL,
                             descripcion VARCHAR(255)
                         );
                         """;
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("Tabla Material creada");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void insertarMaterial(Connection con) {
        Material c = getCategoria();
        String sql = "INSERT INTO material (nombre, descripcion) VALUES (?, ?);";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            int filas = ps.executeUpdate();
            System.out.println("Material insertado; " + filas + " filas afectadas.");
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

    public static void mostrarMateriales(Connection con) {
        String sql = "SELECT * FROM material";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            int contador = 0;
            while (rs.next()) {
                System.out.println("-------------------");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("NOMBRE: " + rs.getString("nombre"));
                System.out.println("DESCRIPCION: " + rs.getString("descripcion"));
                contador++;
            }
            System.out.println("Total: " + contador);
        } catch (SQLException e) {
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

    public static void crearProcedimientoActualizarDescripcionMaterial(Connection con) {
        Statement st = null;
        String sqlDrop = "DROP PROCEDURE IF EXISTS actualizar_descripcion_material";
        String sqlCreate = "CREATE PROCEDURE actualizar_descripcion_material("
                + "IN p_id INT, IN p_nueva_descripcion VARCHAR(255)) "
                + "BEGIN "
                + "   UPDATE material SET descripcion = p_nueva_descripcion WHERE id = p_id; "
                + "END";

        try {
            st = con.createStatement();
            st.executeUpdate(sqlDrop);
            st.executeUpdate(sqlCreate);
            System.out.println("Procedimiento actualizar_descripcion_material creado con exito.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void llamarActualizarDescripcion(Connection con, int id, String nuevaDescripcion) {
        String call = "{call actualizar_descripcion_material(?, ?)}";
        CallableStatement cs = null;

        try {
            cs = con.prepareCall(call);
            cs.setInt(1, id);
            cs.setString(2, nuevaDescripcion);
            cs.executeUpdate();
            System.out.println("Descripcion del material " + id + " actualizado a: " + nuevaDescripcion);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int pedirIdCategoria() {
        Scanner sc = new Scanner(System.in);
        System.out.println("ID: ");
        return sc.nextInt();
    }

    public static String pedirNuevaDescripcion() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Descripcion: ");
        return sc.nextLine();
    }
}
