public class LanzaHilos {
    public static void main(String[] args) {
        Thread[] hilos = new Thread[600000];
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new Hilo("Hilo " + (i + 1)));
            hilos[i].start();
        }
        System.out.println("Hilo principal acabado");
    }
}