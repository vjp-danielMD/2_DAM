/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danie
 */
public class ListaContactos {

    private List<Contacto> lista = new ArrayList<>();

    public ListaContactos() { };
    
    public void add(Contacto con) {
        lista.add(con);
    }

    public List<Contacto> getListaContactos() {
        return lista;
    }
}
