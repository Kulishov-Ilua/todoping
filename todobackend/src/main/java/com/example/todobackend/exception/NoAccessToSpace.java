package com.example.todobackend.exception;

public class NoAccessToSpace extends RuntimeException {
    public NoAccessToSpace(String message) {
        super(message);
    }
}
