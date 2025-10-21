import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Carrera {
    private List<Resultado> resultados = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        Carrera carrera = new Carrera();
        carrera.iniciarCarrera();
    }

    public void iniciarCarrera() {
        final int NUM_ATLETAS = 8;
        CountDownLatch salida = new CountDownLatch(1);
        Thread[] atletas = new Thread[NUM_ATLETAS];

        try {
            for (int i = 0; i < NUM_ATLETAS; i++) {
                atletas[i] = new Thread(new Atleta(i + 1, salida, this));
                atletas[i].start();
            }

            // Señales de salida
            System.out.println("Preparados");
            Thread.sleep(1000);
            System.out.println("Listos");
            Thread.sleep(1000);
            System.out.println("¡Ya!");
            salida.countDown(); // Da la salida

            for (Thread t : atletas) {
                t.join();
            }

            System.out.println("\n--------- Resultado de la carrera -----------");
            Collections.sort(resultados, new Comparator<Resultado>() {
                @Override
                public int compare(Resultado r1, Resultado r2) {
                    if (r1.getTiempo() < r2.getTiempo()) {
                        return -1;
                    } else if (r1.getTiempo() > r2.getTiempo()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

            int posicion = 1;
            for (int i = 0; i < resultados.size(); i++) {
                Resultado r = resultados.get(i);
                System.out.println(posicion++ + ". Atleta " + r.getDorsal());
            }
        } catch (InterruptedException e) {
            System.err.println("La carrera fue interrumpida inesperadamente.");
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.err.println("Ocurrió un error durante la carrera: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public synchronized void registrarLlegada(int dorsal, long tiempo) {
        resultados.add(new Resultado(dorsal, tiempo));
        System.out.println("Atleta " + dorsal + " ha llegado en " + tiempo + " ms");
    }
}
