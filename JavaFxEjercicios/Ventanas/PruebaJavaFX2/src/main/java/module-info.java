module com.mycompany.pruebajavafx2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.pruebajavafx2 to javafx.fxml;
    exports com.mycompany.pruebajavafx2;
}
