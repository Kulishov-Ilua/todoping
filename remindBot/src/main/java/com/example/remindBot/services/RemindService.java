package com.example.remindBot.services;

import com.example.remindBot.bot.ReminderBot;
import com.example.remindBot.entities.Event;
import com.example.remindBot.entities.Reminders;
import com.example.remindBot.repositories.EventRepository;
import com.example.remindBot.repositories.ReminderRepository;
import com.example.remindBot.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RemindService {
    private final ReminderRepository reminderRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ReminderBot reminderBot;

    @Scheduled(fixedDelayString = "60000")
    private void run() {
        List<Event> events = eventRepository.findByStartDate(LocalDate.now());
        Event eventToRemind= events.stream()
                .filter(e -> !reminderRepository.existsByEventId(e.getId())).min((e1, e2) -> {
                    if (e1.getStartDate().equals(e2.getStartDate())) {
                        return e1.getStartTime().compareTo(e2.getStartTime());
                    }
                    return e2.getStartDate().compareTo(e1.getStartDate());
                }).orElse(null);
            if (eventToRemind!=null) {
                Duration duration = Duration.between(LocalTime.now(),eventToRemind.getStartTime());
                int postingInterval = 15;
                if (duration.toMinutes() <= postingInterval) {
                    Reminders reminders=new Reminders();
                    reminders.setEventId(eventToRemind.getId());
                    reminderRepository.save(reminders);
                    reminderBot.sendMessage(eventToRemind,userRepository.findById(eventToRemind.getOwner()).orElse(null));
                }
            }
    }


}