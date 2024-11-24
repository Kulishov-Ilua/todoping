package com.example.todobackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequest {
    @NotBlank(message = "Название должно быть обязательным")
    private String name;
    private String description;
    @NotNull(message = "Дата начала мероприятия обязательно")
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String place;
    @NotNull(message = "Выбор занятости обязателен")
    private boolean isBusy;
    @NotBlank(message = "Выбор напоминания ообязателен")
    private String reminder;
}
