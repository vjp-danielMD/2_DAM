/**
 * CalculadorPrecio
 *
 * Clase de lógica pura extraída de VentanaPrincipal para poder ser
 * testeada con JUnit 5 sin necesidad de levantar la interfaz gráfica.
 *
 * Reglas de negocio del proyecto Burguer:
 *   - Precio base del menú:        8 €
 *   - Ternera / Vegana:           +1 €
 *   - Hamburguesa doble:          +2 €
 *   - Extra de queso:            +0,50 €
 *   - Extra de patatas:           +1 €
 *   - Cada salsa (unidad):       +0,50 €
 *   - Recogida en local:          -20 %
 *   - IVA:                        21 %
 */
public class CalculadorPrecio {

    public static final int PRECIO_BASE = 8;

    public static final double EXTRA_CARNE_PREMIUM = 1.0;   // Ternera / Vegana
    public static final double EXTRA_DOBLE         = 2.0;
    public static final double EXTRA_QUESO         = 0.5;
    public static final double EXTRA_PATATAS       = 1.0;
    public static final double PRECIO_SALSA        = 0.5;   // por unidad
    public static final double DESCUENTO_RECOGIDA  = 0.20;  // 20 %
    public static final double TIPO_IVA            = 0.21;  // 21 %

    /**
     * Calcula el precio base del pedido (sin IVA ni descuento de recogida).
     *
     * @param hamburguesa  "Pollo", "Cerdo", "Ternera" o "Vegana"
     * @param doble        true si se pide hamburguesa doble
     * @param queso        true si se pide extra de queso
     * @param extraPatatas true si se pide extra de patatas
     * @param totalSalsas  número total de unidades de salsa
     * @return precio sin IVA y sin descuento de recogida, o -1 si la
     *         hamburguesa no es válida
     */
    public double calcularPrecioBase(String hamburguesa,
                                     boolean doble,
                                     boolean queso,
                                     boolean extraPatatas,
                                     int totalSalsas) {

        if (hamburguesa == null || hamburguesa.isBlank()) {
            return -1;
        }

        double precio = PRECIO_BASE;

        switch (hamburguesa) {
            case "Pollo", "Cerdo" -> { /* sin recargo */ }
            case "Ternera", "Vegana" -> precio += EXTRA_CARNE_PREMIUM;
            default -> { return -1; }
        }

        if (doble)        precio += EXTRA_DOBLE;
        if (queso)        precio += EXTRA_QUESO;
        if (extraPatatas) precio += EXTRA_PATATAS;

        if (totalSalsas < 0) {
            throw new IllegalArgumentException("El número de salsas no puede ser negativo.");
        }
        precio += totalSalsas * PRECIO_SALSA;

        return precio;
    }

    /**
     * Aplica el descuento del 20 % si el cliente recoge en local.
     *
     * @param precio    precio previo al descuento
     * @param domicilio true = reparto a domicilio (sin descuento);
     *                  false = recogida en local (-20 %)
     * @return precio tras aplicar (o no) el descuento
     */
    public double aplicarDescuento(double precio, boolean domicilio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (!domicilio) {
            precio = precio * (1 - DESCUENTO_RECOGIDA);
        }
        return precio;
    }

    /**
     * Calcula el importe del IVA (21 %) sobre el precio dado.
     *
     * @param precioSinIVA base imponible
     * @return cuota de IVA
     */
    public double calcularIVA(double precioSinIVA) {
        if (precioSinIVA < 0) {
            throw new IllegalArgumentException("El precio sin IVA no puede ser negativo.");
        }
        return precioSinIVA * TIPO_IVA;
    }

    /**
     * Calcula el PVP (precio de venta al público) = precioSinIVA + IVA.
     *
     * @param precioSinIVA base imponible
     * @return PVP total
     */
    public double calcularPVP(double precioSinIVA) {
        return precioSinIVA + calcularIVA(precioSinIVA);
    }

    /**
     * Precio total de todas las salsas pedidas.
     *
     * @param ketchup  unidades de ketchup
     * @param barbacoa unidades de barbacoa
     * @param mostaza  unidades de mostaza
     * @param thai     unidades de salsa thai
     * @return importe total de salsas
     */
    public double calcularPrecioSalsas(int ketchup, int barbacoa, int mostaza, int thai) {
        int total = ketchup + barbacoa + mostaza + thai;
        if (total < 0) {
            throw new IllegalArgumentException("Las cantidades de salsa no pueden ser negativas.");
        }
        return total * PRECIO_SALSA;
    }
}
