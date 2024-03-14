package com.example.evchargingstationapi.model;

import com.example.evchargingstationapi.enums.PowerLevel;
import com.example.evchargingstationapi.enums.StationStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChargingPointTest {

    @Test
    public void testChargingPointId() {
        ChargingPoint chargingPoint = new ChargingPoint("CP001", PowerLevel.LOW, StationStatus.AVAILABLE);
        assertEquals("A1", chargingPoint.getIdentifier());
    }

    @Test
    public void testChargingPointPowerLevel() {
        ChargingPoint chargingPoint = new ChargingPoint("CP002", PowerLevel.LOW,StationStatus.IN_USE);
        assertEquals(PowerLevel.LOW, chargingPoint.getPowerLevel());
    }
}

