package com.example.service2.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Location implements WithName {
    private Integer id;
    private Float x;
    @NotNull
    private Double y; //Поле не может быть null
    @NotNull
    private Double z; //Поле не может быть null
    @NotNull
    @NotEmpty
    private String name; //Строка не может быть пустой, Поле может быть null
}
