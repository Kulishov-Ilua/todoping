package com.example.remindBot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "changes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Change {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "change_id")
    private int id;
    @ManyToOne()
    @JoinColumn(name = "task_id")
    private Task task;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    private String status;
}
