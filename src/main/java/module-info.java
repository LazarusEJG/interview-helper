module com.interview {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.interview to javafx.fxml;
    exports com.interview;
}
