package sample.daytoursnyttsdk;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayTours {
    DayTour dayTour[];
    SearchModel searchModel;
    int sort;

    public DayTours(int rodun){
        sort = rodun;

    }

    public void updateSearchModel(SearchModel sm){
        searchModel = sm;
    }

    public ArrayList<DayTour> getDayTours(SearchModel sm){
        ArrayList<String> m = new ArrayList<String>();
        ArrayList<DayTour> utkoma = new ArrayList<DayTour>();
        if (sm == null){
            System.out.println("Setja þarf SearchModel hlut sem breytu í fallið.");
            return utkoma;
        }
        DB dbConnection = new DB();
        try {
            m = dbConnection.searchDayTours(sm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i = 0; i < m.size(); i++){
            List<String> list = Arrays.asList(m.get(i).split("\\s*,\\s*"));

            //TEMP gervigögn - breyta þegar Participant er útfært
            Participant dummy = new Participant("test", "test", "test", "test", 5);
            Participant[] dummyFylki = new Participant[1];
            dummyFylki[0] = dummy;
            Date dummyDate = new Date(122, 4, 1);
            //TEMP gervigögn


            DayTour temp = new DayTour(list.get(0), list.get(3), Integer.parseInt(list.get(8)), dummyDate,
                    Integer.parseInt(list.get(4)), Integer.parseInt(list.get(2)), list.get(5), Integer.parseInt(list.get(6)),
                    Integer.parseInt(list.get(7)), dummyFylki );
            utkoma.add(temp);

        }
        return utkoma;
    }
}
