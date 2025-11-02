/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author daniel
 */
public class Raton {

    private String marca;
    private String modelo;
    private String frecuencia;

    public Raton(String marca, String modelo, String frecuencia) {
        this.marca = marca;
        this.modelo = modelo;
        this.frecuencia = frecuencia;
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

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String[] toArrayString() {
        String[] s = new String[3];
        s[0] = this.marca;
        s[1] = this.modelo;
        s[2] = this.frecuencia;
        return s;
    }
}
