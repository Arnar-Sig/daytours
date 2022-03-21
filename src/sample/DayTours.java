package sample;

public class DayTours {
    DayTour dayTour[];
    SearchModel searchModel;
    int sort;

    public DayTours(int rodun){
        sort = rodun;

    }

    public void updateSearchModel(SearchModel sm){
        searchModel = sm;
    }
}
