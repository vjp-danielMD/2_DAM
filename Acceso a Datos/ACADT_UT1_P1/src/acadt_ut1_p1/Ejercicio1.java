/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package acadt_ut1_p1;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author alumno
 */
public class Ejercicio1 {

    public static void main(String[] args) throws IOException {
        String ruta = "src/";
        String nombre = "file001.txt";
        File f = new File(ruta + nombre);
        
        f.createNewFile(); // error, lo que faltaba era precisamente hacer createNewFile() en el File lógico

        System.out.println("Nombre: " + f.getName());
        System.out.println("Ruta: " + f.getAbsolutePath());
        System.out.println("Directorio padre: " + f.getParent());
        if (f.exists()) {
            System.out.println("El fichero existe!");
            System.out.println("Permisos (rwx) => " + f.canRead()+" " + f.canWrite()+ " " + f.canExecute());
            System.out.println("Longitud del fichero: " + f.length() + " bytes");
        } else {
            System.out.println("El fichero no existe");
        }

    }

}
