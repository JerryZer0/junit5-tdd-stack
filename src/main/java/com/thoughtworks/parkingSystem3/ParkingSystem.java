package com.thoughtworks.parkingSystem3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingSystem {

    List<ParkingBoy> parkingBoyList = new ArrayList<>();

    ParkingSystem(List<ParkingBoy> parkingBoyList) {
        this.parkingBoyList = parkingBoyList;
    }

    public String getOrder(ParkingSystemIO io) {
        String order = io.getOrder();
        return order;
    }

    public String getCarId(ParkingSystemIO io) {
        String carId = io.getCarId();
        return carId;
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

    public String getReceiptId(ParkingSystemIO io) {
        String receiptId = io.getReceiptId();
        return receiptId;
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

    public static void main(String[] args) {

        ParkingSystemIO io = new ParkingSystemIO();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(1));

        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(parkingBoy);
        ParkingSystem parkingSystem = new ParkingSystem(parkingBoyList);
        while (true) {
            io.systemStarShow();
            String order = parkingSystem.getOrder(io);
            String carId;
            String receiptId;
            if (order.equals("1")) {
                parkCar(io, parkingSystem);
            } else if (order.equals("2")) {
                outCar(io, parkingSystem);
            } else {
                incorrectInput(io);
            }
        }
    }

    public static void parkCar(ParkingSystemIO io, ParkingSystem parkingSystem) {
        String carId;
        if (parkingSystem.isFull()) {
            io.parkingLotIsFull();
        } else {
            io.askCarId();
            carId = parkingSystem.getCarId(io);
            Car car = new Car(carId);
            Receipt receipt = parkingSystem.park(car);
            io.parkSuccessfully(receipt);
        }
    }

    public static void incorrectInput(ParkingSystemIO io) {
        io.remindErrorOrder();
    }

    public static void outCar(ParkingSystemIO io, ParkingSystem parkingSystem) {
        String receiptId;
        io.askReceiptId();
        receiptId = io.getReceiptId();
        Receipt receipt = new Receipt();
        receipt.setUuid(receiptId);
        Car myCar = parkingSystem.getOutCar(receipt);
        if (myCar == null) {
            io.getOutCarFailed();
        } else {
            io.getOutCarSuccessfully(myCar);
        }
    }
}
