/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.Serializable;

/**
 *
 * @author danie
 */
public class Contacto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private String apellidos;
    private String correo;
    private String Numero;

    public Contacto(String nombre, String apellidos, String correo, String Numero) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.Numero = Numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }
}
