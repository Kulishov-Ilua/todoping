package com.example.todobackend.services;

import com.example.todobackend.entities.Attendee;
import com.example.todobackend.entities.Change;
import com.example.todobackend.entities.Task;
import com.example.todobackend.exception.ChangeNotFoundException;
import com.example.todobackend.repositories.ChangeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ChangeService {
    private final ChangeRepository changeRepository;

    public List<Change> findChangesWithUndoneStatusByUser(int userId){
        return changeRepository.findUndoneByUserId(userId);
    }
    public Change findChangeByTaskAndUser(int taskId,int userId){
        Change change=changeRepository.findByTaskIdAndUserId(taskId,userId);
        if(change==null){
            throw new ChangeNotFoundException("Не найдена информация о статусе выполнения задачи!");
        }
        return change;
    }

    public void addChangesToAttendees(Task task, List<Attendee> attendees) {
        attendees.forEach(a->{
            Change change=new Change();
            change.setTask(task);
            change.setUserId(a.getUser().getId());
            change.setStatus("надо сделать");
            changeRepository.save(change);
        });
    }

    public void changeTaskStatus(int taskId, int userId,String status) {
        Change change=findChangeByTaskAndUser(taskId,userId);
        change.setStatus(status);
        if(status.equals("в процессе")){
            change.setStartDate(new Date());
        }
        if(status.equals("сделано")){
            if(change.getStartDate()==null){
                change.setStartDate(new Date());
            }
            change.setEndDate(new Date());
        }
        changeRepository.save(change);
    }

    public void addChangesToOwner(Task task, int owner) {
        Change change=new Change();
        change.setTask(task);
        change.setUserId(owner);
        change.setStatus("надо сделать");
        changeRepository.save(change);
    }

    public void addChangesToAttendee(List<Change> changes) {
        changeRepository.saveAll(changes);
    }
}
