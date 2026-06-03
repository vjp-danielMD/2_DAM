package com.mycompany.ut6.tarea1.davidpugagallego;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PrimaryController {

    @FXML
    private TextField num1;
    @FXML
    private TextField num2;
    @FXML
    private Button btnCalcular;
    @FXML
    private RadioButton RbSuma;
    @FXML
    private ToggleGroup Operaciones;
    @FXML
    private RadioButton RbDividir;
    @FXML
    private RadioButton RbResta;
    @FXML
    private RadioButton RbMultiplicar;
    @FXML
    private TextField Resultado;

    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void calcular(ActionEvent event) {
        String operacion;
        Float res;
        if (RbSuma.isSelected()) {
            operacion = "suma";
        } else if (RbDividir.isSelected()) {
            operacion = "division";
        } else if (RbResta.isSelected()) {
            operacion = "resta";
        } else {
            operacion = "multiplicacion";
        }
        try {
            switch (operacion) {
                case "suma":
                    res = (Float.parseFloat(num1.getText())) + (Float.parseFloat(num2.getText()));
                    Resultado.setText(res + "");
                    break;
                case "division":
                    res = (Float.parseFloat(num1.getText())) / (Float.parseFloat(num2.getText()));
                    Resultado.setText(res + "");
                    break;
                case "resta":
                    res = (Float.parseFloat(num1.getText())) - (Float.parseFloat(num2.getText()));
                    Resultado.setText(res + "");
                    break;
                case "multiplicacion":
                    res = (Float.parseFloat(num1.getText())) * (Float.parseFloat(num2.getText()));
                    Resultado.setText(res + "");
                    break;
                default:
                    System.out.println("Error");
            }
        } catch (NumberFormatException e) {
            Resultado.setText("Error");
        }

    }
}
