package com.example.todobackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequest {
    @NotBlank(message = "Название задачи обязательной")
    private String name;
    private String description;
    @NotNull(message = "Дедлайн обязателен для задачи")
    private LocalDateTime deadline;
    @NotBlank(message = "Приоритет обязателен для выбора")
    private String priority;
    private Integer mainTaskId;
}
