package sample;

import java.net.URL;
import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/***********************************************************
 * Lýsing:
 *
 *
 ***********************************************************/
public class SearchController implements Initializable {

    // Viðmótshlutir
    @FXML
    public VBox fxSearchParameters;
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
    private ArrayList<TextField> numSearchParams;

    // Constants
    static final int DEFAULT_MIN = 0;
    static final int PRICE_MAX = 1000000;
    static final int DIFFICULTY_MAX = 5;
    static final int DURATION_MAX = 888;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseConnection = new DB();
        numSearchParams = new ArrayList<>();
        Collections.addAll(numSearchParams,
                fxSpotsLeft,
                fxMinDuration, fxMinDifficulty, fxMinimumPrice,
                fxMaxDuration, fxMaxDifficulty, fxMaximumPrice);

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

        // BINNI NOTES - HENDA ÚT
        // fxObject.getText().isEmpty() getur tékkað hvort textField er tómur

        int num_params[] = getNumParams();

        SearchModel sm = new SearchModel(fxLocation.getText(), num_params[1], num_params[4],
                 num_params[2], num_params[5], fxActivities1.getText(), num_params[3],
                num_params[6], num_params[0], fra, til, fxHotelPickup.isSelected());
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

    // Sækir gögn úr TextFields. Ef strengur er tómur þá er
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