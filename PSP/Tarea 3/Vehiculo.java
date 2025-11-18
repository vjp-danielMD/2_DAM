class Vehiculo implements Runnable {
    private int id;
    private int peso;
    private boolean esAmbulancia;
    private Puente puente;

    public Vehiculo(int id, int peso, boolean esAmbulancia, Puente puente) {
        this.id = id;
        this.peso = peso;
        this.esAmbulancia = esAmbulancia;
        this.puente = puente;
    }

    @Override
    public void run() {
        try {
            puente.entrarPuente(id, peso, esAmbulancia);
            Thread.sleep(1000 + (int)(Math.random() * 2000)); // tiempo cruzando
            puente.salirPuente(id, peso, esAmbulancia);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}