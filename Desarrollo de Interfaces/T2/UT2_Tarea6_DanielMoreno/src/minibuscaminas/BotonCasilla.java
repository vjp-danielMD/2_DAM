package minibuscaminas;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author daniel
 */

public class BotonCasilla extends JButton {

    private final int fila;
    private final int col;

    public BotonCasilla(int fila, int col) {
        super();
        this.fila = fila;
        this.col = col;
        setOpaque(true);
        setBackground(null);
    }

    public int getFila() {
        return fila;
    }

    public int getCol() {
        return col;
    }

    public void marcarMina() {
        setBackground(Color.RED);
        setEnabled(false);
    }

    public void marcarSeguro() {
        setBackground(Color.GREEN);
        setEnabled(false);
    }
}
