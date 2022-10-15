package com.example.service1.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
