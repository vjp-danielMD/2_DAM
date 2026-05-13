/**
 * Validaciones de datos del cliente para el proyecto Burguer.
 *
 * @author danie
 */
public class ValidadorCliente {

    /**
     * Valida el nombre del cliente.
     *
     * @param nombre nombre a validar
     * @return true si el nombre no es nulo ni está vacío/blank
     */
    public boolean validarNombre(String nombre) {
        return nombre != null && !nombre.isBlank();
    }

    /**
     * Valida un número de teléfono (exactamente 9 dígitos).
     *
     * @param telefono cadena a validar
     * @return true si contiene exactamente 9 dígitos numéricos
     */
    public boolean validarTelefono(String telefono) {
        if (telefono == null) {
            return false;
        }
        return telefono.matches("\\d{9}");
    }
}
