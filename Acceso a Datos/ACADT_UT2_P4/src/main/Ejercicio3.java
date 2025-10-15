/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;

/**
 *
 * @author alumno
 */
public class Ejercicio3 {
    public static void main(String[] args) {
        Empleado emp = new Empleado("Pepe",
                "Lopez",
                20,
                Arrays.asList("Gerente", "Jefe de zona"));
        Gson pg = new GsonBuilder().setPrettyPrinting().create();
        SerializeEmpleado sEmpleado = new SerializeEmpleado(emp);
        System.out.println(pg.toJson(sEmpleado));
                
    }
}
