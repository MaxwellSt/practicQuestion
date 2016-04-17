import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Макс on 08.04.2016.
 */
public class DAO {

    private static DAO ourInstance = new DAO();

    public static DAO getInstance() {
        return ourInstance;
    }

    private DAO() {
    }

    public ArrayList<Question> getAll() {

        Connection connection = null;
        ResultSet rs;

        ArrayList dataList = new ArrayList<Question>();
        try {
            // Load the database driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Get a Connection to the database
            connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "SYSTEM", "1475963");

            //System.out.println(connection.toString());

            //Select the data from the database
            String sql = "SELECT * from QUESTIONS";
            Statement s = connection.createStatement();
            s.executeQuery(sql);
            rs = s.getResultSet();

            while (rs.next()) {
                //Add records into data list
                dataList.add(new Question(rs.getInt("id"), rs.getString("question"), rs.getString("answer"), rs.getInt("points")));
            }
            rs.close();
            s.close();
        } //try
        catch (Exception e) {
            System.out.println("Exception is ;" + e);
        }
        return dataList;
    }

    public void updateUser(String username){

        Connection connection = null;
        ResultSet rs;

        ArrayList dataList = new ArrayList<Question>();
        try {
            // Load the database driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Get a Connection to the database
            connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "SYSTEM", "1475963");

            //System.out.println(connection.toString());

            //Select the data from the database
            String sql = "SELECT * from USERS WHERE NAME = " + username;
            Statement s = connection.createStatement();
            s.executeQuery(sql);
            rs = s.getResultSet();

            if(!rs.next()) {
                //Add records into USERS
                String insert = "INSERT INTO USERS VALUES(1, '" + username + "')";
                Statement s2 = connection.createStatement();
                s2.executeUpdate(insert);
            }
            rs.close();
            s.close();
        } //try
        catch (Exception e) {
            System.out.println("Exception is ;" + e);
        }
    }
    }

