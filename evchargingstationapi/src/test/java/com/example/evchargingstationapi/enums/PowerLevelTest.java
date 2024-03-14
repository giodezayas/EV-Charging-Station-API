package com.example.evchargingstationapi.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerLevelTest {

    @Test
    public void testPowerLevelHigh() {
        assertEquals("High", PowerLevel.HIGH.getLabel());
    }

    @Test
    public void testPowerLevelMedium() {
        assertEquals("Medium", PowerLevel.MEDIUM.getLabel());
    }

    @Test
    public void testPowerLevelLow() {
        assertEquals("Low", PowerLevel.LOW.getLabel());
    }
}

