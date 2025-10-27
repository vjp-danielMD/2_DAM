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
public class Ejercicio2 {
    public static void main(String[] args) {
        Empleado emp1 = new Empleado("Pepe",
                "Lopez",
                20,
                Arrays.asList("Gerente", "Jefe de zona"));
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(prettyGson.toJson(emp1));
    }
}
