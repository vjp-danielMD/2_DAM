/**
 * Modelo de pedido con estado, carrito y cliente.
 * Se llama PedidoBurguer para no colisionar con la clase Pedido existente.
 *
 * @author danie
 */
public class PedidoBurguer {

    private Carrito carrito;
    private Cliente cliente;
    private EstadoPedido estado;

    /**
     * Crea un nuevo pedido en estado PENDIENTE.
     *
     * @param carrito carrito de la compra
     * @param cliente datos del cliente
     */
    public PedidoBurguer(Carrito carrito, Cliente cliente) {
        this.carrito = carrito;
        this.cliente = cliente;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido de " + cliente + " [" + estado + "]";
    }
}
