package sample.daytoursnyttsdk;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayTours {
    private ArrayList<DayTour> currDayTours;
    private SearchModel searchModel;
    private String sort;

    public DayTours(String rodun){
        sort = rodun;
    }
    public void updateSorting(String rodun){ sort = rodun; }

    /**
     * @param sm SearchModel hlutur sem inniheldur þau gögn sem leita á eftir í gagnagrunni.
     */
    public void updateSearchModel(SearchModel sm){
        searchModel = sm;
    }

    /**
     * @param sm = SearchModel hlutur sem inniheldur þau gögn sem leita á eftir í gagnagrunni.
     * @return ArrayList<DayTour> fylki af DayTour hlutum sem fundust í gagnagrunni.
     *         Uppfærir currDayTours fylkið með útkomu.
     */
    public ArrayList<DayTour> getDayTours(SearchModel sm){
        ArrayList<DayTour> utkoma = new ArrayList<DayTour>();
        DB dbConnection = new DB();
        try {
            utkoma = dbConnection.getDayToursDatabase(sm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        currDayTours = utkoma;
        return utkoma;
    }
}
