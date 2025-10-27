/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author alumno
 */
public class Ejercicio6 {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document registroLibros = db.parse(new File("./src/libros.xml"));

        registroLibros.getDocumentElement().normalize();

        System.out.println("El elemento raiz es: " + registroLibros.getDocumentElement().getNodeName());

        NodeList libros = registroLibros.getElementsByTagName("libro");

        System.out.println("Se han encontrado " + libros.getLength() + " libros");

        for (int i = 0; i < libros.getLength(); i++) {
            Node lib = libros.item(i);
            if (lib.getNodeType() == Node.ELEMENT_NODE) {
                Element libro = (Element) lib;
                System.out.println("ISBN: " + libro.getElementsByTagName("isbn").item(0).getTextContent());
                System.out.println("\tTitulo: " + libro.getElementsByTagName("titulo").item(0).getTextContent());
                System.out.println("\tAutor: " + libro.getElementsByTagName("autor").item(0).getTextContent());
                System.out.println("\tEditorial: " + libro.getElementsByTagName("editorial").item(0).getTextContent());
            }
        }
    }
}
