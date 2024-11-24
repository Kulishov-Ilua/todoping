package com.example.remindBot.repositories;

import com.example.remindBot.entities.Reminders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepository extends JpaRepository<Reminders,Integer> {
    boolean existsByEventId(int eventId);
}
