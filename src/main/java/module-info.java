module sample.daytoursnyttsdk {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires junit;


    opens sample.daytoursnyttsdk to javafx.fxml;
    exports sample.daytoursnyttsdk;
}