
package com.mycompany.ut7.tarea5.davidpugagallego;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alumno
 */
public class TerceraController implements Initializable {

    @FXML
    private Label dato;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void MostrarError(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Error en la app");
        alert.showAndWait();
    }

    @FXML
    private void MostrarInfo(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText("Información de la app");
        alert.showAndWait();
    }

    @FXML
    private void MostrarWarning(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("Warning");
        alert.setContentText("Warning en la app");
        alert.showAndWait();
    }

    @FXML
    private void MostrarConfirmation(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Deseas realmente confirmar?");
        Optional<ButtonType> action = alert.showAndWait();
        
        if (action.get() == ButtonType.OK) {
            System.out.println("Has pulsado ok");
        }else{
            System.out.println("Has pulsado cancelar");
        }
    }

    @FXML
    private void mostrarDato(ActionEvent event) {
        TextInputDialog tid = new TextInputDialog();
        tid.setHeaderText(null);
        tid.setTitle("Insertar");
        tid.setContentText("Introduce un valor");
        Optional<String> texto = tid.showAndWait();
        
        dato.setText(texto.get());
    }

    @FXML
    private void cerrar(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
