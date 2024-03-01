package com.example.evchargingstationapi.enums;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChargerTypeTest {

    @Test
    public void testChargerTypeEnumValues() {
        ChargerType acCharger = ChargerType.AC;
        ChargerType dcFastCharger = ChargerType.DC_FAST;

        assertEquals("AC", acCharger.name());
        assertEquals("DC_FAST", dcFastCharger.name());
    }
}


