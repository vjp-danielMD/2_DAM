package com.iesvjp.ut7_tarea5;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;

public class Ventana2Controller {

    @FXML
    private void irVentana1() throws IOException {
        App.setRoot("ventana1");
    }

    @FXML
    private void irVentana3() throws IOException {
        App.setRoot("ventana3");
    }

    @FXML
    private void deClaseBotonSuave() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ventana 2");
        alert.setHeaderText("Estilo Pastel Activo");
        alert.setContentText("Has pulsado el botón con estilo suave.");
        alert.showAndWait();
    }

    @FXML
    private void deClasePeticionOpciones() {
        // Diálogo de selección (ChoiceDialog) para elegir de una lista
        List<String> opciones = Arrays.asList("JavaFX", "Maven", "NetBeans", "CSS");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("JavaFX", opciones);
        dialog.setTitle("Petición de Datos Avanzada");
        dialog.setHeaderText("Selecciona tu herramienta favorita:");
        dialog.setContentText("Tecnología:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(seleccion -> System.out.println("El usuario eligió: " + seleccion));
    }

    @FXML
    private void deClaseBotonLargo() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerta de Ancho Fijo");
        alert.setHeaderText("Componente de 200px");
        alert.setContentText("Este botón demuestra cómo forzar dimensiones por CSS.");
        alert.showAndWait();
    }
}