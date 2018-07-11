package com.thoughtworks.parkingSystem;

import java.util.ArrayList;
import java.util.List;

public class ParkingSystem {
    int saturation = 0;
    List<Car> parkList = new ArrayList<Car>();

    ParkingSystem(int count){
        this.saturation = count;
    }

    public boolean park(Car car) {
        if(parkList.size()<saturation){
            parkList.add(car);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean getCarOut(Car car){
        for(Car outCar:parkList){
            if(car.id == outCar.id){
                return true;
            }
        }
        return false;
    }

}
