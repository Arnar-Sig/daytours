package sample.daytoursnyttsdk;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TgroupTest {
    public static void main(String[] args) throws ClassNotFoundException {

        /** Búa til DayTours object og möndla með það **/
        /*
        DayTours dt = new DayTours("Date");
        ArrayList<String> activities = new ArrayList<>(); activities.add("Hjolaferd"); activities.add("Gonguferd");
        SearchModel sm = new SearchModel("Reykjavik", 0, 10000, 0, 555, activities,
                0, 500000,5, LocalDate.of(2020,1, 1),
                LocalDate.of(2025, 3, 3), false);
        // Ná í daytours úr gagnagrunni
        dt.getDayTours(sm);
        ArrayList<DayTour> list = dt.getDayTourList();
        ArrayList<String> descript = dt.getDayTourDescriptions();
        for(String x: descript){
            System.out.println(x);
        }
        DayTour sample1 = dt.getDayTourList().get(1);
         */

        /* Bæta við nýjum participants
        Participant nr1 = new Participant("Lucifer", "5661818", "satan@hell.com", "0501002020", sample1.getID());
        Participant nr2 = new Participant("Kleina", "5661444", "Klein@yahoo.com", "0311552019", sample1.getID());
        ArrayList<Participant> nyirMedlimir = new ArrayList<>(); nyirMedlimir.add(nr1); nyirMedlimir.add(nr2);
        sample1.addParticipants(nyirMedlimir);
         */

        /*
        ArrayList<Participant> users = sample1.getParticipants();
        for(Participant x: users){
            System.out.println(x.getName());
        }
         */




        /** Til að búa til gögn í SQL töflu **/
        /*

        LocalDate start = LocalDate.of(2022, 5, 1);
        LocalDate end = LocalDate.of(2022, 8, 1);
        List<LocalDate> gagnagrunnsdagsetningafylki = Stream.iterate(start, date -> date.plusDays(1)).limit(ChronoUnit.DAYS.between(start, end))
                .collect(Collectors.toList());

        for(LocalDate x: gagnagrunnsdagsetningafylki){
            System.out.println(x.toString());
        }


        Class.forName("org.sqlite.JDBC");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/java/sample/daytoursnyttsdk/DayTours.db");
            Statement statement;

            for(int i=0; i<gagnagrunnsdagsetningafylki.size(); i++){
                statement = conn.createStatement();
                String sqlSkipun = "INSERT INTO DayTours(name, day, price, location, spots, activityType, "
                        + "activityDifficulty, hotelPickup, duration, PK) VALUES(" + '"' + "Gonguferd um Reykjavik" + '"' + ", "
                        + '"' + gagnagrunnsdagsetningafylki.get(i) + '"' + ", " + "500" + ", " + '"' + "Reykjavik" + '"' + ", "
                        + "15, " + '"' + "Gonguferd" + '"' + ", " + "3, " + "0, " + "120, " + (i+368) + ");";
                //System.out.println(sqlSkipun);
                statement.execute(sqlSkipun);
            }


        } catch(SQLException e){
            System.err.println(e.getMessage());
        } finally{
            try {
                if(conn != null) conn.close();
            }
            catch(SQLException e) {
                System.err.println(e);
            }
        }
        */














































    }
}
