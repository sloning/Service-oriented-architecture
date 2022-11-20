package com.example.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "location")
public class Location implements WithName, Serializable {
    @Id
    @GeneratedValue
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
