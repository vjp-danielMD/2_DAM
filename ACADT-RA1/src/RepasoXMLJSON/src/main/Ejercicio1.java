/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import java.io.*;

/**
 *
 * @author danie
 */
public class Ejercicio1 {

    public static void main(String[] args) {
        File file = new File("peliculas.xml");
        File newfile = new File("peliculas_top.xml");
        leerDOM(file);
        nuevoFicheroTop(newfile);
        anadirPelicula(newfile);
    }

    public static void anadirPelicula(File file) {
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();

            Element pelicula = doc.createElement("Pelicula");
            pelicula.setAttribute("id", "P006");

            //titulo
            Element titulo = doc.createElement("titulo");
            titulo.appendChild(doc.createTextNode("Blade Runner 2049"));
            pelicula.appendChild(titulo);

            //director
            Element director = doc.createElement("director");
            director.appendChild(doc.createTextNode("Denis Villeneuve"));
            pelicula.appendChild(director);

            //año
            Element anio = doc.createElement("anio");
            anio.appendChild(doc.createTextNode("2017"));
            pelicula.appendChild(anio);

            //puntuacion
            Element puntuacion = doc.createElement("puntuacion");
            puntuacion.appendChild(doc.createTextNode("8.5"));
            pelicula.appendChild(puntuacion);

            root.appendChild(pelicula);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void nuevoFicheroTop(File file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("Peliculas");
            doc.appendChild(root);
            String[][] peliculas = {
                {"P001", "The Shawshank Redemption", "Frank Darabont", "1994", "9.3"},
                {"P002", "The Godfather", "Francis Ford Coppola", "1972", "9.2"},
                {"P003", "Inception", "Christopher Nolan", "2010", "9.2"},
                {"P004", "The Matrix", "Wachowski Sisters", "1999", "8.7"}};

            for (String[] p : peliculas) {
                Element pelicula = doc.createElement("Pelicula");
                pelicula.setAttribute("id", p[0]);

                //titulo
                Element titulo = doc.createElement("titulo");
                titulo.appendChild(doc.createTextNode(p[1]));
                pelicula.appendChild(titulo);

                //director
                Element director = doc.createElement("director");
                director.appendChild(doc.createTextNode(p[2]));
                pelicula.appendChild(director);

                //año
                Element anio = doc.createElement("anio");
                anio.appendChild(doc.createTextNode(p[3]));
                pelicula.appendChild(anio);

                //puntuacion
                Element puntuacion = doc.createElement("puntuacion");
                puntuacion.appendChild(doc.createTextNode(p[4]));
                pelicula.appendChild(puntuacion);

                root.appendChild(pelicula);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("Archivo peliculas_top.xml creado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void leerDOM(File file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList lista = doc.getElementsByTagName("Pelicula");
            for (int i = 0; i < lista.getLength(); i++) {
                Node nodo = lista.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nodo;
                    String nombre = elem.getElementsByTagName("titulo").item(0).getTextContent();
                    String atributo = elem.getAttribute("id");
                    String director = elem.getElementsByTagName("director").item(0).getTextContent();
                    String anio = elem.getElementsByTagName("anio").item(0).getTextContent();
                    String puntuacion = elem.getElementsByTagName("puntuacion").item(0).getTextContent();
                    if (Double.parseDouble(puntuacion) >= 8) {
                        System.out.println(nombre + " | " + atributo
                                + "\n" + director + "\n" + anio + "\n" + puntuacion);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
