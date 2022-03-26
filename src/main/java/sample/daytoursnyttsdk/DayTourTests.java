package sample.daytoursnyttsdk;

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
        assertNotEquals(null, testCaseDayTour);
    }

    @Test
    public void getSearchModel(){

        ArrayList<String> testActivityTypes = new ArrayList<>(); testActivityTypes.add("test"); testActivityTypes.add("test2");
        SearchModel sm = new SearchModel("test", 5, 10, 5, 10, testActivityTypes,
                5, 500, 5, LocalDate.now(), LocalDate.now(), false);
        assertNotEquals(null, sm);
    }


}
