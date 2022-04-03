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
        Participant dummy = new Participant("test", "test", "test", "test", 2);
        ArrayList<Participant> dummyFylki = new ArrayList<>();
        dummyFylki.add(dummy);
        testCaseDayTour = new DayTour("test", "test", 5, LocalDate.now(), 5, 5000,
                "test", 5, 0, dummyFylki, 2 );
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
    public void testUIsearchGeneral(){
        ArrayList<String> dummyActivities = new ArrayList<>(); dummyActivities.add("Gongutur"); dummyActivities.add("Hjolaferd");
        SearchModel dummySM = new SearchModel("Reykjavik", 0, 180, 0, 5, dummyActivities,
                0, 10000, 5, LocalDate.of(2022, 01, 01),
                LocalDate.of(2022, 12, 12), false);
        DB databaseConnection = new DB();
        ArrayList<DayTour> dummyUtkoma = new ArrayList<>();
        try {
            dummyUtkoma = databaseConnection.getDayToursDatabase(dummySM);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(dummySM);
        assertNotEquals(0, dummyUtkoma.size());
        assertTrue(dummyUtkoma.get(0) != null);
    }

    @Test
    public void testUIsearchSpecific(){
        ArrayList<String> dummyActivities = new ArrayList<>(); dummyActivities.add("Gonguferd");
        SearchModel dummySM = new SearchModel("Reykjavik", 120, 120, 3, 3, dummyActivities,
                1000, 1000, 5, LocalDate.of(2022, 04, 07),
                LocalDate.of(2022, 04, 07), false);
        DB databaseConnection = new DB();
        ArrayList<DayTour> dummyUtkoma = new ArrayList<>();
        try {
            dummyUtkoma = databaseConnection.getDayToursDatabase(dummySM);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //String[] list = dummyUtkoma.get(0).split(",");
        /*
        System.out.println(dummyUtkoma);
        for(int i=0; i<list.length;i++){
            System.out.println(list[i]);
        }
         */
        assertEquals("Gonguferd um Reykjavik", dummyUtkoma.get(0).getTourName());
        assertEquals(" 2022-04-07", dummyUtkoma.get(0).getDate());
        assertEquals( " " + 1000, dummyUtkoma.get(0).getPrice());
        assertEquals(" Reykjavik", dummyUtkoma.get(0).getLocation());
        assertEquals(" " + 15, dummyUtkoma.get(0).getSpotsLeft());
        assertEquals(" Gonguferd", dummyUtkoma.get(0).getActivityType());
        assertEquals(" " + 3, dummyUtkoma.get(0).getActivityDifficulty());
        //assertEquals(" " + 0, dummyUtkoma.get(1).get);
        assertEquals(" " + 120, dummyUtkoma.get(0).getDuration());
        assertTrue(dummyUtkoma.get(0) instanceof DayTour);
    }
}