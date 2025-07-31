module com.example.idgenerator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.idgenerator to javafx.fxml;
    exports com.example.idgenerator;
}