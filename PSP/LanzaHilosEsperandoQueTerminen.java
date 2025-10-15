public class LanzaHilosEsperandoQueTerminen {
    public static void main(String[] args) {
        Thread h1 = new Thread(new Hilo("Hilo 1"));
        Thread h2 = new Thread(new Hilo("Hilo 2"));
        h1.start();
        h2.start();
        try {
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo principal ha sido interrumpido");
        }   
        System.out.println("Hilo principal acabado");
    }
}
