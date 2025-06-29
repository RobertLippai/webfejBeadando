package com.robertlippai.TreasureHunter.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreasureDto {
    private Long id;
    private String name;
    private String description;
    private Boolean wasFound;
    private String locationName;
}