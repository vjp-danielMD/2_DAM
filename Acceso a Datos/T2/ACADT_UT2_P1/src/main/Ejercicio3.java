/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author alumno
 */
public class Ejercicio3 {

    public static void main(String[] args) {
        String rutaEntrada = "./src/libros.txt";
        String rutaSalida = "./src/libros.xml";

        try {
            List<Libro> listaLibros = leerLibrosFichero(rutaEntrada);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            DOMImplementation implementacion = builder.getDOMImplementation();

            Document documento = implementacion.createDocument(null, "libros", null);
            documento.setXmlVersion("1.0");

            for (Libro l : listaLibros) {
                Element libro = documento.createElement("libro");

                Element isbn = documento.createElement("isbn");
                isbn.appendChild(documento.createTextNode(l.getIsbn()));
                libro.appendChild(isbn);

                Element titulo = documento.createElement("titulo");
                titulo.appendChild(documento.createTextNode(l.getTitulo()));
                libro.appendChild(titulo);

                Element autor = documento.createElement("autor");
                autor.appendChild(documento.createTextNode(l.getAutor()));
                libro.appendChild(autor);

                Element editorial = documento.createElement("editorial");
                editorial.appendChild(documento.createTextNode(l.getEditorial()));
                libro.appendChild(editorial);

                documento.getDocumentElement().appendChild(libro);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

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

    public static List<Libro> leerLibrosFichero(String ruta) {
        List<Libro> libros = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    Libro c = new Libro(datos[0], datos[1], datos[2], datos[3]);
                    libros.add(c);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libros;
    }
}
