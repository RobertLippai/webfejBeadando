package com.robertlippai.TreasureHunter.service;

import com.robertlippai.TreasureHunter.dto.LocationDto;

import java.util.List;

public interface LocationService {
    List<LocationDto> getAllLocations();

    LocationDto getLocation(long id);

    LocationDto createLocation(LocationDto locationDto);

    LocationDto updateLocation(long id, LocationDto locationDto);

    void deleteLocation(long id);
}
