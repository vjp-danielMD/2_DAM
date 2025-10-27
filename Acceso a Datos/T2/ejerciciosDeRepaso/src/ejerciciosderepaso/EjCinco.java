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
public class EjCinco {
        
    public static void main(String[] args) {
        int num;
        Scanner sc = new Scanner (System.in);
        System.out.println("Introduce un numero");
        num =  sc.nextInt();
        if (esPar(num)) {
            System.out.println(num +" es par");
        }else{
            System.out.println(num +" es impar");
        }
    }
    
    public static boolean esPar(int num){
        return num % 2 == 0;
    }
}
