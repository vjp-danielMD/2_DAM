
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author daniel
 */
public class DialogDetallesRaton extends JDialog{

    public DialogDetallesRaton(JDialog parent, boolean modal, Raton raton) {
        super(parent, modal);
        
        setTitle("Detalles del ratón");
        setLayout(new GridLayout(3, 2, 10, 10));
        ((javax.swing.JPanel) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel marca = new JLabel("Marca:");
        marca.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel marcaTexto = new JLabel(raton.getMarca());
        
        JLabel modelo = new JLabel("Modelo:");
        modelo.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel modeloTexto = new JLabel(raton.getModelo());
        
        JLabel frecuencia = new JLabel("Frecuencia:");
        frecuencia.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel frecuenciaTexto = new JLabel(raton.getFrecuencia());
        
        add(marca);
        add(marcaTexto);
        add(modelo);
        add(modeloTexto);
        add(frecuencia);
        add(frecuenciaTexto);
        
        setSize(400, 200);
        setLocationRelativeTo(parent);
        setResizable(false);
                
    }
    
}
