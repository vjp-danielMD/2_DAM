module com.mycompany.ut7.tareafinal.bocadillos {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.ut7.tareafinal.bocadillos to javafx.fxml;
    exports com.mycompany.ut7.tareafinal.bocadillos;
}
