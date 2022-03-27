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
    }

    @Test
    public void testGtSearchModel(){

        ArrayList<String> testActivityTypes = new ArrayList<>(); testActivityTypes.add("test"); testActivityTypes.add("test2");
        SearchModel sm = new SearchModel("test", 5, 10, 5, 10, testActivityTypes,
                5, 500, 5, LocalDate.now(), LocalDate.now(), false);
        assertNotNull(sm);
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
}
