package main;

import java.io.Serializable;

public class Entrada implements Serializable {

    private static final long serialVersionUID = 1L;

    private String titulo;
    private String descripcion;

    public Entrada() {
    } 

    public Entrada(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
