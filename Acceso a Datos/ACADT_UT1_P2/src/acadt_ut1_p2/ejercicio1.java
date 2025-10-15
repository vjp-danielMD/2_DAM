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
 * @author alumno
 */
public class ejercicio1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File f = new File("./src/fichero.txt");

        try {
            Info usuario = pedirDatos(sc);

            FileWriter fw = new FileWriter(f, true); 
            fw.write(usuario.toString() + "\n");
            fw.close();

            System.out.println("\nDatos guardados correctamente.");

            System.out.println("\nDatos en el fichero:");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                mostrarDatos(linea);
            }

            br.close();
            fr.close();

        } catch (IOException e) {
            System.out.println("Error al manejar el fichero: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    public static Info pedirDatos(Scanner sc) {
        System.out.print("Introduzca el DNI del usuario: ");
        String dni = sc.nextLine();

        System.out.print("Introduzca el nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Introduzca los apellidos: ");
        String apellidos = sc.nextLine();

        System.out.print("Introduzca la fecha de nacimiento (dd/mm/yyyy): ");
        String fechaNacimiento = sc.nextLine();

        System.out.print("Introduzca el teléfono: ");
        String telefono = sc.nextLine();
        sc.nextLine();

        return new Info(dni, nombre, apellidos, fechaNacimiento, telefono);
    }
    
    

    public static void mostrarDatos(String linea) {
        String[] datos = new String[5];
        int contador = 0;

        // Recorremos palabra por palabra usando split por espacio
        for (String palabra : linea.split(" ")) {
            if (contador < 5) {
                datos[contador] = palabra;
                contador++;
            }
        }

        System.out.println("DNI: " + datos[0]);
        System.out.println("Nombre: " + datos[1]);
        System.out.println("Apellidos: " + datos[2]);
        System.out.println("Fecha de nacimiento: " + datos[3]);
        System.out.println("Teléfono: " + datos[4]);
        System.out.println("-----------------------------------");
    }
}
