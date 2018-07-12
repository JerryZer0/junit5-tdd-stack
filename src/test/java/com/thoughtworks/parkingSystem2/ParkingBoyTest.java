package com.thoughtworks.parkingSystem2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ParkingBoyTest {

    @Test
    public void should_park_successfully_when_parking_lot1_is_not_full(){
        ParkingBoy parkingBoy = new ParkingBoy(1,2);
        try{
            parkingBoy.park(new Car());
        }catch (ParkingExcpetion p){
            fail("It should not throw exception!");
        }
    }

    @Test
    public void should_park_successfully_when_parking_lot1_is_full_and_parking_lot2_is_not_full(){
        ParkingBoy parkingBoy = new ParkingBoy(0,2);
        try{
            parkingBoy.park(new Car());
        }catch (ParkingExcpetion p){
            fail("It should not throw exception!");
        }
    }

    @Test
    public void should_park_failed_when_parking_lot1_is_full_and_parking_lot2_is__full(){
        ParkingBoy parkingBoy = new ParkingBoy(0,0);
        try{
            parkingBoy.park(new Car());
        }catch (ParkingExcpetion p){
            System.err.println("All parkingLots are full,you can't park any more!!!");
        }
    }
}
