import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PT-12 y PT-13 – Pruebas unitarias del modelo de Pedido (PedidoBurguer).
 *
 * Nota: se usa PedidoBurguer para separarlo del Pedido DTO existente en el proyecto.
 *
 * @author danie
 */
class PedidoTest {

    // PT-12: Estado inicial es PENDIENTE
    @Test
    void testEstadoInicialEsPendiente() {
        // Arrange
        Carrito carrito = new Carrito();
        Cliente cliente = new Cliente("Test", "612345678");

        // Act
        PedidoBurguer pedido = new PedidoBurguer(carrito, cliente);

        // Assert
        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado(),
            "El estado inicial debe ser PENDIENTE");
    }

    // PT-13: Cambio de estado a ENTREGADO
    @Test
    void testCambioDeEstadoAEntregado() {
        // Arrange
        PedidoBurguer pedido = new PedidoBurguer(
            new Carrito(), new Cliente("Test", "612345678"));

        // Act
        pedido.setEstado(EstadoPedido.ENTREGADO);

        // Assert
        assertEquals(EstadoPedido.ENTREGADO, pedido.getEstado(),
            "El estado debe haber cambiado a ENTREGADO");
    }
}
