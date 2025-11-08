
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author daniel
 */
public class Main {

    public static void main(String[] args) {
        List<Alumno> listaAlumnos = Arrays.asList(
                new Alumno("Daniel", Arrays.asList("matematicas", "lengua", "sociales")),
                new Alumno("Lira", Arrays.asList("fotografia", "literatura", "pintura"))
        );
        String ruta = "src/alumnosDOM.xml";
        escrituraDOM(listaAlumnos, ruta);
        mostrarDOM(ruta);
        mostrarDOM(ruta);

    }

    public static void mostrarSAX(String ruta) {
        List<Alumno> leidos = lecturaSAX(new ArrayList<>(), ruta);
        System.out.println("---LECTURA DESDE XML en SAX---");
        for (Alumno leido : leidos) {
            System.out.println(leido);
        }
    }

    public static List<Alumno> lecturaSAX(List<Alumno> lista, String ruta) {
        try {
            SAXParserFactory saxpf = SAXParserFactory.newInstance();
            SAXParser parser = saxpf.newSAXParser();
            AlumnoHandler handler = new AlumnoHandler();
            parser.parse(new File(ruta), handler);
            lista.addAll(handler.getListaAlumnos());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static void mostrarDOM(String ruta) {
        List<Alumno> leidos = lecturaDOM(new ArrayList<>(), ruta);
        System.out.println("---LECTURA DESDE XML en DOM---");
        for (int i = 0; i < leidos.size(); i++) {
            System.out.println(leidos.get(i));
        }
    }

    public static List<Alumno> lecturaDOM(List<Alumno> lista, String ruta) {
        try {
            File archivo = new File(ruta);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(archivo);

            NodeList nodosAlumno = doc.getElementsByTagName("alumno");
            for (int i = 0; i < nodosAlumno.getLength(); i++) {
                Element alumno = (Element) nodosAlumno.item(i);
                String nombre = alumno.getElementsByTagName("nombre").item(0).getTextContent();

                List<String> asignaturas = new ArrayList<>();
                NodeList nodoAsig = alumno.getElementsByTagName("asignatura");
                for (int j = 0; j < nodoAsig.getLength(); j++) {
                    asignaturas.add(nodoAsig.item(j).getTextContent());
                }

                lista.add(new Alumno(nombre, asignaturas));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static void escrituraDOM(List<Alumno> lista, String ruta) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            Element root = document.createElement("alumnos");
            document.appendChild(root);

            for (int i = 0; i < lista.size(); i++) {
                Alumno alumno = lista.get(i);

                Element alumnoElement = document.createElement("alumno");
                root.appendChild(alumnoElement);

                Element nombre = document.createElement("nombre");
                nombre.appendChild(document.createTextNode(alumno.getNombre()));
                alumnoElement.appendChild(nombre);

                Element asignaturas = document.createElement("asignaturas");
                alumnoElement.appendChild(asignaturas);

                List<String> listaAsignaturas = alumno.getAsignaturas();
                for (String a : listaAsignaturas) {
                    Element asignatura = document.createElement("asignatura");
                    asignatura.appendChild(document.createTextNode(a));
                    asignaturas.appendChild(asignatura);
                }
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(ruta));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
