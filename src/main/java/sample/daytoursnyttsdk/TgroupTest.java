package sample.daytoursnyttsdk;

import java.time.LocalDate;
import java.util.ArrayList;

public class TgroupTest {
    public static void main(String[] args) {
        DayTours dt = new DayTours("Date");
        ArrayList<String> activities = new ArrayList<>(); activities.add("Hjolaferd"); activities.add("Gonguferd");
        SearchModel sm = new SearchModel("Reykjavik", 0, 10000, 0, 555, activities,
                0, 500000,5, LocalDate.of(2020,1, 1),
                LocalDate.of(2025, 3, 3), false);
        dt.getDayTours(sm);
        ArrayList<DayTour> list = dt.getDayTourList();
        ArrayList<String> descript = dt.getDayTourDescriptions();
        for(String x: descript){
            System.out.println(x);
        }
        DayTour sample1 = dt.getDayTourList().get(1);
        ArrayList<Participant> users = sample1.getParticipants();
        for(Participant x: users){
            System.out.println(x.getName());
        }
    }
}
