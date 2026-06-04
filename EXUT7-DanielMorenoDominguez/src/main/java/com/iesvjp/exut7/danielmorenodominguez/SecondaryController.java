package com.iesvjp.exut7.danielmorenodominguez;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SecondaryController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtEdad;
    @FXML
    private ChoiceBox<String> cbCiudad;
    @FXML
    private ToggleGroup tgRol;
    @FXML
    private RadioButton rbAdmin;
    @FXML
    private RadioButton rbEditor;
    @FXML
    private RadioButton rbLector;

    @FXML
    public void initialize() {
        cbCiudad.getItems().addAll("Plasencia", "Cáceres", "Badajoz", "Mérida");
        cbCiudad.setValue("Plasencia");
    }

    @FXML
    private void verDatos(ActionEvent event) {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String edad = txtEdad.getText();
        String ciudad = cbCiudad.getValue();

        RadioButton rbSeleccionado = (RadioButton) tgRol.getSelectedToggle();

        if (nombre.isEmpty() || apellido.isEmpty() || edad.isEmpty() || ciudad == null || rbSeleccionado == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Alerta");
            alert.setContentText("Por favor, completa todos los datos.");
            alert.showAndWait();
            return;
        }

        String rol = rbSeleccionado.getText();

        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setHeaderText("Datos Recogidos");
        info.setTitle("Ventana Nueva");
        info.setContentText("Nombre: " + nombre + "\n" +
                            "Apellido: " + apellido + "\n" +
                            "Edad: " + edad + "\n" +
                            "Ciudad: " + ciudad + "\n" +
                            "Rol: " + rol);
        info.showAndWait();
    }
}