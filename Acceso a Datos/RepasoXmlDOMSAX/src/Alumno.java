
import java.io.Serializable;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author daniel
 */
public class Alumno implements Serializable {

    private String nombre;
    private List<String> asignaturas;

    public Alumno(String nombre, List<String> asignaturas) {
        this.nombre = nombre;
        this.asignaturas = asignaturas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getAsignaturas() {
        return asignaturas;
    }

    public String toString() {
        return nombre + " -> " + asignaturas;
    }
}
