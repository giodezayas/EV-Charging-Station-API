package com.example.evchargingstationapi.controller;

import com.example.evchargingstationapi.model.ChargingPoint;
import com.example.evchargingstationapi.model.ChargingStation;
import com.example.evchargingstationapi.enums.StationStatus;
import com.example.evchargingstationapi.model.Location;
import com.example.evchargingstationapi.service.ChargingStationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ChargingStationControllerTest {

    @Mock
    private ChargingStationService chargingStationService;

    @InjectMocks
    private ChargingStationController chargingStationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(chargingStationController).build();
    }

    @Test
    public void testGetAllChargingStations() throws Exception {
        List<ChargingStation> chargingStations = new ArrayList<>();
        when(chargingStationService.getAllChargingStations()).thenReturn(chargingStations);

        mockMvc.perform(get("/charging-stations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(chargingStationService, times(1)).getAllChargingStations();
    }

    @Test
    public void testGetChargingStationById() throws Exception {
        Long id = 1L;
        ChargingStation chargingStation = new ChargingStation();
        when(chargingStationService.getChargingStationById(id)).thenReturn(Optional.of(chargingStation));

        mockMvc.perform(get("/charging-stations/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id));

        verify(chargingStationService, times(1)).getChargingStationById(id);
    }

    @Test
    public void testSaveChargingStation() throws Exception {
        ChargingStation chargingStation = new ChargingStation();
        when(chargingStationService.saveChargingStation(chargingStation)).thenReturn(chargingStation);

        mockMvc.perform(post("/charging-stations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"stationId\": \"1\", \"location\": { \"latitude\": 40.7128, \"longitude\": -74.0060 }, \"chargerType\": \"AC\", \"numberOfChargingPoints\": 2, \"status\": \"AVAILABLE\" }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists());

        verify(chargingStationService, times(1)).saveChargingStation(chargingStation);
    }

    @Test
    public void testDeleteChargingStation() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/charging-stations/{id}", id))
                .andExpect(status().isOk());

        verify(chargingStationService, times(1)).deleteChargingStation(id);
    }

    @Test
    public void testUpdateChargingStationStatus() throws Exception {
        Long id = 1L;
        StationStatus status = StationStatus.IN_USE;
        ChargingStation chargingStation = new ChargingStation();
        when(chargingStationService.updateChargingStationStatus(id, status)).thenReturn(chargingStation);

        mockMvc.perform(put("/charging-stations/{id}/status", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"IN_USE\""))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(status.toString()));

        verify(chargingStationService, times(1)).updateChargingStationStatus(id, status);
    }

    @Test
    public void testGetChargingStationAvailabilityStatus() throws Exception {
        Long id = 1L;
        StationStatus status = StationStatus.AVAILABLE;
        when(chargingStationService.getAvailabilityStatus(id)).thenReturn(status);

        mockMvc.perform(get("/charging-stations/{id}/status", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(status.toString()));

        verify(chargingStationService, times(1)).getAvailabilityStatus(id);
    }

    @Test
    public void testGetChargingStationLocation() throws Exception {
        Long id = 1L;
        Location location = new Location("Direcction1",40.7128, -74.0060);
        when(chargingStationService.getChargingStationLocation(id)).thenReturn(location);

        mockMvc.perform(get("/charging-stations/{id}/location", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.latitude").value(location.getLatitude()))
                .andExpect(jsonPath("$.longitude").value(location.getLongitude()));

        verify(chargingStationService, times(1)).getChargingStationLocation(id);
    }




}
