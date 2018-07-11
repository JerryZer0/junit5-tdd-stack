package com.thoughtworks.parkingSystem2;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private int size;
    public HashMap<Car, Receipt> carList = new HashMap<>();

    ParkingLot(int numb){
        this.size = numb;
    }
    public void park(Car car) {
        if(size>carList.size()){
            carList.put(car,new Receipt());
        }else{
            throw new ParkingExcpetion();
        }
    }
}
