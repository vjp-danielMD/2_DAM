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
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class Ejercicio3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Correo correo = pedirDatos(sc);
        File f = new File("./src/correos.txt");

        escribir(f, correo);
        leer(f, correo);
    }

    public static String pedirDestinatario(Scanner sc) {
        System.out.print("Destinatario: ");
        return sc.nextLine();
    }

    public static String pedirAsunto(Scanner sc) {
        System.out.print("Re: ");
        return sc.nextLine();
    }

    public static String pedirCuerpo(Scanner sc) {
        System.out.println("Cuerpo: ");
        return sc.nextLine();
    }

    public static Correo pedirDatos(Scanner sc) {
        return new Correo(pedirDestinatario(sc), pedirAsunto(sc), pedirCuerpo(sc));
    }

    public static void escribir(File f, Correo correo) {
        try {
            FileWriter fw = new FileWriter(f, true);
            fw.write(correo.getDestinatario() + "\n");
            fw.write(correo.getAsunto() + "\n");
            fw.write(correo.getCuerpo() + "\n");
            fw.write(LocalDateTime.now().toString() + "\n");
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Correo guarado correctamente.");
    }

    public static void leer(File f, Correo correo) {
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String destinatario, asunto, cuerpo, fecha;

            while ((destinatario = br.readLine()) != null) {
                asunto = br.readLine();
                cuerpo = br.readLine();
                fecha = br.readLine();

                System.out.println("--------------");
                System.out.println("Destinatario: " + destinatario);
                System.out.println("Asunto: " + asunto);
                System.out.println("Cuerpo: " + cuerpo);
                System.out.println("Fecha: " + fecha);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
