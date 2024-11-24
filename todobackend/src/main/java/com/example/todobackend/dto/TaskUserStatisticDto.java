package com.example.todobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskUserStatisticDto {
    private int doneTaskCount;
    private int lateTaskCount;
    private int allTaskCount;
    private int avgTime;
    private String avgLabel;
    private int avgUsersCount;
}
