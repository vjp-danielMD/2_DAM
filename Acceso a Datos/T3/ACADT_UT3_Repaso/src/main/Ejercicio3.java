package main;

import java.sql.*;
import java.util.Scanner;

/**
 * 
 * @author alumno
 */
public class Ejercicio3 {

    public static void main(String[] args) {
        Connection conexion = null;
        conexion = Conexion.getConnection();
        if (conexion == null) {
            System.out.println("No se pudo conectar a la base de datos.");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce el ID del cliente: ");
            int idCliente = sc.nextInt();
            mostrarVentasCliente(conexion, idCliente);
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void mostrarVentasCliente(Connection conexion, int idCliente) {
        String sql = """
            SELECT V.IDVENTA, P.DESCRIPCION, P.PVP, V.CANTIDAD,
                   (V.CANTIDAD * P.PVP) AS IMPORTE
            FROM VENTAS V
            JOIN PRODUCTOS P ON V.IDPRODUCTO = P.ID
            WHERE V.IDCLIENTE = ?
            ORDER BY V.IDVENTA
        """;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            int totalVentas = 0;
            double importeTotal = 0;

            System.out.println("\n🧾 Ventas del cliente: " + idCliente);

            while (rs.next()) {
                int idVenta = rs.getInt("IDVENTA");
                String descripcion = rs.getString("DESCRIPCION");
                double pvp = rs.getDouble("PVP");
                int cantidad = rs.getInt("CANTIDAD");
                double importe = rs.getDouble("IMPORTE");

                System.out.println("Venta: " + idVenta);
                System.out.println("Producto: " + descripcion + " -- PVP: " + pvp);
                System.out.println("Cantidad: " + cantidad);
                System.out.println("Importe: " + importe);
                System.out.println("-----------------------------------");

                totalVentas++;
                importeTotal += importe;
            }

            System.out.println("Número total de ventas: " + totalVentas);
            System.out.println("Importe total: " + String.format("%.2f", importeTotal));

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
            if (ps != null) {
                try {
                    ps.close(); 
                } catch (SQLException e) {
                    e.printStackTrace(); 
                }
            }
        }
    }
}
