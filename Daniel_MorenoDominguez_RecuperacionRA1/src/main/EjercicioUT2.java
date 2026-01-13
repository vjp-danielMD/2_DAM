package main;

import com.google.gson.Gson;
import java.util.*;
import org.w3c.dom.*;
import java.io.*;
import java.lang.reflect.Type;
import com.thoughtworks.xstream.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author daniel
 */
public class EjercicioUT2 {

    static List<Simpsons> datosJson;
    static List<Simpsons> datosXML;

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("""
                               Elija una opcion
                               1.- cargar json en memoria; mostrar json
                               2.- deserializar y cargar xml mediante xstream y mostrarlo en consola
                               3.- crear un nuevo fichero json con jsonwriter con los datos del creador
                               """);
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    datosJson = deserializarJSON();
                    if (datosJson == null) {
                        System.out.println("no se han guardado los datos");
                    } else {
                        System.out.println(datosJson);
                    }
                    break;
                case 2:
                    datosXML = deserializarXML();
                    leerXML(datosXML);
                    break;
                case 3:
                    datosXML = deserializarXML();
                    crearNuevoXML(datosXML);
                    break;
                case 0:
                    System.out.println("Saliendo");
                default:
                    System.out.println("Opcion incorrecta.");
            }
        } while (opcion != 0);
    }

    public static void crearNuevoXML(List<Simpsons> datos) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("simpsons");
            doc.appendChild(root);

            for (Simpsons d : datos) {
                Element creador = doc.createElement("creador");
                creador.setTextContent(d.getCreador());
                root.appendChild(creador);

                Element creacion = doc.createElement("creacion");
                creacion.setTextContent(d.getCreacion());
                root.appendChild(creacion);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(new File("nuevoXml.xml")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void leerXML(List<Simpsons> datos) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(new File("simpsons.xml"));
            doc.getDocumentElement().normalize();
            NodeList lista = doc.getElementsByTagName("simpsons");
            for (int i = 0; i < lista.getLength(); i++) {
                Node nodo = lista.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nodo;
                    String creador = elem.getElementsByTagName("creador").item(0).getTextContent();
                    String creacion = elem.getElementsByTagName("creacion").item(0).getTextContent();
                    
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Simpsons> deserializarXML() {
        try {
            XStream xStream = new XStream();
            xStream.setupDefaultSecurity(xStream);
            xStream.allowTypesByWildcard(new String[]{"main.**"});
            xStream.alias("simpsons", Simpsons.class);
            xStream.alias("creador", String.class);
            xStream.alias("creacion", String.class);
            xStream.alias("personajes principales", PersonajesPrincipales.class);
            xStream.alias("personaje", String.class);
            xStream.alias("comercializacion", Comercializacion.class);
            xStream.alias("videjuegos", Videojuegos.class);
            xStream.alias("videojuego", String.class);
            xStream.alias("lugares", Lugares.class);
            xStream.alias("lugar", Lugar.class);
            xStream.alias("nombre", String.class);
            xStream.alias("ciudad", String.class);
            xStream.alias("dueño", String.class);
            FileInputStream fis = new FileInputStream("simpsons.xml");
            List<Simpsons> lista = (List<Simpsons>) xStream.fromXML(fis);
            fis.close();
            System.out.println("Datos cargados!");
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Simpsons> deserializarJSON() {
        try {
            FileReader fr = new FileReader("simpsons.json");
            Gson gson = new Gson();
            Type tipo = new TypeToken<HashMap<String, ArrayList<Simpsons>>>() {
            }.getType();
            HashMap<String, ArrayList<Simpsons>> mapa = gson.fromJson(fr, tipo);
            fr.close();
            ArrayList<Simpsons> simpsons = mapa.get("simpsons");
            Gson pretty = new GsonBuilder().setPrettyPrinting().create();
            System.out.println("Json deserializado.");
            for (Simpsons simpson : simpsons) {
                System.out.println(pretty.toJson(simpson));
            }
            return simpsons;
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
