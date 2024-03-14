package com.example.evchargingstationapi.service;

import com.example.evchargingstationapi.enums.ChargerType;
import com.example.evchargingstationapi.enums.PowerLevel;
import com.example.evchargingstationapi.enums.StationStatus;
import com.example.evchargingstationapi.model.ChargingPoint;
import com.example.evchargingstationapi.model.ChargingStation;
import com.example.evchargingstationapi.model.Location;
import com.example.evchargingstationapi.repository.ChargingStationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChargingStationServiceTest {

    @Mock
    private ChargingStationRepository chargingStationRepository;

    @InjectMocks
    private ChargingStationService chargingStationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllChargingStations() {
        // Arrange
        List<ChargingStation> expectedStations = new ArrayList<>();
        ChargingStation station1 = new ChargingStation();
        ChargingPoint chargingPoint1 = new ChargingPoint();
        chargingPoint1.setId(1L);
        station1.addChargingPoint(chargingPoint1);
        expectedStations.add(station1);
        when(chargingStationRepository.findAll()).thenReturn(expectedStations);

        // Act
        List<ChargingStation> actualStations = chargingStationService.getAllChargingStations();

        // Assert
        assertEquals(expectedStations.size(), actualStations.size());
        assertEquals(expectedStations, actualStations);
    }

    @Test
    public void testGetChargingStationById() {
        // Arrange
        ChargingStation expectedStation = new ChargingStation();
        expectedStation.setId(1L);
        when(chargingStationRepository.findById(1L)).thenReturn(Optional.of(expectedStation));

        // Act
        Optional<ChargingStation> actualStation = chargingStationService.getChargingStationById(1L);

        // Assert
        assertEquals(expectedStation, actualStation.get());
    }

    @Test
    public void testSaveChargingStation() {
        // Arrange
        ChargingStation chargingStationToSave = new ChargingStation();
        chargingStationToSave.setId(1L);
        when(chargingStationRepository.save(chargingStationToSave)).thenReturn(chargingStationToSave);

        // Act
        ChargingStation savedChargingStation = chargingStationService.saveChargingStation(chargingStationToSave);

        // Assert
        assertEquals(chargingStationToSave, savedChargingStation);
    }

    @Test
    public void testDeleteChargingStation() {
        // Arrange
        Long idToDelete = 1L;

        // Act
        chargingStationService.deleteChargingStation(idToDelete);

        // Assert
        verify(chargingStationRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    public void testUpdateChargingStationStatus() {
        // Arrange
        Long idToUpdate = 1L;
        ChargingStation chargingStationToUpdate = new ChargingStation();
        chargingStationToUpdate.setId(idToUpdate);
        chargingStationToUpdate.setStatus(StationStatus.AVAILABLE);

        ChargingStation updatedChargingStation = new ChargingStation();
        updatedChargingStation.setId(idToUpdate);
        updatedChargingStation.setStatus(StationStatus.IN_USE);

        when(chargingStationRepository.findById(idToUpdate)).thenReturn(Optional.of(chargingStationToUpdate));
        when(chargingStationRepository.save(chargingStationToUpdate)).thenReturn(updatedChargingStation);

        // Act
        ChargingStation result = chargingStationService.updateChargingStationStatus(idToUpdate, StationStatus.IN_USE);

        // Assert
        assertEquals(updatedChargingStation, result);
    }





    @Test
    public void testGetAvailabilityStatus() {
        // Arrange
        Long id = 1L;
        ChargingStation chargingStation = new ChargingStation();
        chargingStation.setId(id);
        chargingStation.setStatus(StationStatus.AVAILABLE);
        when(chargingStationRepository.findById(id)).thenReturn(Optional.of(chargingStation));

        // Act
        StationStatus result = chargingStationService.getAvailabilityStatus(id);

        // Assert
        assertEquals(StationStatus.AVAILABLE, result);
    }

    @Test
    public void testEvictAllAvailabilityStatusCache() {
        // Act
        chargingStationService.evictAllAvailabilityStatusCache();

        // Assert - Verificar que no se lancen excepciones
    }

    @Test
    public void testGetChargingStationLocation() {
        // Arrange
        Long id = 1L;
        ChargingStation chargingStation = new ChargingStation();
        chargingStation.setId(id);
        chargingStation.setLocation(new Location("Address", 123.456, 789.012));
        when(chargingStationRepository.findById(id)).thenReturn(Optional.of(chargingStation));

        // Act
        Location result = chargingStationService.getChargingStationLocation(id);

        // Assert
        assertEquals(chargingStation.getLocation(), result);
    }

    @Test
    public void testGetChargingStationChargingPoints() {
        // Arrange
        Long id = 1L;
        ChargingStation chargingStation = new ChargingStation();
        chargingStation.setId(id);
        ChargingPoint chargingPoint1 = new ChargingPoint();
        chargingPoint1.setId(1L);
        chargingStation.addChargingPoint(chargingPoint1);
        ChargingPoint chargingPoint2 = new ChargingPoint();
        chargingPoint2.setId(2L);
        chargingStation.addChargingPoint(chargingPoint2);
        when(chargingStationRepository.findById(id)).thenReturn(Optional.of(chargingStation));

        // Act
        List<ChargingPoint> result = chargingStationService.getAllChargingPoints(id);

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testGetChargingPointAttributes() {
        // Arrange
        Long stationId = 1L;
        Long chargingPointId = 1L;
        ChargingStation chargingStation = new ChargingStation();
        chargingStation.setId(stationId);
        ChargingPoint chargingPoint = new ChargingPoint();
        chargingPoint.setId(chargingPointId);
        chargingPoint.setIdentifier("CP1");
        chargingPoint.setStatus(StationStatus.AVAILABLE);
        chargingPoint.setPowerLevel(PowerLevel.LOW);
        chargingStation.addChargingPoint(chargingPoint);
        when(chargingStationRepository.findById(stationId)).thenReturn(Optional.of(chargingStation));

        // Act
        ChargingPoint result = chargingStationService.getChargingPointAttributes(stationId, chargingPointId);

        // Assert
        assertEquals(chargingPoint, result);
    }
}
