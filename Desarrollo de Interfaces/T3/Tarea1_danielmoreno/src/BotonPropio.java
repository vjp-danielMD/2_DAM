
import java.awt.Color;
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
public class BotonPropio extends JButton implements Serializable {

    public static int contador = 1;

    public BotonPropio() {

        this.setBackground(Color.red);

        this.setText("Boton numero " + contador);

        contador++;

    }

}
