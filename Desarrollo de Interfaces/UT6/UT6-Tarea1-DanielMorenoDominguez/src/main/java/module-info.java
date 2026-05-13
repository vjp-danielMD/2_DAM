module com.iesvjp.prueba1_javafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.iesvjp.prueba1_javafx to javafx.fxml; 
    exports com.iesvjp.prueba1_javafx;
}