/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author danie
 */
public class ESDLA {
    private String autor;
    private Ciudades ciudades;
    private Personajes personajes;

    public ESDLA(String autor, Ciudades ciudades, Personajes personajes) {
        this.autor = autor;
        this.ciudades = ciudades;
        this.personajes = personajes;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Ciudades getCiudades() {
        return ciudades;
    }

    public void setCiudades(Ciudades ciudades) {
        this.ciudades = ciudades;
    }

    public Personajes getPersonajes() {
        return personajes;
    }

    public void setPersonajes(Personajes personajes) {
        this.personajes = personajes;
    }
    
    
}
