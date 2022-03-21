package sample;

import java.sql.*;

public class DB {
    public void searchDatabase(SearchModel sm) throws Exception {
        {
            Class.forName("org.sqlite.JDBC");
            Connection conn = null;
            try
            {
                conn = DriverManager.getConnection("jdbc:sqlite:src/sample/DayTours.db");
                Statement statement = conn.createStatement();
                System.out.println("Virðist hafa tengst við gagnagrunn!");
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
                        + " AND duration BETWEEN " + sm.getDurationMin() + " AND " + sm.getDurationMax();


                System.out.println(sqlSkipun);
                //ResultSet r = statement.executeQuery("SELECT * FROM DayTours");
                ResultSet r = statement.executeQuery(sqlSkipun);
                ResultSetMetaData rm = r.getMetaData();
                int colCount = rm.getColumnCount();
                while(r.next()){
                    String rod = "";
                    for (int i = 1; i <= colCount; i++) {
                        rod += r.getString(i) + ", ";
                    }
                    System.out.println(rod);
                }
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
        }
    }
}
