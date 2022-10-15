package com.example.service1.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class RoutesFilterDto {
    private String nameFilter;
    private Long coordinatesXFilter;
    private Double coordinatesYFilter;
    private ZonedDateTime creationDateFilter;
    private Integer locationFromFilter;
    private Integer locationToFilter;
    private Float distanceFilter;
}
