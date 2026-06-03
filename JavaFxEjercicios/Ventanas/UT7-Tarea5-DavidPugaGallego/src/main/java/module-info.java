module com.mycompany.ut7.tarea5.davidpugagallego {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.ut7.tarea5.davidpugagallego to javafx.fxml;
    exports com.mycompany.ut7.tarea5.davidpugagallego;
}
