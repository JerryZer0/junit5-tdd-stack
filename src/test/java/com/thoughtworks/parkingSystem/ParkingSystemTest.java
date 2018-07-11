package com.thoughtworks.parkingSystem;

import org.junit.jupiter.api.Test;

public class ParkingSystemTest {

    //ParkingSystem pakingSystem = new ParkingSystem(5);
    Car car = new Car("2233");

    //查看停车场空位
    @Test
    public void shoule_return_the_counts_of_parking_space(){
        ParkingSystem pakingSystem = new ParkingSystem(1);
        Car car1 = new Car("123");
        try {
            pakingSystem.park(car1);
        } catch (ParkException e) {
            e.printStackTrace();
        }
        int count = pakingSystem.getParkingSpace();
        assert(count == 0);
    }

    //停车场中有空位时停车
    @Test
    public void should_park_succeed_when_counts_smaller(){
        ParkingSystem pakingSystem = new ParkingSystem(5);
        boolean statue = false;
        try {
            statue = pakingSystem.park(car);
        } catch (ParkException e) {
            e.printStackTrace();
        }
        assert(statue == true);
    }

    //停车场中没有空位时停车
    @Test
    public void should_park_failed_when_counts_bigger(){
        ParkingSystem pakingSystem = new ParkingSystem(0);
        boolean statue = false;
        try {
            statue = pakingSystem.park(car);
        } catch (ParkException e) {
            e.printStackTrace();
        }
        assert(statue == false);
    }

    //车在停车场取车
    @Test
    public void should_get_car_succeed_when_car_is_in_park(){
        //Car car = new Car(2233);
        ParkingSystem pakingSystem = new ParkingSystem(5);
        try {
            pakingSystem.park(car);
        } catch (ParkException e) {
            e.printStackTrace();
        }

        boolean statue = pakingSystem.getCarOut(car);
        assert(statue == true);
    }

    //车不在停车场取车
    @Test
    public void should_get_car_failed_when_car_is_not_in_park(){
        //Car car = new Car(2233);
        ParkingSystem pakingSystem = new ParkingSystem(5);
        //pakingSystem.park(car);

        boolean statue = pakingSystem.getCarOut(car);
        assert(statue == false);
    }
}
