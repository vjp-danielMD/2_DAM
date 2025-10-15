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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Ejercicio2 {
    public static void main(String[] args) {
        
        String rutaEntrada = "./src/contactos.txt";
        String rutaSalida = "./src/contactos.xml";
        
        try {
            // leer contactos desde el archivo.
            List<Contacto> listaContactos = leerContactosFichero(rutaEntrada);
            
            // crear documento xml
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            DOMImplementation implementacion = builder.getDOMImplementation();
            
            Document documento = implementacion.createDocument(null, "contactos", null);
            documento.setXmlVersion("1.0");
            
            for (Contacto c : listaContactos) {
                // nodo <contacto>
                Element contacto = documento.createElement("contacto");
                
                // subnodos <nombre>, <apellidos>, <email>, <telefono>
                Element nombre = documento.createElement("nombre");
                nombre.appendChild(documento.createTextNode(c.getNombre()));
                contacto.appendChild(nombre);
                
                Element apellidos = documento.createElement("apellidos");
                apellidos.appendChild(documento.createTextNode(c.getApellidos()));
                contacto.appendChild(apellidos);
                
                Element correo = documento.createElement("correo");
                correo.appendChild(documento.createTextNode(c.getCorreo()));
                contacto.appendChild(correo);
                
                Element numero = documento.createElement("Numero");
                numero.appendChild(documento.createTextNode(c.getNumero()));
                contacto.appendChild(numero);
                
                //añadimos el nodo contacto al documento
                documento.getDocumentElement().appendChild(contacto);
            }
            
            //GUARDAR XML EN DISCO
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            //dar formato
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            
            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult(new File(rutaSalida));
            
            transformer.transform(source, result);
            System.out.println("XML creado correctamente en: " + rutaSalida);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static List<Contacto> leerContactosFichero(String ruta){
        List<Contacto> contactos = new ArrayList<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;
            
            while ((linea = br.readLine()) != null) {                
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    Contacto c = new Contacto(datos[0], datos[1], datos[2], datos[3]);
                    contactos.add(c);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactos;
    }
}
