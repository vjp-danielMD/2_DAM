import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        carrito.anadir(burger, 1);
        assertEquals(1, carrito.size(), "El carrito debe tener 1 elemento");
    }

    // PT-04: Eliminar el producto deja el carrito vacío
    @Test
    void testEliminarProductoDejaCarritoVacio() {
        carrito.anadir(burger, 1);
        carrito.eliminar(burger);
        assertEquals(0, carrito.size(), "El carrito debe estar vacío");
    }

    // PT-05: Total sin descuento es correcto
    @Test
    void testTotalSinDescuentoCorrecto() {
        carrito.anadir(burger, 2);                    // 2 × 10.00 = 20.00
        Producto bebida = new Producto("Refresco", 2.50, "BEBIDA");
        carrito.anadir(bebida, 1);                    // 1 × 2.50  =  2.50
        assertEquals(22.50, carrito.getTotalSinDescuento(), 0.001,
            "El total debe ser 22.50");
    }
}
