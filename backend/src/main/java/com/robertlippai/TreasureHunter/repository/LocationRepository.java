package com.robertlippai.TreasureHunter.repository;

import com.robertlippai.TreasureHunter.model.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    Boolean existsByNameEqualsIgnoreCaseAndCoordinatesEqualsIgnoreCase(String name, String coordinates);
}
