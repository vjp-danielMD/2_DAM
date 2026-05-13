package com.iesvjp.prueba1_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField txtOp1;
    @FXML
    private TextField txtOp2;
    @FXML
    private RadioButton rbSuma;
    @FXML
    private RadioButton rbResta;
    @FXML
    private RadioButton rbMulti;
    @FXML
    private RadioButton rbDivi;
    @FXML
    private TextArea txtResultado;

    @FXML
    private void handleOperar(ActionEvent event) {
        try {
            // 1. Obtener los valores de los TextField y convertirlos a número
            double op1 = Double.parseDouble(txtOp1.getText());
            double op2 = Double.parseDouble(txtOp2.getText());
            double resultado = 0;

            // 2. Identificar qué RadioButton está seleccionado
            if (rbSuma.isSelected()) {
                resultado = op1 + op2;
            } else if (rbResta.isSelected()) {
                resultado = op1 - op2;
            } else if (rbMulti.isSelected()) {
                resultado = op1 * op2;
            } else if (rbDivi.isSelected()) {
                if (op2 == 0) {
                    txtResultado.setText("Error: No se puede dividir por cero.");
                    return;
                }
                resultado = op1 / op2;
            }

            // 3. Mostrar el resultado en el TextArea
            txtResultado.setText(""+resultado);

        } catch (NumberFormatException e) {
            txtResultado.setText("Error: Por favor, introduce números válidos en ambos campos.");
        }
    }
}