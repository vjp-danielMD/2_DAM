
import java.util.ArrayList;
import java.util.List;

// Clase encargada de registrar y autenticar usuarios
public class SistemaDeAutenticacion {

    private List<Usuario> usuariosRegistrados = new ArrayList<>();

    public boolean registrarUsuario(String nombre, String correo, String contrasena) {
        Usuario nuevo = new Usuario(nombre, correo, contrasena);
        usuariosRegistrados.add(nuevo);
        return true;
    }

    public boolean autenticar(String correo, String contrasena) {
        for (Usuario u : usuariosRegistrados) {
            if (u.getCorreo().equals(correo) && u.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }
}
