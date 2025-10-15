package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Ejercicio6 {

    public static void main(String[] args) {
        // Crear lista de libros
        List<Libro> libros = new ArrayList<>();
        libros.add(new Libro("Cien años de soledad", "Gabriel García Márquez", 1967, 25.99));
        libros.add(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", 1605, 19.50));
        libros.add(new Libro("El principito", "Antoine de Saint-Exupéry", 1943, 14.75));

        // Serializar a JSON
        String json = serializeLibros(libros);
        System.out.println("=== JSON Serializado ===");
        System.out.println(json);

        // Deserializar la lista
        List<Libro> librosDeserializados = deserializeLibros(json);
        System.out.println("\n=== Lista Deserializada ===");
        for (Libro libro : librosDeserializados) {
            System.out.println(libro);
        }
    }

    public static String serializeLibros(List<Libro> libros) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(libros);
    }

    public static List<Libro> deserializeLibros(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Libro>>() {
        }.getType();
        return gson.fromJson(json, listType);
    }
}
