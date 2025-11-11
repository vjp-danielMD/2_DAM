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
public class Series {

    private List<String> series;

    public Series(List<String> series) {
        this.series = new ArrayList<>(series);
    }

    public List<String> getSeries() {
        return series;
    }

    public void setSeries(List<String> series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "Series{" + "series=" + series + '}';
    }

}
