package com.example.evchargingstationapi.controller;

import com.example.evchargingstationapi.enums.StationStatus;
import com.example.evchargingstationapi.model.ChargingStation;
import com.example.evchargingstationapi.service.ChargingStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/charging-stations")
public class ChargingStationController {

    private final ChargingStationService chargingStationService;

    @Autowired
    public ChargingStationController(ChargingStationService chargingStationService) {
        this.chargingStationService = chargingStationService;
    }

    @GetMapping
    public ResponseEntity<List<ChargingStation>> getAllChargingStations() {
        List<ChargingStation> chargingStations = chargingStationService.getAllChargingStations();
        return new ResponseEntity<>(chargingStations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChargingStation> getChargingStationById(@PathVariable Long id) {
        return chargingStationService.getChargingStationById(id)
                .map(station -> new ResponseEntity<>(station, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ChargingStation> saveChargingStation(@RequestBody ChargingStation chargingStation) {
        ChargingStation savedChargingStation = chargingStationService.saveChargingStation(chargingStation);
        return new ResponseEntity<>(savedChargingStation, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChargingStation(@PathVariable Long id) {
        chargingStationService.deleteChargingStation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ChargingStation> updateChargingStationStatus(@PathVariable Long id, @RequestParam StationStatus status) {
        ChargingStation updatedChargingStation = chargingStationService.updateChargingStationStatus(id, status);
        return new ResponseEntity<>(updatedChargingStation, HttpStatus.OK);
    }
}

