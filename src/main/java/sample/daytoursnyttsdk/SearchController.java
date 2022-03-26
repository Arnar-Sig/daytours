package sample.daytoursnyttsdk;

import java.net.URL;
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
    private CheckBox fxActivities0;
    @FXML
    private CheckBox fxActivities1;
    @FXML
    private CheckBox fxActivities2;
    @FXML
    private CheckBox fxActivities3;
    @FXML
    private CheckBox fxActivities4;

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

        /** Ná í öll activities yfir í fylki **/
        CheckBox[] box = {fxActivities0, fxActivities1, fxActivities2, fxActivities3, fxActivities4};
        ArrayList<String> activities = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            if(box[i].isSelected()){
                activities.add(box[i].getText());
            }
        }

        /** Búa til SearchModel út frá viðmótinu **/
        /*
        //Oþarfi?
        String dateFromString = fxDateFrom.getValue().toString();
        System.out.println(dateFromString);
        String[] temp = dateFromString.split("-");
        LocalDate rettDateFrom = LocalDate.of(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
        System.out.println(rettDateFrom);
         */


        SearchModel sm = new SearchModel(fxLocation.getText(), Integer.parseInt(fxMinDuration.getText()), Integer.parseInt(fxMaxDuration.getText()),
                Integer.parseInt(fxMinDifficulty.getText()), Integer.parseInt(fxMaxDifficulty.getText()), activities,
                Integer.parseInt(fxMinimumPrice.getText()), Integer.parseInt(fxMaximumPrice.getText()),
                Integer.parseInt(fxSpotsLeft.getText()), fxDateFrom.getValue(),
                fxDateTo.getValue(), fxHotelPickup.isSelected());

        try {
            /** Kalla á leitarfallið og uppfæra ListView **/
            ArrayList<String> utkoma = databaseConnection.searchDayTours(sm);

            for(int i=0; i< utkoma.size(); i++){
                fxListView.getItems().add(utkoma.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dt.updateSearchModel(sm);
    }
}

