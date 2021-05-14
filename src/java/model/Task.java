package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
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

}
