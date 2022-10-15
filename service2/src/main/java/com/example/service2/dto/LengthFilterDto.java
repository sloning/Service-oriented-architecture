package com.example.service2.dto;

import com.example.service2.model.Length;
import lombok.Data;

@Data
public class LengthFilterDto {

    private final Integer idFrom;
    private final Integer idTo;
    private final Length length;
}
