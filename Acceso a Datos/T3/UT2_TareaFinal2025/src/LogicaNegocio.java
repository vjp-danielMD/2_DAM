/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class LogicaNegocio {

    List<Pelicula> listaPeliculas;

    public LogicaNegocio() {
        this.listaPeliculas = cargarDatos();
    }

    public List<Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }

    public void agregarPelicula(Pelicula pelicula) {
        listaPeliculas.add(pelicula);
        anadirAlFichero(pelicula);
    }

    public static void anadirAlFichero(Pelicula pelicula) {
        String nombreArchivo = "peliculas.txt";

        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo, true))) {
            pw.print(pelicula.getTitulo() + ";" + pelicula.getAno() + ";" + pelicula.getDuracion());
            pw.print(System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error al guardar la película: " + e.getMessage());
        }
    }

    public List<Pelicula> cargarDatos() {
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
}
