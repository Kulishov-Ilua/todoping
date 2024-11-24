package com.example.remindBot.repositories;

import com.example.remindBot.entities.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends JpaRepository<Space,Integer> {
    public List<Space> findByOwnerAndIsPersonal(int owner, boolean isPersonal);
    public List<Space> findByOwner(int owner);
    public boolean existsByCode(String code);
    @Query("SELECT s FROM spaces s JOIN s.attendees a WHERE a.user.id=:userId")
    public List<Space> findByAttendee(@Param("userId") int id);
}
