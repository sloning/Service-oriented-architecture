package com.example.service1.dto;

import com.example.service1.model.Location;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class RouteUpdateRequestDto {
    @Min(1)
    private Integer id;
    @NotEmpty
    private String name;
    private Long x;
    private Double y;
    private ZonedDateTime creationDate;
    private Location from;
    private Location to;
    @DecimalMin(value = "1", inclusive = false)
    private Float distance;
}
