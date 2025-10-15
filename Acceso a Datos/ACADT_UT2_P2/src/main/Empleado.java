/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

public class Empleado implements Comparable<Empleado> {

    private String nombre;
    private String apellido;
    private double sueldo;

    public Empleado(String nombre, String apellido, double sueldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sueldo = sueldo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public double getSueldo() {
        return sueldo;
    }

    @Override
    public int compareTo(Empleado o) {
        return Double.compare(this.sueldo, o.sueldo);
    }

    @Override
    public String toString() {
        return String.format("%s %s, sueldo: %.0f€", nombre, apellido, sueldo);
    }
}
