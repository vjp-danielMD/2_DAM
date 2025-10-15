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
public class Ejercicio5 {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        
        Document registroContactos = builder.parse(new File("./src/contactos.xml"));
        
        registroContactos.getDocumentElement().normalize();
        
        System.out.println("El elemento raiz es: " + registroContactos.getDocumentElement().getNodeName());
        
        NodeList contactos = registroContactos.getElementsByTagName("contacto");
        
        System.out.println("Se han encotnrado " + contactos.getLength() + " contactos.");
        
        for (int i = 0; i < contactos.getLength(); i++) {
            Node con = contactos.item(i);
            if (con.getNodeType() == Node.ELEMENT_NODE) {
                Element contacto = (Element) con;
                System.out.println("Nombre: " + contacto.getElementsByTagName("nombre").item(0).getTextContent());
                System.out.println("\tApellidos: " + contacto.getElementsByTagName("apellidos").item(0).getTextContent());
                System.out.println("\tEmail: " + contacto.getElementsByTagName("correo").item(0).getTextContent());
                System.out.println("\tTLF:: " + contacto.getElementsByTagName("Numero").item(0).getTextContent());
            }
        }
    }
}
