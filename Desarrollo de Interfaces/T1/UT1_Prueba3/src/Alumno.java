/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alumno
 */
public class Alumno {

    private String nombre;
    private String apellidos;
    private String curso;

    public Alumno(String nombre, String apellidos, String curso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curso = curso;
    }

    public Alumno() {
        this.nombre = "";
        this.apellidos = "";
        this.curso = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    // IMPORTANTE; array que se inserta en la tabla
    public String[] toArrayString(){
        String[] s = new String[3];
        s[0] =  this.nombre;
        s[1] = this.apellidos;
        s[2] = this.curso;
        return s;
    }
    
    @Override
    public String toString() {
        return "Alumno{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", curso=" + curso + '}';
    }

}
