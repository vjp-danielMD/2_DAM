
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
public class LogicaNegocio {

    private final List<Articulo> listArticulos;

    public LogicaNegocio() {
        Articulo a = new Articulo(1, "Patata", "Turbeculo", 23);
        Articulo a1 = new Articulo(2, "Plátano", "Fruta amarilla", 12);
        Articulo a2 = new Articulo(3, "Manzana", "Fruta roja", 34);
        Articulo a3 = new Articulo(4, "Portátil HP Pavilion", "Ordenador portátil", 30);
        this.listArticulos = new ArrayList<>();
        listArticulos.add(a);
        listArticulos.add(a1);
        listArticulos.add(a2);
        listArticulos.add(a3);

    }

    public List<Articulo> getListArticulos() {
        return listArticulos;
    }

    public void anadirArticulo(Articulo a) {
        this.listArticulos.add(a);
    }

    public void eliminarPelicula(int index) {
        if (index >= 0 && index < listArticulos.size()) {
            listArticulos.remove(index);
        }
    }

}
