/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author danie
 */
public class Main {

    public static void main(String[] args) {
        List<Alumno> listaAlumnos = new ArrayList<>();
        listaAlumnos.add(new Alumno("Daniel", new ArrayList<>(
                Arrays.asList("matematicas", "lengua", "sociales"))
        ));
        listaAlumnos.add(new Alumno("Lira", new ArrayList<>(
                Arrays.asList("fotografia", "literatura", "pintura"))
        ));

        String ruta = "src/alumnosXStream.xml";
        escrituraXStream(listaAlumnos, ruta);
        mostrarXStream(ruta);
    }

    public static void escrituraXStream(List<Alumno> lista, String ruta) {
        try {
            XStream xStream = new XStream(new StaxDriver());
            xStream.alias("alumno", Alumno.class);
            xStream.alias("alumnos", List.class);
            xStream.alias("asignatura", String.class);

            FileWriter writer = new FileWriter(new File(ruta));
            /** sin formato
             * xStream.toXML(lista, writer);
             */
            xStream.marshal(lista, new PrettyPrintWriter(writer));
            writer.close();
            System.out.println("XML generador con éxito rotundo.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static List<Alumno> lecturaXStream(String ruta){
        List<Alumno> lista = new ArrayList<>();
        try {
            XStream xStream  = new XStream(new StaxDriver());
            xStream.alias("alumno", Alumno.class);
            xStream.alias("alumnos", List.class);
            xStream.alias("asignatura", String.class);
            
            xStream.addPermission(com.thoughtworks.xstream.security.AnyTypePermission.ANY);
            
            FileReader reader = new FileReader(new File(ruta));
            lista = (List<Alumno>) xStream.fromXML(reader);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static void mostrarXStream(String ruta){
        List<Alumno> leidos = lecturaXStream(ruta);
        System.out.println("---LECTURA DESDE XML con XSTREAM---");
        for (Alumno leido : leidos) {
            System.out.println(leido);
        }
    }
}
