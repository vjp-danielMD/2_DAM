package com.mycompany.pruebaexamen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SecondaryController {

    @FXML
    private TextField textNombreModificado;
    @FXML
    private TextField textAliasModificado;
    @FXML
    private ComboBox comboRangoModificado;
    
    private Participantes participanteEditado;

    public void initialize() {

        this.comboRangoModificado.getItems().addAll("Oro", "Master", "Champion");

    }

    public void initAttributes(Participantes participante) {
        
        participanteEditado = participante;

        this.textNombreModificado.setText(participanteEditado.getNombre());
        this.textAliasModificado.setText(participanteEditado.getAlias());
        this.comboRangoModificado.setValue(participanteEditado.getRango());
    }

    @FXML
    private void cerrar(ActionEvent event) {
        
        participanteEditado.setNombre(this.textNombreModificado.getText());
        participanteEditado.setAlias(this.textAliasModificado.getText());
        participanteEditado.setRango(this.comboRangoModificado.getSelectionModel().getSelectedItem() + "");
      
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
    }
}
