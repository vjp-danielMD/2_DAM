/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.google.gson.Gson;
import java.util.Scanner;
import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileInputStream;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author alumno
 */
public class Ejercicio2 {

    static ListaVideojuegos datos = null;

    public static void main(String[] args) {
        File file = new File("videojuegos.xml");
        int opcion;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("""
                               Seleccione una opcion:
                               1.- Deserializar y cargar en memoria, mostrar en pantalla
                               2.- Crear nuevo xml modificado
                               3.- de xml a json
                               4.- mostrar json por pantalla pretty
                               """);
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    datos = deserializarXstream(file);
                    if (datos != null) {
                        mostrarVideojuegos(datos);
                    }
                    break;
                case 2:
                    if (datos != null) {
                        nuevoXML(new File("catalogo.xml"));
                    }
                    break;
                case 3:
                    generarJSON(new File("catalogo.json"));
                    break;
                case 4:
                    leerYMostrarJson(new File("catalogo.json"));
                    break;
                case 0:
                    System.out.println("Saliendo ...");
                    break;
                default:
                    System.out.println("Seleccione una opcion correcta.");
            }
        } while (opcion != 0);
    }

    public static ListaVideojuegos deserializarXstream(File file) {
        try {
            XStream xstream = new XStream();
            XStream.setupDefaultSecurity(xstream);
            xstream.allowTypes(new Class[]{
                ListaVideojuegos.class,
                Videojuego.class
            });
            xstream.alias("Videojuegos", ListaVideojuegos.class);
            xstream.alias("Videojuego", Videojuego.class);
            xstream.addImplicitCollection(ListaVideojuegos.class, "videojuegos");

            FileInputStream fis = new FileInputStream(file);
            ListaVideojuegos lista = (ListaVideojuegos) xstream.fromXML(fis);

            fis.close();
            System.out.println("Datos cargados en memoria!");
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void mostrarVideojuegos(ListaVideojuegos lista) {
        System.out.println("\n=== VIDEOJUEGOS CARGADOS ===");
        for (Videojuego v : lista.getVideojuegos()) {
            System.out.println("Videojuego: "
                    + "\n  Titulo: " + v.getTitulo()
                    + "\n  Plataforma: " + v.getPlataforma()
                    + "\n  Precio: " + v.getPrecio()
                    + "\n");
        }
    }

    public static void nuevoXML(File file) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("Videojuegos");
            doc.appendChild(root);

            for (Videojuego v : datos.getVideojuegos()) {
                Element videojuego = doc.createElement("Videojuego");

                Element titulo = doc.createElement("titulo");
                titulo.setTextContent(v.getTitulo());
                videojuego.appendChild(titulo);

                Element plataforma = doc.createElement("plataforma");
                plataforma.setTextContent(v.getPlataforma());
                videojuego.appendChild(plataforma);

                Element precio = doc.createElement("precio");
                precio.setTextContent(String.valueOf(v.getPrecio()));
                videojuego.appendChild(precio);

                root.appendChild(videojuego);
            }

            Element nuevo = doc.createElement("Videojuego");

            Element titulo = doc.createElement("titulo");
            titulo.setTextContent("Elden Ring");
            nuevo.appendChild(titulo);

            Element plataforma = doc.createElement("plataforma");
            plataforma.setTextContent("PC");
            nuevo.appendChild(plataforma);

            Element precio = doc.createElement("precio");
            precio.setTextContent("59.99");
            nuevo.appendChild(precio);

            root.appendChild(nuevo);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("catalogo.xml creado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generarJSON(File file) {
        try {
            JsonWriter writer = new JsonWriter(new FileWriter(file));
            writer.setIndent("   ");
            writer.beginObject();
            writer.name("Videojuegos");
            writer.beginArray();
            for (Videojuego v : datos.getVideojuegos()) {
                writer.beginObject();
                writer.name("titulo").value(v.getTitulo());
                writer.name("plataforma").value(v.getPlataforma());
                writer.name("precio").value(v.getPrecio());
                writer.endObject();
            }
            writer.beginObject();
            writer.name("titulo").value("Elden Ring");
            writer.name("plataforma").value("PC");
            writer.name("precio").value(59.99);
            writer.endObject();

            writer.endArray();
            writer.endObject();
            writer.close();

            System.out.println("catalogo.json creado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void leerYMostrarJson(File file){
        try {
            FileReader fr = new FileReader(file);
            Gson gson = new Gson();
            
            Type tipoMapa = new TypeToken<HashMap<String, ArrayList<Videojuego>>>(){}.getType();
            HashMap<String, ArrayList<Videojuego>> mapa = gson.fromJson(fr, tipoMapa);
            fr.close();
            
            ArrayList<Videojuego> videojuegos = mapa.get("Videojuegos");
            
            System.out.println("== Contenido de catalogo.json ==");
            Gson pretty = new GsonBuilder().setPrettyPrinting().create();
            for (Videojuego v : videojuegos) {
                System.out.println(pretty.toJson(v));
            }   
            System.out.println("Json procesado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
