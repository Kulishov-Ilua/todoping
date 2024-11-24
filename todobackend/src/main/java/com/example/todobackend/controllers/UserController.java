package com.example.todobackend.controllers;

import com.example.todobackend.authentication.ExtendUserDetails;
import com.example.todobackend.dto.UserDto;
import com.example.todobackend.entities.User;
import com.example.todobackend.services.AuthService;
import com.example.todobackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDto> getPersonalData(){
        ExtendUserDetails user=authService.getUserFromContext();
        User myUser=userService.findUserById(user.getId());
        return ResponseEntity.ok(userService.getUserDtoFromUser(myUser));
    }
}
