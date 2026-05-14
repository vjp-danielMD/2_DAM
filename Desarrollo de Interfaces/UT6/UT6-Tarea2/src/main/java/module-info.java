module com.iesvjp.ut6.tarea2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.iesvjp.ut6.tarea2 to javafx.fxml;
    exports com.iesvjp.ut6.tarea2;
}
