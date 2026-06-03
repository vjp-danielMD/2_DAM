package com.mycompany.ut7.tareafinal.bocadillos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class SecondaryController {

    @FXML
    private TextArea textAreaPedido;

    public void initData(ObservableList<Hamburguesas> listaBurguer, float costeTotal) {
        this.textAreaPedido.clear();

        try (FileWriter fw = new FileWriter("pedido.txt");
             PrintWriter pw = new PrintWriter(fw)) {
            
            pw.println("=== RESUMEN DEL PEDIDO ===");
            this.textAreaPedido.appendText("=== RESUMEN DEL PEDIDO ===\n");
            
            pw.println("");
            this.textAreaPedido.appendText("\n");

            for (Hamburguesas h : listaBurguer) {
                pw.println("- " + h.getNombre());
                this.textAreaPedido.appendText("- " + h.getNombre() + "\n");
                
                pw.println("  Pan: " + h.getPan());
                this.textAreaPedido.appendText("  Pan: " + h.getPan() + "\n");
                
                if (h.getExtras() != null && !h.getExtras().isEmpty()) {
                    pw.println("  Extras: " + h.getExtras());
                    this.textAreaPedido.appendText("  Extras: " + h.getExtras() + "\n");
                }
                
                pw.println("  Precio: " + h.getPrecio() + "€");
                this.textAreaPedido.appendText("  Precio: " + h.getPrecio() + "€\n");
                
                pw.println("---------------------------");
                this.textAreaPedido.appendText("---------------------------\n");
            }

            pw.println("");
            this.textAreaPedido.appendText("\n");
            
            pw.println("PRECIO TOTAL: " + costeTotal + "€");
            this.textAreaPedido.appendText("PRECIO TOTAL: " + costeTotal + "€\n");

        } catch (IOException e) {
            System.out.println("Error al escribir el pedido en el fichero.");
            e.printStackTrace();
        }
    }

    @FXML
    private void cerrar(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}