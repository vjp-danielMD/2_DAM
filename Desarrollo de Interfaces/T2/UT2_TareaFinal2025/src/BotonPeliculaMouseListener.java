
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author danie
 */
public class BotonPeliculaMouseListener extends MouseAdapter {

    private JButton boton;

    public BotonPeliculaMouseListener(JButton boton) {
        this.boton = boton;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Al pasar el ratón: texto negro, fondo blanco
        boton.setForeground(Color.BLACK);
        boton.setBackground(Color.WHITE);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Al salir: volver a texto blanco, fondo negro
        boton.setForeground(Color.WHITE);
        boton.setBackground(Color.BLACK);
    }
}
