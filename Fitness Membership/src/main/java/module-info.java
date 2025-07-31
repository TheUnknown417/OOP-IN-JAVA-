module com.example.myfinalpractice {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.myfinalpractice to javafx.fxml;
    exports com.example.myfinalpractice;
}