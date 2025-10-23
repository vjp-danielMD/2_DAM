/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author alumno
 */
public class LogicaNegocio {

    public List<Alumno> listaAlumnos;

    public LogicaNegocio() {
        this.listaAlumnos = new ArrayList<>();
        listaAlumnos.add(new Alumno("Jose", "Manuel", "2DAM"));
        listaAlumnos.add(new Alumno("Maria", "Campos", "2DAM"));
        listaAlumnos.add(new Alumno("Armando", "Villanueva", "2DAM"));
    }

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }
}
