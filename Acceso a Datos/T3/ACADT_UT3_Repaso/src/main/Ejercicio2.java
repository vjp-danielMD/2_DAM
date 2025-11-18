package main;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {
        Connection conexion = null;
        conexion = Conexion.getConnection();

        if (conexion == null) {
            System.out.println("No se pudo conectar a la base de datos.");
        } else {
            insertarVenta(conexion);
        }

        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertarVenta(Connection conexion) {
        Scanner sc = new Scanner(System.in);

        System.out.print("ID de la venta: ");
        int idVenta = sc.nextInt();

        System.out.print("ID del cliente: ");
        int idCliente = sc.nextInt();

        System.out.print("ID del producto: ");
        int idProducto = sc.nextInt();

        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();

        LocalDate fechaVenta = LocalDate.now();

        boolean ventaExiste = false;
        boolean clienteExiste = false;
        boolean productoExiste = false;

        try {
            ventaExiste = existeVenta(conexion, idVenta);
            clienteExiste = existeCliente(conexion, idCliente);
            productoExiste = existeProducto(conexion, idProducto);

            if (ventaExiste) {
                System.out.println("⚠️ El ID de venta ya existe.");
            } else if (!clienteExiste) {
                System.out.println("⚠️ El cliente no existe.");
            } else if (!productoExiste) {
                System.out.println("⚠️ El producto no existe.");
            } else if (cantidad <= 0) {
                System.out.println("⚠️ La cantidad debe ser mayor que 0.");
            } else {
                String sql = "INSERT INTO VENTAS (IDVENTA, FECHAVENTA, IDCLIENTE, IDPRODUCTO, CANTIDAD) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = null;
                try {
                    ps = conexion.prepareStatement(sql);
                    ps.setInt(1, idVenta);
                    ps.setDate(2, Date.valueOf(fechaVenta));
                    ps.setInt(3, idCliente);
                    ps.setInt(4, idProducto);
                    ps.setInt(5, cantidad);

                    int filas = ps.executeUpdate();
                    if (filas > 0) {
                        System.out.println("Venta insertada.");
                    } else {
                        System.out.println("❌ No se pudo insertar.");
                    }
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean existeVenta(Connection conexion, int idVenta) throws SQLException {
        String sql = "SELECT IDVENTA FROM VENTAS WHERE IDVENTA = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idVenta);
            rs = ps.executeQuery();
            return rs.next();
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

    private static boolean existeCliente(Connection conexion, int idCliente) throws SQLException {
        String sql = "SELECT ID FROM CLIENTES WHERE ID = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();
            return rs.next();
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

    private static boolean existeProducto(Connection conexion, int idProducto) throws SQLException {
        String sql = "SELECT ID FROM PRODUCTOS WHERE ID = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();
            return rs.next();
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
