package database;

import com.sun.javafx.collections.MappingChange;

import java.sql.*;
import java.time.LocalDate;

/**
 * Implements methods to check is the entered data's are correct
 */
public class Check {

    /**
     * Checks if the parameters are null
     * @param task data to check
     * @param person data to check
     * @param startdate data to check
     * @param finishdate data to check
     * @return true is none of the data's are null
     */
    public boolean checkIfEmpty(String task, String person, LocalDate startdate, LocalDate finishdate) {
        return (task.isEmpty() || person.isEmpty() || startdate == null || finishdate == null);
    }

    /**
     * Checks if the task fininis date is before the start date
     * @param startdate task start date
     * @param finishdate task finish date
     * @return true is the date order is correct
     */

    public boolean checkDates(LocalDate startdate, LocalDate finishdate) {
        return startdate.isAfter(finishdate);
    }

    /**
     * Checks if the perdon is already busy at the given date
     * @param name name of the person
     * @param st_date start date
     * @param f_date finish date
     * @return true if person is busy
     */
    public boolean checkIfBusy(String name, LocalDate st_date, LocalDate f_date) {
        boolean talalat = false;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/dlOWKKEPJg", "dlOWKKEPJg", "S9nooKD9cK");
            String sql = "select * from Task where (person= ? and startdate= ?) or (person= ? and finishdate=?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, Date.valueOf(st_date));
            preparedStatement.setString(3, name);
            preparedStatement.setDate(4, Date.valueOf(f_date));

            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()){
                talalat = true;
            } else talalat = false;

        } catch (Exception e) {
            System.out.println("Error in check");
        }
         if (talalat==true){
             return  true;
         } else return false;
    }
}
