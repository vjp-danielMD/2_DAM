package com.mycompany.pruebajavafx2;

import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private Button btnAbrirTercera;
    @FXML
    private Button btnError;
    @FXML
    private Button btnInfo;
    @FXML
    private Button btnWarning;
    @FXML
    private Button btnConfirmation;
    @FXML
    private Button btnInput;

    @FXML
    private void abrirTerceraVentana(ActionEvent event) {
        //Cargamo la vista
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tercera.fxml"));
        
        try {
            //Cargamos la ventana
            Parent root = loader.load();
            
            //Creamos la escena
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
        } catch (IOException ex) {
            System.getLogger(PrimaryController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
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
        
        System.out.println(texto.get());
    }
}
