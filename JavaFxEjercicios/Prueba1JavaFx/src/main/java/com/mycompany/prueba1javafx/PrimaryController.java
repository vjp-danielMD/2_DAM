package com.mycompany.prueba1javafx;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrimaryController {

    @FXML
    private Button Btn1;
    @FXML
    private Label lbl1;
    @FXML
    private Button Btn2;

    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void cambiarVentana(ActionEvent event) throws IOException{
        App.setRoot("secondary");
    }

    @FXML
    private void AccionBotn2(ActionEvent event) {
 
    }
}
