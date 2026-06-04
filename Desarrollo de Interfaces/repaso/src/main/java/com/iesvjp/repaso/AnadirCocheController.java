/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.iesvjp.repaso;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class AnadirCocheController {

    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtColor;
    @FXML
    private TextField txtFoto;

    private Coche coche;
    private boolean guardado = false;

    public void setCoche(Coche c) {
        this.coche = c;
        if (c != null) {
            txtMarca.setText(c.getMarca());
            txtModelo.setText(c.getModelo());
            txtColor.setText(c.getColor());
            txtFoto.setText(c.getFoto());
        }
    }

    public Coche getCoche(){
        return this.coche;
    }
    
    public boolean isGuardado() {
        return guardado;
    }

    @FXML
    private void guardar(ActionEvent event) {
        String ma = txtMarca.getText();
        String mo = txtModelo.getText();
        String co = txtColor.getText();
        String fo = txtFoto.getText();

        if (ma.isEmpty() || ma.isEmpty() || co.isEmpty() || fo.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("INCOMPLETO");
            alert.setContentText("Rellena todos los campos.");
            alert.showAndWait();
            return;
        }

        if (coche == null) {
            coche = new Coche(ma, mo, co, fo);
        } else {
            coche.setMarca(ma);
            coche.setModelo(mo);
            coche.setColor(co);
            coche.setFoto(fo);
        }
        
        guardado = true;
        Stage stage = (Stage) txtMarca.getScene().getWindow();
        stage.close();
    }
}
