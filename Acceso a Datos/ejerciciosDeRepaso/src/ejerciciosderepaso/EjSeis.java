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
public class EjSeis {
    
    public final static double IVA = 0.21;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int precio = pedirPrecio(sc);
        mostrarPrecioCompleto(precio);
    }

    public static int pedirPrecio(Scanner sc) {

        System.out.println("Introduzca el precio: ");
        return sc.nextInt();
    }

    public static void mostrarPrecioCompleto(int precio) {
        double conIva = precio + (precio * IVA);
        System.out.println("Precio con IVA: " + conIva);
    }
}
