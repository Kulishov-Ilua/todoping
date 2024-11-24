package com.example.todobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatisticDto {
    private int id;
    private String name;
    private String description;
    private String deadline;
    private String priority;
    private int doneTaskCount;
    private int attendeeCount;
    private long avg;
    private String avgLabel;
}
