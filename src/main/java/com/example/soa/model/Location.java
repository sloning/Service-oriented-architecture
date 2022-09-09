package com.example.soa.model;

import lombok.Data;

@Data
public class Location {
    private float x;
    private Double y; //Поле не может быть null
    private Double z; //Поле не может быть null
    private String name; //Строка не может быть пустой, Поле может быть null
}
