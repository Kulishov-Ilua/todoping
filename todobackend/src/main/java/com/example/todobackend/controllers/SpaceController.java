package com.example.todobackend.controllers;

import com.example.todobackend.authentication.ExtendUserDetails;
import com.example.todobackend.dto.*;
import com.example.todobackend.entities.Space;
import com.example.todobackend.services.AuthService;
import com.example.todobackend.services.SpaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/spaces")
public class SpaceController {
    private final AuthService authService;
    private final SpaceService spaceService;

    @GetMapping("/my")
    public ResponseEntity<List<ShortSpaceDto>> getMySpaces(){
        ExtendUserDetails user=authService.getUserFromContext();
        List<Space> spaces=spaceService.findByUser(user.getId());
        return ResponseEntity.ok(spaceService.getShortSpaceDtoFromSpaceList(spaces));
    }
    @GetMapping("/my/{id}")
    public ResponseEntity<BigSpaceDto> getMySpaceById(@PathVariable(name = "id") int id){
        ExtendUserDetails user=authService.getUserFromContext();
        Space space=spaceService.findSpaceById(id,user.getId());
        return ResponseEntity.ok(spaceService.getBigSpaceDtoFromSpace(space, user.getId()));
    }

    @GetMapping("/my/{id}/stat_admin")
    public ResponseEntity<List<TaskStatisticDto>> getTaskStatisticBySpaceId(@PathVariable(name = "id") int id) {
        ExtendUserDetails user = authService.getUserFromContext();
        Space space = spaceService.findSpaceById(id, user.getId());
        return ResponseEntity.ok(spaceService.getTestStatisticDtoListFromSpace(space, user.getId()));

    }

    @GetMapping("/my/{id}/stat_user")
    public ResponseEntity<TaskUserStatisticDto> getUserTaskStatisticBySpaceId(@PathVariable(name = "id") int id) {
        ExtendUserDetails user = authService.getUserFromContext();
        Space space = spaceService.findSpaceById(id, user.getId());
        return ResponseEntity.ok(spaceService.getUserStatisticDtoFromSpace(space, user.getId()));

    }



    @PostMapping("/my/{id}/event")
    public ResponseEntity<String> createEvent(@PathVariable(name = "id") int id, @RequestBody CreateEventRequest event){
        ExtendUserDetails user=authService.getUserFromContext();
        spaceService.addEventInSpace(id,event,user.getId());
        return ResponseEntity.ok("Событие было успешно добавлено!");
    }
    @PostMapping("/my/{id}/task")
    public ResponseEntity<String> createTask(@PathVariable(name = "id") int id,@RequestBody CreateTaskRequest task){
        ExtendUserDetails user=authService.getUserFromContext();
        spaceService.addTaskInSpace(id,task,user.getId());
        return ResponseEntity.ok("Задача была добавлена!");
    }
    @PostMapping()
    public ResponseEntity<String> createSpace(@RequestBody CreateSpaceRequest space){
        ExtendUserDetails user=authService.getUserFromContext();
        spaceService.createSpace(space,user.getId());
        return ResponseEntity.ok("Пространство было создано");
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginSpace(@RequestBody String code){
        ExtendUserDetails user=authService.getUserFromContext();
        spaceService.addUserInSpace(code,user.getId());
        return ResponseEntity.ok("Вы были успешно добавлены в пространство!");
    }
}
