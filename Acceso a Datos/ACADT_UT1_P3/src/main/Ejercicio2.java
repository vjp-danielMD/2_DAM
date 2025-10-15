/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class Ejercicio2 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        File f = new File("./src/tiempo.dat");

        // Guardar datos de la semana (7 días)
        for (int i = 0; i < 7; i++) {
            System.out.println("=== Día " + (i + 1) + " ===");
            Tiempo tm = pedirDatos(sc);
            escribir(f, tm);
            sc.nextLine(); // limpiar buffer
        }

        System.out.println("\nContenido del fichero:");
        verFichero(f);

        System.out.println("\n=== Día más caluroso ===");
        Tiempo masCaluroso = diaMasCaluroso(f);
        if (masCaluroso != null) {
            System.out.println("Fecha: " + masCaluroso.getFecha()
                    + ", Temp. Máxima: " + masCaluroso.getTempMax());
        } else {
            System.out.println("El archivo está vacío o no hay datos.");
        }
    }

    public static String pedirFecha(Scanner sc) {
        System.out.println("Introduzca la fecha: ");
        return sc.nextLine();
    }

    public static double pedirMaxTemp(Scanner sc) {
        System.out.println("Temperatura Maxima: ");
        return sc.nextDouble();
    }

    public static double pedirMinTemp(Scanner sc) {
        System.out.println("Temperatura Minima: ");
        return sc.nextDouble();
    }

    public static Tiempo pedirDatos(Scanner sc) {
        return new Tiempo(pedirFecha(sc), pedirMinTemp(sc), pedirMaxTemp(sc));

    }

    public static void escribir(File f, Tiempo tm) {
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos;

            if (f.length() == 0) {
                oos = new ObjectOutputStream(fos);
            } else {
                oos = new MyObjectOutputStream(fos);
            }

            oos.writeObject(tm);

            System.out.println("Temperatura agregada con exito.");

            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void verFichero(File f) throws FileNotFoundException, IOException {
        if (!f.exists() || f.length() == 0) {
            System.out.println("Fichero vacio.");
        } else {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            try {
                while (true) {
                    Tiempo tm = (Tiempo) ois.readObject();
                    System.out.println(tm.toString());
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Clase no encontrada");
            } catch (EOFException e) {
                System.out.println("Fin del archivo.");
                ois.close();
            }
        }
    }

    public static Tiempo diaMasCaluroso(File f) throws IOException {
        if (!f.exists() || f.length() == 0) {
            System.out.println("No hay registros.");
            return null;
        }

        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Tiempo masCaluroso = null;

        try {
            while (true) {
                Tiempo tm = (Tiempo) ois.readObject();
                if (masCaluroso == null || tm.getTempMax() > masCaluroso.getTempMax()) {
                    masCaluroso = tm;
                }
            }
        } catch (EOFException e) {
            // fin de archivo
        } catch (ClassNotFoundException e) {
            System.out.println("Clase Tiempo no encontrada.");
        } finally {
            ois.close();
        }

        return masCaluroso;
    }
}
