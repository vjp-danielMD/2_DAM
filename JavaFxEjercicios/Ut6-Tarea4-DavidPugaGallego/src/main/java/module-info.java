module com.mycompany.ut6.tarea4.davidpugagallego {
    // Módulos necesarios para JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    
    // Módulo nativo de Java para hacer peticiones web
    requires java.net.http;
    
    // Librería externa para manejar JSON (¡Ojo al nombre correcto para el sistema de módulos!)
    requires org.json;

    // Permite a JavaFX acceder a las vistas y controladores
    opens com.mycompany.ut6.tarea4.davidpugagallego to javafx.fxml;
    exports com.mycompany.ut6.tarea4.davidpugagallego;
}