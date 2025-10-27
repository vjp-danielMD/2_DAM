/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acadt_ut1_p1;

import java.io.File;

/**
 *
 * @author alumno
 */
public class Ejercicio4 {

    public static void main(String[] args) {
        String ruta = "src/2DAM";
        File directorio = new File(ruta);
        
        String ficheros[] = directorio.list();
        System.out.println("Listado de ficheros: ");
        for (int i = 0; i < ficheros.length; i++) {
            System.out.println(ficheros[i]);
        }
        
//        File ficheros[] = directorio.listFiles();
//        System.out.println("Listado de ficheros: ");
//        for (int i = 0; i < ficheros.length; i++) {
//            if (ficheros[i].isFile()) {
//                System.out.println(ficheros[i].getName());
//            }
//        }
    }

}
