/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proceso;

import java.io.IOException;

/**
 *
 * @author alumno
 */
public class Proceso {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        String[] command = {"cmd", "/c", "dir"};
        ProcessBuilder pb = new ProcessBuilder(command);

        try {
            Process ps = pb.start();
            int exitCode = ps.waitFor();

            System.out.println("cODIGO DE SALIDA: " + exitCode);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
