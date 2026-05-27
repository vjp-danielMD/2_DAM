package com.iesvjp.tareafinaljavafx;
/**
 * 
 * @author alumno
 */
public class PedidoItem {

    private String hamburguesa;
    private String pan;
    private String extras;
    private double precio;

    public PedidoItem(String hamburguesa, String pan, String extras, double precio) {
        this.hamburguesa = hamburguesa;
        this.pan = pan;
        this.extras = extras;
        this.precio = precio;
    }

    public String getHamburguesa() {
        return hamburguesa;
    }

    public void setHamburguesa(String hamburguesa) {
        this.hamburguesa = hamburguesa;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
