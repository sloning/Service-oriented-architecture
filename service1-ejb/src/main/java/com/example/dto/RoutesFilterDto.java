package com.example.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
public class RoutesFilterDto implements Serializable {
    private String nameFilter;
    private Long coordinatesXFilter;
    private Double coordinatesYFilter;
    private ZonedDateTime creationDateFilter;
    private Integer locationFromFilter;
    private Integer locationToFilter;
    private Float distanceFilter;
}
