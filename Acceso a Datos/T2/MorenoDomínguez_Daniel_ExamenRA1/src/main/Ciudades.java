/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Ciudades {

    private List<String> ciudades;

    public Ciudades(List<String> ciudades) {
        this.ciudades = new ArrayList<>(ciudades);
    }

    public List<String> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<String> ciudades) {
        this.ciudades = ciudades;
    }

    @Override
    public String toString() {
        return "Ciudades{" + "ciudades=" + ciudades + '}';
    }

}
