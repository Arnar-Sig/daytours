module project8d.daytours {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens project8d.daytours to javafx.fxml;
    exports project8d.daytours;
}