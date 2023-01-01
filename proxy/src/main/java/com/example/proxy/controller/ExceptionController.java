package com.example.proxy.controller;

import com.example.proxy.model.Response;
import com.example.proxy.model.exception.BadRequestException;
import com.example.proxy.model.exception.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.ws.soap.client.SoapFaultClientException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @Hidden
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public Response<Void> handleEntityNotFoundException(Exception ex) {
        Response<Void> response = new Response<>();
        response.setMessage(ex.getMessage());
        return response;
    }

    @Hidden
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        Response<Map<String, String>> response = new Response<>(errors);
        response.setMessage("Invalid data");
        return response;
    }

    @Hidden
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public Response<Void> handleBadRequestException(BadRequestException ex) {
        Response<Void> response = new Response<>();
        response.setMessage(ex.getMessage());
        return response;
    }

    @Hidden
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SoapFaultClientException.class)
    public Response<Void> handleSoapFaultClientException(SoapFaultClientException ex) {
        Response<Void> response = new Response<>();
        response.setMessage(ex.getMessage());
        return response;
    }
}
