package com.example.todobackend.repositories;

import com.example.todobackend.entities.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee,Integer> {

}
