/**
 * Modelo Producto para el proyecto Burguer.
 * Representa un artículo del catálogo (hamburguesa, bebida, acompañante…).
 *
 * @author danie
 */
public class Producto {

    private String nombre;
    private double precio;
    private String categoria;

    /**
     * Crea un nuevo producto.
     *
     * @param nombre    nombre del producto
     * @param precio    precio unitario (debe ser >= 0)
     * @param categoria categoría (HAMBURGUESA, BEBIDA, ACOMPAÑANTE…)
     * @throws IllegalArgumentException si el precio es negativo
     */
    public Producto(String nombre, double precio, String categoria) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return nombre + " (" + categoria + ") - " + precio + "€";
    }
}
