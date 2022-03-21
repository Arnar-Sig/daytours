package sample;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
/***********************************************************
 * Lýsing:
 *
 *
 ***********************************************************/
public class SearchController implements Initializable {

    @FXML
    private Slider fxPrice;

    @FXML
    private Slider fxDifficulty;

    @FXML
    private Slider fxDuration;

    @FXML
    private Label fxPriceText;

    @FXML
    private Button fxBook;

    @FXML
    private ChoiceBox fxSortBy;

    // geymslubreytur
    private DB databaseConnection;
    private DayTours dt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseConnection = new DB();
        /*
        try {
            databaseTest.tengjastDB();
        } catch (Exception e) {
            e.printStackTrace();
        }

         */

    }

    public void searchButton(ActionEvent actionEvent) {
        dt = new DayTours(1);
        // To-do: Tengja breytur í searchmodelinu við fxHlutina.

        Date fra = new Date(122, 4, 1);
        Date til = new Date(122, 4, 5);
        SearchModel sm = new SearchModel("Keilir", 0,
                300, 0, 5, "Gonguferd",
                0, 10000, 1, fra,
                til, false);
        try {
            databaseConnection.searchDayTours(sm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dt.updateSearchModel(sm);
        //"2022-04-03 17:00:000"
    }
}

