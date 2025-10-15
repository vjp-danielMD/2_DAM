package main;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.FileInputStream;
import java.util.List;

public class Ejercicio4 {

    public static void main(String[] args) {
        try {
            XStream xstream = new XStream(new DomDriver("UTF-8"));
            xstream.addPermission(AnyTypePermission.ANY);

            // Alias
            xstream.alias("blog", Blog.class);
            xstream.alias("autor", Autor.class);
            xstream.alias("entrada", Entrada.class);

            // Implicit collection para que todas las etiquetas <entrada> se agreguen a la lista
            xstream.addImplicitCollection(Blog.class, "entrada");

            Blog blog = (Blog) xstream.fromXML(new FileInputStream("./src/blog.xml"));

            System.out.println("Autor: " + blog.getAutor().getNombre());
            List<Entrada> entradas = blog.getEntrada();
            for (Entrada e : entradas) {
                System.out.println("\nTítulo: " + e.getTitulo());
                System.out.println("Descripción: " + e.getDescripcion());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
