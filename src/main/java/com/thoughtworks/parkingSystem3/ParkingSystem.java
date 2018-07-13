package com.thoughtworks.parkingSystem3;

import java.util.Scanner;

public class ParkingSystem {









//    public static void main(String []args){
//
//        ParkingSystemIO parkingSystemIO = new ParkingSystemIO();
//        parkingSystemIO.systemStarShow();
//
//
//    }

    public boolean getOrder(ParkingSystemIO io) {
        String order = io.getOrder();
        boolean status = false;
        if(order == "1" || order == "2"){
            status = true;
        }
        return status;
    }
}
