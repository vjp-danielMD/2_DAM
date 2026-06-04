/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesvjp.exut7.danielmorenodominguez;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class VisorFotoController {

    @FXML private ImageView imageView;
    @FXML private Button btnCerrar;

    public void cargarImagen(String rutaFoto) {
        try {
            if (!rutaFoto.startsWith("http") && !rutaFoto.startsWith("file:")) {
                rutaFoto = "file:///" + rutaFoto.replace("\\", "/");
            }
            Image imagen = new Image(rutaFoto);
            imageView.setImage(imagen);
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + rutaFoto);
        }
    }

    @FXML
    void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
}
