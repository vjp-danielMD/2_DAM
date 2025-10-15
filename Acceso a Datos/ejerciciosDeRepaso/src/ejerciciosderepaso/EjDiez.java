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
public class EjDiez {
    public static void main(String[] args) {
        int cantidad;
        int ventas = 0;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Cuantas ventas quieres?");
        cantidad = pedirNum(sc);
        
        for (int i = 0; i < cantidad; i++) {
            System.out.println("venta "+ i + ":" );
            ventas = sc.nextInt();
            ventas = ventas + ventas;
        }
        
        System.out.println("Suma de ventas total: "+ ventas);
    }
    
    public static int pedirNum(Scanner sc){
        return sc.nextInt();
    }
}
