package database;

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

    /*public boolean checkIfBusy(LocalDate date_to_check, LocalDate st_date, LocalDate f_date){
        return st_date.compareTo(date_to_check) * date_to_check.compareTo(f_date) >= 0;
    }*/
}
