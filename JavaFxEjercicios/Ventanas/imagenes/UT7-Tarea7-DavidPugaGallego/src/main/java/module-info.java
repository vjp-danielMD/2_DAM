module com.mycompany.ut7.tarea7.davidpugagallego {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.ut7.tarea7.davidpugagallego to javafx.fxml;
    exports com.mycompany.ut7.tarea7.davidpugagallego;
}
