/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesvjp.ut7t7.modelo;

import javafx.beans.property.SimpleStringProperty;

public class Registro {

    private SimpleStringProperty nombre;
    private SimpleStringProperty foto;

    public Registro(String nombre, String foto) {
        this.nombre = new SimpleStringProperty(nombre);
        this.foto = new SimpleStringProperty(foto);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getFoto() {
        return foto.get();
    }

    public void setFoto(String foto) {
        this.foto.set(foto);
    }
}
