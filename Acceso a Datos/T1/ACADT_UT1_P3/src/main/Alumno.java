/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author alumno
 */
import java.io.Serializable;

public class Alumno implements Serializable {  // 

    private static int contadorId = 0;

    private int id;
    private String nombre;
    private String apellido;
    private double notaRedes;
    private double notaPorg;

    public Alumno(String nombre, String apellido, double notaRedes, double notaPorg) {
        this.id = ++contadorId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.notaRedes = notaRedes;
        this.notaPorg = notaPorg;
    }

    public Alumno() {
        this.id = ++contadorId;
        this.nombre = "";
        this.apellido = "";
        this.notaRedes = 0.0;
        this.notaPorg = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public double getNotaRedes() {
        return notaRedes;
    }

    public double getNotaPorg() {
        return notaPorg;
    }

    public double getMedia() {
        return (notaRedes + notaPorg) / 2.0;
    }

    @Override
    public String toString() {
        return "ID:" + id
                + "\nNombre: " + nombre
                + "\nApellido:" + apellido
                + "\nRedes:" + notaRedes
                + "\nNotaPorg:" + notaPorg
                + "\nMedia:" + getMedia();
    }
}
