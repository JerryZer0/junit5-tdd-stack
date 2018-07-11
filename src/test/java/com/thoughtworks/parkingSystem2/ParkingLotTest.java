package com.thoughtworks.parkingSystem2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.fail;


import org.junit.jupiter.api.Test;

public class ParkingLotTest {

    @Test
    public void shoule_park_successfully_when_the_park_is_not_full(){
        ParkingLot parkingLot = new ParkingLot(2);
        try{
            parkingLot.park(new Car());
        }catch (ParkingExcpetion p){
            fail("It should not throw exception!");
        }
    }
}
