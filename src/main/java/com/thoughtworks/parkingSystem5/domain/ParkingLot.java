package com.thoughtworks.parkingSystem5.domain;

import com.thoughtworks.parkingSystem5.ParkingExcpetion;

import java.util.HashMap;

public class ParkingLot {

    private int size;
    private String name;
    private String id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        String info = "|" + getId() + "|" + getName() + "|" + getSize() + "(个)|"
                + getCarCounts() + "(辆)|" + (getCarCounts() - getSize()) + "(个)|";
        return info;
    }
}
