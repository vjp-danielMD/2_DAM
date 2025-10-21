import java.util.concurrent.CountDownLatch;
import java.util.*;

class Atleta implements Runnable {
    private int dorsal;
    private CountDownLatch salida;
    private Carrera carrera;

    Atleta(int dorsal, CountDownLatch salida, Carrera carrera) {
        this.dorsal = dorsal;
        this.salida = salida;
        this.carrera = carrera;
    }
    @Override
    public void run() {
        try {
            try {
            // Espera el pistoletazo de salida
            salida.await();

            // Simula el tiempo de carrera entre 9 y 11 segundos
            long tiempoCarrera = (long) (9000 + Math.random() * 2000);

            long inicio = System.currentTimeMillis();
            Thread.sleep(tiempoCarrera);
            long fin = System.currentTimeMillis();

            long tiempoTotal = fin - inicio;

            carrera.registrarLlegada(dorsal, tiempoTotal);

        } catch (InterruptedException e) {
            System.err.println("El atleta " + dorsal + " fue interrumpido durante la carrera.");
            Thread.currentThread().interrupt();
        }
        } catch (Exception e) {
            System.err.println("Se produjo un error inesperado para el atleta " + dorsal + ": " + e.getMessage());
        }
    }
    
}