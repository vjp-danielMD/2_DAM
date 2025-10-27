/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.google.gson.Gson;
 /**
  * 
  * @author alumno
  */
public class Ejercicio4 {

    public static void main(String[] args) {
        String json = "{\"nombre\":\"Pepe\",\"apellidos\":\"Lopez\","
                    + "\"edad\":20,\"puestos\":[\"Gerente\",\"Jefe de zona\"]}";

        Gson gson = new Gson();
        Empleado emp1 = gson.fromJson(json, Empleado.class);
        System.out.println(emp1.toString());
    }
}

