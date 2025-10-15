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
public class EjOnce {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;

        do {
            System.out.println("Introduce un numero: ");
            num = pedirNum(sc);
        } while (num < 0 || num == 0);
        System.out.println("Numero introducido: " + num);
    }

    public static int pedirNum(Scanner sc) {
        return sc.nextInt();
    }
}
