module com.iesvjp.ut7t7 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.iesvjp.ut7t7 to javafx.fxml;
    opens com.iesvjp.ut7t7.controlador to javafx.fxml;
    opens com.iesvjp.ut7t7.modelo to javafx.base; 
    
    exports com.iesvjp.ut7t7;
}