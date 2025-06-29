package com.robertlippai.TreasureHunter.service.impl;

import com.robertlippai.TreasureHunter.dto.TreasureCreateResultDto;
import com.robertlippai.TreasureHunter.dto.TreasureDto;
import com.robertlippai.TreasureHunter.exceptions.*;
import com.robertlippai.TreasureHunter.model.LocationEntity;
import com.robertlippai.TreasureHunter.model.TreasureEntity;
import com.robertlippai.TreasureHunter.repository.LocationRepository;
import com.robertlippai.TreasureHunter.repository.TreasureRepository;
import com.robertlippai.TreasureHunter.service.TreasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TreasureServiceImpl implements TreasureService {
    private TreasureRepository treasureRepository;
    private LocationRepository locationRepository;

    @Autowired
    public TreasureServiceImpl(TreasureRepository treasureMapRepository, LocationRepository locationRepository) {
        this.treasureRepository = treasureMapRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<TreasureDto> getAllTreasures() {
        List<TreasureEntity> treasureEntityList = treasureRepository.findAll();
        for (TreasureEntity entity: treasureEntityList) {
            System.out.println(entity.getName() + " " + entity.getUnlockID());
        }
        return treasureEntityList.stream().map(x -> mapToDto(x)).collect(Collectors.toList());
    }

    @Override
    public TreasureDto getTreasureById(long id) {
        TreasureEntity treasureEntity = treasureRepository.findById(id)
                .orElseThrow(() -> new TreasureNotFoundException("Treasure could not be found."));

        return mapToDto(treasureEntity);
    }

    @Override
    public List<TreasureDto> getTreasuresByLocationId(long id) {
        List<TreasureEntity> treasureEntities = treasureRepository.findByLocationId(id);

        return treasureEntities.stream().map( x -> mapToDto(x)).collect(Collectors.toList());
    }

    @Override
    public TreasureCreateResultDto createTreasure(long locationId, TreasureDto treasureMapDto) {
        if(treasureRepository.existsByNameEqualsIgnoreCaseAndDescriptionEqualsIgnoreCaseAndLocationId
                (treasureMapDto.getName(),
                        treasureMapDto.getDescription(),
                        locationId
                        )) {
            throw new TreasureAlreadyExists("Treasure already exists.");
        }

        long unlockCode = generateUnlockID();

        TreasureEntity treasureEntity = new TreasureEntity();
        treasureEntity.setName(treasureMapDto.getName());
        treasureEntity.setDescription(treasureMapDto.getDescription());
        treasureEntity.setWasFound(treasureMapDto.getWasFound());
        treasureEntity.setUnlockID(unlockCode);

        LocationEntity locationEntity = locationRepository.findById(locationId)
                .orElseThrow(() -> new LocationNotFoundException("Location could not be found."));

        treasureEntity.setLocation(locationEntity);

        TreasureEntity newTreasure = treasureRepository.save(treasureEntity);

        return new TreasureCreateResultDto(mapToDto(newTreasure), Long.toString(unlockCode));
    }

    @Override
    public TreasureDto updateTreasure(TreasureDto treasureDto, long id) {
        TreasureEntity treasureMap = treasureRepository.findById(id)
                .orElseThrow(() -> new TreasureNotFoundException("Treasure could not be updated."));

        treasureMap.setName(treasureDto.getName());
        treasureMap.setDescription(treasureDto.getDescription());
        treasureMap.setWasFound(treasureDto.getWasFound());

        TreasureEntity updatedMap = treasureRepository.save(treasureMap);
        return mapToDto(updatedMap);
    }

    public void markTreasureAsFound(Long treasureId, Long unlockId) {
        TreasureEntity treasureEntity = treasureRepository.findById(treasureId)
                .orElseThrow(() -> new TreasureNotFoundException("Incorrect unlockID or treasure not found."));

        if(!Objects.equals(unlockId, treasureEntity.getUnlockID())){
            throw new IncorrectUnlockCodeException("Incorrect unlockID");
        }

        treasureEntity.setWasFound(true);
        treasureRepository.save(treasureEntity);
    }

    @Override
    public void deleteTreasureById(long id) {
        TreasureEntity treasureEntity = treasureRepository.findById(id)
                .orElseThrow(() -> new TreasureNotFoundException("Treasure could not be deleted."));

        treasureRepository.delete(treasureEntity);
    }

    private TreasureDto mapToDto(TreasureEntity treasureMap){
        TreasureDto treasureMapDto = new TreasureDto();

        treasureMapDto.setId(treasureMap.getId());
        treasureMapDto.setName(treasureMap.getName());
        treasureMapDto.setDescription(treasureMap.getDescription());
        treasureMapDto.setWasFound(treasureMap.getWasFound());
        treasureMapDto.setLocationName(treasureMap.getLocation().getName());

        return treasureMapDto;
    }

    private TreasureEntity mapToEntity(TreasureDto treasureMapDto){
        TreasureEntity treasureMap = new TreasureEntity();

        treasureMap.setId(treasureMapDto.getId());
        treasureMap.setName(treasureMapDto.getName());
        treasureMap.setDescription(treasureMapDto.getDescription());
        treasureMap.setWasFound(treasureMapDto.getWasFound());

        return treasureMap;
    }

    private Long generateUnlockID() {
        return new Random().nextLong();
    }
}
