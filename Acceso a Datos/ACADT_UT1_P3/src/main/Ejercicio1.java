/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author alumno
 */
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class Ejercicio1 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        File f = new File("./src/notas.dat");

        menu(sc, f);
    }

    public static String pedirNombre(Scanner sc) {
        System.out.print("Introduzca el nombre: ");
        return sc.nextLine();
    }

    public static String pedirApellidos(Scanner sc) {
        System.out.print("Introduzca los apellidos: ");
        return sc.nextLine();
    }

    public static double pedirRedes(Scanner sc) {
        System.out.println("Introduzca la nota de Redes: ");
        return sc.nextDouble();
    }

    public static double pedirPorg(Scanner sc) {
        System.out.println("Introduzca la nota de PRGMC: ");
        return sc.nextDouble();
    }

    public static Alumno pedirDatos(Scanner sc) {
        return new Alumno(pedirNombre(sc), pedirApellidos(sc), pedirRedes(sc), pedirPorg(sc));
    }

    public static void menu(Scanner sc, File f) throws IOException {
        int opcion;
        do {
            System.out.println("1. Insertar usuario");
            System.out.println("2. Ver fichero");
            System.out.println("3. Mostrar Alumnos con mejor Mejor Media");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    Alumno al = pedirDatos(sc);
                    int ultimo = obtenerUltimoId(f);
                    al.setId(ultimo + 1);
                    escribir(f, al);
                    break;
                case 2:
                    verFichero(f);
                    break;
                case 3:
                    mejorMedia(f);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 4);
    }

    public static void escribir(File f, Alumno al) {
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos;

            if (f.length() == 0) {
                oos = new ObjectOutputStream(fos);
            } else {
                oos = new MyObjectOutputStream(fos);
            }

            oos.writeObject(al);

            System.out.println("Alumno " + al.getNombre() + " con ID " + al.getId() + " guardado en fichero.");

            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void verFichero(File f) throws IOException {

        if (!f.exists() || f.length() == 0) {
            System.out.println("Fichero vacio.");
        } else {

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            try {
                while (true) {
                    Alumno al = (Alumno) ois.readObject();
                    System.out.println(al);
                }
            } catch (ClassNotFoundException e) {
                System.out.println("clase no encontrada");
            } catch (EOFException e) {
                System.out.println("Fin del archivo.");
                ois.close();
            }
        }
    }

    public static void mejorMedia(File f) throws IOException {
        if (!f.exists() || f.length() == 0) {
            System.out.println("El fichero está vacío.");
            return;
        }

        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);

        double mejor = -1;
        try {
            while (true) {
                Alumno al = (Alumno) ois.readObject();
                double media = al.getMedia();
                if (media > mejor) {
                    mejor = media;
                }
            }
        } catch (EOFException e) {
            // fin de archivo
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada.");
        }
        ois.close();

        // Segundo recorrido para mostrar los mejores
        fis = new FileInputStream(f);
        ois = new ObjectInputStream(fis);

        try {
            while (true) {
                Alumno al = (Alumno) ois.readObject();
                if (al.getMedia() == mejor) {
                    System.out.println("Alumno con mejor media:");
                    System.out.println(al);
                }
            }
        } catch (EOFException e) {
            System.out.println("Fin del archivo.");
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada.");
        }
        ois.close();
    }

    public static int obtenerUltimoId(File f) {
        int maxId = 0;
        if (!f.exists() || f.length() == 0) {
            return 0;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                Alumno al = (Alumno) ois.readObject();
                if (al.getId() > maxId) {
                    maxId = al.getId();
                }
            }
        } catch (EOFException e) {
            // fin de archivo
        } catch (Exception e) {
            System.out.println("Error leyendo IDs: " + e.getMessage());
        }

        return maxId;
    }

}
