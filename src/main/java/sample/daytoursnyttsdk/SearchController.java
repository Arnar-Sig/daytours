package sample.daytoursnyttsdk;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/***********************************************************
 * Lýsing:
 *
 *
 ***********************************************************/
public class SearchController implements Initializable {

    @FXML
    public TextField fxSpotsLeft;
    @FXML
    public ListView fxListView;
    @FXML
    private DatePicker fxDate;
    @FXML
    private TextField fxLocation;
    @FXML
    private TextField fxMinimumPrice;
    @FXML
    private TextField fxMaximumPrice;
    @FXML
    private TextField fxMinDifficulty;
    @FXML
    private TextField fxMaxDifficulty;
    @FXML
    private TextField fxMinDuration;
    @FXML
    private TextField fxMaxDuration;
    @FXML
    private CheckBox fxHotelPickup;
    @FXML
    private CheckBox fxActivities1;
    @FXML
    private CheckBox fxActivities2;
    @FXML
    private CheckBox fxActivities3;
    @FXML
    private CheckBox fxActivities4;
    @FXML
    private CheckBox fxActivities5;

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
        // To-do:
        //        Ná í öll activities í fylki, ekki bara checkbox1
        //        Laga Date þannig að það nái í dagsetningu úr viðmótshlut

        Date fra = new Date(122, 4, 1);
        Date til = new Date(122, 4, 5);
        SearchModel sm = new SearchModel(fxLocation.getText(), Integer.parseInt(fxMinDuration.getText()), Integer.parseInt(fxMaxDuration.getText()),
                 Integer.parseInt(fxMinDifficulty.getText()), Integer.parseInt(fxMaxDifficulty.getText()), fxActivities1.getText(), Integer.parseInt(fxMinimumPrice.getText()),
                 Integer.parseInt(fxMaximumPrice.getText()), Integer.parseInt(fxSpotsLeft.getText()), fra, til, fxHotelPickup.isSelected());

        /*
        //SEARCHMODEL DÆMI SEM VIRKAR
        Date fra = new Date(122, 4, 1);
        Date til = new Date(122, 4, 5);
        SearchModel sm = new SearchModel("Keilir", 0,
                300, 0, 5, "Gonguferd",
                0, 10000, 1, fra,
                til, false);
        */
        try {
            ArrayList<String> utkoma = databaseConnection.searchDayTours(sm);
            for(int i=0; i< utkoma.size(); i++){
                fxListView.getItems().add(utkoma);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        dt.updateSearchModel(sm);
        //"2022-04-03 17:00:000"
    }
}

