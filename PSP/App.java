public class App implements Runnable {
    private String nombre;

    App(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        this.nombre = "nuevo nombre";
    }

    public static void main(String[] args) {
        Thread hilo = new Thread(new App("nombre inicial"));
        hilo.start();
    }
}
