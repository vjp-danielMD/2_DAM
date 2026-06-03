package com.mycompany.ut6.tarea2.davidpugagallego;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DavidPg
 */
public class AltaController implements Initializable {

    public static Persona persona;

    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEdad;

    private ObservableList<Persona> personas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void addPersona(ActionEvent event) {
        String nombre = this.txtNombre.getText();
        String apellidos = this.txtApellidos.getText();

        try {
            int edad = Integer.parseInt(this.txtEdad.getText());

            persona = new Persona(nombre, apellidos, edad);

            Stage stage = (Stage) this.btnAdd.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            mostrarError("Edad no válida", "La edad debe ser un número entero.");
        }
    }

    public void initAttributes(ObservableList<Persona> personas) {
        this.personas = personas;
    }

    public static Persona getPersona() {
        return persona;
    }


    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
