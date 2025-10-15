/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acadt_ut1_p1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class Ejercicio2 {

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");

            // Validación para asegurar que el usuario introduzca un número
            while (!sc.hasNextInt()) {
                System.out.println("Por favor, introduzca un número válido.");
                sc.next(); // Descarta la entrada incorrecta
                System.out.print("Seleccione una opción: ");
            }

            opcion = sc.nextInt();
            sc.nextLine(); // Limpia el buffer de Scanner después de nextInt()

            switch (opcion) {
                case 1:
                    crearDirectorio(sc);
                    break;
                case 2:
                    crearFichero(sc);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Introduzca una opción correcta.");
            }
        } while (opcion != 3);

        sc.close(); // Cierra el scanner al salir
    }

    public static void mostrarMenu() {
        System.out.println("""
                           
                           ====== MENÚ ======
                           1. Crear directorio
                           2. Crear fichero
                           3. Salir
                           ==================
                           """);
    }

    // Crear directorio
    public static void crearDirectorio(Scanner sc) {
        System.out.print("Introduzca la ruta del directorio: ");
        String ruta = sc.nextLine();

        File directorio = new File(ruta);

        if (directorio.exists()) {
            System.out.println("El directorio ya existe.");
        } else {
            if (directorio.mkdir()) {
                System.out.println("Directorio creado correctamente en: " + ruta);
            } else {
                System.out.println("No se pudo crear el directorio. Verifique la ruta.");
            }
        }
    }

    // Crear fichero
    public static void crearFichero(Scanner sc) {
        System.out.print("Introduzca la ruta completa del archivo a crear: ");
        String ruta = sc.nextLine();

        File fichero = new File(ruta);

        if (fichero.exists()) {
            System.out.println("El archivo ya existe.");
        } else {
            try {
                if (fichero.createNewFile()) {
                    System.out.println("Archivo creado correctamente en: " + ruta);
                } else {
                    System.out.println("No se pudo crear el archivo. Verifique la ruta.");
                }
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }
    }
}
