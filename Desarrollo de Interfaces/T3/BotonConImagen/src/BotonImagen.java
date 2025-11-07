
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author alumno
 */
public class BotonImagen extends JButton implements Serializable {

    private String ruta;

    public BotonImagen() {
        ruta = "/img.png";
        ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
        // Redimensionamos la imagen
        ImageIcon icon = new ImageIcon(icono.getImage().getScaledInstance(50, 50, 100));
        this.setIcon(icon);
        this.setIconTextGap(2);
        // Alineamos
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        this.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        this.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
    }

}
