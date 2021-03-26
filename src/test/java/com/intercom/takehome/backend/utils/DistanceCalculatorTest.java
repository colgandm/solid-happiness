package com.intercom.takehome.backend.utils;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static com.intercom.takehome.backend.helper.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class DistanceCalculatorTest {

    @InjectMocks
    private DistanceCalculator unitUnderTest;

    @Before
    public void init() {
        ReflectionTestUtils.setField(unitUnderTest, "longitude", DEST_LONGITUDE);
        ReflectionTestUtils.setField(unitUnderTest, "latitude", DEST_LATITUDE);
    }

    @Test
    public void shouldReturnCalculatedDistanceGreaterThan100() {
        double calculatedDistance = unitUnderTest.findDistanceToCustomer(getCustomerRecord(LONGITUDE_GT, LATITUDE_GT));
        assertTrue(calculatedDistance > ONE_HUNDRED);
    }

    @Test
    public void shouldReturnCalculatedDistanceLessThan100() {
        double calculatedDistance = unitUnderTest.findDistanceToCustomer(getCustomerRecord(LONGITUDE_LT, LATITUDE_LT));
        assertTrue(calculatedDistance < ONE_HUNDRED);
    }

}