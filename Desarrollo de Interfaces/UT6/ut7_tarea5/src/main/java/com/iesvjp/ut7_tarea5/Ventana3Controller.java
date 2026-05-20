package com.iesvjp.ut7_tarea5;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class Ventana3Controller {

    @FXML
    private void irVentana1() throws IOException {
        App.setRoot("ventana1");
    }

    @FXML
    private void irVentana2() throws IOException {
        App.setRoot("ventana2");
    }

    @FXML
    private void deClaseGuardarExito() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Simulación de Base de Datos");
        alert.setHeaderText("¡Éxito!");
        alert.setContentText("El registro ha sido guardado correctamente en la ventana 3.");
        alert.showAndWait();
    }

    @FXML
    private void deClaseEliminarPeligro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Acción Peligrosa");
        alert.setHeaderText("Eliminación Crítica");
        alert.setContentText("Se han borrado los archivos temporales del sistema.");
        alert.showAndWait();
    }

    @FXML
    private void deClaseMostrarEnlace() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        // Botón de tipo cerrar obligatorio para las alertas de tipo NONE
        alert.getButtonTypes().add(javafx.scene.control.ButtonType.CLOSE);
        alert.setTitle("Ayuda");
        alert.setHeaderText("Documentación de JavaFX");
        alert.setContentText("Redirigiendo a la guía oficial de estilos CSS de Oracle...");
        alert.showAndWait();
    }
}