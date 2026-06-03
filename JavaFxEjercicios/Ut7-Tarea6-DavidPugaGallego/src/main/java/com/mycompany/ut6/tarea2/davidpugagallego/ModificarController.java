package com.mycompany.ut6.tarea2.davidpugagallego;

import java.net.URL;
import java.util.ResourceBundle;
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
public class ModificarController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEdad;

    private Persona personaAEditar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    


    public void initAttributes(Persona persona) {
        this.personaAEditar = persona;
        
        if (this.personaAEditar != null) {
            this.txtNombre.setText(personaAEditar.getNombre());
            this.txtApellidos.setText(personaAEditar.getApellidos());
            this.txtEdad.setText(String.valueOf(personaAEditar.getEdad()));
        }
    }

 
    @FXML
    private void modPersona(ActionEvent event) { 
        String nombre = this.txtNombre.getText();
        String apellidos = this.txtApellidos.getText();

        if (nombre.trim().isEmpty() || apellidos.trim().isEmpty()) {
            mostrarError("Campos obligatorios", "Por favor, rellena el nombre y los apellidos.");
            return;
        }

        try {
            int edad = Integer.parseInt(this.txtEdad.getText());

            this.personaAEditar.setNombre(nombre);
            this.personaAEditar.setApellidos(apellidos);
            this.personaAEditar.setEdad(edad);

            Button btnPulsado = (Button) event.getSource();
            Stage stage = (Stage) btnPulsado.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            mostrarError("Edad no válida", "La edad debe ser un número entero válido.");
        }
    }
    
    /**
     * Método cómodo para lanzar alertas de error
     */
    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}