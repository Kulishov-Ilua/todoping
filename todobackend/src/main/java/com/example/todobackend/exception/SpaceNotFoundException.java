package com.example.todobackend.exception;

public class SpaceNotFoundException extends RuntimeException {
    public SpaceNotFoundException(String message) {
        super(message);
    }
}
