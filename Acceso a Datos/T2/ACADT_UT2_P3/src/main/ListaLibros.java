/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class ListaLibros implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private List<Libro> lista = new ArrayList<>();
    
    public ListaLibros() {};
    
    public void add(Libro libro){
        lista.add(libro);
    }
    
    public List<Libro> getListaLibros(){
        return lista;
    }
}
