/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acadt_ut1_p2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class Ejercicio4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Info info = null;
        File f = new File("./src/datos.txt");

        menu(sc, info, f);
    }

    public static String pedirDni(Scanner sc) {
        System.out.println("Introduzca el DNI del usuario: ");
        return sc.nextLine();
    }

    public static String pedirNombre(Scanner sc) {
        System.out.print("Introduzca el nombre: ");
        return sc.nextLine();
    }

    public static String pedirApellidos(Scanner sc) {
        System.out.print("Introduzca los apellidos: ");
        return sc.nextLine();
    }

    public static String pedirFecha(Scanner sc) {
        System.out.println("Introduzca la fecha de nacimiento (dd/mm/yyyy): ");
        return sc.nextLine();
    }

    public static String pedirTlf(Scanner sc) {
        System.out.println("Introduzca el numero de telefono: ");
        return sc.nextLine();
    }

    public static Info pedirDatos(Scanner sc) {
        return new Info(pedirDni(sc), pedirNombre(sc), pedirApellidos(sc), pedirFecha(sc), pedirTlf(sc));
    }

    public static void escribir(File f, Info info) {
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(info.getDni() + "\n");
            fw.write(info.getNombre() + "\n");
            fw.write(info.getApellidos() + "\n");
            fw.write(info.getFecha() + "\n");
            fw.write(info.getTelefono() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Datos de " + info.getNombre() + " " + info.getApellidos() + " guardados con exito.");
    }

    public static void leerUsuarios(File f, int limite, String dniBuscar) {
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String dni, nombre, apellidos, fecha, telefono;
            int contador = 0;
            boolean salir = false;

            while (!salir && (dni = br.readLine()) != null) {
                nombre = br.readLine();
                apellidos = br.readLine();
                fecha = br.readLine();
                telefono = br.readLine();

                // Si hay un DNI buscado y no coincide → saltamos impresión
                if (dniBuscar == null || dni.equalsIgnoreCase(dniBuscar)) {
                    System.out.println("-------");
                    System.out.println("DNI: " + dni);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Apellidos: " + apellidos);
                    System.out.println("Fecha: " + fecha);
                    System.out.println("Tlf: " + telefono);
                }

                contador++;

                // Si hay límite de usuarios y ya hemos llegado salimos del bucle
                if (limite > 0 && contador >= limite) {
                    salir = true;
                }

                // Si se buscaba un DNI y ya se mostró → salimos del bucle
                else if (dniBuscar != null && dni.equalsIgnoreCase(dniBuscar)) {
                    salir = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
    }

    public static void mostrarTodos(File f) {
        leerUsuarios(f, 0, null); // sin límite, sin filtro
    }

    public static void mostrarN(File f, int n) {
        leerUsuarios(f, n, null); // limite N, sin filtro
    }

    public static void buscarPorDni(File f, String dniBuscar) {
        leerUsuarios(f, 0, dniBuscar); //con filtro por DNI
    }

    public static void menu(Scanner sc, Info info, File f) {
        int opcion;
        do {
            System.out.println("1. Insertar usuario");
            System.out.println("2. Mostrar todos");
            System.out.println("3. Mostrar N primeros");
            System.out.println("4. Buscar por DNI");
            System.out.println("5. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    info = pedirDatos(sc);
                    escribir(f, info);
                    break;
                case 2:
                    mostrarTodos(f);
                    break;
                case 3:
                    System.out.print("¿Cuántos usuarios quieres mostrar?: ");
                    int n = sc.nextInt();
                    sc.nextLine();
                    mostrarN(f, n);
                    break;
                case 4:
                    System.out.print("Introduce DNI a buscar: ");
                    String dniBuscar = sc.nextLine();
                    buscarPorDni(f, dniBuscar);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 5);

    }
}
