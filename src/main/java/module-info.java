module com.example.pokerjawa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    exports com.example.pokerjawa;
    opens com.example.pokerjawa to javafx.fxml;
}