
import java.util.ArrayList;
import java.util.List;

// Clase que gestiona el cuaderno digital del usuario

public class CuadernoDigital {

    private List<Experiencia> experiencias = new ArrayList<>();

    public void registrarExperiencia(Experiencia exp) {
        experiencias.add(exp);
    }

    public List<Experiencia> obtenerExperiencias() {
        return experiencias;
    }
}
