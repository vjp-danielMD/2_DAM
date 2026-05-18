import java.util.concurrent.Semaphore;

/**
 * Clase que representa el salón de barbería con sincronización.
 * Gestiona la silla de barbero, sillas de espera y la sincronización entre barbero y clientes.
 */
public class SalonBarberia {
    private final int numSillasEspera;
    private int clientesEsperando;
    private boolean barberoDisponible;
    private boolean barberoDespierto;
    
    // Semáforo para controlar el acceso a las sillas de espera
    private final Semaphore sillasEspera;
    
    // Semáforo para que el barbero espere a que llegue un cliente
    private final Semaphore clienteLlegada;
    
    public SalonBarberia(int numSillasEspera) {
        this.numSillasEspera = numSillasEspera;
        this.clientesEsperando = 0;
        this.barberoDisponible = true;
        this.barberoDespierto = false;
        
        // Semáforo con permisos iniciales = numSillasEspera
        this.sillasEspera = new Semaphore(numSillasEspera);
        
        // Semáforo para que el barbero espere clientes (inicia en 0)
        this.clienteLlegada = new Semaphore(0);
    }
    
    /**
     * El barbero se sienta a esperar clientes.
     * Si no hay clientes, se duerme. Si hay clientes, atiende al primero.
     */
    public synchronized void barberoEsperando() throws InterruptedException {
        while (true) {
            if (clientesEsperando > 0) {
                // Hay clientes esperando, atiende al siguiente
                System.out.println("[BARBERO] Atendiendo a un cliente. Clientes esperando: " + (clientesEsperando - 1));
                barberoDisponible = false;
                return;
            } else {
                // No hay clientes, el barbero se duerme
                System.out.println("[BARBERO] Durmiendo... (no hay clientes)");
                barberoDespierto = false;
                wait(); // El barbero se duerme
            }
        }
    }
    
    /**
     * El barbero termina de atender a un cliente.
     */
    public synchronized void barberoTerminaAtencion() {
        System.out.println("[BARBERO] Terminó de atender al cliente");
        barberoDisponible = true;
        notifyAll(); // Despierta al barbero si está dormido
    }
    
    /**
     * Un cliente intenta entrar al salón de barbería.
     * 
     * @param idCliente ID del cliente
     * @return true si el cliente puede entrar, false si no hay sillas de espera
     */
    public boolean clienteIntenta(int idCliente) {
        // Intenta adquirir un permiso del semáforo de sillas de espera
        if (sillasEspera.tryAcquire()) {
            System.out.println("[CLIENTE " + idCliente + "] Entró al salón. Esperando...");
            return true;
        } else {
            System.out.println("[CLIENTE " + idCliente + "] No hay sillas de espera. Se va!");
            return false;
        }
    }
    
    /**
     * El cliente se sienta en una silla de espera.
     * Si el barbero está durmiendo, lo despierta.
     */
    public synchronized void clienteEspera(int idCliente) throws InterruptedException {
        clientesEsperando++;
        System.out.println("[CLIENTE " + idCliente + "] Se sentó en la sala de espera. Total esperando: " + clientesEsperando);
        
        // Si el barbero está durmiendo, lo despierta
        if (!barberoDespierto) {
            System.out.println("[CLIENTE " + idCliente + "] Despertando al barbero...");
            barberoDespierto = true;
            notify(); // Despierta al barbero
        }
        
        // El cliente espera a ser atendido
        while (clientesEsperando > 0) {
            wait();
        }
    }
    
    /**
     * El cliente es atendido por el barbero.
     * Simula el tiempo de atención.
     */
    public void clienteAtendido(int idCliente) throws InterruptedException {
        System.out.println("[CLIENTE " + idCliente + "] Siendo atendido por el barbero...");
        
        // Simula el tiempo de corte de cabello (entre 2 y 5 segundos)
        long tiempoAtencion = 2000 + (long) (Math.random() * 3000);
        Thread.sleep(tiempoAtencion);
        
        System.out.println("[CLIENTE " + idCliente + "] Terminó el corte. Pagando y se va...");
    }
    
    /**
     * El cliente libera la silla de espera.
     */
    public synchronized void clienteLibera(int idCliente) {
        clientesEsperando--;
        System.out.println("[CLIENTE " + idCliente + "] Liberó silla. Clientes esperando: " + clientesEsperando);
        sillasEspera.release(); // Libera el permiso del semáforo
        
        // Si hay más clientes esperando, despierta al barbero
        if (clientesEsperando > 0) {
            notifyAll();
        }
    }
    
    /**
     * Obtiene el número de clientes esperando.
     */
    public synchronized int getClientesEsperando() {
        return clientesEsperando;
    }
    
    /**
     * Obtiene si el barbero está disponible.
     */
    public synchronized boolean isBarberoDisponible() {
        return barberoDisponible;
    }
}
