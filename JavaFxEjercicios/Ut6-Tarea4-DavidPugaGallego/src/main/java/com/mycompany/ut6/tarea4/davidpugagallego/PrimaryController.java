package com.mycompany.ut6.tarea4.davidpugagallego;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PrimaryController implements Initializable {

    @FXML private ImageView imgLupa;
    @FXML private TextField txtBuscar;
    @FXML private ListView<Pais> listPaises;
    @FXML private Pane panelDetalles;
    @FXML private ImageView imgBanderaMini;
    @FXML private Label lblNombre;
    @FXML private Label lblNombreOficial;
    @FXML private Label lblCapital;
    @FXML private Label lblRegion;
    @FXML private Label lblSubregion;
    @FXML private Label lblPoblacion;
    @FXML private Label lblMoneda;
    @FXML private Label lblIdioma;
    @FXML private ImageView imgBanderaGrande;
    @FXML private Label lblEstado;

    private final PaisService paisService = new PaisService();
    private final ObservableList<Pais> masterData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 1. Configurar texto de carga inicial
        lblEstado.setText("Cargando países desde la API...");
        lblEstado.setStyle("-fx-text-fill: blue;");

        // 2. Cargar los datos en un hilo secundario para que la interfaz no se congele
        new Thread(() -> {
            try {
                List<Pais> temporal = paisService.obtenerTodosLosPaises();
                Platform.runLater(() -> {
                    masterData.addAll(temporal);
                    lblEstado.setText("Datos cargados correctamente. Total: " + masterData.size() + " países.");
                    lblEstado.setStyle("-fx-text-fill: green;");
                });
            } catch (Exception e) {
                Platform.runLater(() -> {
                    lblEstado.setText("Error al conectar a la API o cargar datos.");
                    lblEstado.setStyle("-fx-text-fill: red;");
                    e.printStackTrace();
                });
            }
        }).start();

        // 3. Configurar el Filtro de Búsqueda en tiempo real
        FilteredList<Pais> filteredData = new FilteredList<>(masterData, p -> true);
        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pais -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return pais.getNombre().toLowerCase().contains(lowerCaseFilter);
            });
        });
        listPaises.setItems(filteredData);

        // 4. Configurar el evento de selección del ListView
        listPaises.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            mostrarDetallesPais(newValue);
        });
    }

    private void mostrarDetallesPais(Pais pais) {
        if (pais == null) return;

        // Asignar los textos
        lblNombre.setText(pais.getNombre());
        lblNombreOficial.setText(pais.getNombreOficial());
        lblCapital.setText(pais.getCapital());
        lblRegion.setText(pais.getRegion());
        lblSubregion.setText(pais.getSubregion());
        
        // Formatear población con separadores de miles
        lblPoblacion.setText(String.format("%,d", pais.getPoblacion()));
        lblMoneda.setText(pais.getMonedas());
        lblIdioma.setText(pais.getIdiomas());

        // Cargar las imágenes de las banderas desde la URL de internet de forma asíncrona
        if (pais.getUrlBandera() != null && !pais.getUrlBandera().isEmpty()) {
            Image image = new Image(pais.getUrlBandera(), true); // true = carga en segundo plano
            imgBanderaMini.setImage(image);
            imgBanderaGrande.setImage(image);
        } else {
            imgBanderaMini.setImage(null);
            imgBanderaGrande.setImage(null);
        }
    }
}