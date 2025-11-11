/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author alumno
 */
public class JuegoDeTronos {
    private String autor;
    private Casas casas;
    private Ciudades ciudades;
    private Historias historias;

    public JuegoDeTronos(String autor, Casas casas, Ciudades ciudades, Historias historias) {
        this.autor = autor;
        this.casas = casas;
        this.ciudades = ciudades;
        this.historias = historias;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Casas getCasas() {
        return casas;
    }

    public void setCasas(Casas casas) {
        this.casas = casas;
    }

    public Ciudades getCiudades() {
        return ciudades;
    }

    public void setCiudades(Ciudades ciudades) {
        this.ciudades = ciudades;
    }

    public Historias getHistorias() {
        return historias;
    }

    public void setHistorias(Historias historias) {
        this.historias = historias;
    }
    
    
}
