package com.example.service2.dto;

import lombok.Data;

@Data
public class OrderedRoutesDto {

    private final Integer idFrom;
    private final Integer idTo;
    private final String orderBy;
}
