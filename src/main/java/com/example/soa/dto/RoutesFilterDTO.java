package com.example.soa.dto;

import java.time.ZonedDateTime;

public class RoutesFilterDTO {
    String nameFilter;
    Long coordinatesXFilter;
    Double coordinatesYFilter;
    ZonedDateTime creationDateFilter;
    Integer locationFromFilter;
    Integer locationToFilter;
    Float distanceFilter;
}
