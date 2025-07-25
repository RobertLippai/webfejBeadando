package com.robertlippai.TreasureHunter.repository;

import com.robertlippai.TreasureHunter.model.TreasureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreasureRepository extends JpaRepository<TreasureEntity, Long> {
    //List<TreasureEntity> findByLocation(LocationEntity locationEntity);
    List<TreasureEntity> findByLocationId(long locationId);
    Boolean existsByNameEqualsIgnoreCaseAndDescriptionEqualsIgnoreCaseAndLocationId(String name, String description, long locationId);
}
