import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Gestión del carrito de la compra del proyecto Burguer.
 *
 * @author danie
 */
public class Carrito {

    /** Mapa producto → cantidad */
    private final Map<Producto, Integer> items = new LinkedHashMap<>();

    /**
     * Añade un producto al carrito con la cantidad indicada.
     * Si el producto ya existe, se acumula la cantidad.
     */
    public void anadir(Producto producto, int cantidad) {
        items.merge(producto, cantidad, Integer::sum);
    }

    /**
     * Elimina un producto del carrito por completo.
     */
    public void eliminar(Producto producto) {
        items.remove(producto);
    }

    /**
     * Devuelve el número de líneas (productos distintos) en el carrito.
     */
    public int size() {
        return items.size();
    }

    /**
     * Calcula el total sin aplicar ningún descuento.
     *
     * @return suma de (precio × cantidad) de cada línea
     */
    public double getTotalSinDescuento() {
        double total = 0;
        for (Map.Entry<Producto, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrecio() * entry.getValue();
        }
        return total;
    }

    /**
     * Indica si el carrito está vacío.
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
