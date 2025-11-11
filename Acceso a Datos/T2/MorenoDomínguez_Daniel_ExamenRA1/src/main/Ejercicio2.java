/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

/**
 *
 * @author alumno
 */
public class Ejercicio2 {

    public static void main(String[] args) {
        String rutaEntrada = "src/juegodetronos.xml";
        String rutaSalida = "src/examen.xml";
        File file = new File(rutaSalida);
        List<JuegoDeTronos> lista = cargarFicheroEnMemoria(rutaSalida);
        escribirNuevoXml(lista, file);
    }

//    public static List<JuegoDeTronos> cargarObjetosManualmente() {
//        List<String> libros = "juego de tronos", "choque "; 
//        List<JuegoDeTronos> lista = new ArrayList<>(Arrays.asList(
//                new JuegoDeTronos("George R. R. Martin",
//                        new Casas(Arrays.asList(
//                                new Casa("Targaryen", "Dragón"),
//                                new Casa("Stark", "Lobo")
//                        )), new Ciudades(Arrays.asList(
//                                "Antigua", "Astapor", "Braavos", "Desembarco del Rey"
//                        )), new Historias(Arrays.asList(
//                                new Series(Arrays.asList(
//                                        "Juego de tronos", "La casa del dragón"
//                                ))
//                        ), new Libros(libros)))
//        ));
//        return lista;
//    }

    public static File escribirNuevoXml(List<JuegoDeTronos> lista, File fileSalida) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            DOMImplementation domi = db.getDOMImplementation();
            Document document = domi.createDocument(null, "juegodetronos", null);
            document.setXmlVersion("1.0");

            for (int i = 0; i < lista.size(); i++) {
                Element autorElement = document.createElement("autor");
                document.createTextNode(lista.get(i).getAutor());
                document.appendChild(autorElement);

                Element casasElement = document.createElement("casas");
                document.appendChild(casasElement);

                Element casaElement = document.getElementById("casa");
                casasElement.appendChild(casaElement);

                Element nombreCasaElement = document.createElement("nombre");
                document.createTextNode(lista.get(i).getCasas().getCasas().get(i).getNombre());
                casasElement.appendChild(nombreCasaElement);

                Element simboloCasaElement = document.createElement("simbolo");
                document.createTextNode(lista.get(i).getCasas().getCasas().get(i).getSimbolo());
                casasElement.appendChild(simboloCasaElement);

                Element ciudadesElement = document.createElement("ciudades");
                document.appendChild(ciudadesElement);

                Element ciudadElement = document.createElement("ciudad");
                document.createTextNode(lista.get(i).getCiudades().getCiudades().get(i));
                ciudadesElement.appendChild(ciudadElement);

                Element historiasElement = document.createElement("historias");
                document.appendChild(historiasElement);

                Element seriesElement = document.createElement("series");
                historiasElement.appendChild(seriesElement);

                Element serieElement = document.createElement("serie");
                document.createTextNode(lista.get(i).getHistorias().getSeries().get(i).getSeries().get(i));
                seriesElement.appendChild(serieElement);

                Element librosElement = document.createElement("libros");
                historiasElement.appendChild(librosElement);

                Element libroElement = document.createElement("libro");
                document.createTextNode(lista.get(i).getHistorias().getLibros().get(i).getLibros().get(i));
                librosElement.appendChild(libroElement);
            }

            Source source = new DOMSource(document);
            Result result = new StreamResult(fileSalida);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileSalida;
    }

    public static List<JuegoDeTronos> cargarFicheroEnMemoria(String ruta) {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("autor", String.class);
        xStream.alias("casas", Casas.class);
        xStream.alias("casa", Casa.class);
        xStream.alias("ciudaes", Ciudades.class);
        xStream.alias("cuidad", String.class);
        xStream.alias("historias", Historias.class);
        xStream.alias("series", Series.class);
        xStream.alias("serie", List.class);
        xStream.alias("libros", Libros.class);
        xStream.alias("libro", List.class);
        List<JuegoDeTronos> lista = null;

        try {
            List<JuegoDeTronos> jdt = (List<JuegoDeTronos>) xStream.fromXML(new FileInputStream(new File(ruta)));
            lista = new ArrayList<>();
            for (int i = 0; i < jdt.size(); i++) {
                ListIterator<JuegoDeTronos> iterator = jdt.listIterator();
                while (iterator.hasNext()) {
                    JuegoDeTronos next = iterator.next();
                    lista.add(next);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
