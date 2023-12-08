package com.robertlippai.TreasureHunter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TreasureCreateResultDto {
    private TreasureDto treasureDto;
    private String unlockID;

    // azért lett String az unlockID mert a böngészők valamiért kerekítenének a Long-on
}
