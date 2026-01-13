/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.List;

/**
 *
 * @author alumno
 */
public class JuegosMesa {

    private List<String> juegos;
    
    public void add(String j) {
        juegos.add(j);
    }

    public List<String> getVideojuegos() {
        return juegos;
    }
}
