public class SimulacionPuente {
    public static void main(String[] args) {
        Puente puente = new Puente();

        // Crear 10 coches
        for (int i = 1; i <= 10; i++) {
            int peso = 1000 + (int)(Math.random() * 5000); // 1000-6000
            new Thread(new Vehiculo(i, peso, false, puente)).start();
        }

        // Crear 5 ambulancias
        for (int i = 11; i <= 15; i++) {
            int peso = 4000 + (int)(Math.random() * 4000); // 4000-8000
            new Thread(new Vehiculo(i, peso, true, puente)).start();
        }
    }
}