package com.thoughtworks.parkingSystem;

public class ParkException extends Exception{
    public ParkException(){
        System.out.println("您已不能停车");
    }
}
