package com.example.todobackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int id;
    private String name;
    private String description;
    private int priority;
    private LocalDate deadline;
    @Column(name = "space_id")
    private Integer spaceId;
    @Column(name = "is_main_task")
    private boolean isMainTask;
    private List<Integer> subtasks;
    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Change> changes;

}
