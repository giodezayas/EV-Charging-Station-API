package com.example.evchargingstationapi.model;



import com.example.evchargingstationapi.enums.ChargerType;
import com.example.evchargingstationapi.enums.StationStatus;
import org.junit.jupiter.api.Test;
import com.example.evchargingstationapi.enums.PowerLevel;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChargingStationTest {

    @Test
    public void testChargingStationId() {
        ChargingStation chargingStation = new ChargingStation();
        chargingStation.setStationId("CS001");
        assertEquals("CS001", chargingStation.getStationId());
    }

    @Test
    public void testChargingStationLocation() {
        ChargingStation chargingStation = new ChargingStation();
        Location location = new Location("123 Main St", 40.7128, -74.0060);
        chargingStation.setLocation(location);
        assertEquals(location, chargingStation.getLocation());
    }

    @Test
    public void testChargingStationChargerType() {
        ChargingStation chargingStation = new ChargingStation();
        chargingStation.setChargerType(ChargerType.AC);
        assertEquals(ChargerType.AC, chargingStation.getChargerType());
    }

    @Test
    public void testChargingStationChargingPoints() {
        ChargingStation chargingStation = new ChargingStation();
        ChargingPoint chargingPoint1 = new ChargingPoint("CP001", PowerLevel.LOW,StationStatus.AVAILABLE);
        ChargingPoint chargingPoint2 = new ChargingPoint("CP002", PowerLevel.HIGH,StationStatus.IN_USE);
        chargingStation.addChargingPoint(chargingPoint1);
        chargingStation.addChargingPoint(chargingPoint2);
        assertEquals(2, chargingStation.getChargingPoints().size());
    }

    @Test
    public void testChargingStationStatus() {
        ChargingStation chargingStation = new ChargingStation();
        chargingStation.setStatus(StationStatus.AVAILABLE);
        assertEquals(StationStatus.AVAILABLE, chargingStation.getStatus());
    }
}

