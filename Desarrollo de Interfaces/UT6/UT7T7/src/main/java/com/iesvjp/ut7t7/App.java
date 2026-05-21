package com.iesvjp.ut7t7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/iesvjp/ut7t7/MainWindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        
        stage.setTitle("Gestor de Fotos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}