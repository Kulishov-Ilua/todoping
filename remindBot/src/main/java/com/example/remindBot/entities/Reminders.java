package com.example.remindBot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "reminded_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reminders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "event_id")
    private int eventId;
}
