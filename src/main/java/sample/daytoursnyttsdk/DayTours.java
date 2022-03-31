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
        ArrayList<String> m = new ArrayList<String>();
        ArrayList<DayTour> utkoma = new ArrayList<DayTour>();
        DB dbConnection = new DB();
        try {
            m = dbConnection.getDayToursDatabase(sm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i = 0; i < m.size(); i++){
            List<String> list = Arrays.asList(m.get(i).split("\\s*,\\s*"));

            //TEMP gervigögn - breyta þegar Participant er útfært
            Participant dummy = new Participant("test", "test", "test", "test", 5);
            ArrayList<Participant> dummyFylki = new ArrayList<>();
            dummyFylki.add(dummy);
            //TEMP gervigögn

            DayTour temp = new DayTour(list.get(0), list.get(3), Integer.parseInt(list.get(8)), LocalDate.parse(list.get(1)),
                    Integer.parseInt(list.get(4)), Integer.parseInt(list.get(2)), list.get(5), Integer.parseInt(list.get(6)),
                    Integer.parseInt(list.get(7)), dummyFylki, Integer.parseInt(list.get(9)));
            utkoma.add(temp);

        }
        currDayTours = utkoma;
        return utkoma;
    }
}
