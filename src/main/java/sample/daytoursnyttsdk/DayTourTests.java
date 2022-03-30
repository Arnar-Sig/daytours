package sample.daytoursnyttsdk;

import javafx.scene.control.ListView;
import org.junit.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DayTourTests {


    DayTours DayToursTestCase;
    DayTour testCaseDayTour;

    @Test
    public void testDayTourObject(){
        Participant dummy = new Participant("test", "test", "test", "test", 5);
        Participant[] dummyFylki = new Participant[1];
        dummyFylki[0] = dummy;
        testCaseDayTour = new DayTour("test", "test", 5, LocalDate.now(), 5, 5000,
                "test", 5, 0, dummyFylki );
        assertNotNull(testCaseDayTour);
        assertEquals("test", testCaseDayTour.getTourName());
    }

    @Test
    public void testSearchModel(){

        ArrayList<String> testActivityTypes = new ArrayList<>(); testActivityTypes.add("test"); testActivityTypes.add("test2");
        SearchModel sm = new SearchModel("test", 5, 10, 5, 10, testActivityTypes,
                5, 500, 5, LocalDate.now(), LocalDate.now(), false);
        assertNotNull(sm);
        assertEquals("test", sm.getLocation());
    }

    @Test
    public void testUIsearch(){
        ArrayList<String> dummyActivities = new ArrayList<>(); dummyActivities.add("Gongutur"); dummyActivities.add("Hjolaferd");
        SearchModel dummySM = new SearchModel("Reykjavik", 0, 180, 0, 5, dummyActivities,
                0, 10000, 5, LocalDate.of(2022, 01, 01),
                LocalDate.of(2022, 12, 12), false);
        DB databaseConnection = new DB();
        ArrayList<String> dummyUtkoma = new ArrayList<>();
        try {
            dummyUtkoma = databaseConnection.searchDayTours(dummySM);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(dummySM);
        assertNotEquals(0, dummyUtkoma.size());
        assertTrue(dummyUtkoma.get(0) instanceof String);
    }

    @Test
    public void testUIsearchSpecific(){
        ArrayList<String> dummyActivities = new ArrayList<>(); dummyActivities.add("Gonguferd");
        SearchModel dummySM = new SearchModel("Reykjavik", 120, 120, 3, 3, dummyActivities,
                1000, 1000, 5, LocalDate.of(2022, 04, 07),
                LocalDate.of(2022, 04, 07), false);
        DB databaseConnection = new DB();
        ArrayList<String> dummyUtkoma = new ArrayList<>();
        try {
            dummyUtkoma = databaseConnection.searchDayTours(dummySM);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] list = dummyUtkoma.get(0).split(",");
        /*
        System.out.println(dummyUtkoma);
        for(int i=0; i<list.length;i++){
            System.out.println(list[i]);
        }
         */
        assertEquals("Gonguferd um Reykjavik", list[0]);
        assertEquals(" 2022-04-07", list[1]);
        assertEquals( " " + 1000, list[2]);
        assertEquals(" Reykjavik", list[3]);
        assertEquals(" " + 15, list[4]);
        assertEquals(" Gonguferd", list[5]);
        assertEquals(" " + 3, list[6]);
        assertEquals(" " + 0,list[7]);
        assertEquals(" " + 120, list[8]);

    }
}
