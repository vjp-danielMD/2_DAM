import java.io.IOException;

public class GestionarProceso {

    public static void main(String[] args) {
        try {
            // 1. CREAR UN PROCESO ejecutando el proceso notepad.exe de Windows
            
            ProcessBuilder builder = new ProcessBuilder("notepad.exe"); // Windows
            

            System.out.println("Iniciando el proceso...");
            Process proceso = builder.start();

            
            // Esperar 10 segundos antes de terminarlo
            Thread.sleep(10000);

            // Verificar si el proceso sigue vivo
            if (proceso.isAlive()) {
                System.out.println("El proceso sigue en ejecución... Terminándolo ahora.");

                // 3. TERMINAR EL PROCESO
                proceso.destroy();

                // Esperar a que se termine
                proceso.waitFor();

                System.out.println("Proceso terminado con éxito.");
            } else {
                System.out.println("El proceso ya ha finalizado por sí solo.");
            }

        } catch (IOException e) {
            System.err.println("Error al iniciar el proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Error al esperar o terminar el proceso: " + e.getMessage());
        }
    }
}