package com.example.evchargingstationapi.controller;



import com.example.evchargingstationapi.enums.StationStatus;
import com.example.evchargingstationapi.model.ChargingStation;
import com.example.evchargingstationapi.service.ChargingStationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChargingStationControllerTest {

    @Mock
    private ChargingStationService chargingStationService;

    @InjectMocks
    private ChargingStationController chargingStationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetChargingStationById() {
        // Arrange
        Long id = 1L;
        ChargingStation chargingStation = new ChargingStation();
        chargingStation.setId(id);
        when(chargingStationService.getChargingStationById(id)).thenReturn(Optional.of(chargingStation));

        // Act
        ResponseEntity<ChargingStation> response = chargingStationController.getChargingStationById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(chargingStation, response.getBody());
    }

    @Test
    public void testGetChargingStationByIdNotFound() {
        // Arrange
        Long id = 1L;
        when(chargingStationService.getChargingStationById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<ChargingStation> response = chargingStationController.getChargingStationById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testSaveChargingStation() {
        // Arrange
        ChargingStation chargingStationToSave = new ChargingStation();
        when(chargingStationService.saveChargingStation(chargingStationToSave)).thenReturn(chargingStationToSave);

        // Act
        ResponseEntity<ChargingStation> response = chargingStationController.saveChargingStation(chargingStationToSave);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(chargingStationToSave, response.getBody());
    }

    @Test
    public void testDeleteChargingStation() {
        // Arrange
        Long idToDelete = 1L;

        // Act
        ResponseEntity<Void> response = chargingStationController.deleteChargingStation(idToDelete);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(chargingStationService, times(1)).deleteChargingStation(idToDelete);
    }

    @Test
    public void testUpdateChargingStationStatus() {
        // Arrange
        Long idToUpdate = 1L;
        ChargingStation chargingStationToUpdate = new ChargingStation();
        chargingStationToUpdate.setId(idToUpdate);
        when(chargingStationService.updateChargingStationStatus(idToUpdate, StationStatus.IN_USE)).thenReturn(chargingStationToUpdate);

        // Act
        ResponseEntity<ChargingStation> response = chargingStationController.updateChargingStationStatus(idToUpdate, StationStatus.IN_USE);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(chargingStationToUpdate, response.getBody());
    }
}

