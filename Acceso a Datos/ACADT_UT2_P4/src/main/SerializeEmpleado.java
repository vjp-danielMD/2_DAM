/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author alumno
 */
public class SerializeEmpleado {
    
    @SerializedName ("empleado")
    private Empleado empleado;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    public SerializeEmpleado(Empleado empleado){
        super();
        this.empleado =  empleado;
    }
}
