package com.thoughtworks.parkingSystem3;

import org.junit.jupiter.api.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingSystemTest {

    @Test
    public void should_return_false_when_input_is_incorrect(){
        ParkingSystem parkingSystem = new ParkingSystem();

        ParkingSystemIO parkingSystemIO = mock(ParkingSystemIO.class);
        when(parkingSystemIO.getOrder()).thenReturn("343");

        boolean order = parkingSystem.getOrder(parkingSystemIO);
        assertThat(order,is(false));
    }

    @Test
    public void should_return_true_when_input_is_correct(){
        ParkingSystem parkingSystem = new ParkingSystem();

        ParkingSystemIO parkingSystemIO = mock(ParkingSystemIO.class);
        when(parkingSystemIO.getOrder()).thenReturn("1");

        boolean order = parkingSystem.getOrder(parkingSystemIO);
        assertThat(order,is(true));
    }

}
