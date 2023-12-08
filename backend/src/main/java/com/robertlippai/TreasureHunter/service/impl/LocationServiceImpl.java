package com.robertlippai.TreasureHunter.service.impl;

import com.robertlippai.TreasureHunter.dto.LocationDto;
import com.robertlippai.TreasureHunter.exceptions.LocationAlreadyExists;
import com.robertlippai.TreasureHunter.exceptions.LocationNotFoundException;
import com.robertlippai.TreasureHunter.model.LocationEntity;
import com.robertlippai.TreasureHunter.repository.LocationRepository;
import com.robertlippai.TreasureHunter.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    private LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<LocationDto> getAllLocations() {
        List<LocationEntity> locationEntityList = locationRepository.findAll();

        return locationEntityList.stream().map(x -> mapToDto(x)).collect(Collectors.toList());
    }

    @Override
    public LocationDto getLocation(long id) {
        LocationEntity locationEntity = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location could not be found."));

        return mapToDto(locationEntity);
    }

    @Override
    public LocationDto createLocation(LocationDto locationDto) {
        if(locationRepository.existsByNameEqualsIgnoreCaseAndCoordinatesEqualsIgnoreCase
                (locationDto.getName(),
                locationDto.getCoordinates())) {
            throw new LocationAlreadyExists("Location already exists.");
        }

        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setName(locationDto.getName());
        locationEntity.setCoordinates(locationDto.getCoordinates());

        LocationEntity newLocationEntity = locationRepository.save(locationEntity);

        return mapToDto(newLocationEntity);
    }

    @Override
    public LocationDto updateLocation(long id, LocationDto locationDto) {
        LocationEntity locationEntity = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location could not be updated."));

        // az id-et sose változtatjuk meg! mert az új rekordot eredményezne
        locationEntity.setName(locationDto.getName());
        locationEntity.setCoordinates(locationDto.getCoordinates());

        LocationEntity updatedLocation = locationRepository.save(locationEntity);
        return mapToDto(updatedLocation);
    }

    @Override
    public void deleteLocation(long id) {
        LocationEntity location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location could not be deleted."));

        locationRepository.delete(location);
    }

    private LocationDto mapToDto(LocationEntity locationEntity){
        LocationDto locationDto = new LocationDto();

        locationDto.setId(locationEntity.getId());
        locationDto.setName(locationEntity.getName());
        locationDto.setCoordinates(locationEntity.getCoordinates());

        return locationDto;
    }

    private LocationEntity mapToEntity(LocationDto locationDto){
        LocationEntity locationEntity = new LocationEntity();

        locationEntity.setId(locationDto.getId());
        locationEntity.setName(locationDto.getName());
        locationEntity.setCoordinates(locationDto.getCoordinates());

        return locationEntity;
    }
}
