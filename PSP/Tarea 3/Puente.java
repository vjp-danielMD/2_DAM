class Puente {
    private int pesoActual = 0;
    private int vehiculosActuales = 0;
    private int ambulanciasEsperando = 0;

    public synchronized void entrarPuente(int numVehiculo, int peso, boolean esAmbulancia) throws InterruptedException {
        if (esAmbulancia) ambulanciasEsperando++;

        while (!puedeEntrar(peso, esAmbulancia)) {
            System.out.println("Vehículo " + numVehiculo + (esAmbulancia ? " (Ambulancia)" : " (Coche)") +
                               " espera para entrar. Peso=" + peso);
            wait();
        }

        if (esAmbulancia) ambulanciasEsperando--;

        pesoActual += peso;
        vehiculosActuales++;
        System.out.println("Vehículo " + numVehiculo + (esAmbulancia ? " (Ambulancia)" : " (Coche)") +
                           " entra al puente. Peso=" + peso +
                           " | Total: " + vehiculosActuales + " vehículos, " + pesoActual + " Kg");
    }

    public synchronized void salirPuente(int numVehiculo, int peso, boolean esAmbulancia) {
        pesoActual -= peso;
        vehiculosActuales--;
        System.out.println("Vehículo " + numVehiculo + (esAmbulancia ? " (Ambulancia)" : " (Coche)") +
                           " sale del puente. Peso=" + peso +
                           " | Total: " + vehiculosActuales + " vehículos, " + pesoActual + " Kg");
        notifyAll();
    }

    private boolean puedeEntrar(int peso, boolean esAmbulancia) {
        boolean condicionesSeguridad = (pesoActual + peso <= 15000) && (vehiculosActuales < 10);
        if (!condicionesSeguridad) return false;

        // Prioridad: si hay ambulancias esperando, los coches deben esperar
        if (!esAmbulancia && ambulanciasEsperando > 0) return false;

        return true;
    }
}