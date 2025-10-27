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
public class EjCatorce {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el número mínimo: ");
        int min = sc.nextInt();

        System.out.print("Introduce el número máximo: ");
        int max = sc.nextInt();

        if (min >= max) {
            System.out.println("El número mínimo debe ser menor que el máximo.");
            return;
        }

        System.out.println("10 números aleatorios entre " + min + " y " + max + ":");
        for (int i = 0; i < 10; i++) {
            int aleatorio = generarNum(min, max);
            System.out.println(aleatorio);
        }
    }

    public static int generarNum(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
