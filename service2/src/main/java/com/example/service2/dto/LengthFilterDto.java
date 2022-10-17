package com.example.service2.dto;

import com.example.service2.model.Length;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LengthFilterDto {

    private Integer idFrom;
    private Integer idTo;
    private Length length;
}
