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
public class BigSpaceDto {
    private int id;
    private String name;
    private String access_status;
    private String code;
    private boolean isPersonal;
    private List<ShortEventDto> events;
    private List<ShortTaskDto> tasks;
    private List<AttendeeDto> attendees;
}
