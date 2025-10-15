/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class Ejercicio3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner busqueda = new Scanner(System.in);
        File f = new File("./src/contactos.dat");

        Contacto contactos = new Contacto(pedirNombre(sc), pedirApellidos(sc), pedirEmail(sc), pedirTelefono(sc));
        escribir(f, contactos);
        System.out.println("Introduzca criterio de busqueda: ");
        String bus = busqueda.nextLine();
        verFichero(f, bus);
        
    }

    public static String pedirNombre(Scanner sc) {
        System.out.print("Introduzca el nombre: ");
        return sc.nextLine();
    }

    public static String pedirApellidos(Scanner sc) {
        System.out.print("Introduzca los apellidos: ");
        return sc.nextLine();
    }

    public static String pedirEmail(Scanner sc) {
        System.out.println("Introduzca el email: ");
        return sc.nextLine();
    }

    public static String pedirTelefono(Scanner sc) {
        System.out.println("Introduzca el telefono: ");
        return sc.nextLine();
    }

    public static void escribir(File f, Contacto cn) {
        try {
            if (!f.exists() || f.length() == 0) {
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(cn);
                oos.close();
                fos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f, true);
                MyObjectOutputStream oos = new MyObjectOutputStream(fos);
                oos.writeObject(cn);
                oos.close();
                fos.close();
            }

            System.out.println("Contacto agregado correctamente.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static void verFichero(File f, String busqueda) {
        if (!f.exists() || f.length() == 0) {
            System.out.println("Fichero vacio.");
            return;
        }

        try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                try {
                    Contacto cn = (Contacto) ois.readObject();
                    if (cn.getNombre().toLowerCase().contains(busqueda.toLowerCase())
                            || cn.getApellidos().toLowerCase().contains(busqueda.toLowerCase())) {
                        System.out.println(cn.toString());
                    }
                } catch (java.io.EOFException e) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
