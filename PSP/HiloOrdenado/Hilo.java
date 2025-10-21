package HiloOrdenado;

/**
 * El siguiente programa lanza dos hilos. Cada uno de ellos hace pausas de duración 
 * aleatoria de entre 10 y 500 ms, utilizando el método sleep de la clase Thread.
 * El hilo principal utiliza el método join para esperar a que terminen los dos hilos
 * lanzados, por lo que siempre terminará el último. 
 * Los dos métodos anteriores pausan la ejecución del hilo, 
 * y durante ese periodo de tiempo se podría interrumpir.
 */

import java.util.Random;

class Hilo implements Runnable {
    private final String nombre;

    Hilo(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.println("Hola, soy el hilo: " + this.nombre);
        Random r = new Random();

        for (int i = 0; i < 5; i++) {
            int pausa = 10 + r.nextInt(491); // rango 10-500
            System.out.printf("Hilo %s hace una pausa de %d ms.%n", this.nombre, pausa);

            try {
                Thread.sleep(pausa);
            } catch (InterruptedException e) {
                System.out.printf("Hilo %s interrumpido.%n", this.nombre);
            }
        }

        System.out.printf("Hilo %s terminado.%n", this.nombre);
    }
}
