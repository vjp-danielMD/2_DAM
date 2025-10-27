/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author alumno
 */
public class Ejercicio2 {
    public static void main(String[] args) {
        JSONReaderFile();
    }
    public static void JSONReaderFile() {
        InputStream fis;
        Gson gson = new Gson();
        try {
            fis = new FileInputStream("./src/productos.json");
            JsonReader reader = new JsonReader(new InputStreamReader(fis, "UTF-8"));

            reader.beginArray();
            while (reader.hasNext()) {
                Producto pro = gson.fromJson(reader, Producto.class);
                System.out.println(pro.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
