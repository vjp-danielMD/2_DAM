class Controlador {
    private int hidrogenos = 0;
    private int oxigenos = 0;

    // Método para recibir átomos
    public synchronized void recibirAtomo(String tipo) throws InterruptedException {
        if (tipo.equals("H")) {
            while (hidrogenos >= 2) {
                System.out.println("Tubería H espera (ya hay suficientes H).");
                wait();
            }
            hidrogenos++;
            System.out.println("Controlador toma átomo de Hidrógeno. Total H=" + hidrogenos);
        } else {
            while (oxigenos >= 1) {
                System.out.println("Tubería O espera (ya hay suficiente O).");
                wait();
            }
            oxigenos++;
            System.out.println("Controlador toma átomo de Oxígeno. Total O=" + oxigenos);
        }

        // Intentar formar agua
        if (hidrogenos >= 2 && oxigenos >= 1) {
            hidrogenos -= 2;
            oxigenos -= 1;
            System.out.println("💧 Se produce una molécula de H2O");
            Thread.sleep(300); // evitar recalentamiento
            notifyAll();
        }
    }
}