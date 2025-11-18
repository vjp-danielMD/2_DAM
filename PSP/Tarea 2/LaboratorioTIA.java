public class LaboratorioTIA {
    public static void main(String[] args) {
        Controlador controlador = new Controlador();

        Thread tuberiaH = new Thread(new TuberiaHidrogeno(controlador));
        Thread tuberiaO = new Thread(new TuberiaOxigeno(controlador));

        tuberiaH.start();
        tuberiaO.start();
    }
}