/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;

/**
 *
 * @author danie
 */
public class Ejercicio5 {
    public static void main(String[] args) {
         try {
            Document doc = cargarXML("src/esdla.xml");

            modificarAutor(doc);
            anadirCiudad(doc, "Osgiliath", "Gondor");
            eleminarUltimoPersonajePorLista(doc);

            guardarXML(doc, "src/esdla_actualizado.xml");
            System.out.println("Archivo 'esdla_actualizado.xml' creado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Document cargarXML(String ruta){
        Document d = null;
        try {
            File file = new File(ruta);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            d = db.parse(file);
            d.getDocumentElement().normalize();
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        }
        return d;
    }
    
    public static Element crearElementoConTexto(Document doc, String nombre, String texto){
        Element elem = doc.createElement(nombre);
        elem.setTextContent(texto);
        return elem;
    }
    
    public static void modificarAutor(Document doc){
        Node autor = doc.getElementsByTagName("autor").item(0);
        while (autor.hasChildNodes()){
            autor.removeChild(autor.getFirstChild());
        }
        autor.appendChild(crearElementoConTexto(doc, "nombre", "John Ronald Reuel Tolkien"));
        autor.appendChild(crearElementoConTexto(doc, "diminutivo", "J. R. R. Tolkien"));
        autor.appendChild(crearElementoConTexto(doc, "nacimiento", "1892"));
        autor.appendChild(crearElementoConTexto(doc, "fallecimiento", "1973"));
    }
    
    public static void anadirCiudad(Document doc, String nombreCiudad, String faccion){
        Node ciudades = doc.getElementsByTagName("ciudades").item(0);
        Element ciudad = doc.createElement("ciudad");
        ciudad.appendChild(crearElementoConTexto(doc, "nombre", nombreCiudad));
        ciudad.appendChild(crearElementoConTexto(doc, "faccion", faccion));
        ciudades.appendChild(ciudad);
    }
    
    public static void eleminarUltimoPersonajePorLista(Document doc){
        NodeList listas = doc.getElementsByTagName("personajes");
        if (listas.getLength() > 0) {
            Node personajes = listas.item(0);
            NodeList tipos = personajes.getChildNodes();
            for (int i = 0; i < tipos.getLength(); i++) {
                Node tipo = tipos.item(i);
                if (tipo.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList lista = tipo.getChildNodes();
                    for (int j = lista.getLength() - 1; j >= 0; j--) {
                        if (lista.item(j).getNodeType() == Node.ELEMENT_NODE && lista.item(j).getNodeName().equals("personaje")) {
                            tipo.removeChild(lista.item(j));
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public static void guardarXML(Document doc, String rutaSalida){
        TransformerFactory tf = null;
        Transformer t = null;
        try {
            tf = TransformerFactory.newInstance();
            t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(rutaSalida));
            t.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
