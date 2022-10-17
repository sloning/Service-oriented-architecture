package com.example.service2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderedRoutesDto {

    private Integer idFrom;
    private Integer idTo;
    private String orderBy;
}
