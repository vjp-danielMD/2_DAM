public class Hilo implements Runnable {
    private String nombre;

    Hilo(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.println("hola soy el hilo: " + this.nombre + "\n");
        System.out.println("hola soy el hilo acabado: " + this.nombre + "\n");
    }    
}