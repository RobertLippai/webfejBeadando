package com.robertlippai.TreasureHunter.controller;

import com.robertlippai.TreasureHunter.dto.TreasureCreateResultDto;
import com.robertlippai.TreasureHunter.dto.TreasureDto;
import com.robertlippai.TreasureHunter.service.TreasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treasure")
@CrossOrigin(origins = "http://localhost:4200")
public class TreasureController {
    private TreasureService treasureService;
    private Boolean wasSampled = false;

    @Autowired
    public TreasureController(TreasureService treasureService) {
        this.treasureService = treasureService;
    }

    @GetMapping("/get-treasures")
    public ResponseEntity<List<TreasureDto>> getAllTreasures(){
        return new ResponseEntity<>(treasureService.getAllTreasures(), HttpStatus.OK);
    }

    @GetMapping("/get-treasure/{id}")
    public ResponseEntity<TreasureDto> getTreasureById(@PathVariable Long id){
        return new ResponseEntity<>(treasureService.getTreasureById(id), HttpStatus.OK);
    }

    @GetMapping("/get-treasures-by-location/{id}")
    public ResponseEntity<List<TreasureDto>> getTreasureByLocationId(@PathVariable Long id){
        List<TreasureDto> treasureDtoList=  treasureService.getTreasuresByLocationId(id);
        return new ResponseEntity<>(treasureDtoList, HttpStatus.OK);
    }

    @PostMapping("/add-treasure/{locationId}")
    public ResponseEntity<TreasureCreateResultDto> addTreasure(@PathVariable Long locationId, @RequestBody TreasureDto treasureDto){
        TreasureCreateResultDto newTreasureDto = treasureService.createTreasure(locationId, treasureDto);

        return new ResponseEntity<>(newTreasureDto, HttpStatus.CREATED);
    }

    @PutMapping("/update-treasure/{id}")
    public ResponseEntity<TreasureDto> updateTreasuresById(@PathVariable Long id, @RequestBody TreasureDto newTreasureDto){
        TreasureDto updateTreasure = treasureService.updateTreasure(newTreasureDto, id);
        return new ResponseEntity<>(updateTreasure, HttpStatus.OK);
    }

    @PostMapping("/mark-as-found/{id}")
    public ResponseEntity<String> markTreasureAsFound(@PathVariable(name = "id") Long treasureId, @RequestParam Long unlockId) {
        treasureService.markTreasureAsFound(treasureId, unlockId);
        return new ResponseEntity<>("Treasure unlocked successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/delete-treasure/{id}")
    public ResponseEntity<HttpStatus> deleteTreasuresById(@PathVariable Long id){
        treasureService.deleteTreasureById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sample-treasures")
    public ResponseEntity<HttpStatus> initTreasures(){
        if(wasSampled){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        wasSampled = true;

        treasureService.createTreasure(1L, new TreasureDto(1L, "Ezüst Barlang", "A bejáratnál, a nagy kő alatt találod.", false, null));
        treasureService.createTreasure(2L, new TreasureDto(1L, "Zafír Forrás", "A folyó partján, a három nagy fa között rejtve.", true, null));
        treasureService.createTreasure(3L, new TreasureDto(1L, "Gyémánt Barlang", "A barlang mélyén, a kristályok között rejtve.", false, null));
        treasureService.createTreasure(4L, new TreasureDto(1L, "Rubin Hegy", "A csúcson, a kis barlangban rejtve.", false, null));
        treasureService.createTreasure(2L, new TreasureDto(1L, "Arany Tó", "A tó déli partján, a nagy kő alatt található.", true, null));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
