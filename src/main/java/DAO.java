import model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public ArrayList<Question> getAllQuestion() {

        Connection connection = null;
        ResultSet rs;

        ArrayList dataList = new ArrayList<Question>();
        try {
            // Load the database driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Get a Connection to the database
            connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "SYSTEM", "1475963max");

            //System.out.println(connection.toString());

            //Select the data from the database
            PreparedStatement sql = connection.prepareStatement("SELECT * from QUESTIONS");
            rs = sql.executeQuery();

            while (rs.next()) {
                //Add records into data list
                dataList.add(new Question(rs.getInt("id"), rs.getString("question"), rs.getString("answer"), rs.getInt("points")));
            }
            rs.close();
            sql.close();
            connection.close();
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
            connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "SYSTEM", "1475963max");

            //Select the data from the database
            PreparedStatement sql = connection.prepareStatement("SELECT * from USERS WHERE NAME = '" + username + "'");
            rs = sql.executeQuery();

            if(!rs.next()) {
                //select max id
                PreparedStatement sqlMaxId = connection.prepareStatement("SELECT MAX(ID) as ID from USERS");
                ResultSet selectMaxId = sqlMaxId.executeQuery();

                int maxId = 0;

                if (selectMaxId.next()){
                    maxId = selectMaxId.getInt("ID");
                }

                //Add records into USERS
                PreparedStatement insert = connection.prepareStatement("INSERT INTO USERS(ID, NAME) VALUES("+(maxId+1)+", '" + username + "')");
                insert.executeUpdate();
            }
            rs.close();
            sql.close();
            connection.close();
        } //try
        catch (Exception e) {
            System.out.println("Exception is ;" + e);
        }
    }

    public void updateUserPoint(String username, int points){

        Connection connection = null;
        ResultSet rs;

        ArrayList dataList = new ArrayList<Question>();
        try {
            // Load the database driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Get a Connection to the database
            connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "SYSTEM", "1475963max");

            PreparedStatement sql = connection.prepareStatement("UPDATE USERS SET MAX_POINTS = " + points + " WHERE NAME = '"
                    + username + "' and isnull(MAX_POINTS, 0) < " + points);
            sql.executeUpdate();

            sql.close();
            connection.close();
        } //try
        catch (Exception e) {
            System.out.println("Exception is ;" + e);
        }
    }

    public Map<String, Integer> getAllUsers() {

        Connection connection = null;
        ResultSet rs;

        Map<String, Integer> mapUsers = new HashMap<String, Integer>();
        try {
            // Load the database driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Get a Connection to the database
            connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "SYSTEM", "1475963max");

            //Select the data from the database
            PreparedStatement sql = connection.prepareStatement("SELECT * from USERS");
            rs = sql.executeQuery();

            while (rs.next()) {
                //Add records into data list
                mapUsers.put(rs.getString("NAME"), rs.getInt("MAX_POINTS"));
            }
            rs.close();
            sql.close();
            connection.close();
        } //try
        catch (Exception e) {
            System.out.println("Exception is ;" + e);
        }
        return mapUsers;
    }
    }

