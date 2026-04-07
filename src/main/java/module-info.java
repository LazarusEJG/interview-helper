module com.interview {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires junit;

    opens com.interview to javafx.fxml;
    exports com.interview;

    opens com.model to javafx.fxml;
    exports com.model;

    opens com.controllers to javafx.fxml;
}
