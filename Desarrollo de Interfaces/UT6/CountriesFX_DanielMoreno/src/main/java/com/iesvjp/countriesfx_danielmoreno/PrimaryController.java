package com.iesvjp.countriesfx_danielmoreno;

import com.iesvjp.countriesfx_danielmoreno.model.Country;
import com.iesvjp.countriesfx_danielmoreno.service.ApiService;
import javafx.application.Platform;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;

public class PrimaryController {

    @FXML private TextField txtSearch;
    @FXML private ListView<Country> listCountries;
    @FXML private Label lblName, lblCapital, lblRegion, lblSubregion, lblPopulation, lblCurrencies, lblLanguages, lblStatus;
    @FXML private ImageView imgFlagLarge;

    private ObservableList<Country> allCountries = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarCeldaLista();
        loadData();
    }

    private void configurarCeldaLista() {
        listCountries.setCellFactory(lv -> new ListCell<>() {
            private final ImageView iv = new ImageView();
            @Override
            protected void updateItem(Country c, boolean empty) {
                super.updateItem(c, empty);
                if (empty || c == null) { setText(null); setGraphic(null); }
                else {
                    setText(c.getNameEs());
                    iv.setFitHeight(15); iv.setPreserveRatio(true);
                    iv.setImage(new Image(c.getFlagUrl(), true));
                    setGraphic(iv);
                }
            }
        });
    }

    private void loadData() {
        lblStatus.setText("Cargando países...");
        new Thread(() -> {
            try {
                var list = ApiService.getCountries();
                Platform.runLater(() -> {
                    allCountries.setAll(list);
                    setupSearch();
                    lblStatus.setText("Datos cargados correctamente.");
                    lblStatus.setStyle("-fx-text-fill: blue;");
                });
            } catch (Exception e) {
                Platform.runLater(() -> {
                    lblStatus.setText("Error al conectar.");
                    lblStatus.setStyle("-fx-text-fill: red;");
                });
            }
        }).start();
    }

    private void setupSearch() {
        FilteredList<Country> filteredData = new FilteredList<>(allCountries, p -> true);
        txtSearch.textProperty().addListener((obs, oldV, newV) -> {
            filteredData.setPredicate(c -> {
                if (newV == null || newV.isEmpty()) return true;
                return c.getNameEs().toLowerCase().contains(newV.toLowerCase());
            });
        });
        listCountries.setItems(filteredData);
        listCountries.getSelectionModel().selectedItemProperty().addListener((o, old, n) -> {
            if (n != null) showDetails(n);
        });
    }

    private void showDetails(Country c) {
        lblName.setText(c.getNameEs());
        lblCapital.setText("Capital: " + c.getCapital());
        lblRegion.setText("Región: " + c.getRegion());
        lblSubregion.setText("Subregión: " + c.getSubregion());
        lblPopulation.setText("Población: " + String.format("%,d", c.getPopulation()));
        lblCurrencies.setText("Moneda(s): " + c.getCurrenciesAsString());
        lblLanguages.setText("Idioma(s): " + c.getLanguagesAsString());
        imgFlagLarge.setImage(new Image(c.getFlagUrl(), true));
    }
}