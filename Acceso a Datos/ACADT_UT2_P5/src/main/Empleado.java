/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.List;

/**
 *
 * @author alumno
 */
public class Empleado {

    private String nombre;
    private String apellidos;
    private int edad;
    private List puesto;

    public Empleado(String nombre, String apellidos, int edad, List puesto) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.puesto = puesto;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List getPuesto() {
        return puesto;
    }

    public void setPuesto(List puesto) {
        this.puesto = puesto;
    }

    @Override
    public String toString() {
        return "Empleado{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + ", puesto=" + puesto + '}';
    }
    
}
