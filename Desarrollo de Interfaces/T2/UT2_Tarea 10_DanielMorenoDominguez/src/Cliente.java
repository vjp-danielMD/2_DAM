/**
 * Modelo Cliente para el proyecto Burguer.
 *
 * @author danie
 */
public class Cliente {

    private String nombre;
    private String telefono;

    /**
     * Crea un nuevo cliente.
     *
     * @param nombre   nombre del cliente
     * @param telefono teléfono de contacto
     */
    public Cliente(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return nombre + " (" + telefono + ")";
    }
}
