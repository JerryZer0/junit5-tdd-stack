package com.thoughtworks.parkingSystem2;

import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.fail;

public class ParkingBoyTest {

    @Test
    public void should_park_successfully_when_parking_lot1_is_not_full(){
        int contain[] ={1,2};
        ParkingBoy parkingBoy = new ParkingBoy(contain.length,contain);
        try{
            parkingBoy.park(new Car());
        }catch (ParkingExcpetion p){
            fail("It should not throw exception!");
        }
    }

    @Test
    public void should_park_successfully_when_parking_lot1_is_full_and_parking_lot2_is_not_full(){
        int contain[] ={0,2};
        ParkingBoy parkingBoy = new ParkingBoy(contain.length,contain);
        try{
            parkingBoy.park(new Car());
        }catch (ParkingExcpetion p){
            fail("It should not throw exception!");
        }
    }

    @Test
    public void should_park_failed_when_parking_lot1_is_full_and_parking_lot2_is__full(){
        int contain[] ={0,0};
        ParkingBoy parkingBoy = new ParkingBoy(contain.length,contain);
        try{
            parkingBoy.park(new Car());
        }catch (ParkingExcpetion p){
            System.err.println("All parkingLots are full,you can't park any more!!!");
        }
    }

    @Test
    public void should_get_out_car_successfully_when_car_is_in_lot(){
        int contain[] ={1,2};
        ParkingBoy parkingBoy = new ParkingBoy(contain.length,contain);
        Car car = new Car();
        Receipt receipt = parkingBoy.park(car);
        assertThat(parkingBoy.getOutCar(receipt),is(car));
    }

    @Test
    public void should_get_out_car_failed_when_car_is_not_in_lot(){
        int contain[] ={1,2};
        ParkingBoy parkingBoy = new ParkingBoy(contain.length,contain);
        Car car = new Car();
        Receipt receipt = parkingBoy.park(new Car());
        assertThat(parkingBoy.getOutCar(receipt),not(car));
    }

    @Test
    public void should_park_successfully_when_car_is_out_from_parkingLot1(){
        int contain[] ={1,1};
        ParkingBoy parkingBoy = new ParkingBoy(contain.length,contain);
        Car car = new Car();
        Receipt receipt1 = parkingBoy.park(car);
        Receipt receipt2 = parkingBoy.park(new Car());
        car = parkingBoy.getOutCar(receipt1);
        try{
            parkingBoy.park(car);
        }catch (ParkingExcpetion p){
            fail("It should not throw exception!");
        }
    }
}
