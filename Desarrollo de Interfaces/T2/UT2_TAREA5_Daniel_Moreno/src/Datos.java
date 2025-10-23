
import java.io.Serializable;

/**
 *
 * @author daniel
 */

public class Datos implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String tiempo;
    private String nivel;

    public Datos(String nombre, String tiempo, String nivel) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTiempo() {
        return tiempo;
    }

    public String getNivel() {
        return nivel;
    }

    public String[] toArrayString() {
        return new String[]{nombre, tiempo, nivel};
    }

    @Override
    public String toString() {
        return "Datos{" + "nombre=" + nombre + ", tiempo=" + tiempo + ", nivel=" + nivel + '}';
    }
}
