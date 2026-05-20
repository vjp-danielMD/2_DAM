package com.iesvjp.ut7_tarea5;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

public class Ventana1Controller {

    // Navegación
    @FXML
    private void irVentana2() throws IOException {
        App.setRoot("ventana2");
    }

    @FXML
    private void irVentana3() throws IOException {
        App.setRoot("ventana3");
    }

    // Alerta Informativa
    @FXML
    private void mostrarAlertaInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información del Sistema");
        alert.setHeaderText("Operación completada con éxito");
        alert.setContentText("Los datos se han procesado correctamente de acuerdo al PDF de clase.");
        alert.showAndWait();
    }

    // Alerta de Advertencia (Warning)
    @FXML
    private void mostrarAlertaWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText("Espacio de almacenamiento casi lleno");
        alert.setContentText("Por favor, revisa tus archivos antes de continuar.");
        alert.showAndWait();
    }

    // Alerta de Error
    @FXML
    private void mostrarAlertaError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Crítico");
        alert.setHeaderText("Fallo en la conexión");
        alert.setContentText("No se pudo establecer comunicación con la base de datos.");
        alert.showAndWait();
    }

    // Alerta de Confirmación (Retorna un booleano implícito)
    @FXML
    private void mostrarConfirmacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Acción");
        alert.setHeaderText("Borrado de datos");
        alert.setContentText("¿Estás completamente seguro de eliminar este registro?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("Acción confirmada por el usuario.");
        } else {
            System.out.println("Acción cancelada por el usuario.");
        }
    }

    // Petición de datos (Input Dialog)
    @FXML
    private void pedirDato() {
        TextInputDialog dialog = new TextInputDialog("Juan Pérez");
        dialog.setTitle("Entrada de Datos");
        dialog.setHeaderText("Registro de nuevo estudiante");
        dialog.setContentText("Introduce tu nombre completo:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nombre -> {
            System.out.println("Nombre capturado: " + nombre);
        });
    }
}