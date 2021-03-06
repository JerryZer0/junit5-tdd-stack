package com.thoughtworks.parkingSystem5;


import com.thoughtworks.parkingSystem5.domain.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class ParkingLotTest {

    @Test
    public void should_park_successfully_when_the_park_is_not_full() {
        ParkingLot parkingLot = new ParkingLot(2);
        try {
            parkingLot.park(new Car("AS56"));
        } catch (ParkingExcpetion p) {
            fail("It should not throw exception!");
        }
    }

    @Test
    @DisplayName("throws exception")
    public void should_park_failed_when_the_park_is_full() {
        ParkingLot parkingLot = new ParkingLot(0);
        assertThrows(ParkingExcpetion.class, () -> parkingLot.park(new Car("AS56")));
//        try{
//            parkingLot.park(new Car("AS56"));
//        }catch (ParkingExcpetion p){
//            System.out.println("You can not park any more.The park is full!!");
//        }
    }

    @Test
    public void should_park_failed_when_the_park_is_full_1() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("AS56"));
        assertThrows(ParkingExcpetion.class, () -> parkingLot.park(new Car("AS562")));
//        try{
//            parkingLot.park(new Car("AS567"));
//
//        }catch (ParkingExcpetion p){
//            System.out.println("You can not park any more.The park is full!!");
//        }
    }

    @Test
    public void should_get_out_the_car_when_given_receipt_is_true() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car("AS56");
        Receipt receipt = parkingLot.park(car);

        assertThat(parkingLot.getOutCar(receipt.getReceiptId()), is(car));

    }

    @Test
    public void should_not_get_out_the_car_when_given_receipt_is_wrong() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car("AS56");
        Receipt receipt = new Receipt();

        assertThat(parkingLot.getOutCar(receipt.getReceiptId()), not(car));

    }

    @Test
    public void should_be_true_when_call_isFull_given_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(0);
        assertThat(parkingLot.isFull(), is(true));
    }

    @Test
    public void should_be_false_when_call_isFull_given_parking_lot_is_not_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        assertThat(parkingLot.isFull(), is(false));
    }

    @Test
    public void should_be_false_when_call_isFull_given_a_full_parking_lot_take_out_a_car() {
        ParkingLot parkingLot = new ParkingLot(1);
        Receipt receipt = parkingLot.park(new Car("AS56"));
        parkingLot.getOutCar(receipt.getReceiptId());
        assertThat(parkingLot.isFull(), is(false));
    }

    @Test
    public void should_park_successfully_when_a_full_park_take_out_a_car() {
        ParkingLot parkingLot = new ParkingLot(1);
        Receipt receipt = parkingLot.park(new Car("AS56"));
        parkingLot.getOutCar(receipt.getReceiptId());
        try {
            parkingLot.park(new Car("AS56"));
        } catch (ParkingExcpetion excpetion) {
            fail("should park successfully");
        }
    }
}
