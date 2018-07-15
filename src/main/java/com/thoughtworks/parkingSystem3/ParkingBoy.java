package com.thoughtworks.parkingSystem3;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    List<ParkingLot> parkingLotList = new ArrayList<>();
    //停车位总量

    public int getContain() {
        int count = 0;
        for (int i = 0; i < parkingLotList.size(); i++)
            count += parkingLotList.get(i).getSize();
        return count;
    }

    ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    private int findCarCounts() {
        int number = 0;
        for (ParkingLot parkingLot : parkingLotList) {
            number += parkingLot.getCarCounts();
        }
        return number;
    }

    public Receipt park(Car car) {
        Receipt r = null;

        for (ParkingLot parkingLot : parkingLotList) {
            if (!parkingLot.isFull()) {
                r = parkingLot.park(car);
                break;
            }
        }
        return r;
    }

    public Car getOutCar(Receipt receipt) {
        Car car = null;
        for (ParkingLot parkingLot : parkingLotList) {
            car = parkingLot.getOutCar(receipt.getReceiptId());
            if (car != null) {
                break;
            }
        }
        return car;
    }

    public boolean isFull() {
        boolean key = false;
        for(ParkingLot parkingLot:parkingLotList){
            key = key || parkingLot.isFull();
        }
        return key;
    }
}
