/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Ejercicio1 {

    public static void JSONReaderFile() {
        InputStream fis;
        Gson gson = new Gson();
        try {
            fis = new FileInputStream("./src/empleados.json");
            JsonReader reader = new JsonReader(new InputStreamReader(fis, "UTF-8"));

            reader.beginArray();
            while (reader.hasNext()) {
                Empleado emp = gson.fromJson(reader, Empleado.class);
                System.out.println(emp.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void JSONWriterFile(ArrayList<Empleado> empleados) {
        JsonWriter writer;
        try {
            writer = new JsonWriter(new FileWriter("./src/empleados.json"));
            Iterator<Empleado> itEmp = empleados.iterator();
            writer.beginArray();

            while (itEmp.hasNext()) {
                Empleado emp = itEmp.next();
                writer.beginObject();
                writer.name("nombre").value(emp.getNombre());
                writer.name("apellidos").value(emp.getApellidos());
                writer.name("edad").value(emp.getEdad());
                writer.name("puestos");
                writer.beginArray();
                List<String> puestos = emp.getPuesto();
                for (int i = 0; i < puestos.size(); i++) {
                    writer.value(puestos.get(i));
                }
                writer.endArray();
                writer.endObject();
            }
            writer.endArray();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
