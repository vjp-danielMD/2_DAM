module com.iesvjp.tareafinaljavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.iesvjp.tareafinaljavafx to javafx.fxml;
    exports com.iesvjp.tareafinaljavafx;
}
