package com.mycompany.ut7.tarea7.davidpugagallego;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SecondaryController {

    @FXML
    private ImageView foto;

    private Datos dato;

    public void initialize() {
    }

    public void setDato(Datos dato) {
        this.dato = dato;
        mostrarImagen();
    }

    private void mostrarImagen() {
        if (this.dato.getFoto() != null) {
            try {
                String rutaImagen = this.dato.getFoto();

                Image img = new Image(rutaImagen);
                this.foto.setImage(img);
            } catch (Exception e) {
                Image imgError = new Image(getClass().getResourceAsStream("error.png"));
                this.foto.setImage(imgError);
            }
        }
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
