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
public class Ejdoce {

    public static void main(String[] args) {
        String pass = "123456";
        int intentos = 3;
        String con;
        Scanner sc = new Scanner(System.in);
        boolean correcto = false;

        for (int i = 0; i < intentos; i++) {
            do {
                System.out.println("Introduzca la contrasena:");
                con = sc.nextLine();
                if (con == pass) {
                    correcto = true;
                }
            } while (correcto);
        }
        if (correcto) {
            System.out.println("Enhorabuena!");
        }else{
            System.out.println("Contrasena equivocada");
        }
    }
}
