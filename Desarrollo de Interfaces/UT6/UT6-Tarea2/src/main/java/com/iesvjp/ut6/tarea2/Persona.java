/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesvjp.ut6.tarea2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author alumno
 */
public class Persona {
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty apellidos;
    private final SimpleIntegerProperty edad;

    public Persona(String nombre, String apellidos, int edad) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.edad = new SimpleIntegerProperty(edad);
    }

    // Getters para las propiedades (necesarios para el TableView)
    public String getNombre() { return nombre.get(); }
    public String getApellidos() { return apellidos.get(); }
    public int getEdad() { return edad.get(); }
    
    public SimpleStringProperty nombreProperty() { return nombre; }
    public SimpleStringProperty apellidosProperty() { return apellidos; }
    public SimpleIntegerProperty edadProperty() { return edad; }
}