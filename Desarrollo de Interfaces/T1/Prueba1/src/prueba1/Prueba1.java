/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Prueba1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(cargarDatos().toString());
    }

    public static ArrayList<Palabra> cargarDatos() {
        File f = null;
        FileReader reader = null;
        BufferedReader buffer = null;
        List<Palabra> palabras = new ArrayList<>(); // lista de palabras

        try {
            f = new File("./src/prueba1/texto.txt"); // archivo de entrada
            reader = new FileReader(f);
            buffer = new BufferedReader(reader);

            String linea;
            while ((linea = buffer.readLine()) != null) { // leer cada linea del fichero
                String[] leido = linea.split(";"); // separar 
                if (leido.length == 3) { // comprobar 
                    Palabra pal = new Palabra(leido[0], leido[1], leido[2]); // crear objeto
                    palabras.add(pal); // agregar a la lista
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // mostrar error si ocurre
        } finally {
            try {
                if (reader != null) {
                    reader.close(); // cerrar lector
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return (ArrayList<Palabra>) palabras; // devolver lista de palabras
    }
}
