/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alumno
 */
public class Articulo {

    private int idAritculo;
    private String articulo;
    private String descripcion;
    private int precio;

    public Articulo(int idAritculo, String articulo, String descripcion, int precio) {
        this.idAritculo = idAritculo;
        this.articulo = articulo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getIdAritculo() {
        return idAritculo;
    }

    public void setIdAritculo(int idAritculo) {
        this.idAritculo = idAritculo;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String[] toArrayString() {
        String[] s = new String[4];
        s[0] = String.valueOf(this.articulo);
        s[1] = this.articulo;
        s[2] = this.descripcion;
        s[3] = String.valueOf(this.precio);
        return s;
    }
}
