package com.mycompany.ut7.tareafinal.bocadillos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController {

    public List<String> productos = new ArrayList<>();

    @FXML
    private ToggleGroup pan;
    @FXML
    private RadioButton radioIntegral;
    @FXML
    private RadioButton radioNormal;
    @FXML
    private RadioButton radioMollete;
    @FXML
    private CheckBox chQueso;
    @FXML
    private CheckBox chBacon;
    @FXML
    private CheckBox chHuevo;
    @FXML
    private ComboBox listaHamburguesas;
    @FXML
    private TableView tabla;
    @FXML
    private TableColumn colHamburguesa;
    @FXML
    private TableColumn colPan;
    @FXML
    private TableColumn colExtras;
    @FXML
    private TableColumn colPercio;
    @FXML
    private ImageView imgBocadillo;
    private ObservableList<Hamburguesas> listaBurguer;

    private float precioExtras;
    @FXML
    private TextField campoPrecioFinal;

    public void initialize() {
        rellenarBocadillos();
        this.imgBocadillo.setImage(new Image(aplicarUrl("Infierno")));

        listaBurguer = FXCollections.observableArrayList();
        this.tabla.setItems(listaBurguer);

        this.colHamburguesa.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colPan.setCellValueFactory(new PropertyValueFactory<>("pan"));
        this.colExtras.setCellValueFactory(new PropertyValueFactory<>("extras"));
        this.colPercio.setCellValueFactory(new PropertyValueFactory<>("precio"));
    }

    public float hambInfiernos = 8.50f;
    public float hambJerte = 10.50f;
    public float hambDehesa = 11.0f;
    public float precioFinal;
    public float CostePedido = 0.0f;
    public Hamburguesas ham;

    @FXML
    private void addPedido(ActionEvent event) {
        precioExtras = 0.0f;

        String nombre = (String) this.listaHamburguesas.getSelectionModel().getSelectedItem();
        String pan = ((RadioButton) this.pan.getSelectedToggle()).getText();
        String extras = "";

        if (this.chBacon.isSelected()) {
            extras += "Bacon, ";
            precioExtras += 1.5f;
        }
        if (this.chHuevo.isSelected()) {
            extras += "Huevo, ";
            precioExtras += 2.0f;
        }
        if (this.chQueso.isSelected()) {
            extras += "Queso, ";
            precioExtras += 1.0f;
        }

        if (nombre.contains("Infierno")) {
            this.precioFinal += this.hambInfiernos;
        } else if (nombre.contains("Jerte")) {
            this.precioFinal += this.hambJerte;
        } else if ((nombre.contains("Dehesa"))) {
            this.precioFinal += this.hambDehesa;
        } else {
            this.precioFinal += 0.0f;
        }

        this.precioFinal += this.precioExtras;

        Hamburguesas ham = new Hamburguesas(nombre, pan, extras, precioFinal);
        this.listaBurguer.add(ham);
        this.tabla.refresh();
        this.CostePedido += this.precioFinal;
        this.campoPrecioFinal.setText(this.CostePedido + "");
        this.precioFinal = 0.0f;

    }

    @FXML
    private void eliminarPedido(ActionEvent event) {
        this.listaBurguer.clear();

        this.CostePedido = 0.0f;

        this.campoPrecioFinal.setText(this.CostePedido + "");;
    }

    @FXML
    private void cobrar(ActionEvent event) {
    if (this.listaBurguer.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Atención");
            alert.setContentText("No hay ningún elemento añadido a la tabla");
            alert.showAndWait();
        return;
    }

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();

        SecondaryController controladorSecundario = loader.getController();

        controladorSecundario.initData(this.listaBurguer, this.CostePedido);

        Stage stage = new Stage();
        stage.setTitle("Resumen del Pedido");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((javafx.scene.Node) event.getSource()).getScene().getWindow());
        
        stage.showAndWait();

        this.listaBurguer.clear();
        this.CostePedido = 0.0f;
        this.campoPrecioFinal.setText(this.CostePedido + "");
        this.precioFinal = 0.0f;
        
    } catch (IOException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Atención");
            alert.setContentText("No hay ningún elemento añadido a la tabla");
            alert.showAndWait();
        e.printStackTrace();
    }
    }

    public void rellenarBocadillos() {

        productos.add("Hamburguesa Infiernos" + " - " + hambInfiernos + "€");
        productos.add("Hamburguesa Del Jerte" + " - " + hambJerte + "€");
        productos.add("Hamburguesa Dehesa" + " - " + hambDehesa + "€");

        listaHamburguesas.getItems().setAll(productos);

        listaHamburguesas.getSelectionModel().selectFirst();
    }

    @FXML
    private void pnerImg(ActionEvent event) {

        String productoSeleccionado = (String) this.listaHamburguesas.getSelectionModel().getSelectedItem();
        String urlImagen = aplicarUrl(productoSeleccionado);
        this.imgBocadillo.setImage(new Image(urlImagen));

    }

    public String aplicarUrl(String producto) {

        if (producto.contains("Infierno")) {
            return "https://cdn.pixabay.com/photo/2017/04/23/09/02/hamburger-2253345_1280.jpg";

        } else if (producto.contains("Jerte")) {
            return "https://cdn.pixabay.com/photo/2021/01/26/16/29/burguer-closeup-5952157_1280.jpg";
        } else if ((producto.contains("Dehesa"))) {
            return "https://cdn.pixabay.com/photo/2017/04/23/09/02/hamburger-2253344_1280.jpg";
        } else {
            return "";
        }

    }

}
