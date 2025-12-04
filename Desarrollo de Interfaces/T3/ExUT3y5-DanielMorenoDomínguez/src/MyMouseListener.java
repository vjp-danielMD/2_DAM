
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
 * @author alumno
 */
public class MyMouseListener extends MouseAdapter {

    private JButton button;

    @Override
    public void mouseExited(MouseEvent e) {
        button = (JButton) e.getSource();
        button.setBackground(Color.yellow);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        button = (JButton) e.getSource();
        button.setBackground(Color.red);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        button = (JButton) e.getSource();
        button.setBackground(Color.green);
    }

}
