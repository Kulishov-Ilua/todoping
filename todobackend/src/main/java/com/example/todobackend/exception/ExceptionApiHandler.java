package com.example.todobackend.exception;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<AppError> badCredentialsException(BadCredentialsException e){
        return new ResponseEntity<AppError>(new AppError(HttpStatus.BAD_REQUEST.value(), "Неверный логин и/или пароль!",new Date()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<AppError> validationException(ValidationException e){
        return new ResponseEntity<AppError>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),new Date()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<AppError> userAlreadyExistsException(UserAlreadyExistsException e){
        return new ResponseEntity<AppError>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),new Date()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public ResponseEntity<AppError> passwordDoNotMatchException(PasswordsDoNotMatchException e){
        return new ResponseEntity<AppError>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),new Date()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SpaceNotFoundException.class)
    public ResponseEntity<AppError> spaceNotFoundException(SpaceNotFoundException e){
        return new ResponseEntity<AppError>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),new Date()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<AppError> taskNotFoundException(TaskNotFoundException e){
        return new ResponseEntity<AppError>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),new Date()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<AppError> userNotFoundException(UserNotFoundException e){
        return new ResponseEntity<AppError>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),new Date()),HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ChangeNotFoundException.class)
    public ResponseEntity<AppError> changeNotFoundException(ChangeNotFoundException e){
        return new ResponseEntity<AppError>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),new Date()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoAccessToSpace.class)
    public ResponseEntity<AppError> noAccessToSpaceException(NoAccessToSpace e){
        return new ResponseEntity<AppError>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),new Date()),HttpStatus.BAD_REQUEST);
    }
}
