/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesvjp.exut7.danielmorenodominguez;

/**
 *
 * @author daniel
 */
public class Coche {

    private String marca;
    private String modelo;
    private String color;
    private String Foto;

    public Coche(String marca, String modelo, String color, String Foto) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.Foto = Foto;
    }
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String Foto) {
        this.Foto = Foto;
    }
    
}
