package com.example.todobackend.exception;

public class ChangeNotFoundException extends RuntimeException {
    public ChangeNotFoundException(String message) {
        super(message);
    }
}
