package com.thoughtworks.parkingSystem3;

import java.util.*;

public class ParkingLot {

    private int size;
    public HashMap<String, Car> carList = new HashMap<>();

    ParkingLot(int size) {
        this.size = size;
    }

    public Receipt park(Car car) {
        Receipt receipt = new Receipt();
        if (size > carList.size()) {
            carList.put(receipt.getReceiptId(), car);
        } else {
            throw new ParkingExcpetion();
        }
        return receipt;
    }

    public Car getOutCar(String receiptId) {

        Car car = carList.get(receiptId);
        carList.remove(receiptId);
        return car;
    }

    public boolean isFull() {
        return size == carList.size();
    }

    public int getSize() {
        return this.size;
    }

    public int getCarCounts() {
        return carList.size();
    }
}
