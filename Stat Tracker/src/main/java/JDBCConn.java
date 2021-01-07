import java.security.spec.ECField;
import java.sql.*;

public class JDBCConn {


    static void insertData(String DB_URL, String USER, String PASS, double score) {
        //Open a connection
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();

            String sql = "INSERT INTO `leaguedata`.`leaguedata_scores` (`scoreValue`) VALUES ('" + score + "');";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        } finally {
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException ignored){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException sqlException){
                sqlException.printStackTrace();
            }
        }

    }

    private static void resetTable(String DB_URL, String USER, String PASS) {
        //Open a connection
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();

            String sql = "alter table leaguedata AUTO_INCREMENT = 1";
            stmt.executeUpdate(sql);

            String sql2 = "truncate table leaguedata";
            stmt.executeUpdate(sql2);

            stmt.close();
            conn.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        } finally {
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException ignored){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException sqlException){
                sqlException.printStackTrace();
            }
        }
    }




}