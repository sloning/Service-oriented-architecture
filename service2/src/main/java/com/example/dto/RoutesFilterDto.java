package com.example.dto;

import java.time.ZonedDateTime;

public class RoutesFilterDto {
    String nameFilter;
    Long coordinatesXFilter;
    Double coordinatesYFilter;
    ZonedDateTime creationDateFilter;
    Integer locationFromFilter;
    Integer locationToFilter;
    Float distanceFilter;
}
