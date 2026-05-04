import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PT-09, PT-10 y PT-11 – Pruebas unitarias del validador de datos de cliente.
 *
 * @author danie
 */
class ValidadorClienteTest {

    private final ValidadorCliente val = new ValidadorCliente();

    // PT-09: Nombre vacío → false
    @Test
    void testNombreVacioEsInvalido() {
        // Arrange & Act & Assert
        assertFalse(val.validarNombre(""), "Nombre vacío debe ser inválido");
    }

    // PT-10: Nombre correcto → true
    @Test
    void testNombreValidoEsAceptado() {
        // Arrange & Act & Assert
        assertTrue(val.validarNombre("María García"),
            "Nombre válido debe ser aceptado");
    }

    // PT-11: Validación de teléfono (9 dígitos exactos)
    @Test
    void testValidacionTelefono() {
        // Caso válido: exactamente 9 dígitos
        assertTrue(val.validarTelefono("612345678"),
            "Teléfono de 9 dígitos debe ser válido");

        // Caso inválido: menos de 9 dígitos
        assertFalse(val.validarTelefono("123"),
            "Teléfono corto debe ser inválido");

        // Caso inválido: más de 9 dígitos
        assertFalse(val.validarTelefono("6123456789"),
            "Teléfono largo debe ser inválido");

        // Caso inválido: contiene letras
        assertFalse(val.validarTelefono("61234567A"),
            "Teléfono con letras debe ser inválido");
    }
}
