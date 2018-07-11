package com.thoughtworks.parkingSystem;

import java.util.ArrayList;
import java.util.List;

public class ParkingSystem {
    int saturation = 0;
    List<Car> parkList = new ArrayList<Car>();

    ParkingSystem(int count){
        this.saturation = count;
    }

    public boolean park(Car car) throws ParkException{
        if(parkList.size()<saturation){
            parkList.add(car);
            return true;
        }
        else{
            throw new ParkException();
            //return false;
        }
    }

    public boolean getCarOut(Car car){
        for(Car outCar:parkList){
            if(car.id == outCar.id){
                parkList.remove(car);
                return true;
            }
        }
        return false;
    }

    public int getParkingSpace() {
        int num = saturation-parkList.size();
        if(num<1)
            System.out.println("停车场已满");
        return num;
    }
}
