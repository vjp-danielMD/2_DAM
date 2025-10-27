/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;

/**
 *
 * @author danie
 */
public class Ejercicio4 {

    public static void main(String[] args) {
        XStream xStream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypesByWildcard(new String[]{"main.*"});

        xStream.alias("esdla", ESDLA.class);
        xStream.alias("ciudades", Ciudades.class);
        xStream.alias("ciudad", Ciudad.class);
        xStream.alias("personajes", Personajes.class);
        xStream.alias("personaje", String.class);

        xStream.addImplicitCollection(Ciudades.class, "ciudad");

        File file = new File("src/esdla.xml");
        ESDLA esdla = (ESDLA) xStream.fromXML(file);

        leerXML(file, xStream, esdla);
    }

    public static void leerXML(File file, XStream xStream, ESDLA esdla) {
        System.out.println("Autor: " + esdla.getAutor());
        for (Ciudad c : esdla.getCiudades().getCiudad()) {
            System.out.println(c.getNombre() + " - " + c.getFaccion());
        }

        System.out.println("Hobbits: " + esdla.getPersonajes().getHobbits());
        System.out.println("Elfos: " + esdla.getPersonajes().getElfos());
        System.out.println("Enanos: " + esdla.getPersonajes().getEnanos());
        System.out.println("Hombres: " + esdla.getPersonajes().getHombres());
        System.out.println("Magos: " + esdla.getPersonajes().getMagos());
        System.out.println("Villanos: " + esdla.getPersonajes().getVillanos());

    }
}
