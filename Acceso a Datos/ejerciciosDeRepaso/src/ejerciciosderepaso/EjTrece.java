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
public class EjTrece {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dia = pedirDia(sc);
        
        switch (dia) {
            case "lunes":
            case "martes":
            case "miercoles":
            case "jueves":
            case "viernes":
                System.out.println(dia +" es un dia laboral");
                break;
            case "sabado":
            case "domingo":
                System.out.println(dia + " es un dia no laboral");
            default:
                System.out.println("Escribe un dia correcto");
        }
    }

    public static String pedirDia(Scanner sc) {
        System.out.println("INtroduce un dia de la semana:");
        return sc.nextLine();
    }
}
