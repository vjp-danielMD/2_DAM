/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acadt_ut1_p1;

import java.io.File;

/**
 *
 * @author alumno
 */
public class Ejercicio3 {

    public static void main(String[] args) {
        // Ruta original del archivo
        String rutaOriginal = "src/2DAM/AD/P1.txt";

        String rutaNueva = "src/2DAM/AD/practica1.txt";

        renombrarArchivo(rutaOriginal, rutaNueva);
    }

    public static void renombrarArchivo(String rutaActual, String rutaNueva) {
        File archivoOriginal = new File(rutaActual);
        File archivoNuevo = new File(rutaNueva);

        if (!archivoOriginal.exists()) {
            System.out.println("El archivo o carpeta a renombrar no existe: " + rutaActual);
        } else if (archivoNuevo.exists()) {
            System.out.println("Ya existe un archivo con el nombre destino: " + rutaNueva);
        } else if (archivoOriginal.renameTo(archivoNuevo)) {
            System.out.println("Archivo renombrado correctamente.");
            System.out.println("Ruta anterior: " + rutaActual);
            System.out.println("Nueva ruta: " + rutaNueva);
        } else {
            System.out.println("No se pudo renombrar el archivo o carpeta.");
        }
    }
}
