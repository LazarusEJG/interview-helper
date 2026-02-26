module com.interview {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens com.interview to javafx.fxml;
    exports com.interview;
}
