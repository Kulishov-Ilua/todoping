package com.example.todobackend.repositories;

import com.example.todobackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByLogin(String login);
    boolean existsByLogin(String login);

    List<User> findByTgId(String id);
}
