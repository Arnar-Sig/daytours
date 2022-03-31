package sample.daytoursnyttsdk;

import java.net.URL;
import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private ChoiceBox fxSort;
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
    private ArrayList<TextField> numSearchParams;
    private LocalDate defaultDateFrom;
    private LocalDate defaultDateTo;

    // Constants
    static final int DEFAULT_MIN = 0;
    static final int PRICE_MAX = 1000000;
    static final int DIFFICULTY_MAX = 5;
    static final int DURATION_MAX = 888;
    static final ArrayList<String> defaultActivities = new ArrayList<>(
            Arrays.asList("Gonguferd", "Hjolaferd")
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseConnection = new DB();
        numSearchParams = new ArrayList<>();
        Collections.addAll(numSearchParams,
                fxSpotsLeft,
                fxMinDuration, fxMinDifficulty, fxMinimumPrice,
                fxMaxDuration, fxMaxDifficulty, fxMaximumPrice);
        defaultDateFrom = LocalDate.now();
        defaultDateTo = defaultDateFrom.plusYears(1);

        fxSort.getItems().addAll("Date", "Name", "Price");
        fxSort.setValue("Date");
        fxSort.setOnAction((event -> {
            String s = fxSort.getValue().toString();
            dt.updateSort(s);
        }));
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
        String sorting = fxSort.getValue().toString();
        dt = new DayTours(sorting);

        /** Ná í öll activities yfir í fylki **/
        CheckBox[] box = {fxActivities0, fxActivities1, fxActivities2, fxActivities3, fxActivities4};
        ArrayList<String> activities = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            if(box[i].isSelected()){
                activities.add(box[i].getText());
            }
        }
        /** Ef ekkert activity er valið: Birta öll activity **/
        if (activities.size() == 0) {
            activities = defaultActivities;
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
        /** Sækja töluleg gildi úr textareitum **/
        int num_params[] = getNumParams();

        /** Ef ekkert Date valið: Stinga inn default gildum **/
        LocalDate from = (fxDateFrom.getValue() == null) ? defaultDateFrom : fxDateFrom.getValue();
        LocalDate to = (fxDateTo.getValue() == null) ? defaultDateTo : fxDateTo.getValue();

        // num_params[] geymir töluleg gögn úr viðmótshlutum (eða sjálfgefin gildi) í eftirfarandi röð:
        // num_params[SpotsLeft, MinDuration, MinDifficulty, MinimumPrice, MaxDuration, MaxDifficulty, MaximumPrice]
        dt.updateSearchModel(fxLocation.getText(), num_params[1], num_params[4],
                num_params[2], num_params[5], activities,
                num_params[3], num_params[6],
                num_params[0], from,
                to, fxHotelPickup.isSelected());

        SearchModel sm = dt.getSearchModel();
        ArrayList<DayTour> utkoma = new ArrayList<>();
        try {
            /** Kalla á leitarfallið **/
            utkoma = databaseConnection.searchDayTours(sm);

        } catch (Exception e) {
            e.printStackTrace();
        }

        dt.updateDayTours(utkoma);
        updateUI();
    }

    private void updateUI() {
        ArrayList<String> tourDescriptions = dt.getDayTourDescriptions();
        for (int i = 0; i < tourDescriptions.size(); i++) {
            fxListView.getItems().add(tourDescriptions.get(i));
        }
    }

    // Sækir töluleg gögn úr TextFields. Ef strengur er tómur þá er
    // skilað sjálfgefnu há- eða lággildi
    private int[] getNumParams() {
        int p[] = new int[7];
        for (int i = 0; i < numSearchParams.size(); i++) {
            TextField text = numSearchParams.get(i);
            if (!text.getText().isEmpty()) {
                p[i] = Integer.parseInt(text.getText());
            }
            else {
                if (i < 4) {
                    p[i] = DEFAULT_MIN;
                }
                else if (i == 4) {
                    p[i] = DURATION_MAX;
                }
                else if (i == 5){
                    p[i] = DIFFICULTY_MAX;
                }
                else {
                    p[i] = PRICE_MAX;
                }
            }
        }
        return p;
    }
}

