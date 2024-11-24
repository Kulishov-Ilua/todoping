package com.example.todobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;
    private String name;
    private List<ShortEventDto> events;
    private List<ShortTaskDto> tasks;
    private int doneTaskCount;
    private int lateTaskCount;
    private int allTaskCount;
    private int avgTime;
    private String avgLabel;
}
