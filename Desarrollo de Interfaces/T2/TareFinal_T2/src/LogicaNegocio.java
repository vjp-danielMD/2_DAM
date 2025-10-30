/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.List;

/**
 *
 * @author alumno
 */
public class LogicaNegocio {

    private List<Pelicula> listaPeliculas;

    public LogicaNegocio() {
        this.listaPeliculas = Datos.cargarPeliculas();
    }

    public List<Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }

  
    public void agregarPelicula(Pelicula pelicula) {
        listaPeliculas.add(pelicula);
        Datos.agregarPelicula(pelicula);
    }
}
