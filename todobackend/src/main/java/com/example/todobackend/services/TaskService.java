package com.example.todobackend.services;

import com.example.todobackend.dto.CreateTaskRequest;
import com.example.todobackend.entities.Task;
import com.example.todobackend.exception.TaskNotFoundException;
import com.example.todobackend.mappers.PriorityMapper;
import com.example.todobackend.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task findTaskById(int id){
        return taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException("Задача не найдена!"));
    }

    public Task addTask(CreateTaskRequest task,int spaceId){
        Task myTask=new Task();
        myTask.setName(task.getName());
        myTask.setDescription(task.getDescription());
        myTask.setSpaceId(spaceId);
        myTask.setPriority(PriorityMapper.stringPriorityToIntPriority(task.getPriority()));
        myTask.setDeadline(task.getDeadline().toLocalDate());
        myTask.setMainTask(task.getMainTaskId()==null);
        taskRepository.save(myTask);
        if(task.getMainTaskId()!=null){
            Task mainTask=findTaskById(task.getMainTaskId());
            List<Integer> subtasks=mainTask.getSubtasks();
            subtasks.add(myTask.getId());
            mainTask.setSubtasks(subtasks);
            taskRepository.save(mainTask);
        }
        return myTask;
    }
}
