package com.thoughtworks.parkingSystem2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ParkingSystemTest {

    @Test
    public void should_park_successfully_when_parking_lot_is_not_full(){
        ParkingBoy parkingBoy = new ParkingBoy(1,2);
        try{
            parkingBoy.park(new Car());
        }catch (ParkingExcpetion p){
            fail("It should not throw exception!");
        }
    }
}
