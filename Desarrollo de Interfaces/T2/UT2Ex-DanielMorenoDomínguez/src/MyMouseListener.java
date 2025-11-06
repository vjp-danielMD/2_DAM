
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author alumno
 */
public class MyMouseListener extends MouseAdapter {

    PantallaPrincipal padre = new PantallaPrincipal();

    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setBackground(Color.red);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(padre, "Mensaje!", "Has pulsado el boton " + e.getComponent().getName(), JOptionPane.INFORMATION_MESSAGE);
    }

}
