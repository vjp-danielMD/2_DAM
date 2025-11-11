/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Libros {
    List<String> libros;

    public Libros(List<String> libros) {
        this.libros = new ArrayList<>(libros);
    }

    public List<String> getLibros() {
        return libros;
    }

    public void setLibros(List<String> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Libros{" + "libros=" + libros + '}';
    }
    
}
