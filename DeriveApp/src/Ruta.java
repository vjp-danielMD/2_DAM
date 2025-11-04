
import java.util.List;

// Clase que representa una ruta generada

public class Ruta {

    private String puntoInicio;
    private List<String> trayectoria;

    public Ruta(String puntoInicio, List<String> trayectoria) {
        this.puntoInicio = puntoInicio;
        this.trayectoria = trayectoria;
    }

    public String getPuntoInicio() {
        return puntoInicio;
    }

    public void setPuntoInicio(String puntoInicio) {
        this.puntoInicio = puntoInicio;
    }

    public List<String> getTrayectoria() {
        return trayectoria;
    }

    public void setTrayectoria(List<String> trayectoria) {
        this.trayectoria = trayectoria;
    }

    
}
