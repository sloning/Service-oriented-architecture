package com.example.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Coordinates implements Serializable {
    private Long x;
    private Double y;
}
