/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesvjp.repaso;

/**
 *
 * @author danie
 */
public class Coche {

    private String marca;
    private String modelo;
    private String color;
    private String foto;

    public Coche(String marca, String modelo, String color, String foto) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.foto = foto;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public String getFoto() {
        return foto;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    
}
