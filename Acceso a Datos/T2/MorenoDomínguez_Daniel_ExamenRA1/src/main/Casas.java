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
public class Casas {

    private List<Casa> casas;

    public Casas(List<Casa> casas) {
        this.casas = new ArrayList<>(casas);
    }

    public List<Casa> getCasas() {
        return casas;
    }

    public void setCasas(List<Casa> casas) {
        this.casas = casas;
    }

    @Override
    public String toString() {
        return "Casas{" + "casas=" + casas + '}';
    }

}
