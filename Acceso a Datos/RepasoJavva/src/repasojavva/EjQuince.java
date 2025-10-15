/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repasojavva;

import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class EjQuince {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numero = pedirCifra(sc);
        int cifras = contarCifras(numero);
        System.out.println("El numero " +numero+ " tiene " +cifras+ " cifras." );
    }

    public static int pedirCifra(Scanner sc) {
        System.out.println("Introduzca una cifra: ");
        return sc.nextInt();
    }
    
    public static int contarCifras(int num){
        int contador = 0;
        
        if (num == 0) {
            contador = 1;
        } else {
            for (int i = Math.abs(num); i > 0; i /= 10) {
                contador++;
            }
        }
        return contador;
    }
}
