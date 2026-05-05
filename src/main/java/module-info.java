module com.emergencias {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.emergencias to javafx.fxml;
    opens com.emergencias.controller to javafx.fxml;
    opens com.emergencias.model to javafx.fxml;

    exports com.emergencias;
}