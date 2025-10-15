/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acadt_ut1_p2;

/**
 *
 * @author alumno
 */
public class Info {

    private String dni;
    private String nombre;
    private String apellidos;
    private String fecha;
    private String telefono;

    public Info(String dni, String nombre, String apellidos, String fecha, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha = fecha;
        this.telefono = telefono;
    }

    public Info() {
        this.dni = "";
        this.nombre = "";
        this.apellidos = "";
        this.fecha = "";
        this.telefono = "";
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return  dni +" "+ nombre +" "+ apellidos +" "+ fecha  +" "+ telefono;
    }

}
