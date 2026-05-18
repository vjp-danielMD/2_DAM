public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numSillasEspera = 3; // Número de sillas de espera disponibles
        SalonBarberia salon = new SalonBarberia(numSillasEspera);
        
        // Crear el hilo del barbero
        Thread hiloBarero = new Thread(new Barbero(salon), "Barbero");
        hiloBarero.start();
        
        // Simular la llegada de clientes en intervalos aleatorios
        int clienteId = 1;
        long tiempoSimulacion = 30000; // 30 segundos de simulación
        long tiempoInicio = System.currentTimeMillis();
        
        System.out.println("=== INICIO DE LA SIMULACIÓN DEL BARBERO DORMILÓN ===");
        System.out.println("Sillas de espera disponibles: " + numSillasEspera);
        System.out.println("=====================================================\n");
        
        while (System.currentTimeMillis() - tiempoInicio < tiempoSimulacion) {
            // Intervalo aleatorio entre llegadas de clientes (entre 1 y 3 segundos)
            long tiempoEspera = 1000 + (long) (Math.random() * 2000);
            Thread.sleep(tiempoEspera);
            
            // Crea un nuevo cliente y lo ejecuta en un hilo separado
            Cliente cliente = new Cliente(clienteId, salon);
            Thread hiloCliente = new Thread(cliente, "Cliente-" + clienteId);
            hiloCliente.start();
            
            clienteId++;
        }
        
        // Espera a que terminen los últimos clientes
        System.out.println("\n>>> Esperando a que terminen los últimos clientes...");
        Thread.sleep(5000);
        
        System.out.println("\n=== FIN DE LA SIMULACIÓN ===");
        System.exit(0);
    }
}
