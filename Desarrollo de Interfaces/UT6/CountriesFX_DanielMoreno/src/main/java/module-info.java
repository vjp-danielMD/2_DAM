module com.iesvjp.countriesfx_danielmoreno {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    opens com.iesvjp.countriesfx_danielmoreno to javafx.fxml;
    opens com.iesvjp.countriesfx_danielmoreno.model to com.google.gson;
    
    exports com.iesvjp.countriesfx_danielmoreno;
}