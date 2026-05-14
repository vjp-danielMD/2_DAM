package com.iesvjp.ut6.tarea2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtEdad;
    
    @FXML private TableView<Persona> tablaPersonas;
    @FXML private TableColumn<Persona, String> colNombre;
    @FXML private TableColumn<Persona, String> colApellidos;
    @FXML private TableColumn<Persona, Integer> colEdad;

    private ObservableList<Persona> listaPersonas;

    @FXML
    public void initialize() {
        // Inicializar la lista observable
        listaPersonas = FXCollections.observableArrayList();
        tablaPersonas.setItems(listaPersonas);

        // Vincular columnas con atributos de la clase Persona
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    }

    @FXML
    private void agregarPersona() {
        try {
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            int edad = Integer.parseInt(txtEdad.getText());

            Persona p = new Persona(nombre, apellidos, edad);
            listaPersonas.add(p);

            // Limpiar campos después de agregar
            txtNombre.clear();
            txtApellidos.clear();
            txtEdad.clear();
            txtNombre.requestFocus();
            
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La edad debe ser un número válido.");
            alert.showAndWait();
        }
    }
}