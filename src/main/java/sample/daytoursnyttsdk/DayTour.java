package sample.daytoursnyttsdk;

import java.sql.Date;
import java.time.LocalDate;

public class DayTour {
    /** Getters **/
    public String getAll() {
        String part = "";
        for(int i=0; i<participants.length; i++){
            part = part + participants[i].getName();
        }
        String ut = tourName + ", " + location + ", " + duration + ", " + date + ", " + spotsLeft + ", " + price + ", "
                + activityType + ", " + activityDifficulty + ", " + hotelPickUp + ", " + part;
        return ut;
    }
    public String getTourName() {
        return tourName;
    }
    public String getLocation() {
        return location;
    }
    public int getDuration() {
        return duration;
    }
    public LocalDate getDate() {
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

    /** Local breytur **/
    private String tourName;
    private String location;
    private int duration;
    private LocalDate date;
    private int spotsLeft;
    private int price;
    private String activityType;
    private int activityDifficulty;
    private int hotelPickUp;
    private Participant[] participants;

    public DayTour(String nafn, String loc, int dur, LocalDate dags, int plassEftir, int verd,
                   String type, int erfidleikastig, int pickup, Participant[] medlimir){
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
