package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Autor autor;
    private List<Entrada> entrada = new ArrayList<>();

    public Blog() {
    } 

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Entrada> getEntrada() {
        return entrada;
    }

    public void setEntrada(List<Entrada> entrada) {
        this.entrada = entrada;
    }

    public void addEntrada(Entrada e) {
        entrada.add(e);
    }
}
