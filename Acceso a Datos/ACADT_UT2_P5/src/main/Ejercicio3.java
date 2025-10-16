package main;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio3 {

    public static void main(String[] args) {
        XStream xstream = new XStream();

        // Seguridad
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypesByWildcard(new String[]{"main.*"});

        // Alias de clases
        xstream.alias("libros", ListaLibros.class);
        xstream.alias("libro", Libro.class);

        // Alias de campos
        xstream.aliasField("ISBN", Libro.class, "ISBN");

        // Lista de autores
        xstream.addImplicitCollection(ListaLibros.class, "libro");
        xstream.addImplicitCollection(Libro.class, "autores", "autor", String.class);

        ListaLibros listaLibros = null;
        try {
            listaLibros = (ListaLibros) xstream.fromXML(new File("./src/libros-14.xml"));
        } catch (Exception e) {
            System.out.println("Error al leer el XML: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Mostrar los libros leídos
        System.out.println("Libros cargados desde XML:");
        for (Libro libro : listaLibros.getLibro()) {
            System.out.println(libro);
        }

        // Convertir a JSON
        Gson gson = new Gson();
        String json = gson.toJson(listaLibros.getLibro());

        // Guardar en archivo
        try (FileWriter writer = new FileWriter("./src/libros.json")) {
            writer.write(json);
            System.out.println("\nArchivo JSON creado correctamente: libros.json");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }
}
