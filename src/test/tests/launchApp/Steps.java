package test.tests.launchApp;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;

import java.sql.*;

public class Steps {
    private Connection conn = null;


    public void addConnectionAndDataInDB(String query){
        dataBaseConfig();
        addDataInDB(query);
    }

    public void dataBaseConfig(){
        try {

            //Class.forName("com.mysql.jdbc.Driver");
            String conURL = "jdbc:mysql://awsdb.c7dlmpdkx5kd.us-west-2.rds.amazonaws.com:3306/pandora?user=emulator&password=4FdRurSz7tDCVhgWYY3YhP";
            conn = DriverManager.getConnection(conURL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /*public void checkDataInDB(String SqlQuery) {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(SqlQuery);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount(); //number of column
            String columnName[] = new String[count];

            for (int i = 1; i <= count; i++)
            {
                columnName[i-1] = metaData.getColumnLabel(i);
                System.out.println(columnName[i-1]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * @param SqlQuery
     * @Description : Execute add data query.
     */
    public void addDataInDB(String SqlQuery) {
        Statement statement = null;
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void swipRightToLeft(AndroidDriver driver){

        Dimension size = driver.manage().window().getSize();
        System.out.println(size);

        //Find swipe start and end point from screen's with and height.
        //Find startx point which is at right side of screen.
        int startx = (int) (size.width * 0.90);
        //Find endx point which is at left side of screen.
        int endx = (int) (size.width * 0.10);
        //Find vertical point where you wants to swipe. It is in middle of screen height.
        int starty = size.height / 2;
        int endy = size.height/2;
        System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);

        //Swipe from Right to Left.
        driver.swipe(startx, starty, endx, endy, 3000);
        //driver.swipe(startx, starty, endx, starty, 3000);
    }

}
