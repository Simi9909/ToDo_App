package model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;


/**
 * Model of a Task.
 */
@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String task;

    @Column(nullable = false)
    private String person;

    @Column(nullable = false)
    private LocalDate startdate;

    @Column(nullable = false)
    private LocalDate finishdate;

    /**
     * Creates a new instanse of the Task class
     *
     * @param id         the task id
     * @param task       the task name
     * @param person     the person who get the task
     * @param startdate  the start date of the task
     * @param finishdate the finish date of the task
     */
    public Task(long id, String task, String person, LocalDate startdate, LocalDate finishdate) {
        this.id = id;
        this.task = task;
        this.person = person;
        this.startdate = startdate;
        this.finishdate = finishdate;
    }

    /**
     * Public no args constructor
     */
    public Task() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public String getPerson() {
        return person;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public void setFinishdate(LocalDate finishdate) {
        this.finishdate = finishdate;
    }

    public LocalDate getFinishdate() {
        return finishdate;
    }

}
