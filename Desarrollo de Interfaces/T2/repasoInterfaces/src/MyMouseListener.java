
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author daniel
 */
public class MyMouseListener extends MouseAdapter {

    private JButton boton;

    public MyMouseListener(JButton boton) {
        this.boton = boton;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        boton.setForeground(Color.black);
        boton.setBackground(Color.white);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        boton.setForeground(Color.WHITE);
        boton.setBackground(Color.BLACK);
    }

}
