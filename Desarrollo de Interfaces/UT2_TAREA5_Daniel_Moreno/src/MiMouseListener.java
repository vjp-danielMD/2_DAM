

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author alumno
 */
public class MiMouseListener implements MouseListener {
    NewJFrame padre;
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Perdiste p = new Perdiste(padre, true);
        p.setVisible(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
