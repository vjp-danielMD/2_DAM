public class Barbero implements Runnable {
    private final SalonBarberia salon;
    
    public Barbero(SalonBarberia salon) {
        this.salon = salon;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                // El barbero espera a que llegue un cliente
                salon.barberoEsperando();
                
                // Atiende al cliente (simula el tiempo de corte)
                long tiempoCorte = 2000 + (long) (Math.random() * 3000);
                System.out.println("[BARBERO] Cortando pelo... ( " + tiempoCorte + " ms)");
                Thread.sleep(tiempoCorte);
                
                // Termina de atender
                salon.barberoTerminaAtencion();
            }
        } catch (InterruptedException e) {
            System.out.println("[BARBERO] Fue interrumpido");
            Thread.currentThread().interrupt();
        }
    }
}
