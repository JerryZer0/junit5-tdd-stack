package com.thoughtworks.parkingSystem2;

public class ParkingBoy {

    ParkingLot parkLot1;
    ParkingLot parkLot2;

    ParkingBoy(int p1,int p2){
        parkLot1 = new ParkingLot(p1);
        parkLot2 = new ParkingLot(p2);
    }

    public void park(Car car) {
        Receipt r = new Receipt();
        if(!parkLot1.isFull()){
            r = parkLot1.park(car);
        }else if(!parkLot2.isFull()){
            r = parkLot2.park(car);
        }
    }
}
