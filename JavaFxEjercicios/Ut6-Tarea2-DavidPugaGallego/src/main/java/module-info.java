module com.mycompany.ut6.tarea2.davidpugagallego {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.ut6.tarea2.davidpugagallego to javafx.fxml;
    exports com.mycompany.ut6.tarea2.davidpugagallego;
}
