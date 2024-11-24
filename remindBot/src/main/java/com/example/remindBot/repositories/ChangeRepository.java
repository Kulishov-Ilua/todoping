package com.example.remindBot.repositories;

import com.example.remindBot.entities.Change;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangeRepository extends JpaRepository<Change,Integer> {
    @Query("SELECT c FROM changes c WHERE c.userId =:userId AND c.status !='сделано'")
    public List<Change> findUndoneByUserId(int userId);
    public Change findByTaskIdAndUserId(int taskId,int userId);
}
