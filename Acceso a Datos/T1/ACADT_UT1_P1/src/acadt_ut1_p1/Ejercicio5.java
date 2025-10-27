/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acadt_ut1_p1;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class Ejercicio5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String ruta = "src/";

        File carpeta = new File(ruta);

        if (!carpeta.exists() || !carpeta.isDirectory()) {
            System.out.println("La ruta indicada no es válida o no es una carpeta.");
        } else {

            System.out.print("Quieres mostrar solo archivos .txt? (s/n): ");
            String opcion = sc.nextLine().trim().toLowerCase();

            boolean soloTxt = opcion.equals("s");

            System.out.println("\nContenido de la carpeta y susbcarpetas:\n");
            mostrarContenido(carpeta, soloTxt);

            sc.close();
        }
    }

    public static void mostrarContenido(File carpeta, boolean soloTxt) {
        File[] elementos = carpeta.listFiles();

        if (elementos == null) {
            System.out.println("No se puede acceder a la carpeta: " + carpeta.getAbsolutePath());
        } else {
            for (File elemento : elementos) {
                if (elemento.isDirectory()) {
                    System.out.println("[DIR]  " + elemento.getAbsolutePath());
                    // Llamada recursiva para ver subcarpetas
                    mostrarContenido(elemento, soloTxt);
                } else {
                    if (!soloTxt || elemento.getName().toLowerCase().endsWith(".txt")) {
                        System.out.println(" [FILE] " + elemento.getAbsolutePath());
                    }
                }
            }
        }
    }
}
