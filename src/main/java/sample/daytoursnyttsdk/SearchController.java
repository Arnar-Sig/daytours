package sample.daytoursnyttsdk;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
    private RadioButton fxSortByNafn;
    @FXML
    private RadioButton fxSortByDate;
    @FXML
    private TextField fxSpotsLeft;
    @FXML
    private ListView fxListView;
    @FXML
    private DatePicker fxDateFrom;
    @FXML
    private DatePicker fxDateTo;
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
        /*
        LocalDate u = LocalDate.now();
        System.out.println(u);

         */

        /*
        //TEST KÓÐI FYRIR LEIT I GAGNAGRUNNI AN VIDMOTS
        SearchModel sm = new SearchModel("Keilir", 0,
                300, 0, 5, "Gonguferd",
                0, 10000, 1, LocalDate.of(2020, 2, 1),
                LocalDate.of(2023, 6, 1), false);
        dt = new DayTours("Date");
        ArrayList<DayTour> ut = dt.getDayTours(sm);
        for(DayTour x : ut){
            System.out.println(x.getDate());
        }
         */
    }

    public void searchButton(ActionEvent actionEvent) {
        /** Taka út það sem var í ListView fyrir **/
        ArrayList<String> resetter = new ArrayList<>();
        fxListView.getItems().setAll(resetter);

        /** Finna út eftir hverju á að raða **/
        String sorting = "Date";
        if (fxSortByNafn.isSelected()){
            sorting = "Name";
        }
        dt = new DayTours(sorting);
        /*
        // To-do:
        //        Ná í öll activities í fylki, ekki bara checkbox1
        //        Laga Date þannig að það nái í dagsetningu úr viðmótshlut
        Date fra = new Date(122, 4, 1);
        Date til = new Date(122, 4, 5);
         */

        /** Búa til SearchModel út frá viðmótinu **/
        SearchModel sm = new SearchModel(fxLocation.getText(), Integer.parseInt(fxMinDuration.getText()), Integer.parseInt(fxMaxDuration.getText()),
                Integer.parseInt(fxMinDifficulty.getText()), Integer.parseInt(fxMaxDifficulty.getText()), fxActivities1.getText(),
                Integer.parseInt(fxMinimumPrice.getText()), Integer.parseInt(fxMaximumPrice.getText()),
                Integer.parseInt(fxSpotsLeft.getText()), fxDateFrom.getValue(),
                fxDateTo.getValue(), fxHotelPickup.isSelected());
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
            /** Kalla á leitarfallið og uppfæra ListView **/
            ArrayList<String> utkoma = databaseConnection.searchDayTours(sm);

            for(int i=0; i< utkoma.size(); i++){
                fxListView.getItems().add(utkoma);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dt.updateSearchModel(sm);
    }
}

