/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class Ejercicio2 {

    public static void copiarArchivo(String origen, String destino) {
        try {
            FileInputStream fis = new FileInputStream(origen);
            FileOutputStream fos = new FileOutputStream(destino);

            int byteLeido;
            while ((byteLeido = fis.read()) != -1) {
                fos.write(byteLeido);
            }
            System.out.println("Archivo copiado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del archivo origen:");
        String origen = "./src/" + sc.nextLine();
        System.out.println("Introduce el nombre del archivo destino:");
        String destino = "./src/" + sc.nextLine();

        copiarArchivo(origen, destino);
    }
}
