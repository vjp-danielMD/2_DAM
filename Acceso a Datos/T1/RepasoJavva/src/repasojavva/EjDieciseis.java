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
public class EjDieciseis {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = pedirCifra(sc);
        
        if (esPrimo(num)) {
            System.out.println("El numero es primo");
        }else{
            System.out.println("No es primo");
        }
    }

    public static boolean esPrimo(int num) {

        if (num <= 1) {
            return false;
        } else {
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int pedirCifra(Scanner sc) {
        System.out.println("Introduzca una cifra: ");
        return sc.nextInt();
    }
}
