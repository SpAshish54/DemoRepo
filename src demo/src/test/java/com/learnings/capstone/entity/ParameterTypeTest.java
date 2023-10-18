package com.learnings.capstone.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ParameterTypeTest {

    @Test
    public void testEnumValues() {
        ParameterType[] values = ParameterType.values();

        assertEquals(3, values.length); // Ensure the correct number of enum values

        assertEquals(ParameterType.QUANTITY, values[0]);
        assertEquals(ParameterType.PRICE, values[1]);
        assertEquals(ParameterType.OTHER, values[2]);
    }
}


