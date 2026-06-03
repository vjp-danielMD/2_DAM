/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ut7.tarea7.davidpugagallego;

/**
 *
 * @author alumno
 */
public class Datos {
    
    private String nombre;
    private String foto;
    
    public Datos() {
        this.nombre = "";
        this.foto = "";
    }

    public Datos(String nombre, String foto) {
        this.nombre = nombre;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}
