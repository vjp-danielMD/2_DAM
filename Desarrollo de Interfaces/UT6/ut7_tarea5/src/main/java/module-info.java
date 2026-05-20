module com.iesvjp.ut7_tarea5 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.iesvjp.ut7_tarea5 to javafx.fxml;
    exports com.iesvjp.ut7_tarea5;
}
