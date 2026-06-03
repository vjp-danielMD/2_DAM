/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebaexamen;

/**
 *
 * @author david
 */
public class Participantes {
    
    private String nombre;
    private String alias;
    private String rango;
    
    public Participantes(){
        this.nombre = "";
        this.alias = "";
        this.rango = "";
    }

    public Participantes(String nombre, String alias, String rango) {
        this.nombre = nombre;
        this.alias = alias;
        this.rango = rango;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }
    
    
    
}
