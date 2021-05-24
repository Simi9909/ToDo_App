package database;

import model.Task;
import org.tinylog.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Query {

    public boolean areDatesColliding(String person, LocalDate s_date, LocalDate f_date) {
        List<Task> datesList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/dlOWKKEPJg", "dlOWKKEPJg", "S9nooKD9cK");
            String query = "select * from Task where person=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, person);
            ResultSet rs = ps.executeQuery();
            Logger.info("Query result");
            while (rs.next()){
                Integer id = rs.getInt("id");
                String pers = rs.getString("person");
                String ta = rs.getString("task");
                LocalDate st_date = rs.getDate("startdate").toLocalDate();
                LocalDate fi_date = rs.getDate("finishdate").toLocalDate();

                Task task = new Task();

                task.setId(id);
                task.setPerson(pers);
                task.setTask(ta);
                task.setStartdate(st_date);
                task.setFinishdate(fi_date);
                datesList.add(task);
                Logger.trace("task "+task);
                Logger.info("Check dates");
            }

        } catch (Exception e){
            Logger.error("Error in query");
        }
        boolean start_after_finish = datesList.stream().anyMatch(x->x.getFinishdate().isAfter(s_date));
        boolean finish_before_start = datesList.stream().anyMatch(x->x.getStartdate().isBefore(f_date));
        return start_after_finish && finish_before_start;

    }

}


