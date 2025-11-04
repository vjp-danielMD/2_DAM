// synchronized: Solo un hilo puede ejecutar esa sección
// wait(): El hilo libera el lock,garantiza que ningún otro hilo pueda
// entrar al mismo código sincronizado, y queda bloqueado
// notify(): Despierta un hilo que estaba esperando.
// notifyall(): Despierta a todos los hilos que estaba esperando.

// Nota importante:
//   wait(), notify() y notifyAll() solo pueden usarse 
//dentro de código sincronizado, sino → ❌ IllegalMonitorStateException

//  Explicación del ejemplo:

// El Productor produce datos y los pone en un buffer.

// Si el buffer está lleno, debe esperar (wait()).

// El Consumidor toma datos del buffer.

// Si el buffer está vacío, debe esperar (wait()).

// Cuando el Productor produce → notifica (notify()) al Consumidor

// Cuando el Consumidor consume → notifica (notify()) al Productor
class Buffer {
    private int dato;
    private boolean disponible = false;

    public synchronized void producir(int valor) throws InterruptedException {
        while (disponible) {  // Si ya hay un dato, espera
            wait();
        }
        dato = valor;
        System.out.println("Producido: " + valor);
        disponible = true;
        notify(); // Notifica al consumidor
    }

    public synchronized int consumir() throws InterruptedException {
        while (!disponible) { // Si no hay dato, espera
            wait();
        }
        System.out.println("Consumido: " + dato);
        disponible = false;
        notify(); // Notifica al productor
        return dato;
    }
}

public class EjemploWaitNotify {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread productor = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    buffer.producir(i);
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }
        });

        Thread consumidor = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    buffer.consumir();
                    Thread.sleep(800);
                } catch (InterruptedException e) {}
            }
        });

        productor.start();
        consumidor.start();
    }
}

