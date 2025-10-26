/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author danie
 */
public class DOMSerializacion {

    public static void main(String[] args) {
        try {
            List<Persona> personas = Arrays.asList(
                new Persona("Ana", 25),
                new Persona ("Luis", 30));
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            
            Element root = doc.createElement("personas");
            doc.appendChild(root);
            
            for (Persona p : personas) {
                Element personaElement = doc.createElement("persona");
                
                Element nombre = doc.createElement("nombre");
                nombre.appendChild(doc.createTextNode(p.getNombre()));
                personaElement.appendChild(nombre);
                
                Element edad = doc.createElement("edad");
                edad.appendChild(doc.createTextNode(String.valueOf(p.getEdad())));
                personaElement.appendChild(edad);
                
                root.appendChild(personaElement);
            }
            
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(new File ("personas_dom.xml")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
