/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author daniel
 */
public class Main {

    public static void main(String[] args) {
        List<Alumno> listaAlumnos = new ArrayList<>();
        listaAlumnos.add(new Alumno("Daniel", new ArrayList<>(Arrays.asList("matematicas", "lengua", "sociales"))));
        listaAlumnos.add(new Alumno("Lira", new ArrayList<>(Arrays.asList("fotografia", "literatura", "pintura"))));

        String ruta = "src/alumnosGson.json";

        escrituraGson(listaAlumnos, ruta);
        mostrarGson(ruta);
    }

    public static void escrituraGson(List<Alumno> lista, String ruta) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // 
            FileWriter writer = new FileWriter(new File(ruta));
            gson.toJson(lista, writer);
            writer.close();
            System.out.println("JSON generado correctamente en: " + ruta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Alumno> lecturaGson(String ruta) {
        List<Alumno> lista = new ArrayList<>();
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(new File(ruta));
            Type tipoLista = new TypeToken<List<Alumno>>() {
            }.getType(); // Necesario para List<Alumno>
            lista = gson.fromJson(reader, tipoLista);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static void mostrarGson(String ruta) {
        List<Alumno> leidos = lecturaGson(ruta);
        System.out.println("---LECTURA DESDE JSON con GSON---");
        for (Alumno a : leidos) {
            System.out.println(a);
        }
    }
}
