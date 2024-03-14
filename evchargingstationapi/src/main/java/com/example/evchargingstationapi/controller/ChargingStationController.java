package com.example.evchargingstationapi.controller;

import com.example.evchargingstationapi.model.ChargingPoint;
import com.example.evchargingstationapi.model.ChargingStation;
import com.example.evchargingstationapi.enums.StationStatus;
import com.example.evchargingstationapi.model.Location;
import com.example.evchargingstationapi.service.ChargingStationService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/charging-stations")
public class ChargingStationController {

    private final ChargingStationService chargingStationService;

    public ChargingStationController(ChargingStationService chargingStationService) {
        this.chargingStationService = chargingStationService;
    }

    @GetMapping
    public List<ChargingStation> getAllChargingStations() {
        return chargingStationService.getAllChargingStations();
    }

    @GetMapping("/{id}")
    public ChargingStation getChargingStationById(@PathVariable Long id) {
        return chargingStationService.getChargingStationById(id)
                .orElseThrow(() -> new EntityNotFoundException("Charging station not found with ID: " + id));
    }

    @PostMapping
    public ChargingStation saveChargingStation(@RequestBody ChargingStation chargingStation) {
        return chargingStationService.saveChargingStation(chargingStation);
    }

    @DeleteMapping("/{id}")
    public void deleteChargingStation(@PathVariable Long id) {
        chargingStationService.deleteChargingStation(id);
    }

    @PutMapping("/{id}/status")
    public ChargingStation updateChargingStationStatus(@PathVariable Long id, @RequestParam StationStatus status) {
        return chargingStationService.updateChargingStationStatus(id, status);
    }

    @PostMapping("/{id}/charging-points")
    public void addChargingPoint(@PathVariable Long id, @RequestBody ChargingPoint chargingPoint) {
        chargingStationService.addChargingPoint(id, chargingPoint);
    }

    @DeleteMapping("/{stationId}/charging-points/{chargingPointId}")
    public void removeChargingPoint(@PathVariable Long stationId, @PathVariable Long chargingPointId) {
        chargingStationService.removeChargingPoint(stationId, chargingPointId);
    }

    @GetMapping("/{id}/availability-status")
    public StationStatus getAvailabilityStatus(@PathVariable Long id) {
        return chargingStationService.getAvailabilityStatus(id);
    }

    @PostMapping("/clear-cache")
    public void evictAllAvailabilityStatusCache() {
        chargingStationService.evictAllAvailabilityStatusCache();
    }

    @GetMapping("/{id}/location")
    public Location getChargingStationLocation(@PathVariable Long id) {
        return chargingStationService.getChargingStationLocation(id);
    }

    @GetMapping("/{id}/charging-points")
    public List<ChargingPoint> getAllChargingPoints(@PathVariable Long id) {
        return chargingStationService.getAllChargingPoints(id);
    }

    @GetMapping("/{stationId}/charging-points/{chargingPointId}")
    public ChargingPoint getChargingPointAttributes(@PathVariable Long stationId, @PathVariable Long chargingPointId) {
        return chargingStationService.getChargingPointAttributes(stationId, chargingPointId);
    }
}
