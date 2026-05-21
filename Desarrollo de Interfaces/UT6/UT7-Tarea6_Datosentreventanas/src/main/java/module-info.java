module com.iesvjp.ut7.tarea6_datosentreventanas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.iesvjp.ut7.tarea6_datosentreventanas to javafx.fxml;

    opens com.iesvjp.ut7.tarea6_datosentreventanas.controlador to javafx.fxml;
    
    opens com.iesvjp.ut7.tarea6_datosentreventanas.modelo to javafx.base; 

    exports com.iesvjp.ut7.tarea6_datosentreventanas;
}