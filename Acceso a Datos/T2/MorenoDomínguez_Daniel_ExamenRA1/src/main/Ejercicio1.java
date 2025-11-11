/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class Ejercicio1 {

    public static void main(String[] args) {
        String origen = "src/origen.txt";
        String destino = "src/destino.txt";
        List<Alumno> listaAlumnos = new ArrayList<>();
        menu(origen, destino, listaAlumnos);
    }

    public static File guardarAlumnos(List<Alumno> lista) {
        File file = new File("src/alumnos.dat");
        Alumno a = pedirAlumno();
        lista.add(a);
        ObjectOutputStream oos = null;
        try {
            if (file.exists()) {
                oos = new MyObjectOutputStream(new FileOutputStream(file, true));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(file));
            }
            oos.writeObject(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return file;
    }

    public static void leerFichero(File f, List<Alumno> lista) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(f));
            for (Alumno listaAlumnos : lista) {
                Alumno a = (Alumno) ois.readObject();
                System.out.println(a.toString());
            }
        } catch (EOFException e) {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Alumno pedirAlumno() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre del alumno:");
        String nombre = sc.nextLine();
        System.out.println("Edad del alumno:");
        int edad = sc.nextInt();

        return new Alumno(nombre, edad);
    }

    public static void listarFicheros() {
        String directorio = "src/";
        File f = new File(directorio);
        if (f.isDirectory()) {

        }
    }

    public static void copiarFicheros(String origen, String destino) {
        File origenfile = new File(origen);
        File destinoFile = new File(destino);
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new FileReader(origenfile));
            bw = new BufferedWriter(new FileWriter(destinoFile));
            String line = br.readLine();
//            while (true) {                
//                bw.write(line);
//            } lo comento por bucle infinito!
        } catch (IOException e) {
            try {
                bw.close();
                e.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void menu(String origen, String destino, List<Alumno> lista) {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        while (opcion != 4) {
            System.out.println("""
                           1. Copiar fichero origen a destino
                           2. Mostar listado de archivos
                           3. Guardar alumnos y mostrarlos
                           4. Salir
                           Elige una opcion:""");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    copiarFicheros(origen, destino);
                    break;
                case 2:
                    listarFicheros();
                    break;
                case 3:
                    leerFichero(guardarAlumnos(lista), lista);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
}
