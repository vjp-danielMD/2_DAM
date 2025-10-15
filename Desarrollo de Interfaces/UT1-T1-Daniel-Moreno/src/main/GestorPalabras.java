package main;

import java.io.*;
import java.util.*;

public class GestorPalabras {

    public static ArrayList<Palabra> cargarDatos() {
        List<Palabra> palabras = new ArrayList<>();
        BufferedReader br = null;

        try {
            FileReader fr = new FileReader("./src/main/palabras.txt");
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] leido = linea.split(";");
                if (leido.length == 2) {
                    Palabra palabra = new Palabra(leido[0], leido[1]);
                    palabras.add(palabra);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e2) {
                System.out.println(e2.getMessage());
            }
        }

        return new ArrayList<>(palabras);
    }
}

