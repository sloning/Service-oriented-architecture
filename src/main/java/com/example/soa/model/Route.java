package com.example.soa.model;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Route implements WithName {
    @Min(1)
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotNull
    @NotEmpty
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull
    private Coordinates coordinates; //Поле не может быть null
    @NotNull
    private java.time.ZonedDateTime creationDate = java.time.ZonedDateTime.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @NotNull
    private Location from; //Поле не может быть null
    @NotNull
    private Location to; //Поле может быть null
    @DecimalMin(value = "1", inclusive = false)
    private float distance; //Значение поля должно быть больше 1
}
