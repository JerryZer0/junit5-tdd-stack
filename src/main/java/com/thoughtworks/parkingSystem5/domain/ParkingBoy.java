package com.thoughtworks.parkingSystem5.domain;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    List<ParkingLot> parkingLotList = new ArrayList<>();
    //停车位总量
    public int getTotalSize(){
        int totalSize = 0;
        for(ParkingLot parkingLot:parkingLotList)
            totalSize += parkingLot.getSize();
        return totalSize;
    }

    public int getContain() {
        int count = 0;
        for (int i = 0; i < parkingLotList.size(); i++)
            count += parkingLotList.get(i).getSize();
        return count;
    }

    ParkingBoy() {}

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public void addParkingLot(ParkingLot parkingLot){
        parkingLot.setId(String.format("%03d", parkingLotList.size()+1));
        parkingLotList.add(parkingLot);
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

    public String lotInfo() {
        String info = "|停车场ID|名称|车位|已停车辆|剩余车位|\n";
        info += "|停车场ID|名称|车位|已停车辆|剩余车位|\n";
        info += "======================================\n";
        for(ParkingLot parkingLot : parkingLotList){
            info += parkingLot.toString();
        }
        info += "/n总车位："+getTotalSize()+"(个)\n";
        info += "停车总量："+getContain()+"(辆)\n";
        info += "总剩余车位："+(getTotalSize()-getContain())+"（个）\n";
        return info;
    }

    public int removeLot(String lotId) {
        int flag = 0;
        for(ParkingLot parkingLot:parkingLotList){
            if(parkingLot.getId() == lotId){
                if(parkingLot.getCarCounts()>0){
                    flag = 1;
                }else{
                    flag = 2;
                    parkingLotList.remove(parkingLot);
                }
            }
        }
        return flag;
    }
}
