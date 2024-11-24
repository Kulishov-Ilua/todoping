package com.example.todobackend.services;

import com.example.todobackend.entities.Attendee;
import com.example.todobackend.repositories.AttendeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;

    public void saveAttendee(Attendee attendee){
        attendeeRepository.save(attendee);
    }
}
