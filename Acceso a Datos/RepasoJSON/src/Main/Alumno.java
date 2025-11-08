package Main;

import java.util.List;

/**
 *
 * @author daniel
 */

public class Alumno {

    private String nombre;
    private List<String> asignaturas;

    public Alumno(String nombre, List<String> asignaturas) {
        this.nombre = nombre;
        this.asignaturas = asignaturas;
    }

    public Alumno() {
        // Constructor vacío necesario para Gson
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getAsignaturas() {
        return asignaturas;
    }

    @Override
    public String toString() {
        return "Alumno{" + "nombre=" + nombre + ", asignaturas=" + asignaturas + '}';
    }
}
