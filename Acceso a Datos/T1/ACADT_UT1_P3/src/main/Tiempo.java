/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.Serializable;

/**
 *
 * @author alumno
 */
public class Tiempo implements Serializable{

    private String fecha;
    private double tempMin;
    private double tempMax;

    public Tiempo() {
        this.fecha = "";
        this.tempMin = 0.0;
        this.tempMax = 0.0;
    }

    public Tiempo(String fecha, double tempMin, double tempMax) {
        this.fecha = fecha;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    @Override
    public String toString() {
        return "Tiempo{" + "fecha=" + fecha + ", tempMin=" + tempMin + ", tempMax=" + tempMax + '}';
    }

}
