package com.robertlippai.TreasureHunter.service;

import com.robertlippai.TreasureHunter.dto.TreasureCreateResultDto;
import com.robertlippai.TreasureHunter.dto.TreasureDto;
import java.util.List;

public interface TreasureService {
    List<TreasureDto> getAllTreasures();

    TreasureDto getTreasureById(long id);

    // kell a locationID hogy a location-höz tudjuk kötni
    TreasureCreateResultDto createTreasure(long locationId, TreasureDto treasureMapDto);

    TreasureDto updateTreasure(TreasureDto mapDto, long id);
    void markTreasureAsFound(Long treasureId, Long unlockId);

    void deleteTreasureById(long id);

    List<TreasureDto> getTreasuresByLocationId(long id);
}
