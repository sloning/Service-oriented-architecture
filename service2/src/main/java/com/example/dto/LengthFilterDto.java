package com.example.dto;

import com.example.model.Length;
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
