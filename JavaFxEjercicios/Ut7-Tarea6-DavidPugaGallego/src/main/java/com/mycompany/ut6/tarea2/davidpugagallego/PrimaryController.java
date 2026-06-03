package com.mycompany.ut6.tarea2.davidpugagallego;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController {

    private TextField textNombre;
    private TextField textApellidos;
    private TextField textEdad;
    private ObservableList<Persona> personas;
    @FXML
    private Button btnAdd;
    @FXML
    private TableView tabla;
    @FXML
    private TableColumn nombre;
    @FXML
    private TableColumn apellidos;
    @FXML
    private TableColumn edad;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;

    public void initialize() {
        personas = FXCollections.observableArrayList();

        this.tabla.setItems(personas);

        this.nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        this.edad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    }

    @FXML
    private void agregarPersona(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Alta.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);

        AltaController controlador = loader.getController();
        controlador.initAttributes(personas);

        stage.showAndWait();

        Persona p = AltaController.getPersona();

        if (p != null) {
            this.personas.add(p);
            this.tabla.refresh();
        }

    }

    @FXML
    private void modificarPersona(ActionEvent event) throws IOException {

        Persona seleccionada = (Persona) this.tabla.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Atención");
            alert.setContentText("Por favor, selecciona una persona de la tabla para modificar.");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Modificar.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            ModificarController controlador = loader.getController();
            controlador.initAttributes(seleccionada);

            stage.showAndWait();

            this.tabla.refresh();
        }

    }

    @FXML
    private void eliminarPersona(ActionEvent event) {

        Persona seleccionada = (Persona) this.tabla.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Atención");
            alert.setContentText("Por favor, selecciona una persona de la tabla para eliminar.");
            alert.showAndWait();
        } else {
            this.personas.remove(seleccionada);
        }
    }
}
