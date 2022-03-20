package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
public class DB {
    public void tengjastDB() throws Exception {
        {
            Class.forName("org.sqlite.JDBC");
            Connection conn = null;
            try
            {
                conn = DriverManager.getConnection("jdbc:sqlite:src/sample/DayTours.db");
                Statement statement = conn.createStatement();
                System.out.println("Virðist hafa tengst við gagnagrunn!");
                ResultSet r = statement.executeQuery("SELECT * FROM DayTours");
                while(r.next() != false){
                    System.out.println(r.getString(1));
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
