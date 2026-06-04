module com.iesvjp.repaso {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.iesvjp.repaso to javafx.fxml;
    exports com.iesvjp.repaso;
}
