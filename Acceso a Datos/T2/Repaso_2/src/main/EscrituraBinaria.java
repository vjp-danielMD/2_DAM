/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author danie
 */
public class EscrituraBinaria {
    public static void main(String[] args) {
        List<Persona> personas = Arrays.asList(
                new Persona("Ana", 25),
                new Persona("Luis", 30));
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("personas.dat"));
            oos.writeObject(personas);
            System.out.println("Archivo binario creado con exito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
