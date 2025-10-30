
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author alumno
 */
public class Datos {

    public static List<Pelicula> cargarPeliculas() {
        List<Pelicula> lista = new ArrayList<>();
        String nombreArchivo = "peliculas.txt";
        try {
            FileReader reader = new FileReader(nombreArchivo);
            BufferedReader br = new BufferedReader(reader);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    String titulo = partes[0].trim();
                    String ano = partes[1].trim();
                    String duracion = partes[2].trim();
                    lista.add(new Pelicula(titulo, ano, duracion));
                } else {
                    System.err.println("Línea con formato incorrecto: " + linea);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return lista;
    }

    public static void agregarPelicula(Pelicula pelicula) {
        String nombreArchivo = "peliculas.txt";

        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo, true))) {
            pw.print(pelicula.getTitulo() + ";" + pelicula.getAno() + ";" + pelicula.getDuracion());
            pw.print(System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error al guardar la película: " + e.getMessage());
        }
    }

}
