package com.example.evchargingstationapi.service;

import com.example.evchargingstationapi.model.ChargingStation;
import com.example.evchargingstationapi.enums.ChargerType;
import com.example.evchargingstationapi.enums.StationStatus;
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
        station1.setId(1L);
        station1.setLocation("Location 1");
        station1.setChargerType(ChargerType.AC);
        station1.setNumberOfChargingPoints(2);
        expectedStations.add(station1);
        ChargingStation station2 = new ChargingStation();
        station2.setId(2L);
        station2.setLocation("Location 2");
        station2.setChargerType(ChargerType.DC_FAST);
        station2.setNumberOfChargingPoints(1);
        expectedStations.add(station2);
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
        expectedStation.setLocation("Location 1");
        expectedStation.setChargerType(ChargerType.AC);
        expectedStation.setNumberOfChargingPoints(2);
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
        chargingStationToSave.setLocation("Location 1");
        chargingStationToSave.setChargerType(ChargerType.AC);
        chargingStationToSave.setNumberOfChargingPoints(2);
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

}
