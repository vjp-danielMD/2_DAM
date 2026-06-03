/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ut7.tareafinal.bocadillos;

/**
 *
 * @author alumno
 */
public class Hamburguesas {
    
    private String nombre;
    private String pan;
    private String extras;
    private Float precio;

    public Hamburguesas(String nombre, String pan, String extras, Float precio) {
        this.nombre = nombre;
        this.pan = pan;
        this.extras = extras;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPan() {
        return pan;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }    
    
}
