package com.thoughtworks.parkingSystem2;

import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

public class ParkingBoyTest {

    @Test
    public void should_park_successfully_when_parking_lot_is_not_full(){
        int contain[] ={2};
        ParkingBoy parkingBoy = new ParkingBoy(contain.length,contain);
        Receipt receipt = new Receipt();
        ParkingLot parkingLot = mock(ParkingLot.class);

        try{
            //when(parkingLot.park(new Car())).thenReturn(receipt);
            parkingBoy.park(new Car());
        }catch (ParkingExcpetion p){
            fail("It should not throw exception!");
        }
    }

    @Test
    public void should_get_out_car_successfully_when_car_is_in_the_only_lot(){
        int contain[] ={2};
        ParkingBoy parkingBoy = new ParkingBoy(contain.length,contain);
        Car car = new Car();
        Receipt receipt = new Receipt();
        //ParkingLot parkingLot = mock(ParkingLot.class);
        //when(parkingLot.park(car)).thenReturn(receipt);

        //when(parkingLot.getOutCar(receipt)).thenReturn(car);
        receipt = parkingBoy.park(car);
        car = parkingBoy.getOutCar(receipt);
        assertThat(car,is(car));
    }

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
    public void should_get_out_car_successfully_when_car_is_in_lots(){
        int contain[] ={1,2};
        ParkingBoy parkingBoy = new ParkingBoy(contain.length,contain);
        Car car = new Car();
        Receipt receipt = parkingBoy.park(car);
        assertThat(parkingBoy.getOutCar(receipt),is(car));
    }

    @Test
    public void should_get_out_car_failed_when_car_is_not_in_lots(){
        int contain[] ={1,2};
        ParkingBoy parkingBoy = new ParkingBoy(contain.length,contain);
        Car car = new Car();
        Receipt receipt = parkingBoy.park(new Car());
        assertThat(parkingBoy.getOutCar(receipt),not(car));
    }

    @Test
    public void should_park_successfully_when_car_is_out_from_parkingLots(){
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

    @Test
    public void should_return_true_when_the_car_is_the_one_from_lot1(){
        int contain[] ={2,1};
        ParkingBoy parkingBoy = new ParkingBoy(contain.length,contain);
        Car car = new Car();
        Receipt receipt1 = parkingBoy.park(car);
        Receipt receipt2 = parkingBoy.park(new Car());
        Receipt receipt3 = parkingBoy.park(new Car());
        assertThat(parkingBoy.parkLotList.get(0).carList.get(receipt1),is(car));
    }

}
