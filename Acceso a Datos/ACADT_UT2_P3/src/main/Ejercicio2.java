/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author alumno
 */
public class Ejercicio2 {

    public static void main(String[] args) {
        File fichero = new File("./src/contactos.txt");
        ListaContactos listaContactos = crearXML(fichero);
        LeerXML();
    }

    public static ListaContactos crearXML(File fichero) {
        ListaContactos listaContactos = new ListaContactos();

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    Contacto c = new Contacto(partes[0], partes[1], partes[2], partes[3]);
                    listaContactos.add(c);
                }
            }
            System.out.println("Fichero de texto leído correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            XStream xstream = new XStream(new DomDriver("UTF-8"));
            xstream.alias("contacto", Contacto.class);
            xstream.alias("contactos", ListaContactos.class);
            xstream.addImplicitCollection(ListaContactos.class, "lista");

            xstream.toXML(listaContactos, new FileOutputStream("contactos.xml"));
            System.out.println("Creado fichero XML....");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return listaContactos;
    }

    public static void LeerXML() {
        XStream xstream = new XStream(new DomDriver("UTF-8"));

        // Permitir deserialización
        xstream.addPermission(AnyTypePermission.ANY);

        // Usar los mismos alias que en crearXML()
        xstream.alias("contacto", Contacto.class);
        xstream.alias("contactos", ListaContactos.class);
        xstream.addImplicitCollection(ListaContactos.class, "lista");

        try {
            // Asegurarse de que el nombre del archivo también coincida (minusculas)
            ListaContactos contactos = (ListaContactos) xstream.fromXML(new FileInputStream("contactos.xml"));

            if (contactos == null) {
                System.out.println("Error: No se pudo leer o deserializar el archivo XML.");
                return;
            }

            System.out.println("Número de Contactos: " + contactos.getListaContactos().size());

            List<Contacto> listaContactos = contactos.getListaContactos();
            ListIterator<Contacto> iterador = listaContactos.listIterator();

            while (iterador.hasNext()) {
                Contacto c = iterador.next();
                System.out.println(
                        "Nombre: " + c.getNombre()
                        + ", apellidos: " + c.getApellidos()
                        + ", email: " + c.getCorreo()
                );
            }

            System.out.println("Fin de listado.");
        } catch (FileNotFoundException e) {
            System.out.println("❌ Archivo contactos.xml no encontrado.");
            e.printStackTrace();
        }
    }

}
