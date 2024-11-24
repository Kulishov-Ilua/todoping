package com.example.todobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortTaskDto {
    private int id;
    private String name;
    private String deadline;
    private String status;
    private String priority;
}
