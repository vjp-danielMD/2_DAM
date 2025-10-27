/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Ejercicio3 {

    public static void main(String[] args) {
        ListaLibros lista = new ListaLibros();
        lista.add(new Libro("Cien Años de Soledad", "Gabriel García Márquez", 1967, "978-84-376-0494-7"));
        lista.add(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", 1605, "978-84-376-0495-4"));
        lista.add(new Libro("El Principito", "Antoine de Saint-Exupéry", 1943, "978-84-376-0496-1"));

        crearXML(lista);

        leerXML();
    }

    public static void crearXML(ListaLibros lista) {
        try {
            XStream xStream = new XStream(new DomDriver("UTF-8"));
            xStream.alias("libro", Libro.class);
            xStream.alias("libros", ListaLibros.class);
            xStream.addImplicitCollection(ListaLibros.class, "lista");

            xStream.toXML(lista, new FileOutputStream("./src/libros.xml"));
            System.out.println("XML generado con essito");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void leerXML() {
        try {
            XStream xStream = new XStream(new DomDriver("UTF-8"));
            xStream.addPermission(AnyTypePermission.ANY);

            xStream.alias("libro", Libro.class);
            xStream.alias("libros", ListaLibros.class);
            xStream.addImplicitCollection(ListaLibros.class, "lista");

            ListaLibros lista = (ListaLibros) xStream.fromXML(new FileInputStream("./src/libros.xml"));

            System.out.println("Libros deserializados desde XML: ");
            List<Libro> libros = lista.getListaLibros();
            for (Libro l : libros) {
                System.out.println("Título: " + l.getTitulo() + ", Autor: " + l.getAutor()
                        + ", Año: " + l.getAnio() + ", ISBN: " + l.getIsbn());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
