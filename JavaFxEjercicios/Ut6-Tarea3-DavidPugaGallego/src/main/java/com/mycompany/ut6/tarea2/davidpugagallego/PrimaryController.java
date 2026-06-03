package com.mycompany.ut6.tarea2.davidpugagallego;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController {

    @FXML
    private TextField textNombre;
    @FXML
    private TextField textApellidos;
    @FXML
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
        this.nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        this.edad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    }

    @FXML
    private void agregarPersona(ActionEvent event) throws IOException {
        int contador = 0;

        this.textNombre.setStyle("-fx-control-inner-background: white;");
        this.textApellidos.setStyle("-fx-control-inner-background: white;");
        this.textEdad.setStyle("-fx-control-inner-background: white;");

        String n = this.textNombre.getText();
        String a = this.textApellidos.getText();
        String e = this.textEdad.getText();
        if (n.equalsIgnoreCase("")) {
            this.textNombre.setStyle("-fx-control-inner-background: red;");
            contador++;
        }
        if (a.equalsIgnoreCase("")) {
            this.textApellidos.setStyle("-fx-control-inner-background: red;");
            contador++;
        }

        if (e.equalsIgnoreCase("")) {
            this.textEdad.setStyle("-fx-control-inner-background: red;");
            contador++;
        }

        if (contador == 0) {
            Persona persona = new Persona(n, a, Integer.parseInt(e));
            this.personas.add(persona);
            this.tabla.setItems(personas);
            this.textNombre.setText("");
            this.textApellidos.setText("");
            this.textEdad.setText("");
        }

    }

    @FXML
    private void modificarPersona(ActionEvent event) {
        this.textNombre.setStyle("-fx-control-inner-background: white;");
        this.textApellidos.setStyle("-fx-control-inner-background: white;");
        this.textEdad.setStyle("-fx-control-inner-background: white;");
        int contador = 0;
        Persona seleccionada = (Persona) this.tabla.getSelectionModel().getSelectedItem();

        String n = this.textNombre.getText();
        String a = this.textApellidos.getText();
        String e = this.textEdad.getText();
        if (n.equalsIgnoreCase("")) {
            this.textNombre.setStyle("-fx-control-inner-background: red;");
            contador++;
        }
        if (a.equalsIgnoreCase("")) {
            this.textApellidos.setStyle("-fx-control-inner-background: red;");
            contador++;
        }

        if (e.equalsIgnoreCase("")) {
            this.textEdad.setStyle("-fx-control-inner-background: red;");
            contador++;
        }

        if (contador == 0) {
            seleccionada.setNombre(this.textNombre.getText());
            seleccionada.setApellidos(this.textApellidos.getText());
            seleccionada.setEdad(Integer.parseInt(this.textEdad.getText()));

            this.tabla.refresh();
        }

    }

    @FXML
    private void eliminarPersona(ActionEvent event) {

        Persona seleccionada = (Persona) this.tabla.getSelectionModel().getSelectedItem();

        if (seleccionada != null) {
            this.personas.remove(seleccionada);
        }
    }
}
