package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "route", schema = "public")
public class Route implements WithName, Serializable {
    @Min(1)
    @Id
    @GeneratedValue
    private Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotNull
    @NotEmpty
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull
    @Embedded
    private Coordinates coordinates; //Поле не может быть null
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @Column(name = "creation_date")
    private ZonedDateTime creationDate = ZonedDateTime.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @NotNull
    @ManyToOne
    private Location from; //Поле не может быть null
    @NotNull
    @ManyToOne
    private Location to; //Поле может быть null
    @DecimalMin(value = "1", inclusive = false)
    private Float distance; //Значение поля должно быть больше 1
}
