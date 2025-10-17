/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class Ejercicio1 {

    public static String pedirFichero(Scanner sc) {
        System.out.println("Introduce el nombre del fichero: ");
        String ruta = "./src/" + sc.nextLine();
        return ruta;
    }

    public static void leerFichero(String ruta) {
        File f = new File(ruta);
        try {
            Scanner lector = new Scanner(f);
            System.out.println("Contenido del fichero");
            while (lector.hasNext()) {
                String linea = lector.next();
                System.out.println(linea);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado.  ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ruta = pedirFichero(sc);
        leerFichero(ruta);
    }
}
