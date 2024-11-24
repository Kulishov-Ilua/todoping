package com.example.remindBot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Entity(name = "spaces")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "space_id")
    private int id;
    private int owner;
    private String code;
    private String name;
    private String description;
    @Column(name = "is_personal")
    private boolean isPersonal;
    private String color;
    @OneToMany(mappedBy = "space")
    private List<Attendee> attendees;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "space_id")
    private List<Event> events;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "space_id")
    private List<Task> tasks;

}
