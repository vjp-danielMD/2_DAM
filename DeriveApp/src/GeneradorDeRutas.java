
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Clase que genera rutas aleatorias basadas en mapas reales
public class GeneradorDeRutas {

    private Random random = new Random();

    public Ruta generarRutaAleatoria(String ubicacionActual) {
        // Simula generación de puntos de trayectoria
        List<String> trayectoria = new ArrayList<>();
        trayectoria.add(ubicacionActual);
        for (int i = 0; i < 5; i++) {
            trayectoria.add("Punto_" + random.nextInt(100));
        }
        return new Ruta(ubicacionActual, trayectoria);
    }
}
