import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DescuentoTest {

    private final DescuentoService svc = new DescuentoService();

    // PT-06: Descuento del 10 % correcto
    @Test
    void testDescuentoDiezPorCiento() {
        double resultado = svc.aplicarDescuento(100.0, 10);
        assertEquals(90.0, resultado, 0.001, "100 con 10% de descuento = 90");
    }

    // PT-07: Descuento del 100 % deja precio a 0
    @Test
    void testDescuentoCienPorCienDejaCero() {
        assertEquals(0.0, svc.aplicarDescuento(50.0, 100), 0.001);
    }

    // PT-08: Descuento negativo lanza excepción
    @Test
    void testDescuentoNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
            () -> svc.aplicarDescuento(50.0, -5));
    }
}
