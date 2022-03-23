module sample.daytoursnyttsdk {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens sample.daytoursnyttsdk to javafx.fxml;
    exports sample.daytoursnyttsdk;
}