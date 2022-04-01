package sample.daytoursnyttsdk;

import java.sql.Date;
import java.time.LocalDate;

public class DayTour implements Comparable<DayTour>{
    /** Getters **/
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
    public Integer getPrice() {
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
    private Integer price;
    private String activityType;
    private int activityDifficulty;
    private int hotelPickUp;
    private Participant[] participants;
    private String sortType;

    private String[] sortTypes = {"Name", "Price: Low to High", "Price: High to Low"};

    public DayTour(String nafn, String loc, int dur, LocalDate dags, int plassEftir, int verd,
                   String type, int erfidleikastig, int pickup, Participant[] medlimir){
        tourName = nafn;
        location = loc;
        duration = dur;
        date = dags;
        spotsLeft = plassEftir;
        price = Integer.valueOf(verd);
        activityType = type;
        activityDifficulty = erfidleikastig;
        hotelPickUp = pickup;
        participants = medlimir;
    }

    @Override
    public int compareTo(DayTour o) {
        if (this.sortType.equals(sortTypes[0])) {
            return this.getTourName().compareTo(o.getTourName());
        }
        else if (this.sortType.equals(sortTypes[1]) || this.sortType.equals(sortTypes[2])) {
            return this.getPrice().compareTo(o.getPrice());
        }
        else return this.getDate().compareTo(o.getDate());
    }

    public void sortBy(String s) {
        sortType = s;
    }
}
