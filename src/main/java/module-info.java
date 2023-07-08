module com.example.tramitec {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;
    requires java.sql;
    requires commons.dbcp2;
    requires junit;

    opens com.example.tramitec to javafx.fxml;
    exports com.example.tramitec;
    exports com.example.tramitec.controllers;
    opens com.example.tramitec.controllers to javafx.fxml;
}