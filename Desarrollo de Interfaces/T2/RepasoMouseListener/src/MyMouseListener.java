
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author danie
 */
public class MyMouseListener extends MouseAdapter{

    @Override
    public void mouseExited(MouseEvent e) {
        e.getComponent().setBackground(Color.YELLOW);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setBackground(Color.red);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        e.getComponent().setBackground(Color.GREEN);
    }
    
}
