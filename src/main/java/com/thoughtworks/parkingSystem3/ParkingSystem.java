package com.thoughtworks.parkingSystem3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingSystem {

    List<ParkingBoy> parkingBoyList = new ArrayList<>();

    ParkingSystem(List<ParkingBoy> parkingBoyList){
        this.parkingBoyList = parkingBoyList;
    }

    public boolean getOrder(ParkingSystemIO io) {
        String order = io.getOrder();
        boolean status = false;
        if(order == "1" || order == "2"){
            status = true;
        }
        return status;
    }

    public String getCarId(ParkingSystemIO io) {
        String carId = io.getCarId();
        return carId;
    }

    public Receipt park(int parkingBoyId, Car car) {
        Receipt receipt = parkingBoyList.get(parkingBoyId-1).park(car);
        return receipt;
    }

    public boolean isFull(ParkingBoy parkingBoy) {
        return parkingBoy.isFull();
    }

    public String getReceiptId(ParkingSystemIO io) {
        String receiptId = io.getReceiptId();
        return receiptId;
    }

    public Car getOutCar(ParkingBoy boy, Receipt receipt) {
        Car car = boy.getOutCar(receipt);
        return car;
    }

    public static void main(String []args){

        ParkingSystemIO io = new ParkingSystemIO();
//        List<ParkingLot> parkingLotList = new ArrayList<>();
//        parkingLotList.add(new ParkingLot(2));
//        parkingLotList.add(new ParkingLot(1));
        int[] parkingLotList = {1,2};
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(parkingBoy);
        ParkingSystem parkingSystem = new ParkingSystem(parkingBoyList);
        while(true){
            io.systemStarShow();

        }


    }

}
