package com.thoughtworks.parkingSystem2;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    List<ParkingLot> parkLotList = new ArrayList<>();
    //停车位总量
    int contain = 0;

    ParkingBoy(int num, int []p){
        for(int i=0;i<num;i++){
            parkLotList.add(new ParkingLot(p[i]));
            contain += p[i];
        }
    }

    private int findCarCounts(List<ParkingLot> parkLotList){
        int number = 0;
        for(int i=0;i<parkLotList.size();i++){
            number += parkLotList.get(i).getCarCounts();
        }
        return number;
    }

    public Receipt park(Car car) {
        Receipt r = new Receipt();

        int countCars = findCarCounts(parkLotList);
        if(countCars<contain){
            for(int i=0;i<parkLotList.size();i++){
                if(!parkLotList.get(i).isFull()){
                    r = parkLotList.get(i).park(car);
                    parkLotList.get(i).carList.put(r,car);
                    break;
                }
            }
        }else{
            throw new ParkingExcpetion();
        }
        return r;
    }

//    public Car getOutCar(Receipt receipt) {
//        Car car = new Car();
//        if()
//        return new Car();
//    }

//    public Receipt getOutTheCar(Receipt receipt) {
//        Receipt r = new Receipt();
//        if(!parkLot1.isFull()){
//            r = parkLot1.park(car);
//        }else if(!parkLot2.isFull()){
//            r = parkLot2.park(car);
//        }else{
//            throw new ParkingExcpetion();
//        }
//        return r;
//    }
}
