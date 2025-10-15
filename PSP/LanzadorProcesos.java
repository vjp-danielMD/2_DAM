public class LanzadorProcesos {
    public void ejecutar(String ruta) {
        ProcessBuilder pb;
        try {
            pb = new ProcessBuilder(ruta);
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String ruta = "C:\\Windows\\System32\\notepad.exe"; // Cambia esto por la ruta del proceso que deseas ejecutar
        LanzadorProcesos lp = new LanzadorProcesos();
        lp.ejecutar(ruta);
    }
}