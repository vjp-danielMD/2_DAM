package com.mycompany.pruebaexamen;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private TableView tabla;
    @FXML
    private TableColumn nombre;
    @FXML
    private TableColumn alias;
    @FXML
    private TableColumn rango;
    private ObservableList<Participantes> participantes;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textAlias;
    @FXML
    private ComboBox comboRango;

    public void initialize() {
        participantes = FXCollections.observableArrayList();

        this.tabla.setItems(participantes);

        this.nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.alias.setCellValueFactory(new PropertyValueFactory("alias"));
        this.rango.setCellValueFactory(new PropertyValueFactory("rango"));

        this.comboRango.getItems().addAll("Oro", "Master", "Champion");

    }

    @FXML
    private void addParticipante(ActionEvent event) {

        //Comprobación para que no existan campos vacios.
        if (this.comboRango.getSelectionModel().getSelectedItem() == null || this.textNombre.getText().equalsIgnoreCase("") || this.textAlias.getText().equalsIgnoreCase("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No puede haber campos vacios");
            alert.showAndWait();
        } else {
            Participantes p = new Participantes(this.textNombre.getText(), this.textAlias.getText(), (String) this.comboRango.getSelectionModel().getSelectedItem());
            this.participantes.add(p);
            this.tabla.refresh();
            this.textNombre.setText("");
            this.textAlias.setText("");
        }

    }

    @FXML
    private void generarParejas(ActionEvent event) {
        
        try {
                //Cargar la vista FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Parejas.fxml"));

                // Cargar la ventana
                Parent root = loader.load();

                // Cargar el controlador
                ParejasController controlador = loader.getController();
                controlador.initAttributes(this.participantes);

                //Crear la scene
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                this.tabla.refresh();

            } catch (IOException ex) {
            }
    }

    @FXML
    private void modificarParticipante(ActionEvent event) {

        if (this.tabla.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No hay ningún participante seleccionado");
            alert.showAndWait();

        } else {

            Participantes seleccionado = (Participantes) this.tabla.getSelectionModel().getSelectedItem();

            try {
                //Cargar la vista FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));

                // Cargar la ventana
                Parent root = loader.load();

                // Cargar el controlador
                SecondaryController controlador = loader.getController();
                controlador.initAttributes(seleccionado);

                //Crear la scene
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                this.tabla.refresh();

            } catch (IOException ex) {
            }
        }

    }

    @FXML
    private void eliminarParticipante(ActionEvent event) {

        Participantes seleccionado = (Participantes) this.tabla.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            this.participantes.remove(seleccionado);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Atención");
            alert.setContentText("Por favor, selecciona un participante de la tabla para eliminarlo.");
            alert.showAndWait();
        }
    }
}
