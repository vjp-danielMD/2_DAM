
/**
 *
 * @author danie
 */
public class Evento {

    private String nombre;
    private String fecha;
    private String ubicacion;

    public Evento(String nombre, String fecha, String ubicacion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String[] toArrayString() {
        String[] s = new String[3];
        s[0] = this.nombre;
        s[1] = this.fecha;
        s[2] = this.ubicacion;
        return s;
    }

    @Override
    public String toString() {
        return nombre + " (" + fecha + ") - " + ubicacion;
    }
}
