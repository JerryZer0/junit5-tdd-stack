package com.thoughtworks.parkingSystem3;

import java.util.HashMap;

public class ParkingLot {

    private int size;
    public HashMap<Receipt, Car> carList = new HashMap<>();

    ParkingLot(int numb){
        this.size = numb;
    }
    public Receipt park(Car car) {
        Receipt receipt = new Receipt();
        if(size>carList.size()){
            carList.put(receipt, car);
        }else{
            throw new ParkingExcpetion();
        }
        return receipt;
    }

    public Car getOutCar(Receipt receipt) {
        Car car = carList.get(receipt);
        carList.remove(receipt);
        return car;
    }

    public boolean isFull() {
        return size == carList.size();
    }

    public int getSize(){
        return this.size;
    }

    public int getCarCounts(){
        return carList.size();
    }
}
