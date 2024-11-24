package com.example.todobackend.controllers;

import com.example.todobackend.authentication.ExtendUserDetails;
import com.example.todobackend.dto.AuthRequest;
import com.example.todobackend.dto.JwtResponse;
import com.example.todobackend.dto.RegRequest;
import com.example.todobackend.entities.User;
import com.example.todobackend.services.AuthService;
import com.example.todobackend.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Validated
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> sendToken(@Valid @RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @GetMapping("/auth/{id}/telegram")
    public ResponseEntity<Resource> authUser(@PathVariable(name = "id")int id) {
        org.springframework.core.io.Resource resource = new ClassPathResource("static/telegramAuth.html");
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION,Integer.toString(id));
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=telegramAuth.html");
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @GetMapping("/auth/telegram")
    public ResponseEntity<Resource> auth() {
        ExtendUserDetails auhtUser=authService.getUserFromContext();
        org.springframework.core.io.Resource resource = new ClassPathResource("static/telegramAuth.html");
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION,Integer.toString(auhtUser.getId()));
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=telegramAuth.html");
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    //сюда пользователи пойдут после того как авторизуются
    @PostMapping("/auth/{id}/telegram/token")
    public String telegramSignIn(@PathVariable(name = "id")int user,@RequestParam String id) {
        userService.setTg(id,user);
        return "ok";
    }


    @GetMapping("/access_token")
    public ResponseEntity<JwtResponse> getAccessToken(@RequestParam(name = "refresh_token") String refreshToken) {
        return ResponseEntity.ok(authService.getAccessToken(refreshToken));
    }

    @PostMapping("/reg")
    public ResponseEntity<JwtResponse> regUser(@Valid @RequestBody RegRequest regRequest){
        return ResponseEntity.ok(authService.regUser(regRequest));
    }
}
