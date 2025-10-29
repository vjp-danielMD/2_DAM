package main;

import com.google.gson.*;
import com.google.gson.reflect.*;
import com.google.gson.stream.*;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.security.*;
import java.io.*;
import java.lang.reflect.*;
import java.nio.charset.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;


/**
 *
 * @author danie
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File f = new File("personas.dat");
        File sax = new File("personas_SAX.xml");
        List<Persona> personas = cargarDatos(f);
        menu(sc, personas, f);
    }

    public static void menu(Scanner sc, List<Persona> l, File f) {
        int opcion = 0;

        while (opcion != 7) {
            System.out.println("Eliga una opcion: ");
            System.out.println("""
                           ===== XML / Binario =====
                           1. Escribir fichero
                           2. Leer fichero
                           3. Serializarlo en DOM
                           4. Serializarlo en SAX
                           5. Deserializarlo en XStream
                           6. Menu JSON
                           7. Salir
                           """);
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 ->
                    escribirFicheroBinario(l, sc, f);
                case 2 -> {
                    l.clear();
                    l.addAll(leerBinario(f));
                }
                case 3 -> {
                    File xmlDOM = new File("personas_DOM.xml");
                    convertirDOM(l, xmlDOM);
                }
                case 4 -> {
                    File xmlSAX = new File("personas_SAX.xml");
                    convertirSAX(l, xmlSAX);
                }
                case 5 -> {
                    System.out.println("¿Desea leer desde DOM o SAX? (1=DOM, 2=SAX): ");
                    int tipo = sc.nextInt();
                    File xmlFile;
                    if (tipo == 1) {
                        xmlFile = new File("personas_DOM.xml");
                    } else {
                        xmlFile = new File("personas_SAX.xml");
                    }
                    deserializarXStream(xmlFile);
                }
                case 6 ->
                    menuJSON(sc, l);

                default ->
                    throw new AssertionError();
            }
        }
    }

    public static void menuJSON(Scanner sc, List<Persona> l) {
        File jsonFile = new File("personas.json");
        int jsonOpcion = 0;

        while (jsonOpcion != 5) {
            System.out.println("""
            ===== MENU JSON =====
            1. Escribir fichero JSON
            2. Leer fichero JSON
            3. Serializar lista a consola (JSON)
            4. Deserializar JSON de consola
            5. Salir JSON
            """);
            jsonOpcion = sc.nextInt();
            sc.nextLine(); 

            switch (jsonOpcion) {
                case 1 ->
                    escribirFicheroJSON(l, jsonFile);
                case 2 ->
                    leerFicheroJson(jsonFile);
                case 3 ->
                    serializarPersonas(l);
                case 4 -> {
                    System.out.print("Introduce JSON: ");
                    String jsonInput = sc.nextLine();
                    deserializarPersonas(jsonInput);
                }
                case 5 ->
                    System.out.println("Saliendo de JSON...");
                default ->
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void serializarPersona(Persona p) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(p);
        System.out.println(json);
    }

    public static void serializarPersonas(List<Persona> lista) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(lista);
        System.out.println(json);
    }

    public static void deserializarPersona(String json) {
        Gson gson = new Gson();
        Persona p = gson.fromJson(json, Persona.class);
        System.out.println(p);
    }

    public static void deserializarPersonas(String json) {
        Gson gson = new Gson();
        Type tipoLista = new TypeToken<List<Persona>>() {
        }.getType();
        List<Persona> lista = gson.fromJson(json, tipoLista);
        for (Persona persona : lista) {
            System.out.println(persona);
        }
    }

    public static void leerFicheroJson(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            JsonReader reader = new JsonReader(new InputStreamReader(fis, StandardCharsets.UTF_8));

            Gson gson = new Gson();
            reader.beginArray();
            while (reader.hasNext()) {
                Persona p = gson.fromJson(reader, Persona.class);
                System.out.println(p);
            }
            reader.endArray();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void escribirFicheroJSON(List<Persona> lista, File jsonFile) {
        try (JsonWriter writer = new JsonWriter(new FileWriter(jsonFile))) {
            writer.setIndent("  "); // para pretty printing

            writer.beginArray(); // inicio array
            Iterator<Persona> it = lista.iterator();
            while (it.hasNext()) {
                Persona p = it.next();
                writer.beginObject();
                writer.name("nombre").value(p.getNombre());
                writer.name("edad").value(p.getEdad());
                writer.name("ciudad").value(p.getCiudad());
                writer.endObject();
            }
            writer.endArray(); // fin array

        } catch (Exception e) {
            System.err.println("Error escribiendo JSON: " + e.getMessage());
        }
    }

    public static void deserializarXStream(File file) {
        try {
            XStream xStream = new XStream();
            xStream.addPermission(AnyTypePermission.ANY);

            //alias para nodo raíz y para la clase Persona
            xStream.alias("personas", List.class);
            xStream.alias("persona", Persona.class);

            // Leer XML y convertir a lista
            FileInputStream fis = new FileInputStream(file);
            List<Persona> lista = (List<Persona>) xStream.fromXML(fis);

            for (Persona persona : lista) {
                System.out.println(persona);
            }
            fis.close();
        } catch (Exception e) {
            System.err.println("Error al deserializar con XStream: " + e.getMessage());
        }
    }

    public static void convertirSAX(List<Persona> lista, File xmlSAX) {

        try {
            //crear el factory y el handler
            SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
            TransformerHandler handler = factory.newTransformerHandler();

            //configurar salida
            handler.getTransformer().setOutputProperty(OutputKeys.INDENT, "yes");
            handler.getTransformer().setOutputProperty(OutputKeys.METHOD, "xml");
            handler.getTransformer().setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            handler.setResult(new StreamResult(xmlSAX));

            // comenzar el documento
            handler.startDocument();

            AttributesImpl attr = new AttributesImpl();

            handler.startElement("", "", "personas", attr);

            for (Persona p : lista) {
                handler.startElement("", "", "persona", attr);

                //nombre
                handler.startElement("", "", "nombre", attr);
                handler.characters(p.getNombre().toCharArray(), 0, p.getNombre().length());
                handler.endElement("", "", "nombre");

                //edad
                String edadString = String.valueOf(p.getEdad());
                handler.startElement("", "", "edad", attr);
                handler.characters(edadString.toCharArray(), 0, edadString.length());
                handler.endElement("", "", "edad");

                //ciudad
                handler.startElement("", "", "ciudad", attr);
                handler.characters(p.getCiudad().toCharArray(), 0, p.getCiudad().length());
                handler.endElement("", "", "ciudad");

                handler.endElement("", "", "persona");
            }
            handler.endElement("", "", "personas");
            handler.endDocument();

            System.out.println("XML generador correctamente con SAX en: " + xmlSAX.getAbsolutePath());
        } catch (TransformerConfigurationException | SAXException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void convertirDOM(List<Persona> lista, File xmlDOM) {
        try {
            // estructura en memoria
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.newDocument();

            // raiz
            Element raiz = d.createElement("personas");
            d.appendChild(raiz);

            // recorrer la lista de personas y crear sus nodos
            for (Persona persona : lista) {
                Element personaElement = d.createElement("persona");

                Element nombre = d.createElement("nombre");
                nombre.setTextContent(persona.getNombre());
                personaElement.appendChild(nombre);

                Element edad = d.createElement("edad");
                edad.setTextContent(String.valueOf(persona.getEdad()));
                personaElement.appendChild(edad);

                Element ciudad = d.createElement("ciudad");
                ciudad.setTextContent(persona.getCiudad());
                personaElement.appendChild(ciudad);

                raiz.appendChild(personaElement);
            }

            // escribir el contenido del dom en un fichero
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            DOMSource source = new DOMSource(d);
            StreamResult result = new StreamResult(xmlDOM);
            transformer.transform(source, result);

            System.out.println("XML generado correctamente en: " + xmlDOM.getAbsolutePath());
        } catch (ParserConfigurationException | TransformerException e) {
            System.err.println("Error al generar el XML con DOM: " + e.getMessage());
        }
    }

    public static Persona escribirPersona(Scanner sc) {
        System.out.println("AGREGAR PERSONA (presiona enter)");
        sc.nextLine();
        String nombre = pedirNombre(sc);
        int edad = pedirEdad(sc);
        sc.nextLine();
        String ciudad = pedirCiudad(sc);
        return new Persona(nombre, edad, ciudad);
    }

    public static String pedirNombre(Scanner sc) {
        System.out.print("Introduzca el nombre de la persona: ");
        return sc.nextLine();
    }

    public static String pedirCiudad(Scanner sc) {
        System.out.print("Introduzca el nombre de la ciudad: ");
        return sc.nextLine();
    }

    public static int pedirEdad(Scanner sc) {
        System.out.print("Introduzca la edad: ");
        return sc.nextInt();
    }

    public static void escribirFicheroBinario(List<Persona> lista, Scanner sc, File f) {
        boolean continuar = true;

        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos;

            if (f.length() == 0) {
                oos = new ObjectOutputStream(fos);
            } else {
                oos = new MyObjectOutputStream(fos);
            }

            while (continuar) {
                Persona p = escribirPersona(sc);
                lista.add(p);
                oos.writeObject(p);
                System.out.println("Persona guardada correctamente.\n");

                System.out.print("¿Desea añadir otra persona? (s/n): ");
                String respuesta = sc.next().toLowerCase();
                if (!respuesta.equals("s")) {
                    continuar = false;
                }
            }

            oos.close();
            fos.close();
            System.out.println("\nDatos añadidos correctamente a: " + f.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }

    public static List<Persona> leerBinario(File f) {
        List<Persona> lista = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                try {
                    Persona p = (Persona) ois.readObject();
                    lista.add(p);
                    System.out.println(p);
                } catch (EOFException e) {
                    System.out.println("Fin de fichero.");
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return lista;
    }

    public static List<Persona> cargarDatos(File f) {
        List<Persona> lista = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                try {
                    Persona p = (Persona) ois.readObject();
                    lista.add(p);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return lista;
    }
}
