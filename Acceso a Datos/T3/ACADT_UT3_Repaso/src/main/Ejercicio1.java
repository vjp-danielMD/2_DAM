package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {
        Connection conexion = null;
        conexion = Conexion.getConnection();
        if (conexion == null) {
            System.out.println("No se pudo conectar a la base de datos.");
        } else {
            menu(conexion);
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void menu(Connection conexion) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Introducir productos");
            System.out.println("2. Introducir clientes");
            System.out.println("3. Mostrar productos");
            System.out.println("4. Mostrar clientes");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> Producto.insertarProducto(conexion);
                case 2 -> Cliente.insertarCliente(conexion);
                case 3 -> Producto.mostrarProductos(conexion);
                case 4 -> Cliente.mostrarClientes(conexion);
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
