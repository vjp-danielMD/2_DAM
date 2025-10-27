package main;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author daniel
 */
public class Binario {

    public static void main(String[] args) {
        File fichero = new File("./src/contactos.dat"); // archivo binario
        ListaContactos listaContactos = crearXML(fichero);
        LeerXML();
    }

    public static ListaContactos crearXML(File fichero) {
        ListaContactos listaContactos = new ListaContactos();

        // Lectura de objetos binarios
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            while (true) {
                try {
                    Contacto c = (Contacto) ois.readObject();
                    listaContactos.add(c);
                } catch (IOException e) {
                    // EOFException indica fin de fichero
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    break;
                }
            }
            System.out.println("Fichero binario leído correctamente.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo " + fichero.getName() + " no encontrado.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Creación del XML
        try {
            XStream xstream = new XStream(new DomDriver("UTF-8"));
            xstream.alias("contacto", Contacto.class);
            xstream.alias("contactos", ListaContactos.class);
            xstream.addImplicitCollection(ListaContactos.class, "lista");

            xstream.toXML(listaContactos, new FileOutputStream("contactos.xml"));
            System.out.println("Creado fichero XML....");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return listaContactos;
    }

    public static void LeerXML() {
        XStream xstream = new XStream(new DomDriver("UTF-8"));

        // Permitir deserialización
        xstream.addPermission(AnyTypePermission.ANY);

        // Alias
        xstream.alias("contacto", Contacto.class);
        xstream.alias("contactos", ListaContactos.class);
        xstream.addImplicitCollection(ListaContactos.class, "lista");

        try {
            ListaContactos contactos = (ListaContactos) xstream.fromXML(new FileInputStream("contactos.xml"));

            if (contactos == null) {
                System.out.println("Error: No se pudo leer o deserializar el archivo XML.");
                return;
            }

            System.out.println("Número de Contactos: " + contactos.getListaContactos().size());

            List<Contacto> listaContactos = contactos.getListaContactos();
            ListIterator<Contacto> iterador = listaContactos.listIterator();

            while (iterador.hasNext()) {
                Contacto c = iterador.next();
                System.out.println(
                        "Nombre: " + c.getNombre()
                        + ", apellidos: " + c.getApellidos()
                        + ", email: " + c.getCorreo()
                        + ", teléfono: " + c.getNumero()
                );
            }

            System.out.println("Fin de listado.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo contactos.xml no encontrado.");
            e.printStackTrace();
        }
    }
}
