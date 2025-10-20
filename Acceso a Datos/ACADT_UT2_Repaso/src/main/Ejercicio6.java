/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.File;
import java.io.FileWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author danie
 */
public class Ejercicio6 {

    public static void main(String[] args) {
        try {
            Document doc = cargarXML("src/esdla.xml");

            agregarCiudad(doc, "Osgiliath", "Gondor");
            agregarCiudad(doc, "Plasencia", "Gitanos");

            eliminarPrimerPersonajeDeCadaLista(doc);

            guardarComoJSON(doc, "src/esdla.json");

            System.out.println("Archivo json generado exitosamente.");
            
            mostrarJSONPorConsola("src/esdla.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 Elimina el primer personaje de una lista (si existe)
    private static void eliminarPrimerPersonaje(List<String> lista) {
        if (lista != null && !lista.isEmpty()) {
            lista.remove(0);
        }
    }

    public static Document cargarXML(String ruta) {
        Document d = null;
        try {
            File file = new File(ruta);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            d = db.parse(file);
            d.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public static void agregarCiudad(Document doc, String nombre, String faccion) {
        Element ciudad = doc.createElement("ciudad");
        Element nombreElem = doc.createElement("nombre");
        nombreElem.setTextContent(nombre);
        ciudad.appendChild(nombreElem);

        Element faccionElem = doc.createElement("faccion");
        faccionElem.setTextContent(faccion);
        ciudad.appendChild(faccionElem);

        NodeList ciudadesList = doc.getElementsByTagName("ciudades");
        if (ciudadesList.getLength() > 0) {
            ciudadesList.item(0).appendChild(ciudad);
        }
    }

    public static void eliminarPrimerPersonajeDeCadaLista(Document doc) {
        String[] listas = {"hobbits", "elfos", "enanos", "hombres", "magos", "villanos"};

        for (String lista : listas) {
            NodeList nodos = doc.getElementsByTagName(lista);
            if (nodos.getLength() > 0) {
                Element listaElement = (Element) nodos.item(0);
                NodeList personajes = listaElement.getElementsByTagName("personaje");
                if (personajes.getLength() > 0) {
                    Node primero = personajes.item(0);
                    listaElement.removeChild(primero);
                }
            }
        }
    }

    public static void guardarComoJSON(Document doc, String rutaSalida) {
        try {
            Map<String, Object> estructura = convertirXMLaEstructura(doc.getDocumentElement());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(estructura);

            try (FileWriter fw = new FileWriter(rutaSalida)) {
                fw.write(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> convertirXMLaEstructura(Element elemento) {
        Map<String, Object> mapa = new LinkedHashMap<>();
        NodeList hijos = elemento.getChildNodes();

        for (int i = 0; i < hijos.getLength(); i++) {
            Node nodo = hijos.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nodo;
                String nombre = elem.getTagName();

                Object valor;
                if (elem.hasChildNodes() && tieneElementosHijos(elem)) {
                    valor = convertirXMLaEstructura(elem);
                } else {
                    valor = elem.getTextContent().trim();
                }
                if (mapa.containsKey(nombre)) {
                    Object existente = mapa.get(nombre);
                    if (existente instanceof List) {
                        ((List<Object>) existente).add(valor);
                    } else {
                        List<Object> nuevaLista = new ArrayList<>();
                        nuevaLista.add(existente);
                        nuevaLista.add(valor);
                        mapa.put(nombre, nuevaLista);
                    }
                } else {
                    mapa.put(nombre, valor);
                }
            }

        }
        return mapa;
    }

    public static boolean tieneElementosHijos(Element elem) {
        NodeList hijos = elem.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            if (hijos.item(i).getNodeType() == Node.ELEMENT_NODE) {
                return true;
            }
        }
        return false;
    }

    public static void mostrarJSONPorConsola(String rutaJSON) {
        try {
            StringBuilder sb = new StringBuilder();
            try (java.util.Scanner sc = new java.util.Scanner(new File(rutaJSON))) {
                while (sc.hasNextLine()) {
                    sb.append(sc.nextLine());
                }
            }

            String jsonStr = sb.toString();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Object jsonObject = gson.fromJson(jsonStr, Object.class);

            String prettyJson = gson.toJson(jsonObject);
            System.out.println("Contenido de " + rutaJSON + ":");
            System.out.println(prettyJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
