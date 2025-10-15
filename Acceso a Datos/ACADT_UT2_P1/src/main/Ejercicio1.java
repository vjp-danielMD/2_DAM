/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.parsers.*;
import java.io.*;

/**
 *
 * @author alumno
 */
public class Ejercicio1 {
    public static void main(String[] args) throws ParserConfigurationException {
        /**
         * Creamos un DocumentBuilder haciendo uso de la factoria
         * DocumentBuilderFactory
         */
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        
        /**
         *  Creamos un documento vacio
         * Nombre --> RegistroEmpleados
         * Nodo Raiz --> Empleados
         */
        DOMImplementation implementacion = builder.getDOMImplementation();
        Document registroEmpleados = implementacion.createDocument(null, "empleados", null);
        
        // asignar version XML
        registroEmpleados.setXmlVersion("1.0");
        
        //Creamos el nodo empleado
        Element empleado = registroEmpleados.createElement("empleado");
        // Lo añadimos como hijo de empleados
        registroEmpleados.getDocumentElement().appendChild(empleado);
        // Creamos el nodo ID
        Element id = registroEmpleados.createElement("id");
        //creamos el nodo texto con el valor de la id
        Text texto = registroEmpleados.createTextNode("01");
        // añadimos el valor al nodo id
        id.appendChild(texto);
        // añadimos el nodo id a empleado
        empleado.appendChild(id);
        
        Element nombre = registroEmpleados.createElement("nombre");
        texto = registroEmpleados.createTextNode("Antonio");
        nombre.appendChild(texto);
        empleado.appendChild(nombre);
        Element apellidos = registroEmpleados.createElement("apellidos");
        texto = registroEmpleados.createTextNode("Moreales");
        apellidos.appendChild(texto);
        empleado.appendChild(apellidos);
    }
            
}
