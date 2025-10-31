/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alumno
 */
public class Pelicula {

    private String titulo;
    private String ano;
    private String duracion;

    public Pelicula(String titulo, String ano, String duracion) {
        this.titulo = titulo;
        this.ano = ano;
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String[] toArrayString() {
        String[] s = new String[3];
        s[0] = this.titulo;
        s[1] = this.ano;
        s[2] = this.duracion;
        return s;
    }

}
