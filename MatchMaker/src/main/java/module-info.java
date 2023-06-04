module com.example.matchmaker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.matchmaker to javafx.fxml;
    exports com.example.matchmaker;
}