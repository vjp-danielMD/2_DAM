/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Objects.Pieza;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class PiezaSQL {

    public static Pieza getPieza() {
        Scanner sc = new Scanner(System.in);
        String nombre, descripcion;
        int stock;
        double precio;
        boolean unico;

        System.out.println("Nombre: ");
        nombre = sc.nextLine();
        System.out.println("Decripcion: ");
        descripcion = sc.nextLine();
        System.out.println("Stock: ");
        stock = sc.nextInt();
        System.out.println("Precio: ");
        precio = sc.nextDouble();
        System.out.println("Unico: ");
        unico = sc.nextBoolean();

        return new Pieza(nombre, descripcion, stock, precio, unico);
    }

    public static void crearTablaPieza(Connection con) {
        String sql = """
                     CREATE TABLE pieza(
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(50),
                        descripcion VARCHAR(100),
                        stock INT,
                        precio DECIMAL(10,2),
                        unico BOOLEAN
                     );
                     """;
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("Tabla Pieza creada con exito!");
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

    public static void insertarPieza(Connection con) {
        Pieza p = getPieza();
        String sql = """
                     INSERT INTO pieza (nombre, descripcion, stock, precio, unico) VALUES (?, ?, ?, ?, ?);
                     """;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getStock());
            ps.setDouble(4, p.getPrecio());
            ps.setBoolean(5, p.isUnico());
            int filas = ps.executeUpdate();
            System.out.println("Pieza insertada; " + filas + " filas afectadas.");
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

    public static void mostrarPiezas(Connection con) {
        String sql = "SELECT * FROM pieza";
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
                System.out.println("STOCK: " + rs.getInt("stock"));
                System.out.println("PRECIO: " + rs.getDouble("precio"));
                System.out.println("PIEZA UNICA: " + rs.getBoolean("unico"));
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

    public static void crearProcedimientoActualizarStock(Connection con) {
        Statement st = null;
        String sqlDrop = "DROP PROCEDURE IF EXISTS actualizar_stock";
        String sqlCreate
                = "CREATE PROCEDURE actualizar_stock (IN p_id INT, IN p_nuevo_stock INT) "
                + "BEGIN "
                + "   UPDATE pieza SET stock = p_nuevo_stock WHERE id = p_id; "
                + "END";

        try {
            st = con.createStatement();
            st.executeUpdate(sqlDrop);
            st.executeUpdate(sqlCreate);
            System.out.println("Procedimiento actualizar_stock creado con exito.");
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

    public static void llamarActualizarStock(Connection con, int id, int newStock) {
        String call = "{call actualizar_stock(?, ?)}";
        CallableStatement cs = null;
        try {
            cs = con.prepareCall(call);
            cs.setInt(1, id);
            cs.setInt(2, newStock);
            cs.executeUpdate();
            System.out.println("Stock de la pieza " + id + " actualizado en " + newStock);
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

    public static int pedirId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("ID: ");
        return sc.nextInt();
    }
    
    public static int pedirNewCantidad() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nuevo Stock:");
        return sc.nextInt();
    }
}
