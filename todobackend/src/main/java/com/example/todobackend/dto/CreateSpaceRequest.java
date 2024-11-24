package com.example.todobackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSpaceRequest {
    @NotBlank(message = "Название пространства обязательно")
    private String name;
    private String description;
    private String color;
    @NotNull(message = "Выбор типа пространства")
    private boolean isPersonal;
}
