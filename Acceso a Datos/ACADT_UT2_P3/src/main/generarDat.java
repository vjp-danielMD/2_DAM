package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class generarDat {
    public static void main(String[] args) {
        Contacto[] contactos = new Contacto[] {
            new Contacto("Antonio", "Morales", "antonio@example.com", "600123456"),
            new Contacto("Laura", "Pérez", "laura@example.com", "699654321"),
            new Contacto("Carlos", "García", "carlos@example.com", "688777888"),
            new Contacto("María", "López", "maria.lopez@example.com", "622334455"),
            new Contacto("Javier", "Sánchez", "javier.sanchez@example.com", "655443322"),
            new Contacto("Elena", "Ruiz", "elena.ruiz@example.com", "677889900"),
            new Contacto("Pedro", "Martínez", "pedro.martinez@example.com", "699112233"),
            new Contacto("Lucía", "Ortega", "lucia.ortega@example.com", "611223344"),
            new Contacto("Raúl", "Castro", "raul.castro@example.com", "633445566"),
            new Contacto("Sofía", "Fernández", "sofia.fernandez@example.com", "644556677")
        };

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/contactos.dat"))) {
            for (Contacto c : contactos) {
                oos.writeObject(c);
            }
            System.out.println("Archivo contactos.dat creado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
