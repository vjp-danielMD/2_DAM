class TuberiaHidrogeno implements Runnable {
    private Controlador controlador;

    public TuberiaHidrogeno(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void run() {
        try {
            while (true) {
                controlador.recibirAtomo("H");
                Thread.sleep(100); // simula flujo continuo
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}