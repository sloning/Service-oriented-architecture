package com.example.service2.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
