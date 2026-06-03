module com.mycompany.pruebaexamen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.pruebaexamen to javafx.fxml;
    exports com.mycompany.pruebaexamen;
}
