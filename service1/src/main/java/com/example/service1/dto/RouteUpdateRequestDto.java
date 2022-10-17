package com.example.service1.dto;

import com.example.service1.model.Coordinates;
import com.example.service1.model.Location;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.ZonedDateTime;

@Data
public class RouteUpdateRequestDto {
    @Min(1)
    private final Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotEmpty
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final Location from; //Поле не может быть null
    private final Location to; //Поле может быть null
    @DecimalMin(value = "1", inclusive = false)
    private final Float distance; //Значение поля должно быть больше 1
}
