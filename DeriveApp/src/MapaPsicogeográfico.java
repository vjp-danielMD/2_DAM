
import java.util.ArrayList;
import java.util.List;

// Clase que genera mapas psicogeográficos personalizados

public class MapaPsicogeográfico {

    private List<EtiquetaEmocional> etiquetas = new ArrayList<>();

    public void agregarEtiqueta(String lugar, String emocion) {
        etiquetas.add(new EtiquetaEmocional(lugar, emocion));
    }

    public List<EtiquetaEmocional> obtenerMapa() {
        return etiquetas;
    }
}
