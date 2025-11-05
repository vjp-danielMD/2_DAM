
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
 * @author danie
 */
public class MyMouseListener extends MouseAdapter {

    private JButton boton;

    public MyMouseListener(JButton boton) {
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
