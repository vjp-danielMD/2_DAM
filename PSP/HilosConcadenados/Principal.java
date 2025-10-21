package HilosConcadenados;

/* Realizar un programa en Java que simule el concepto de Hilos.
 *   Dicho programa realiza estas características:
 *      . Crea 5 hilos aleatorios en la cual cada hilo creará un número.
 *      . Al final, mostrará el número concadenado pero según su corres
 *pondiente situación del hilo.
   Ejemplo:
      Hilo 0: 5
      Hilo 4: 7
      Hilo 2: 2
      Hilo 1: 9
      Hilo 3: 8
      Número concadenado: 59287
 */

import java.util.ArrayList;
import java.util.List;

public class Principal {

    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>(); // Declaramos un
        // array con tamaño fijo de 5 elementos
        List<Thread> hilosGeneradores = new ArrayList<>();
        Integer NUM_HILOS = 5;

        for (int i = 0; i < NUM_HILOS; i++) {
            numeros.add(null);
        }

        // Creamos y lanzamos los hilos
        for (int i = 0; i < NUM_HILOS; i++) {
            Thread hilo = new Thread(new Generador(numeros, i));
            hilosGeneradores.add(hilo);
            hilo.start();
        }

        // Esperan al hilo final
        for (Thread hilo : hilosGeneradores) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Creamos y lanzamos el hilo combinador
        Thread combinador = new Thread(new Combinador(numeros));
        combinador.start();

    }
}
