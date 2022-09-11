package com.example.soa.model;

import lombok.Data;

@Data
public class Response<T> {

    private String message;
    private T payload;

    public Response(T payload) {
        this.payload = payload;
    }
}
