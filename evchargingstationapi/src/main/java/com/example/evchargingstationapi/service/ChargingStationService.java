package com.example.evchargingstationapi.service;

import com.example.evchargingstationapi.model.ChargingPoint;
import com.example.evchargingstationapi.model.ChargingStation;
import com.example.evchargingstationapi.enums.StationStatus;
import com.example.evchargingstationapi.model.Location;
import com.example.evchargingstationapi.repository.ChargingStationRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ChargingStationService {

    private final ChargingStationRepository chargingStationRepository;

    public ChargingStationService(ChargingStationRepository chargingStationRepository) {
        this.chargingStationRepository = chargingStationRepository;
    }

    public List<ChargingStation> getAllChargingStations() {
        return chargingStationRepository.findAll();
    }

    public Optional<ChargingStation> getChargingStationById(Long id) {
        return chargingStationRepository.findById(id);
    }

    public ChargingStation saveChargingStation(ChargingStation chargingStation) {
        return chargingStationRepository.save(chargingStation);
    }

    public void deleteChargingStation(Long id) {
        chargingStationRepository.deleteById(id);
    }

    public ChargingStation updateChargingStationStatus(Long id, StationStatus status) {
        ChargingStation chargingStation = chargingStationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Charging station not found with ID: " + id));
        chargingStation.setStatus(status);
        return chargingStationRepository.save(chargingStation);
    }

    public void addChargingPoint(Long stationId, ChargingPoint chargingPoint) {
        ChargingStation chargingStation = chargingStationRepository.findById(stationId)
                .orElseThrow(() -> new EntityNotFoundException("Charging station not found with ID: " + stationId));
        chargingStation.addChargingPoint(chargingPoint);
        chargingStationRepository.save(chargingStation);
    }

    public void removeChargingPoint(Long stationId, Long chargingPointId) {
        ChargingStation chargingStation = chargingStationRepository.findById(stationId)
                .orElseThrow(() -> new EntityNotFoundException("Charging station not found with ID: " + stationId));
        chargingStation.getChargingPoints().removeIf(chargingPoint -> chargingPoint.getId().equals(chargingPointId));
        chargingStationRepository.save(chargingStation);
    }

    @Cacheable(value = "availabilityStatusCache")
    public StationStatus getAvailabilityStatus(Long id) {
        ChargingStation chargingStation = chargingStationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estación de carga no encontrada con ID: " + id));

        return chargingStation.getStatus();
    }

    @CacheEvict(value = "availabilityStatusCache", allEntries = true)
    public void evictAllAvailabilityStatusCache() {
        // Este método eliminará todas las entradas de la caché
    }

    public Location getChargingStationLocation(Long id) {
        ChargingStation chargingStation = chargingStationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estación de carga no encontrada con ID: " + id));

        return chargingStation.getLocation();
    }

    public List<ChargingPoint> getAllChargingPoints(Long id) {
        ChargingStation chargingStation = chargingStationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Charging station not found with ID: " + id));
        return chargingStation.getChargingPoints();
    }

    public ChargingPoint getChargingPointAttributes(Long stationId, Long chargingPointId) {
        ChargingStation chargingStation = chargingStationRepository.findById(stationId)
                .orElseThrow(() -> new EntityNotFoundException("Charging station not found with ID: " + stationId));
        return chargingStation.getChargingPoints().stream()
                .filter(chargingPoint -> chargingPoint.getId().equals(chargingPointId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Charging point not found with ID: " + chargingPointId));
    }
}
