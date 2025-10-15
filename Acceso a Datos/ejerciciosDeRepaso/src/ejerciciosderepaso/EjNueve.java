/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosderepaso;

/**
 *
 * @author alumno
 */
public class EjNueve {
    public static void main(String[] args) {
        mostrarNums();
    }
    
    public static boolean esPar(int num){
        return num % 2 == 0;
    }
    
    public static boolean divisibleTres(int num){
        return num % 3 == 0;
    }
    
    public static void mostrarNums(){
        for (int i = 1; i < 101; i++) {
            if ( esPar(i) && divisibleTres(i)){
                System.out.println(i);
            }
        }
    }
}
