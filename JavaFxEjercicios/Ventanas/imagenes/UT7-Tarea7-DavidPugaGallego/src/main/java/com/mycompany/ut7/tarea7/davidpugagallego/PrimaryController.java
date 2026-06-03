package com.mycompany.ut7.tarea7.davidpugagallego;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private TextField textNombre;
    @FXML
    private TextField textFoto;

    @FXML
    private TableView<Datos> tabla;
    @FXML
    private TableColumn<Datos, String> nombre;
    @FXML
    private TableColumn<Datos, String> foto;

    private ObservableList<Datos> datos;

    public static Datos pasarDatos;

    public void initialize() {
        datos = FXCollections.observableArrayList();

        this.tabla.setItems(datos);
        this.nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.foto.setCellValueFactory(new PropertyValueFactory<>("foto"));
    }

    @FXML
    private void addDatos(ActionEvent event) {
        if (this.textNombre.getText().trim().isEmpty() || this.textFoto.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error, no puede haber campos vacíos");
            alert.showAndWait();
        } else {
            pasarDatos = new Datos(this.textNombre.getText(), this.textFoto.getText());
            this.datos.add(pasarDatos);
            this.tabla.refresh();
            this.textFoto.setText("");
            this.textNombre.setText("");
        }
    }

    @FXML
    private void verFotoVentanaNueva(ActionEvent event) {
        Datos seleccionado = this.tabla.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atención");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona primero una fila de la tabla.");
            alert.showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml")); // Ajusta el nombre de tu archivo .fxml
            Parent root = loader.load();

            SecondaryController controller = loader.getController();

            controller.setDato(seleccionado);

            Stage stage = new Stage();
            stage.setTitle("Visor de Foto");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Datos getDatos() {
        return pasarDatos;
    }

}
