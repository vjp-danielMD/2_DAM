package com.mycompany.ut7.tarea5.davidpugagallego;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private void abrirVentana2(ActionEvent event) {
        //Cargamo la vista
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));

        try {
            //Cargamos la ventana
            Parent root = loader.load();

            //Creamos la escena
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();

            Node source = (Node) event.getSource();
            stage = (Stage) source.getScene().getWindow();
            stage.close();

        } catch (IOException ex) {
            System.getLogger(PrimaryController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
