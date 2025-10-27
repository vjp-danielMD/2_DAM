/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author alumno
 */
public class EmpleadosReader extends DefaultHandler {

    private final List<Empleado> empleados = new ArrayList<>();
    private final StringBuilder contenido = new StringBuilder();
    private String nombre, apellido;
    private double sueldo;

    public EmpleadosReader() {
        super();
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        System.out.println("Inicio del documento.");
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        System.out.println("Fin del documento.");

        Collections.sort(empleados);

        for (Empleado e : empleados) {
            System.out.println(e);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        contenido.setLength(0);

        if (qName.equalsIgnoreCase("empleado")) {
            nombre = "";
            apellido = "";
            sueldo = 0;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName.toLowerCase()) {
            case "nombre":
                nombre = contenido.toString();
                break;
            case "apellido":
                apellido = contenido.toString();
                break;
            case "sueldo":
                try {
                    sueldo = Double.parseDouble(contenido.toString());
                } catch (NumberFormatException e) {
                    sueldo = 0;
                }
                break;
            case "empleado":
                empleados.add(new Empleado(nombre, apellido, sueldo));
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        contenido.append(ch, start, length);
    }

}
