package com.example.evchargingstationapi.service;

import com.example.evchargingstationapi.enums.StationStatus;
import com.example.evchargingstationapi.model.ChargingStation;
import com.example.evchargingstationapi.repository.ChargingStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ChargingStationService {

    private final ChargingStationRepository chargingStationRepository;

    @Autowired
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
    @Cacheable(value = "availabilityStatusCache")
    public StationStatus getAvailabilityStatus(Long id) {
        ChargingStation chargingStation = chargingStationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estación de carga no encontrada con ID: " + id));

        return chargingStation.getStatus();
    }
    public ChargingStation updateChargingStationStatus(Long id, StationStatus status) {
        Optional<ChargingStation> optionalChargingStation = chargingStationRepository.findById(id);
        if (optionalChargingStation.isPresent()) {
            ChargingStation chargingStation = optionalChargingStation.get();
            chargingStation.setStatus(status); // Aquí se establece el estado correctamente
            return chargingStationRepository.save(chargingStation);
        } else {
            throw new EntityNotFoundException("Charging Station not found with id: " + id);
        }
    }

    @CacheEvict(value = "availabilityStatusCache", allEntries = true)
    public void evictAllAvailabilityStatusCache() {
        // Este método eliminará todas las entradas de la caché
    }
}

