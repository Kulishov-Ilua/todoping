package com.example.remindBot.repositories;

import com.example.remindBot.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {
    List<Event> findByStartDate(LocalDate date);
    List<Event> findByOwner(int owner);
}
