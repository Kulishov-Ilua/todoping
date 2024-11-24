package com.example.todobackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    @NotBlank(message = "Логин является обязательной для авторизации.")
    private String login;

    @NotBlank(message = "Пароль является обязательным для авторизации")
    private String password;
}
