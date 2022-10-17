package com.example.service2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class OrderedRoutesDto {

    @Min(1)
    private Integer idFrom;
    @Min(1)
    private Integer idTo;
    private String orderBy;
}
