package com.iesvjp.ut6.tarea3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController {

    @FXML private TextField nombre, apellidos, edad;
    @FXML private TableView<Persona> tablaPersonas;
    @FXML private TableColumn<Persona, String> colNombre, colApellidos, colEdad;

    private final ObservableList<Persona> listaPersonas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        tablaPersonas.setItems(listaPersonas);
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

        // Opcional: Al hacer clic en una fila de la tabla, cargar los datos en los campos
        tablaPersonas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nombre.setText(newSelection.getNombre());
                apellidos.setText(newSelection.getApellidos());
                edad.setText(newSelection.getEdad());
            }
        });
    }

    @FXML
    private void agregarPersona() {
        String n = nombre.getText().trim(), a = apellidos.getText().trim(), e = edad.getText().trim();
        if (esValido(n, a, e)) {
            listaPersonas.add(new Persona(n, a, e));
            limpiarCampos();
        }
    }

    @FXML
    private void modificarPersona() {
        Persona seleccionada = tablaPersonas.getSelectionModel().getSelectedItem();
        
        if (seleccionada == null) {
            mostrarAlerta("Atención", "Selecciona a alguien en la tabla para modificar.", Alert.AlertType.WARNING);
            return;
        }

        String n = nombre.getText().trim(), a = apellidos.getText().trim(), e = edad.getText().trim();
        
        if (esValido(n, a, e)) {
            // Como tu clase Persona no usa Properties, creamos un objeto nuevo y reemplazamos
            int indice = listaPersonas.indexOf(seleccionada);
            listaPersonas.set(indice, new Persona(n, a, e));
            tablaPersonas.refresh(); // Forzamos el refresco visual
            limpiarCampos();
        }
    }

    @FXML
    private void eliminarPersona() {
        Persona seleccionada = tablaPersonas.getSelectionModel().getSelectedItem();
        
        if (seleccionada != null) {
            listaPersonas.remove(seleccionada);
            limpiarCampos();
        } else {
            mostrarAlerta("Atención", "Selecciona a alguien para eliminar.", Alert.AlertType.WARNING);
        }
    }

    private boolean esValido(String n, String a, String e) {
        if (n.isEmpty() || a.isEmpty() || e.isEmpty()) {
            mostrarAlerta("Incompleto", "Rellena todos los campos.", Alert.AlertType.WARNING);
            return false;
        }
        if (!e.matches("\\d+")) {
            mostrarAlerta("Edad inválida", "La edad debe ser un número.", Alert.AlertType.ERROR);
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
        nombre.clear(); apellidos.clear(); edad.clear();
        tablaPersonas.getSelectionModel().clearSelection();
        nombre.requestFocus();
    }
}