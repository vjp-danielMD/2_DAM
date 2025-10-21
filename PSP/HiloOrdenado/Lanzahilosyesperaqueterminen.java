package HiloOrdenado;

public class Lanzahilosyesperaqueterminen {
    public static void main(String[] args) {
        Thread h1 = new Thread(new Hilo("H1"));
        Thread h2 = new Thread(new Hilo("H2"));
        h1.start();
        h2.start();
        try {
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            System.out.println("Hilo principal interrumpido.");
        }
        System.out.println("Hilo principal terminado.");
    }
}
