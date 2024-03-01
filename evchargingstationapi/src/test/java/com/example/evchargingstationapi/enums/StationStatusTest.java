package com.example.evchargingstationapi.enums;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StationStatusTest {

    @Test
    public void testStationStatusEnumValues() {
        StationStatus available = StationStatus.AVAILABLE;
        StationStatus inUse = StationStatus.IN_USE;

        assertEquals("AVAILABLE", available.name());
        assertEquals("IN_USE", inUse.name());
    }
}

