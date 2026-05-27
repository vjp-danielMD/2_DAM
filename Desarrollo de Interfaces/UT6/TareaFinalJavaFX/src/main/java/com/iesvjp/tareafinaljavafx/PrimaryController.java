package com.iesvjp.tareafinaljavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Callback;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PrimaryController {

    @FXML
    private ComboBox<String> comboHamburguesa;
    @FXML
    private RadioButton radioIntegral, radioNormal, radioMollete;
    
    // Sin el @FXML
    private ToggleGroup groupPan; 
    
    @FXML
    private CheckBox checkQueso, checkBacon, checkHuevo;
    @FXML
    private ImageView imgHamburguesa;

    @FXML
    private TableView<PedidoItem> tablaPedido;
    @FXML
    private TableColumn<PedidoItem, String> colHamburguesa;
    @FXML
    private TableColumn<PedidoItem, String> colPan;
    @FXML
    private TableColumn<PedidoItem, String> colExtras;
    @FXML
    private TableColumn<PedidoItem, Double> colPrecio;

    private final ObservableList<PedidoItem> masterData = FXCollections.observableArrayList();

    private final String IMG_GRAN_CAPITAN = "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=400";
    private final String IMG_CLASICA = "https://images.unsplash.com/photo-1550547660-d9450f859349?w=400";
    private final String IMG_ESPECIAL = "https://images.unsplash.com/photo-1571091718767-18b5b1457add?w=400";

    @FXML
    public void initialize() {
        // Inicializar el ToggleGroup manualmente
        groupPan = new ToggleGroup();
        radioNormal.setToggleGroup(groupPan);
        radioIntegral.setToggleGroup(groupPan);
        radioMollete.setToggleGroup(groupPan);

        // Inicializar ComboBox
        comboHamburguesa.getItems().addAll("Gran Capitan", "Clásica", "Especial");
        comboHamburguesa.getSelectionModel().selectFirst();
        actualizarImagen(comboHamburguesa.getSelectionModel().getSelectedItem());

        comboHamburguesa.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obs, String oldVal, String newVal) {
                if (newVal != null) {
                    actualizarImagen(newVal);
                }
            }
        });

        // Configurar columnas de la Tabla
        colHamburguesa.setCellValueFactory(new PropertyValueFactory<>("hamburguesa"));
        colPan.setCellValueFactory(new PropertyValueFactory<>("pan"));
        colExtras.setCellValueFactory(new PropertyValueFactory<>("extras"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        colPrecio.setCellFactory(new Callback<TableColumn<PedidoItem, Double>, TableCell<PedidoItem, Double>>() {
            @Override
            public TableCell<PedidoItem, Double> call(TableColumn<PedidoItem, Double> param) {
                return new TableCell<PedidoItem, Double>() {
                    @Override
                    protected void updateItem(Double price, boolean empty) {
                        super.updateItem(price, empty);
                        if (empty || price == null) {
                            setText(null);
                        } else {
                            setText(String.format("%.2f €", price));
                        }
                    }
                };
            }
        });

        tablaPedido.setItems(masterData);
    }

    private void actualizarImagen(String nombreHamburguesa) {
        String url = "";
        switch (nombreHamburguesa) {
            case "Gran Capitan":
                url = IMG_GRAN_CAPITAN;
                break;
            case "Clásica":
                url = IMG_CLASICA;
                break;
            case "Especial":
                url = IMG_ESPECIAL;
                break;
            default:
                url = IMG_CLASICA;
                break;
        }
        imgHamburguesa.setImage(new Image(url, true));
    }

    @FXML
    private void handleAnadir() {
        String hamburguesa = comboHamburguesa.getValue();

        // Obtener Pan seleccionado usando la variable inicializada
        RadioButton seleccionado = (RadioButton) groupPan.getSelectedToggle();
        if (seleccionado == null) {
            mostrarAlerta("Error", "Por favor, selecciona un tipo de pan.");
            return;
        }
        String pan = seleccionado.getText();

        // Calcular base de precio según la hamburguesa
        double precioBase = 0.0;
        switch (hamburguesa) {
            case "Gran Capitan":
                precioBase = 6.50;
                break;
            case "Clásica":
                precioBase = 4.50;
                break;
            case "Especial":
                precioBase = 7.50;
                break;
        }

        // Calcular extras
        List<String> listaExtras = new ArrayList<>();
        if (checkQueso.isSelected()) {
            precioBase += 1.00;
            listaExtras.add("Queso");
        }
        if (checkBacon.isSelected()) {
            precioBase += 1.50;
            listaExtras.add("Bacon");
        }
        if (checkHuevo.isSelected()) {
            precioBase += 2.00;
            listaExtras.add("Huevo");
        }

        String extras;
        if (listaExtras.isEmpty()) {
            extras = "Ninguno";
        } else {
            extras = String.join(", ", listaExtras);
        }

        // Añadir ítem a la lista observable de la tabla
        masterData.add(new PedidoItem(hamburguesa, pan, extras, precioBase));

        // Resetear extras tras añadir
        checkQueso.setSelected(false);
        checkBacon.setSelected(false);
        checkHuevo.setSelected(false);
    }

    @FXML
    private void handleEliminar() {
        PedidoItem seleccionado = tablaPedido.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            masterData.remove(seleccionado);
        } else {
            mostrarAlerta("Atención", "Selecciona un elemento de la tabla para eliminarlo.");
        }
    }

    @FXML
    private void handleCobrar() {
        if (masterData.isEmpty()) {
            mostrarAlerta("Pedido Vacío", "No hay elementos en el pedido actual.");
            return;
        }

        double total = 0;
        StringBuilder contenidoTicket = new StringBuilder();
        contenidoTicket.append("========================================\n");
        contenidoTicket.append("        BOCATERÍA VALLE DEL JERTE       \n");
        contenidoTicket.append("========================================\n");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        contenidoTicket.append("Fecha: ").append(dtf.format(LocalDateTime.now())).append("\n\n");
        contenidoTicket.append(String.format("%-15s %-10s %-15s %-8s\n", "Burguer", "Pan", "Extras", "Precio"));
        contenidoTicket.append("----------------------------------------\n");

        for (PedidoItem item : masterData) {
            total += item.getPrecio();
            contenidoTicket.append(String.format("%-15s %-10s %-15s %.2f€\n",
                    item.getHamburguesa(), item.getPan(), item.getExtras(), item.getPrecio()));
        }

        contenidoTicket.append("----------------------------------------\n");
        contenidoTicket.append(String.format("TOTAL A PAGAR: %.2f €\n", total));
        contenidoTicket.append("========================================\n");

        // 1. Guardar en fichero TXT
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pedido_actual.txt"))) {
            writer.write(contenidoTicket.toString());
        } catch (IOException e) {
            mostrarAlerta("Error de Guardado", "No se pudo guardar el archivo txt.");
        }

        // 2. Mostrar nueva ventana emergente modalizada
        mostrarVentanaCobro(contenidoTicket.toString());

        // 3. Al cerrarse la ventana (la ejecución del método espera hasta que se cierre el modal), limpiar la tabla
        masterData.clear();
    }

    private void mostrarVentanaCobro(String ticket) {
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.setTitle("Resumen de Cuenta / Ticket");

        TextArea txtTicket = new TextArea(ticket);
        txtTicket.setEditable(false);
        txtTicket.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 13px;");

        Button btnCerrar = new Button("Finalizar y Nuevo Pedido");
        btnCerrar.getStyleClass().add("btn-cobrar");
        
        // SIN LAMBDA: Evento de botón clásico
        btnCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                modalStage.close();
            }
        });

        VBox layout = new VBox(15, txtTicket, btnCerrar);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20px; -fx-background-color: #f4f4f9;");

        Scene scene = new Scene(layout, 420, 480);
        
        // Carga el CSS (Si no tienes el archivo styles.css en la misma carpeta, comenta esta línea)
        if (App.class.getResource("styles.css") != null) {
            scene.getStylesheets().add(App.class.getResource("styles.css").toExternalForm());
        }
        
        modalStage.setScene(scene);
        modalStage.showAndWait(); // Pausa la ejecución hasta cerrar esta ventana
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}