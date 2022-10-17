package com.example.service2.dto;

import com.example.service2.model.Length;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class LengthFilterDto {

    @Min(1)
    private Integer idFrom;
    @Min(1)
    private Integer idTo;
    private Length length;
}
