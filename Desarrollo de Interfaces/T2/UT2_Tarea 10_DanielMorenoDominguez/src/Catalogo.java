import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Catálogo de productos del proyecto Burguer.
 *
 * @author danie
 */
public class Catalogo {

    private final List<Producto> productos = new ArrayList<>();

    /**
     * Agrega un producto al catálogo.
     */
    public void agregar(Producto producto) {
        productos.add(producto);
    }

    /**
     * Filtra los productos por categoría.
     *
     * @param categoria categoría a buscar
     * @return lista (nunca null) de productos de esa categoría
     */
    public List<Producto> filtrarPorCategoria(String categoria) {
        return productos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    /**
     * Devuelve todos los productos del catálogo.
     */
    public List<Producto> getTodos() {
        return new ArrayList<>(productos);
    }

    public int size() {
        return productos.size();
    }
}
