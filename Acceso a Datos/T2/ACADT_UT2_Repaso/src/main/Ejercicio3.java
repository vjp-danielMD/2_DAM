/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author alumno
 */
public class Ejercicio3 {

    public static Set<String> cargarPalabrasTabu(String ruta) {
        Set<String> palabras = new HashSet<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;
            while ((linea = br.readLine()) != null) {
                palabras.add(linea.trim().toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return palabras;
    }

    public static void analizarEmails(String ruta, Set<String> palabras) {
        File carpeta = new File(ruta);
        if (!carpeta.exists() || !carpeta.isDirectory()) {
            System.out.println("No existe el directorio.");
        } else {
            File[] archivos = carpeta.listFiles();
            if (archivos == null || archivos.length == 0) {
                System.out.println("No hay emails.");
            } else {
                for (int i = 0; i < archivos.length; i++) {
                    File email = archivos[i];
                    procesarEmail(email, palabras);
                }
            }
        }
    }

    private static void procesarEmail(File email, Set<String> palabrasTabu) {
        int contadorTabu = 0;

        String nombreArchivo = email.getName();
        String remitente = nombreArchivo.replace(".txt", "").replace("From-", "");

        Scanner sc = null;
        try {
            sc = new Scanner(email);
            while (sc.hasNext()) {
                String palabra = sc.next().toLowerCase().replaceAll("[^a-záéíóúñ]", "");
                if (palabrasTabu.contains(palabra)) {
                    contadorTabu++;
                }
            }

            if (contadorTabu > 3) {
                System.out.println("El trabajador " + remitente
                        + " está infringiendo las normativas de seguridad (" + contadorTabu + " palabras tabú).");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se pudo leer el email: " + email.getName());
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    public static void main(String[] args) {
        String rutaTabu = "src/tabu.txt";      
        String rutaEmails = "src/emails";      

        Set<String> palbrasTabu = cargarPalabrasTabu(rutaTabu);
        analizarEmails(rutaEmails, palbrasTabu);
    }
}
