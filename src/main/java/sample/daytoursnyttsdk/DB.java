package sample.daytoursnyttsdk;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    public ArrayList<String> searchDayTours(SearchModel sm) throws Exception {
            // TO-do: láta fallið skila hlut af DayTours með öllu útfylltu
            //        (þarf að ná í gögn úr Participants gagnagrunni t.d.)

            Class.forName("org.sqlite.JDBC");
            Connection conn = null;
            ArrayList<String> fylkiAfRodum = new ArrayList<>();
            try
            {
                conn = DriverManager.getConnection("jdbc:sqlite:src/main/java/sample/daytoursnyttsdk/DayTours.db");
                Statement statement = conn.createStatement();
                /*
                String sqlSkipun = "SELECT * FROM DayTours WHERE day BETWEEN " + sm.getDateFrom() + " AND " + sm.getDateTo()
                        + " AND price BETWEEN " + "sm.getPriceMin()" + " AND " + sm.getPriceMax()
                        + " AND location = " + sm.getLocation() + " AND spots >= " + sm.getMinSpotsLeft()
                        + " AND activityType = " + sm.getActivityType() + " AND activityDifficulty BETWEEN " + sm.getActivityDifficultyMin()
                        + " AND " + sm.getActivityDifficultyMax() + " AND hotelPickUp = " + sm.isHotelPickUp()
                        + " AND duration BETWEEN " + sm.getDurationMin() + " AND " + sm.getDurationMax();

                 */


                String sqlSkipun = "SELECT * FROM DayTours WHERE price BETWEEN " + sm.getPriceMin() + " AND " + sm.getPriceMax()
                        + " AND location = " + '"' +  sm.getLocation() + '"' + " AND spots >= " + sm.getMinSpotsLeft()
                        + " AND activityType = " + '"' + sm.getActivityType() + '"' + " AND activityDifficulty BETWEEN " + sm.getActivityDifficultyMin()
                        + " AND " + sm.getActivityDifficultyMax() + " AND hotelPickUp = " + sm.isHotelPickUp()
                        + " AND duration BETWEEN " + sm.getDurationMin() + " AND " + sm.getDurationMax()
                        + " AND day BETWEEN " + '"' + sm.getDateFrom() + '"' + " AND " + '"' + sm.getDateTo() + '"';

                //System.out.println(sqlSkipun);
                //ResultSet r = statement.executeQuery("SELECT * FROM DayTours");
                ResultSet r = statement.executeQuery(sqlSkipun);
                ResultSetMetaData rm = r.getMetaData();
                int colCount = rm.getColumnCount();
                //ArrayList<String> fylkiAfRodum = new ArrayList<String>();
                while(r.next()){
                    String rod = "";
                    for (int i = 1; i <= colCount; i++) {
                        rod += r.getString(i) + ", ";
                    }
                    //System.out.println(rod);
                    fylkiAfRodum.add(rod);
                }
                return fylkiAfRodum;
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }
            finally
            {
                try
                {
                    if(conn != null) conn.close();
                }
                catch(SQLException e)
                {
                    System.err.println(e);
                }
            }
            return fylkiAfRodum;
    }
}
