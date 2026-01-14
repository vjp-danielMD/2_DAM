package com.iesvjp.junit_calculadora;

import javax.swing.*;

public class CalculadoraView extends JFrame {

    JTextField txtNum1;
    JTextField txtNum2;
    JTextField txtResultado;
    JButton btnSumar;

    public CalculadoraView() {
        setTitle("Calculadora");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Campo número 1
        txtNum1 = new JTextField();
        txtNum1.setBounds(20, 20, 100, 30);
        txtNum1.setName("num1");
        add(txtNum1);

        // Campo número 2
        txtNum2 = new JTextField();
        txtNum2.setBounds(150, 20, 100, 30);
        txtNum2.setName("num2");
        add(txtNum2);

        // Botón sumar
        btnSumar = new JButton("Sumar");
        btnSumar.setBounds(20, 70, 100, 30);
        btnSumar.setName("sumar");
        add(btnSumar);

        // Campo resultado
        txtResultado = new JTextField();
        txtResultado.setBounds(150, 70, 100, 30);
        txtResultado.setEditable(false);
        txtResultado.setName("resultado");
        add(txtResultado);

        // Acción del botón con control de errores
        btnSumar.addActionListener(e -> {
            try {
                int a = Integer.parseInt(txtNum1.getText());
                int b = Integer.parseInt(txtNum2.getText());
                txtResultado.setText(String.valueOf(a + b));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Debes introducir números válidos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraView().setVisible(true));
    }
}
