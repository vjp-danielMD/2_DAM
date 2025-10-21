package HilosConcadenados;

import java.util.List;
import java.util.Random;

public class Generador implements Runnable {
    private List<Integer> numeros;
    private int turno;

    // Constructor
    public Generador(List<Integer> numeros, int turno) {
        this.numeros = numeros;
        this.turno = turno;
    }

    @Override
    public void run() {
        Random random = new Random();
        int numero = random.nextInt(10);

        // Asignamos el número a la lista en el índice que toca
        numeros.set(turno, numero);

        System.out.println("Hilo " + Thread.currentThread().getName() + " elige el número: " + numero);

    }

}