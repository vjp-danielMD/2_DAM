module com.iesvjp.ut6.tarea3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.iesvjp.ut6.tarea3 to javafx.fxml;
    exports com.iesvjp.ut6.tarea3;
}
