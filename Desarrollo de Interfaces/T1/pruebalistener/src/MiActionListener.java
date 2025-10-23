
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alumno
 */
public class MiActionListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
           System.out.println("has pulsado el " + e.getActionCommand());
           
    }
    
}
