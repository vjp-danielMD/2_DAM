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
public class Ejercicio4 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        Document registroEmpleados = db.parse(new File("empleados.xml"));
        
        registroEmpleados.getDocumentElement().normalize();
        
        System.out.println("El elemento raiz es " + registroEmpleados.getDocumentElement().getNodeName());
        
        NodeList empleados = registroEmpleados.getElementsByTagName("empleado");
        
        System.out.println("Se han encontrado " + empleados.getLength() + " empleados");
        
        for (int i = 0; i < empleados.getLength(); i++) {
            Node emp = empleados.item(i);
            if (emp.getNodeType()== Node.ELEMENT_NODE) {
                Element empleado = (Element) emp;
                System.out.println("ID: "+ empleado.getElementsByTagName("id").item(0).getTextContent());
                System.out.println("\tNombre: " + empleado.getElementsByTagName("nombre").item(0).getTextContent());
                System.out.println("\tApellido: " + empleado.getElementsByTagName("apellido").item(0).getTextContent());
                
            }
        }
    }
}
