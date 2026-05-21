/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesvjp.ut7t7.controlador;

import com.iesvjp.ut7t7.modelo.Registro;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private TableView<Registro> tablaRegistros;
    @FXML
    private TableColumn<Registro, String> colNombre;
    @FXML
    private TableColumn<Registro, String> colFoto;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtFoto;

    private ObservableList<Registro> listaRegistros;

    @FXML
    public void initialize() {
        listaRegistros = FXCollections.observableArrayList();
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colFoto.setCellValueFactory(new PropertyValueFactory<>("foto"));
        tablaRegistros.setItems(listaRegistros);
    }

    @FXML
    void aniadirRegistro(ActionEvent event) {
        String nombre = txtNombre.getText();
        String foto = txtFoto.getText();

        if (nombre.isEmpty() || foto.isEmpty()) {
            mostrarAlerta("Error", "Debes rellenar todos los campos.");
            return;
        }

        listaRegistros.add(new Registro(nombre, foto));
        txtNombre.clear();
        txtFoto.clear();
    }

    @FXML
    void verFoto(ActionEvent event) {
        Registro seleccionado = tablaRegistros.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Atención", "Debes seleccionar una fila de la tabla primero.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/iesvjp/ut7t7/VisorFoto.fxml"));
            Parent root = loader.load();

            VisorFotoController controlador = loader.getController();
            controlador.cargarImagen(seleccionado.getFoto());

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Visor de Fotografía");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la ventana de la foto.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
