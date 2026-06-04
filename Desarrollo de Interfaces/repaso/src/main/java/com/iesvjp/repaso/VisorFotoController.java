/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.iesvjp.repaso;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class VisorFotoController {

    @FXML
    private ImageView imageView;
    @FXML
    private Button btnCerrar;

    public void cargarImagen(String ruta) {
        try {
            if (!ruta.startsWith("http") && !ruta.startsWith("file:")) {
                ruta = "file:///" + ruta.replace("\\", "/");
            }
            Image imagen = new Image(ruta);
            imageView.setImage(imagen);

        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + ruta);
        }
    }

    @FXML
    public void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

}
