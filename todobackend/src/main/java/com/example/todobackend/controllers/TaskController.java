package com.example.todobackend.controllers;

import com.example.todobackend.authentication.ExtendUserDetails;
import com.example.todobackend.services.AuthService;
import com.example.todobackend.services.ChangeService;
import com.example.todobackend.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
    private final AuthService authService;
    private final TaskService taskService;
    private final ChangeService changeService;

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTaskStatus(@PathVariable(name = "id") int id,@RequestBody String status){
        ExtendUserDetails user=authService.getUserFromContext();
        changeService.changeTaskStatus(id,user.getId(),status);
        return ResponseEntity.ok("Статус успешно обновлен");
    }
}
