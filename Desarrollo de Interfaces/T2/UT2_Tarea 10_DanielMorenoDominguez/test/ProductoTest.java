import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PT-01 y PT-02 – Pruebas unitarias del modelo Producto.
 *
 * @author danie
 */
class ProductoTest {

    // PT-01: Constructor asigna correctamente los atributos
    @Test
    void testConstructorAsignaAtributosCorrectamente() {
        // Arrange
        String nombre = "Hamburguesa Clásica";
        double precio = 8.50;
        String categoria = "HAMBURGUESA";

        // Act
        Producto p = new Producto(nombre, precio, categoria);

        // Assert
        assertEquals(nombre,    p.getNombre(),             "El nombre debe coincidir");
        assertEquals(precio,    p.getPrecio(),    0.001,   "El precio debe coincidir");
        assertEquals(categoria, p.getCategoria(),           "La categoría debe coincidir");
    }

    // PT-02: Precio negativo lanza excepción
    @Test
    void testPrecioNegativoLanzaExcepcion() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class,
            () -> new Producto("Test", -1.0, "BEBIDA"),
            "Precio negativo debe lanzar IllegalArgumentException");
    }
}
