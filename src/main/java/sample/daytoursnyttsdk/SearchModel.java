package sample.daytoursnyttsdk;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class SearchModel {
    public String getLocation() {
        return location;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public int getDurationMax() {
        return durationMax;
    }

    public int getActivityDifficultyMin() {
        return activityDifficultyMin;
    }

    public int getActivityDifficultyMax() {
        return activityDifficultyMax;
    }

    public ArrayList<String> getActivityType() {
        return activityType;
    }

    public int getPriceMin() {
        return priceMin;
    }

    public int getPriceMax() {
        return priceMax;
    }

    public int getMinSpotsLeft() {
        return minSpotsLeft;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public boolean isHotelPickUp() {
        return hotelPickUp;
    }

    private String location;
    private int durationMin;
    private int durationMax;
    private int activityDifficultyMin;
    private int activityDifficultyMax;
    private ArrayList<String> activityType;
    private int priceMin;
    private int priceMax;
    private int minSpotsLeft;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private boolean hotelPickUp;

    public SearchModel(String loc, int durMin, int durMax, int actMin, int actMax, ArrayList<String> actType, int verdMin, int verdMax,
                       int plassEftir, LocalDate dagsFra, LocalDate dagsTil, boolean hotel){
        location = loc;
        durationMin = durMin;
        durationMax = durMax;
        activityDifficultyMin = actMin;
        activityDifficultyMax = actMax;
        activityType = actType;
        priceMin = verdMin;
        priceMax = verdMax;
        minSpotsLeft = plassEftir;
        dateFrom = dagsFra;
        dateTo = dagsTil;
        hotelPickUp = hotel;

    }
}
