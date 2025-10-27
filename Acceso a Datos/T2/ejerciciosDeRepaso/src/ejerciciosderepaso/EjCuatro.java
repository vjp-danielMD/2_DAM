/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosderepaso;

import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class EjCuatro {
    public static void main(String[] args) {
        String nombre = "";
        nombre = pedirNombre(nombre);
        System.out.println("Bienvenido " +nombre);
        
    }
    
    public static String pedirNombre(String nombre){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca su nombre");
        nombre = sc.nextLine();
        return nombre;
    }
}
