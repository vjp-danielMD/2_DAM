import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CatalogoTest {

    private Catalogo catalogo;

    @BeforeEach
    void setUp() {
        catalogo = new Catalogo();
        catalogo.agregar(new Producto("Hamburguesa Clásica", 8.5,  "HAMBURGUESA"));
        catalogo.agregar(new Producto("Hamburguesa BBQ",     9.5,  "HAMBURGUESA"));
        catalogo.agregar(new Producto("Refresco Cola",       2.0,  "BEBIDA"));
    }

    // PT-14: Filtrar por categoría BEBIDA devuelve solo bebidas
    @Test
    void testFiltrarPorCategoriaBebida() {
        List<Producto> bebidas = catalogo.filtrarPorCategoria("BEBIDA");
        assertFalse(bebidas.isEmpty(), "Debe haber al menos una bebida");
        bebidas.forEach(p ->
            assertEquals("BEBIDA", p.getCategoria(),
                "Todos los resultados deben ser de categoría BEBIDA"));
    }

    // PT-15: Categoría inexistente devuelve lista vacía, no null
    @Test
    void testCategoriaInexistenteDevuelveListaVacia() {
        List<Producto> resultado = catalogo.filtrarPorCategoria("POSTRE");
        assertNotNull(resultado, "El resultado no debe ser null");
        assertTrue(resultado.isEmpty(), "La lista debe estar vacía");
    }
}
