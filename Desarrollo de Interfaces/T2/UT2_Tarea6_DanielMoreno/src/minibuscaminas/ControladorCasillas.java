package minibuscaminas;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author alumno
 */
public class ControladorCasillas implements MouseListener {

    private final VentanaPrincipal ventana;
    private final ModeloJuego modelo;

    public ControladorCasillas(VentanaPrincipal ventana, ModeloJuego modelo) {
        this.ventana = ventana;
        this.modelo = modelo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!(e.getSource() instanceof BotonCasilla)) {
            return;
        }

        BotonCasilla b = (BotonCasilla) e.getSource();
        int f = b.getFila();
        int c = b.getCol();

        ModeloJuego.ResultadoRevelar res = modelo.revelar(f, c);
        switch (res) {
            case YA_REVELADO:
                return;
            case SEGURO:
                b.marcarSeguro();
                break;
            case MINA_GOLPEADA:
                b.marcarMina();
                break;
            case MINA_FIN:
                b.marcarMina();
                modelo.revelarTodo();
                ventana.revelarTodo();
                ventana.actualizarEtiquetaVidas();
                JOptionPane.showMessageDialog(ventana, "Has perdido. Te has quedado sin vidas.");
                return;
            case TODAS_SEGURAS:
                b.marcarSeguro();
                ventana.revelarTodo();
                ventana.actualizarEtiquetaVidas();
                JOptionPane.showMessageDialog(ventana, "¡Has ganado! Has encontrado todas las casillas seguras.");
                return;
        }

        ventana.actualizarEtiquetaVidas();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
