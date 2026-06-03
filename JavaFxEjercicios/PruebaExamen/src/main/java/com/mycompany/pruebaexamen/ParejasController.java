package com.mycompany.pruebaexamen;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ParejasController implements Initializable {

    @FXML
    private TableView<Participantes> tablaOro;
    @FXML
    private TableView<Participantes> tablaMaster;
    @FXML
    private TableView<Participantes> tablaChampion;
    
    private ObservableList<Participantes> partOro;
    private ObservableList<Participantes> partMaster;
    private ObservableList<Participantes> partChampion;
    
    @FXML
    private TableColumn<Participantes, String> oroAlias;
    @FXML
    private TableColumn<Participantes, String> masterAlias;
    @FXML
    private TableColumn<Participantes, String> champAlias;
    
    @FXML
    private TableView<Pareja> tablaVs;
    @FXML
    private TableColumn<Pareja, String> vsAliasUno;
    @FXML
    private TableColumn<Pareja, String> vsAlias2;
    
    private ObservableList<Pareja> listaParejas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.partOro = FXCollections.observableArrayList();
        this.partMaster = FXCollections.observableArrayList();
        this.partChampion = FXCollections.observableArrayList();
        this.listaParejas = FXCollections.observableArrayList();

        this.tablaOro.setItems(partOro);
        this.tablaMaster.setItems(partMaster);
        this.tablaChampion.setItems(partChampion);
        this.tablaVs.setItems(listaParejas); 

        this.oroAlias.setCellValueFactory(new PropertyValueFactory<>("alias"));
        this.masterAlias.setCellValueFactory(new PropertyValueFactory<>("alias"));
        this.champAlias.setCellValueFactory(new PropertyValueFactory<>("alias"));
        
        // Vinculamos las columnas de la tabla VS con las propiedades de nuestra clase Pareja
        this.vsAliasUno.setCellValueFactory(new PropertyValueFactory<>("jugador1"));
        this.vsAlias2.setCellValueFactory(new PropertyValueFactory<>("jugador2"));
    }

    public void initAttributes(ObservableList<Participantes> lista) {
        for (Participantes participante : lista) {
            switch (participante.getRango()) {
                case "Oro":
                    this.partOro.add(participante);
                    break;
                case "Master":
                    this.partMaster.add(participante);
                    break;
                case "Champion":
                    this.partChampion.add(participante);
                    break;
                default:
                    break;
            }
        }
        
        // ¡AUTOMÁTICO! Una vez separados por rangos, generamos los emparejamientos
        generarTodosLosEmparejamientos();
    }

    // Lógica para emparejar aleatoriamente todos los grupos
    private void generarTodosLosEmparejamientos() {
        this.listaParejas.clear(); // Limpiamos por si acaso
        
        // Emparejamos cada rango por separado
        emparejarLista(new ArrayList<>(this.partOro));
        emparejarLista(new ArrayList<>(this.partMaster));
        emparejarLista(new ArrayList<>(this.partChampion));
        
        this.tablaVs.refresh();
    }

    // Algoritmo auxiliar que mezcla y empareja de 2 en 2
    private void emparejarLista(List<Participantes> listaRango) {
        // Shuffle mezcla aleatoriamente la lista
        Collections.shuffle(listaRango);
        
        // Vamos cogiendo de dos en dos
        for (int i = 0; i < listaRango.size(); i += 2) {
            // Si queda un jugador desparejado (lista impar), jugará contra un bot o pasa libre
            if (i + 1 < listaRango.size()) {
                String j1 = listaRango.get(i).getAlias() + " (" + listaRango.get(i).getRango() + ")";
                String j2 = listaRango.get(i + 1).getAlias() + " (" + listaRango.get(i + 1).getRango() + ")";
                this.listaParejas.add(new Pareja(j1, j2));
            } else {
                // Caso impar: el último se queda solo o contra un "Descansa"
                String j1 = listaRango.get(i).getAlias() + " (" + listaRango.get(i).getRango() + ")";
                this.listaParejas.add(new Pareja(j1, "--- ESPERA RIVAL ---"));
            }
        }
    }

    @FXML
    private void cerrar(ActionEvent event) {
        // Código rápido para cerrar la ventana actual al pulsar el botón
        Stage stage = (Stage) this.tablaVs.getScene().getWindow();
        stage.close();
    }

    // --- CLASE AUXILIAR INTERNA PARA LA TABLA VS ---
    // (Imprescindible para que PropertyValueFactory encuentre los campos "jugador1" y "jugador2")
    public static class Pareja {
        private final String jugador1;
        private final String jugador2;

        public Pareja(String jugador1, String jugador2) {
            this.jugador1 = jugador1;
            this.jugador2 = jugador2;
        }

        public String getJugador1() { return jugador1; }
        public String getJugador2() { return jugador2; }
    }
}