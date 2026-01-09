/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ut4ex.danielmorenodominguez;

/**
 *
 * @author alumno
 */
public class Videojuego {

    private int idVideojuego;
    private String nombre;
    private String plataforma;
    private String imagen;

    public Videojuego(String nombre, String plataforma, String imagen) {
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.imagen = imagen;
    }

    public Videojuego(int idVideojuego, String nombre, String plataforma, String imagen) {
        this.idVideojuego = idVideojuego;
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.imagen = imagen;
    }

    public int getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(int idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String[] toStringArray() {
        String[] s = new String[4];
        s[0] = String.valueOf(this.idVideojuego);
        s[1] = this.nombre;
        s[2] = this.plataforma;
        s[3] = this.imagen;
        return s;
    }
}
