package com.iesvjp.repaso;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private TableView<Coche> tablaCoches;
    @FXML
    private TableColumn<Coche, String> colModelo, colMarca, colColor, colFoto;
    @FXML
    private final ObservableList<Coche> listaCoches = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        tablaCoches.setItems(listaCoches);

        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colFoto.setCellValueFactory(new PropertyValueFactory<>("foto"));

        // 2 coches por defecto
        listaCoches.add(new Coche("MG", "ZS EV", "Rojo", "https://motor.elpais.com/wp-content/uploads/2024/02/MG-ZS-EV-1536x847.jpg"));
        listaCoches.add(new Coche("MG", "Mas Vendido", "Gris", "https://motor.elpais.com/wp-content/uploads/2025/02/cuanto-cuesta-coche-mas-vendido-MG-1046x616.jpg"));
    }

    @FXML
    public void cargarCoche() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/iesvjp/repaso/anadirCoche.fxml"));
            Parent root = loader.load();

            AnadirCocheController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Añadir Coche");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            if (controlador.isGuardado()) {
                listaCoches.add(controlador.getCoche());
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Eror", "No se puedo abrir la ventana de añadir coche.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void modificarCoche() {
        Coche seleccionado = tablaCoches.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Atención", "Selecciona un coche para modificar", Alert.AlertType.WARNING);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/iesvjp/repaso/anadirCoche.fxml"));
            Parent root = loader.load();

            AnadirCocheController controlador = loader.getController();
            controlador.setCoche(seleccionado);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Modificar Coche");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            if (controlador.isGuardado()) {
                tablaCoches.refresh();
            }
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la ventana de modificar coche.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void eliminarCoche() {
        Coche seleccinado = tablaCoches.getSelectionModel().getSelectedItem();
        if (seleccinado != null) {
            listaCoches.remove(seleccinado);
            tablaCoches.getSelectionModel().clearSelection();
        } else {
            mostrarAlerta("Atención", "Selecciona un coche para eliminar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void verFoto(ActionEvent event) {
        Coche seleccionado = tablaCoches.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Atencion", "Debes seleccionar una fila de la tabla primero.", Alert.AlertType.WARNING);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/iesvjp/repaso/visorFoto.fxml"));
            Parent root = loader.load();

            VisorFotoController controlador = loader.getController();
            controlador.cargarImagen(seleccionado.getFoto());

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Visor Fotografía");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Error al cargar la foto.", Alert.AlertType.WARNING);
        }
    }

    private void mostrarAlerta(String tit, String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setTitle(tit);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    private void ejercicioDos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/iesvjp/repaso/secondary.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Ejercicio 2");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Error al abrir Ejercicio 2.", Alert.AlertType.WARNING);
        }
    }
}
