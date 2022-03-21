package sample;

import java.sql.Date;

public class DayTour {
    private String tourName;
    private String location;
    private int duration;
    private Date date;
    private int spotsLeft;
    private int price;
    private int activityType;
    private int activityDifficulty;
    private boolean hotelPickUp;
    private Participant[] participants;

    public DayTour(String nafn, String loc, int dur, Date dags, int plassEftir, int verd, int type, int erfidleikastig,
                   boolean pickup, Participant[] medlimir){
        tourName = nafn;
        location = loc;
        duration = dur;
        date = dags;
        spotsLeft = plassEftir;
        price = verd;
        activityType = type;
        activityDifficulty = erfidleikastig;
        hotelPickUp = pickup;
        participants = medlimir;
    }
}
