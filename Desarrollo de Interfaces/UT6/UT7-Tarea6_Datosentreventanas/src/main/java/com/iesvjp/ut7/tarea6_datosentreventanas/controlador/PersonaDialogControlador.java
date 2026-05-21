/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesvjp.ut7.tarea6_datosentreventanas.controlador;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.iesvjp.ut7.tarea6_datosentreventanas.modelo.Persona;

public class PersonaDialogControlador {

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtEdad;
    @FXML private Button btnGuardar;
    @FXML private Button btnSalir;

    private ObservableList<Persona> personas;
    private Persona persona; 
    private Persona personaAEditar; 

    
    public void initAttributtes(ObservableList<Persona> personas) {
        this.personas = personas;
        this.personaAEditar = null;
    }

    public void initAttributtes(ObservableList<Persona> personas, Persona personaSeleccionada) {
        this.personas = personas;
        this.personaAEditar = personaSeleccionada;
        
        this.txtNombre.setText(personaSeleccionada.getNombre());
        this.txtApellidos.setText(personaSeleccionada.getApellidos());
        this.txtEdad.setText(String.valueOf(personaSeleccionada.getEdad()));
    }

    @FXML
    private void guardar(ActionEvent event) {
        try {
            String nombre = this.txtNombre.getText();
            String apellidos = this.txtApellidos.getText();
            int edad = Integer.parseInt(this.txtEdad.getText());

            Persona p = new Persona(nombre, apellidos, edad);

            if (personaAEditar == null) {
                if (!personas.contains(p)) {
                    this.persona = p;
                    mostrarAlerta("Información", "Se ha añadido correctamente", Alert.AlertType.INFORMATION);
                    cerrarVentana();
                } else {
                    mostrarAlerta("Error", "La persona ya existe", Alert.AlertType.ERROR);
                }
            } else {
               
                if (personaAEditar.equals(p) || !personas.contains(p)) {
                    personaAEditar.setNombre(nombre);
                    personaAEditar.setApellidos(apellidos);
                    personaAEditar.setEdad(edad);
                    this.persona = personaAEditar;
                    mostrarAlerta("Información", "Se ha modificado correctamente", Alert.AlertType.INFORMATION);
                    cerrarVentana();
                } else {
                    mostrarAlerta("Error", "Ya existe otra persona con estos datos", Alert.AlertType.ERROR);
                }
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Por favor, introduce una edad numérica válida.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        this.persona = null;
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    public Persona getPersona() {
        return persona;
    }
}
    

