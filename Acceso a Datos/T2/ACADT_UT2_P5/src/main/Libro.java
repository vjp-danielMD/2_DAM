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
public class Libro {

    private String ISBN;
    private String titulo;
    private List<String> autores;
    private String editorial;

    public Libro(String ISBN, String titulo, List<String> autores, String editorial) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autores = autores;
        this.editorial = editorial;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setIsbn(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Libro{" + "ISBN=" + ISBN + ", titulo=" + titulo + ", autores=" + autores + ", editorial=" + editorial + '}';
    }

}
