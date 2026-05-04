import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PT-06, PT-07 y PT-08 – Pruebas unitarias del servicio de descuentos.
 *
 * @author danie
 */
class DescuentoTest {

    private final DescuentoService svc = new DescuentoService();

    // PT-06: Descuento del 10 % correcto
    @Test
    void testDescuentoDiezPorCiento() {
        // Arrange
        double precio = 100.0;

        // Act
        double resultado = svc.aplicarDescuento(precio, 10);

        // Assert
        assertEquals(90.0, resultado, 0.001, "100 con 10% de descuento = 90");
    }

    // PT-07: Descuento del 100 % deja precio a 0
    @Test
    void testDescuentoCienPorCienDejaCero() {
        // Arrange & Act & Assert
        assertEquals(0.0, svc.aplicarDescuento(50.0, 100), 0.001,
            "Descuento del 100% debe dejar el precio a 0");
    }

    // PT-08: Descuento negativo lanza excepción
    @Test
    void testDescuentoNegativoLanzaExcepcion() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class,
            () -> svc.aplicarDescuento(50.0, -5),
            "Descuento negativo debe lanzar IllegalArgumentException");
    }
}
