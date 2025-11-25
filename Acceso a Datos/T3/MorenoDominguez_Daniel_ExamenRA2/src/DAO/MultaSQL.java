/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.Scanner;
import objeto.Multa;

/**
 *
 * @author alumno
 */
public class MultaSQL {

    public static void mostrarMultas(Connection con) {
        String sql = "SELECT * FROM multas";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            int contador = 0;
            while (rs.next()) {
                System.out.println("COD_MULTA: " + rs.getInt("codmulta"));
                System.out.println("MATRICULA: " + rs.getString("matricula"));
                System.out.println("FECHA: " + rs.getDate("fecha"));
                System.out.println("LUGAR: " + rs.getString("lugar"));
                System.out.println("TIPO: " + rs.getInt("tipo"));
                System.out.println("SANCION: " + rs.getDouble("sancion") + "€");
                System.out.println("====================");
                contador++;
            }
            System.out.println("TOTAL: " + contador + " multas.");
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

    public static void insertarMulta(Connection con) {
        Multa m = Multa.getMulta();
        String sql = "INSERT INTO multas (matricula, fecha, lugar, tipo, sancion) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, m.getMatricula());
            ps.setDate(2, m.getFecha());
            ps.setString(3, m.getLugar());
            ps.setInt(4, m.getTipo());
            ps.setDouble(5, m.getSancion());
            int filas = ps.executeUpdate();
            System.out.println("Multa insertada! Filas afectadas: " + filas);
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

    public static void CrearProcedimientoBorradoDeMulta(Connection con) {
        String sql = "create procedure borrarMulta(IN Pcodmulta INT) "
                + "begin "
                + "	DELETE FROM multas WHERE codmulta = Pcodmulta; "
                + "end";
        Statement st = null;
        try {
            st = con.createStatement();
            st.execute("DROP PROCEDURE IF EXISTS borrarMulta;");
            st.execute(sql);
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

    public static void callBorradoMulta(Connection con) {
        int codMulta = pedirCodMulta();
        CallableStatement cs = null;
        String call = "{call borrarMulta(?)}";
        try {
            cs = con.prepareCall(call);
            if (!existeMulta(con, codMulta)) {
                System.out.println("El codigo de la multa no existe!");
            }
            cs.setInt(1, codMulta);
            cs.executeUpdate(call);
            System.out.println("Multa eliminada!");
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

    public static boolean existeMulta(Connection con, int codMulta) {
        String sql = "SELECT codmulta FROM multas"
                + " WHERE codmulta = ?";
        Statement st = null;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, codMulta);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("La multa no existe.");
        return false;
    }

    public static int pedirCodMulta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Codigo de multa a eliminar: ");
        return sc.nextInt();
    }

    public static void crearPorcedimientoGetEstadisticas(Connection con) {
        Statement st = null;
        String sql = "create procedure GetEstadisticas() "
                + "begin "
                + "	select count(sancion) as total, matricula from multas group by matricula; "
                + "end";
        try {
            st = con.createStatement();
            st.execute("DROP PROCEDURE IF EXISTS GetEstadisticas;");
            st.execute(sql);
            System.out.println("Procedimiento creado.");
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

    public static void llamarProcedimientoGetEstadisticas(Connection con) {
        CallableStatement cs = null;
        String call = "{call GetEstadisticas()}";
        ResultSet rs = null;
        try {
            cs = con.prepareCall(call);
            rs = cs.executeQuery();
            System.out.println("Estadísticas por vehículo:");
            while (rs.next()) {
                System.out.println("Matricula: " + rs.getString("matricula") + " Total Sanciones: " + rs.getDouble("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cs != null && rs != null) {
                try {
                    cs.close();
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
