package sample.daytoursnyttsdk;

import java.sql.Date;

public class DayTour {
    public String getTourName() {
        return tourName;
    }

    public String getLocation() {
        return location;
    }

    public int getDuration() {
        return duration;
    }

    public Date getDate() {
        return date;
    }

    public int getSpotsLeft() {
        return spotsLeft;
    }

    public int getPrice() {
        return price;
    }

    public String getActivityType() {
        return activityType;
    }

    public int getActivityDifficulty() {
        return activityDifficulty;
    }

    public int isHotelPickUp() {
        return hotelPickUp;
    }

    public Participant[] getParticipants() {
        return participants;
    }

    private String tourName;
    private String location;
    private int duration;
    private Date date;
    private int spotsLeft;
    private int price;
    private String activityType;
    private int activityDifficulty;
    private int hotelPickUp;
    private Participant[] participants;

    public DayTour(String nafn, String loc, int dur, Date dags, int plassEftir, int verd, String type, int erfidleikastig,
                   int pickup, Participant[] medlimir){
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
