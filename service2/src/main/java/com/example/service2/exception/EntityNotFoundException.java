package com.example.service2.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
