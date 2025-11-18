class TuberiaOxigeno implements Runnable {
    private Controlador controlador;

    public TuberiaOxigeno(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void run() {
        try {
            while (true) {
                controlador.recibirAtomo("O");
                Thread.sleep(200); // simula flujo continuo
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}