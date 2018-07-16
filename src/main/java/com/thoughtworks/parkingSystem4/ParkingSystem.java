package com.thoughtworks.parkingSystem4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingSystem {

    List<ParkingBoy> parkingBoyList = new ArrayList<>();


    ParkingSystem(List<ParkingBoy> parkingBoyList) {
        this.parkingBoyList = parkingBoyList;
    }

    public Receipt park(Car car) {
        Receipt receipt = null;
        for (int i = 0; i < parkingBoyList.size(); i++) {
            receipt = parkingBoyList.get(i).park(car);
            if (receipt != null) {
                break;
            }
        }
        return receipt;
    }

    public boolean isFull() {
        boolean full = true;
        for (int i = 0; i < parkingBoyList.size(); i++) {
            if (!parkingBoyList.get(i).isFull()) {
                full = false;
            }
        }
        return full;
    }

    public Car getOutCar(Receipt receipt) {
        Car car = null;
        for (int i = 0; i < parkingBoyList.size(); i++) {
            car = parkingBoyList.get(i).getOutCar(receipt);
            if (car != null) {
                break;
            }
        }
        return car;
    }

}
