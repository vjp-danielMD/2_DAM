import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidadorClienteTest {

    private final ValidadorCliente val = new ValidadorCliente();

    // PT-09: Nombre vacío → false
    @Test
    void testNombreVacioEsInvalido() {
        assertFalse(val.validarNombre(""), "Nombre vacío debe ser inválido");
    }

    // PT-10: Nombre correcto → true
    @Test
    void testNombreValidoEsAceptado() {
        assertTrue(val.validarNombre("María García"),
            "Nombre válido debe ser aceptado");
    }

    // PT-11: Validación de teléfono (9 dígitos)
    @Test
    void testValidacionTelefono() {
        assertTrue(val.validarTelefono("612345678"),  "Teléfono de 9 dígitos OK");
        assertFalse(val.validarTelefono("123"),        "Teléfono corto → false");
        assertFalse(val.validarTelefono("6123456789"), "Teléfono largo → false");
        assertFalse(val.validarTelefono("61234567A"),  "Con letras → false");
    }
}
