package model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    private String task;

    @Column(nullable=false)
    private String person;

    @Column(nullable=false)
    private LocalDate startdate;

    @Column(nullable=false)
    private LocalDate finishdate;

    public Task(EntityManager em) {
    }

    public Task(long id, String task, String person, LocalDate startdate, LocalDate finishdate) {
        this.id = id;
        this.task = task;
        this.person = person;
        this.startdate = startdate;
        this.finishdate = finishdate;
    }

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
