package acadt_ut1_p2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File f = new File("./src/numeros.txt");

        try {
            FileWriter fw = new FileWriter(f);
            int numero;
            System.out.println("Introduce numeros (0 para terminar):");

            do {
                System.out.print("Numero: ");
                numero = sc.nextInt();
                if (numero != 0) {
                    fw.write(numero + System.lineSeparator()); // un número por línea
                }
            } while (numero != 0);

            System.out.println("Numeros guardados en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }

        // Lectura y suma
        int suma = 0;
        try{
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                suma += Integer.parseInt(linea);
            }
            System.out.println("La suma es: " + suma);
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }

        sc.close();
    }
}
