/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesvjp.ut7.tarea6_datosentreventanas.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.iesvjp.ut7.tarea6_datosentreventanas.modelo.Persona;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowControlador implements Initializable {

    @FXML
    private TableView<Persona> tblPersonas;
    @FXML
    private TableColumn<Persona, String> colNombre;
    @FXML
    private TableColumn<Persona, String> colApellidos;
    @FXML
    private TableColumn<Persona, Integer> colEdad;

    private ObservableList<Persona> personas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        personas = FXCollections.observableArrayList();
        this.tblPersonas.setItems(personas);

        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        this.colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    }

    @FXML
    private void agregarPersona(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/iesvjp/ut7/tarea6_datosentreventanas/PersonaDialogVista.fxml"));
            Parent root = loader.load();

            PersonaDialogControlador controlador = loader.getController();
            controlador.initAttributtes(personas);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            Persona p = controlador.getPersona();
            if (p != null) {
                this.personas.add(p);
                this.tblPersonas.refresh();
            }

        } catch (IOException ex) {
            mostrarAlerta("Error", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void modificarPersona(ActionEvent event) {
        Persona personaSeleccionada = this.tblPersonas.getSelectionModel().getSelectedItem();

        if (personaSeleccionada != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/iesvjp/ut7/tarea6_datosentreventanas/PersonaDialogVista.fxml"));
                Parent root = loader.load();

                PersonaDialogControlador controlador = loader.getController();
                controlador.initAttributtes(personas, personaSeleccionada);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                this.tblPersonas.refresh();

            } catch (IOException ex) {
                mostrarAlerta("Error", ex.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Atención", "Debes seleccionar una persona de la tabla para modificarla.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void borrarPersona(ActionEvent event) {
        int seleccionado = this.tblPersonas.getSelectionModel().getSelectedIndex();
        System.out.println(seleccionado); 

        if (seleccionado != -1) {
            this.tblPersonas.getItems().remove(seleccionado);
        } else {
            mostrarAlerta("Atención", "Debes seleccionar una persona de la tabla para eliminarla.", Alert.AlertType.WARNING);
        }
    }

    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
