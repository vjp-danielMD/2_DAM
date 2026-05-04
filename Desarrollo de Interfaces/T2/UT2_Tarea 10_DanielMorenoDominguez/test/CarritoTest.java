import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PT-03, PT-04 y PT-05 – Pruebas unitarias del Carrito de la compra.
 *
 * @author danie
 */
class CarritoTest {

    private Carrito carrito;
    private Producto burger;

    @BeforeEach
    void setUp() {
        carrito = new Carrito();
        burger  = new Producto("Hamburguesa Doble", 10.00, "HAMBURGUESA");
    }

    // PT-03: Añadir un producto incrementa el tamaño
    @Test
    void testAnadirProductoIncrementaTamano() {
        // Arrange – carrito vacío + burger creados en setUp()

        // Act
        carrito.anadir(burger, 1);

        // Assert
        assertEquals(1, carrito.size(), "El carrito debe tener 1 elemento");
    }

    // PT-04: Eliminar el producto deja el carrito vacío
    @Test
    void testEliminarProductoDejaCarritoVacio() {
        // Arrange
        carrito.anadir(burger, 1);

        // Act
        carrito.eliminar(burger);

        // Assert
        assertEquals(0, carrito.size(), "El carrito debe estar vacío");
    }

    // PT-05: Total sin descuento es correcto
    @Test
    void testTotalSinDescuentoCorrecto() {
        // Arrange
        Producto bebida = new Producto("Refresco", 2.50, "BEBIDA");

        // Act
        carrito.anadir(burger, 2);   // 2 × 10.00 = 20.00
        carrito.anadir(bebida, 1);   // 1 ×  2.50 =  2.50

        // Assert
        assertEquals(22.50, carrito.getTotalSinDescuento(), 0.001,
            "El total debe ser 22.50");
    }
}
