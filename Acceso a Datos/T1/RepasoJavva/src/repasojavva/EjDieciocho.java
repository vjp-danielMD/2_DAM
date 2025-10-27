/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repasojavva;

/**
 *
 * @author danie
 */
public class EjDieciocho {

    public static void main(String[] args) {
        String frase = "La lluvia en Sevilla es una maravilla";
        int contadorVocales = 0;

        for (int i = 0; i < frase.length(); i++) {
            char c = Character.toLowerCase(frase.charAt(i));
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                contadorVocales++;
            }
        }

        System.out.println("Numero total de vocales: " + contadorVocales);
    }
}
