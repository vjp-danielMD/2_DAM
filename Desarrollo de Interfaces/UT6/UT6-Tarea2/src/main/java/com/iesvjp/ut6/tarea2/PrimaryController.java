package com.iesvjp.ut6.tarea2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController {

    @FXML
    private TextField nombre, apellidos, edad;
    @FXML
    private TableView<Persona> tablaPersonas;
    @FXML
    private TableColumn<Persona, String> colNombre, colApellidos, colEdad;

    private final ObservableList<Persona> listaPersonas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        tablaPersonas.setItems(listaPersonas);
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    }

    @FXML
    private void agregarPersona() {
        String n = nombre.getText().trim();
        String a = apellidos.getText().trim();
        String e = edad.getText().trim();

        if (esValido(n, a, e)) {
            listaPersonas.add(new Persona(n, a, e));
            limpiarCampos();
        }
    }

    private boolean esValido(String n, String a, String e) {
        if (n.isEmpty() || a.isEmpty() || e.isEmpty()) {
            mostrarAlerta("Incompleto", "Rellena todos los campos.", Alert.AlertType.WARNING);
            return false;
        }
        if (!e.matches("\\d+") || Integer.parseInt(e) < 0 || Integer.parseInt(e) > 120) {
            mostrarAlerta("Edad inválida", "Introduce una edad coherente (0-120).", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String tit, String msg, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setTitle(tit);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        nombre.clear();
        apellidos.clear();
        edad.clear();
        nombre.requestFocus();
    }
}
