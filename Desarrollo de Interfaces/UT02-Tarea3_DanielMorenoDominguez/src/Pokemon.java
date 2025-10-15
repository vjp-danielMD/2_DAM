/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alumno
 */
public class Pokemon {

    private String nombre;
    private String tipo;
    private String ataque1;
    private String ataque2;
    private String ataque3;

    public Pokemon(String nombre, String tipo, String ataque1, String ataque2, String ataque3) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ataque1 = ataque1;
        this.ataque2 = ataque2;
        this.ataque3 = ataque3;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAtaque1() {
        return ataque1;
    }

    public void setAtaque1(String ataque1) {
        this.ataque1 = ataque1;
    }

    public String getAtaque2() {
        return ataque2;
    }

    public void setAtaque2(String ataque2) {
        this.ataque2 = ataque2;
    }

    public String getAtaque3() {
        return ataque3;
    }

    public void setAtaque3(String ataque3) {
        this.ataque3 = ataque3;
    }

    public String[] toArrayString() {
        String[] s = new String[5];
        s[0] = this.nombre;
        s[1] = this.tipo;
        s[2] = this.ataque1;
        s[3] = this.ataque2;
        s[4] = this.ataque3;
        return s;
    }

    @Override
    public String toString() {
        return "Pokemon{" + "nombre=" + nombre + ", tipo=" + tipo + ", ataque1=" + ataque1 + ", ataque2=" + ataque2 + ", ataque3=" + ataque3 + '}';
    }

}
