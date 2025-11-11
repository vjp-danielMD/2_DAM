/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.List;

/**
 *
 * @author alumno
 */
public class Historias {

    private List<Series> series;
    private List<Libros> libros;

    public Historias(List<Series> series, List<Libros> libros) {
        this.series = series;
        this.libros = libros;
    }

    
    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public List<Libros> getLibros() {
        return libros;
    }

    public void setLibros(List<Libros> libros) {
        this.libros = libros;
    }
    
    
}
