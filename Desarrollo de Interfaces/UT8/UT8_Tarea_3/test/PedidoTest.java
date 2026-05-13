import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    // PT-12: Estado inicial es PENDIENTE
    @Test
    void testEstadoInicialEsPendiente() {
        PedidoBurguer pedido = new PedidoBurguer(new Carrito(), new Cliente("Test", "612345678"));
        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado(),
            "El estado inicial debe ser PENDIENTE");
    }

    // PT-13: Cambio de estado a ENTREGADO
    @Test
    void testCambioDeEstadoAEntregado() {
        PedidoBurguer pedido = new PedidoBurguer(new Carrito(), new Cliente("Test", "612345678"));
        pedido.setEstado(EstadoPedido.ENTREGADO);
        assertEquals(EstadoPedido.ENTREGADO, pedido.getEstado(),
            "El estado debe haber cambiado a ENTREGADO");
    }
}
