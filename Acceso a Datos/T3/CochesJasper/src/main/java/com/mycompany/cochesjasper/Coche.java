/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cochesjasper;

/**
 *
 * @author danie
 */
public class Coche {

    private int idCoche;
    private String marca;
    private String modelo;
    private String imagen;

    public Coche(int idCoche, String marca, String modelo, String imagen) {
        this.idCoche = idCoche;
        this.marca = marca;
        this.modelo = modelo;
        this.imagen = imagen;
    }

    public Coche(String marca, String modelo, String imagen) {
        this.marca = marca;
        this.modelo = modelo;
        this.imagen = imagen;
    }

    public int getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String[] toStringArray() {
        String[] s = new String[4];
        s[0] = String.valueOf(this.idCoche);
        s[1] = this.marca;
        s[2] = this.modelo;
        s[3] = this.imagen;
        return s;
    }
}
