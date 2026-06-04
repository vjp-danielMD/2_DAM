package com.iesvjp.exut7.danielmorenodominguez;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogCocheControlador {

    @FXML
    private TextField marca;
    @FXML
    private TextField modelo;
    @FXML
    private TextField color;
    @FXML
    private TextField foto;

    private Coche coche;
    private boolean guardado = false;

    public void setCoche(Coche c) {
        this.coche = c;
        if (c != null) {
            marca.setText(c.getMarca());
            modelo.setText(c.getModelo());
            color.setText(c.getColor());
            foto.setText(c.getFoto());
        }
    }

    public Coche getCoche() {
        return coche;
    }

    public boolean isGuardado() {
        return guardado;
    }

    @FXML
    private void guardar(ActionEvent event) {
        String ma = marca.getText();
        String mo = modelo.getText();
        String co = color.getText();
        String fo = foto.getText();

        if (ma.isEmpty() || mo.isEmpty() || co.isEmpty() || fo.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Incompleto");
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
        Stage stage = (Stage) marca.getScene().getWindow();
        stage.close();
    }
}
