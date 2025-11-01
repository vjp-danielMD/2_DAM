
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author danie
 */
public class MostrarDetallesPelicula extends JDialog {

    public MostrarDetallesPelicula(JDialog parent, boolean modal, Pelicula pelicula) {
        super(parent, modal);

        setTitle("Detalles de la Película");
        setLayout(new GridLayout(3, 2, 10, 10));
        ((javax.swing.JPanel) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));

        // Labels para mostrar los datos
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel lblTituloValor = new JLabel(pelicula.getTitulo());

        JLabel lblAno = new JLabel("Año:");
        lblAno.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel lblAnoValor = new JLabel(pelicula.getAno());

        JLabel lblDuracion = new JLabel("Duración:");
        lblDuracion.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel lblDuracionValor = new JLabel(pelicula.getDuracion() + " minutos");

        // Añadir componentes
        add(lblTitulo);
        add(lblTituloValor);
        add(lblAno);
        add(lblAnoValor);
        add(lblDuracion);
        add(lblDuracionValor);

        // Configuración del diálogo
        setSize(400, 200);
        setLocationRelativeTo(parent);
        setResizable(false);
    }
}
