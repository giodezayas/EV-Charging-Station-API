package com.example.evchargingstationapi.model;



import com.example.evchargingstationapi.enums.ChargerType;
import com.example.evchargingstationapi.enums.StationStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChargingStationTest {

    @Test
    public void testChargingStationInitialization() {
        ChargingStation station = new ChargingStation();
        assertNotNull(station);
    }

    @Test
    public void testChargingStationId() {
        ChargingStation station = new ChargingStation();
        station.setStationId("ABC123");
        assertEquals("ABC123", station.getStationId());
    }

    @Test
    public void testChargingStationLocation() {
        ChargingStation station = new ChargingStation();
        station.setLocation("123 Main St, City, Country");
        assertEquals("123 Main St, City, Country", station.getLocation());
    }

    @Test
    public void testChargingStationChargerType() {
        ChargingStation station = new ChargingStation();
        station.setChargerType(ChargerType.AC);
        assertEquals(ChargerType.AC, station.getChargerType());
    }

    @Test
    public void testChargingStationNumberOfChargingPoints() {
        ChargingStation station = new ChargingStation();
        station.setNumberOfChargingPoints(2);
        assertEquals(2, station.getNumberOfChargingPoints());
    }

    @Test
    public void testChargingStationStatus() {
        ChargingStation station = new ChargingStation();
        station.setStatus(StationStatus.AVAILABLE);
        assertEquals(StationStatus.AVAILABLE, station.getStatus());
    }
}

