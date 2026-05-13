/**
 * Servicio de cálculo de descuentos para el proyecto Burguer.
 *
 * @author danie
 */
public class DescuentoService {

    /**
     * Aplica un porcentaje de descuento sobre un precio.
     *
     * @param precio     precio original (debe ser >= 0)
     * @param porcentaje porcentaje de descuento (0-100)
     * @return precio con el descuento aplicado
     * @throws IllegalArgumentException si el porcentaje es negativo
     */
    public double aplicarDescuento(double precio, double porcentaje) {
        if (porcentaje < 0) {
            throw new IllegalArgumentException(
                    "El porcentaje de descuento no puede ser negativo.");
        }
        return precio * (1 - porcentaje / 100.0);
    }
}
