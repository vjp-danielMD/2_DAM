/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author daniel
 */
public class Main {

    public static void main(String[] args) {
        String rutaTXT = "src/personasTXT.txt";
        String rutaDAT = "src/personasDAT.dat";
        List<Persona> listaPersonas = new ArrayList<>(Arrays.asList(
                new Persona("Daniel", 22),
                new Persona("Lucía", 32),
                new Persona("Lira", 22)
        ));
        System.out.println("---TEXTO PLANO---");
        escribirTXT(listaPersonas, rutaTXT);
        leerTXT(rutaTXT);
        System.out.println("---BINARIO---");
        escribirdDAT(listaPersonas, rutaDAT);
        leerDAT(rutaDAT);
        System.out.println("---REESCRITURA DEL BINARIO---");
        reescrbirDAT(listaPersonas, rutaDAT);
        leerDAT(rutaDAT);
    }

    public static void reescrbirDAT(List<Persona> lista, String ruta) {
        ObjectOutputStream oos = null;
        File file = new File(ruta);
        
        try {
            if (file.exists()) {
                oos = new MyOutputStream(new FileOutputStream(file, true));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(file));
            }

            for (Persona persona : lista) {
                oos.writeObject(persona);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void escribirdDAT(List<Persona> lista, String ruta) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(ruta));
            for (Persona persona : lista) {
                oos.writeObject(persona);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void leerDAT(String ruta) {
        ObjectInputStream ois = null;
        boolean fin = false;
        try {
            ois = new ObjectInputStream(new FileInputStream(ruta));
            while (!fin) {
                try {
                    Persona p = (Persona) ois.readObject();
                    System.out.println(p.toString());
                } catch (EOFException e) {
                    fin = true;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void escribirTXT(List<Persona> lista, String ruta) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));
            for (Persona persona : lista) {
                bw.write(persona.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void leerTXT(String ruta) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(ruta)));
            String linea = br.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
