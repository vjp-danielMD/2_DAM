// Synchronized protege una sección crítica de código 
//al trabajar con múltiples hilos.

class Contador {
    private int valor = 0;

    /*   //Bloquea el objeto completo mientras un hilo ejecuta ese método.
         
       public synchronized void incrementar() {
        valor++; // Sección crítica
    }*/
        // Bloquea solo un fragmento del método → más eficiente
    public void incrementar() {
       synchronized(this) {
          valor++;
       }
    }

    public int getValor() {
        return valor;
    }
}

public class EjemploSynchronized {
    public static void main(String[] args) throws InterruptedException {
        Contador contador = new Contador();

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) contador.incrementar();
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) contador.incrementar();
        });

        t1.start();
        t2.start();

        t1.join(); //Espera a que el hilo t1 termine
        t2.join();

        System.out.println("Valor final: " + contador.getValor());
    }
}

