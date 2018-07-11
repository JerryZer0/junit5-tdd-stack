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
    @Test
    public void shoule_park_failed_when_the_park_is_full(){
        ParkingLot parkingLot = new ParkingLot(0);
        try{
            parkingLot.park(new Car());
        }catch (ParkingExcpetion p){
            System.out.println("You can not park any more.The park is full!!");
        }
    }
    @Test
    public void shoule_get_out_the_car_when_given_receipt_is_true(){
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        Receipt receipt = parkingLot.park(car);

        assertThat(parkingLot.getOutCar(receipt),is(car));

    }
}
