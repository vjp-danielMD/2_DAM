module com.mycompany.prueba1javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.prueba1javafx to javafx.fxml;
    exports com.mycompany.prueba1javafx;
}
