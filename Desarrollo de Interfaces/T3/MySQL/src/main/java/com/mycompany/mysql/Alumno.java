/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mysql;

/**
 *
 * @author alumno
 */
public class Alumno {

    private int idAlumno;
    private String nombre;
    private String curso;

    public Alumno(int idAlumno, String nombre, String curso) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.curso = curso;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String[] toStringArray() {
        String[] s = new String[3];
        s[0] = String.valueOf(this.idAlumno);
        s[1] = this.nombre;
        s[2] = this.curso;
        return s;
    }

}
