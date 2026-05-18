public class Cliente implements Runnable {
    private final int id;
    private final SalonBarberia salon;
    
    public Cliente(int id, SalonBarberia salon) {
        this.id = id;
        this.salon = salon;
    }
    
    @Override
    public void run() {
        try {
            // El cliente intenta entrar al salón
            boolean puedeEntrar = salon.clienteIntenta(id);
            
            if (!puedeEntrar) {
                // No hay sillas de espera disponibles, se va
                return;
            }
            
            // Se sienta en la sala de espera
            salon.clienteEspera(id);
            
            // Es atendido por el barbero
            salon.clienteAtendido(id);
            
            // Libera la silla y se va
            salon.clienteLibera(id);
            
        } catch (InterruptedException e) {
            System.out.println("[CLIENTE " + id + "] Fue interrumpido");
            Thread.currentThread().interrupt();
        }
    }
}
