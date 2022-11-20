package com.example.dto;

import com.example.model.Location;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class RouteUpdateRequestDto implements Serializable {
    @Min(1)
    private Integer id;
    private String name;
    private Long x;
    private Double y;
    private ZonedDateTime creationDate;
    private Location from;
    private Location to;
    @DecimalMin(value = "1", inclusive = false)
    private Float distance;
}
