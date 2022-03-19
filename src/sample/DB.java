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
                statement.execute("DROP TABLE IF EXISTS R");
                statement.execute("CREATE TABLE R(key INTEGER PRIMARY KEY, value DOUBLE)");
                System.out.println("Virðist hafa tengst við gagnagrunn!");

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
