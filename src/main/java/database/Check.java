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

    /**
     * Checks if the perdon is already busy at the given date
     * @param person_name name of the person
     * @param start_date start date
     * @param finish_date finish date
     * @return true if person is busy
     */
    public boolean checkIfBusy(String person_name, LocalDate start_date, LocalDate finish_date) {

        Query query = new Query();
        return query.areDatesColliding(person_name, start_date, finish_date);
    }
}
