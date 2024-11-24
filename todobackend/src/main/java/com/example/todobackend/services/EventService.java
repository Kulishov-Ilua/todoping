package com.example.todobackend.services;

import com.example.todobackend.entities.Event;
import com.example.todobackend.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public void saveEvent(Event event){
        eventRepository.save(event);
    }
}
