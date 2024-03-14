package com.example.evchargingstationapi.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {

    @Test
    public void testLocationAddress() {
        Location location = new Location("123 Main St", 40.7128, -74.0060);
        assertEquals("123 Main St", location.getAddress());
    }

    @Test
    public void testLocationLatitude() {
        Location location = new Location("123 Main St", 40.7128, -74.0060);
        assertEquals(40.7128, location.getLatitude());
    }

    @Test
    public void testLocationLongitude() {
        Location location = new Location("123 Main St", 40.7128, -74.0060);
        assertEquals(-74.0060, location.getLongitude());
    }
}

