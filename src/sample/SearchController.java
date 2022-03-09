package sample;

import java.net.URL;
import java.util.ResourceBundle;
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

    @FXML
    private DatePicker fxDateFrom;

    @FXML
    private DatePicker fxDateTo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
