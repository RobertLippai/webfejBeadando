package com.robertlippai.TreasureHunter.controller;

import com.robertlippai.TreasureHunter.dto.LocationDto;
import com.robertlippai.TreasureHunter.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/api/location")
@CrossOrigin(origins = "http://localhost:4200")
public class LocationController {
    private LocationService locationService;
    private Boolean wasSampled = false;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/get-locations")
    public ResponseEntity<List<LocationDto>> getAllLocations(){
        return new ResponseEntity<>(locationService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping("/get-location/{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable Long id){
        return new ResponseEntity<>(locationService.getLocation(id), HttpStatus.OK);
    }

    @PostMapping("/add-location")
    public ResponseEntity<LocationDto> addLocation(@RequestBody LocationDto locationDto){
        // nem az Id-et kell visszadni, hanem az egész objektumot amit készítettünk
        LocationDto newLocationDto = locationService.createLocation(locationDto);

        return new ResponseEntity<>(newLocationDto, HttpStatus.CREATED);
    }

    @PutMapping("/update-location/{id}")  // Put-ban updatelünk!
    public ResponseEntity<LocationDto> updateLocationById(@PathVariable Long id, @RequestBody LocationDto newMapData){
        LocationDto updatedMap = locationService.updateLocation(id, newMapData);
        return new ResponseEntity<>(updatedMap, HttpStatus.OK);
    }

    @DeleteMapping("/delete-location/{id}")
    public ResponseEntity<HttpStatus> deleteLocationById(@PathVariable Long id){
        // ha kitörüljük az ID felszabadul de a DBMS nem fogja újra felhasználni
        // ami elméletileg normális és így lehet hagyni
        locationService.deleteLocation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/sample-locations")
    public ResponseEntity<HttpStatus> initLocations(){
        // https://stackoverflow.com/questions/28147654/difference-between-getandset-and-compareandset-in-atomicboolean
        if(wasSampled){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        wasSampled = true;

        locationService.createLocation(new LocationDto(1L, "Budapest", "2328739 7878"));
        locationService.createLocation(new LocationDto(1L, "Debreen", "32123 1212"));
        locationService.createLocation(new LocationDto(1L, "Nyíregyháza", "9123 21428"));
        locationService.createLocation(new LocationDto(1L, "Ibrány", "3424 22123"));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}