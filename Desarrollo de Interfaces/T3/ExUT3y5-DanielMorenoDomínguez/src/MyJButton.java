
import java.io.Serializable;
import javax.swing.JButton;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author alumno
 */
public class MyJButton extends JButton implements Serializable {

    private static int contadorGlobal = 1;

    public MyJButton() {
        this.setText("Boton nº " + contadorGlobal);
        this.addMouseListener(new MyMouseListener());
        contadorGlobal++; 
    }

}
