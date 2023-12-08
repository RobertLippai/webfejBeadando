package com.robertlippai.TreasureHunter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // ez nem kell csak az init miatt a locationControllerben
@NoArgsConstructor
public class LocationDto {
    private Long id;
    private String name;
    private String coordinates;
}
