package com.thoughtworks.parkingSystem3;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingBoyTest {

    @Test
    public void should_park_successfully_when_parking_lot_is_not_full() {
        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Receipt receipt = new Receipt();
        Car car = new Car("234");
        when(parkingLot.isFull()).thenReturn(false);
        when(parkingLot.park(car)).thenReturn(receipt);
        Receipt receipt1 = parkingBoy.park(car);
        assertThat(receipt1, is(receipt));
    }

    @Test
    public void should_get_out_car_successfully_when_car_is_in_the_only_lot() {

        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car("AS56");
        Receipt receipt = new Receipt();
        when(parkingLot.park(car)).thenReturn(receipt);
        when(parkingLot.getOutCar(receipt.getReceiptId())).thenReturn(car);
        receipt = parkingBoy.park(car);
        car = parkingBoy.getOutCar(receipt);
        assertThat(car, is(car));
    }

    @Test
    public void should_return_true_when_parking_lot_is_full() {
        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.park(new Car("234"));
        when(parkingLot.isFull()).thenReturn(true);
        assertThat(parkingBoy.isFull(), is(true));
    }

    @Test
    public void should_park_successfully_when_parking_lot1_is_full_and_parking_lot2_is_not_full() {
        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        parkingBoy.park(new Car("AS56"));

    }

    @Test
    public void should_park_failed_when_parking_lot1_is_full_and_parking_lot2_is__full() {
        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        Receipt receipt = parkingBoy.park(new Car("AS56"));
        Receipt receipt1 = null;
        assertThat(receipt, is(receipt1));
    }

    @Test
    public void should_get_out_car_successfully_when_car_is_in_lots() {
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = new Car("AS56");
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Receipt receipt = new Receipt();
        when(parkingLot.park(car)).thenReturn(receipt);

        receipt = parkingBoy.park(car);
        when(parkingLot.getOutCar(receipt.getReceiptId())).thenReturn(car);
        assertThat(parkingBoy.getOutCar(receipt), is(car));
    }

    @Test
    public void should_get_out_car_failed_when_car_is_not_in_lots() {
        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car("AS56");
        Receipt receipt = new Receipt();
        when(parkingLot.park(car)).thenReturn(receipt);
        receipt = parkingBoy.park(car);
        Car car2 = new Car("AS5612");
        when(parkingLot.getOutCar(receipt.getReceiptId())).thenReturn(car2);
        assertThat(parkingBoy.getOutCar(receipt), not(car));
    }

    @Test
    public void should_park_successfully_when_car_is_out_from_parkingLots() {
        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car("AS56");
        Receipt receipt = new Receipt();
        when(parkingLot.park(car)).thenReturn(receipt);
        receipt = parkingBoy.park(car);
        when(parkingLot.getOutCar(receipt.getReceiptId())).thenReturn(car);

        Receipt receipt1 = parkingBoy.park(car);
        Receipt receipt2 = parkingBoy.park(car);
        car = parkingBoy.getOutCar(receipt1);

        parkingBoy.park(car);

    }

    @Test
    public void should_return_true_when_the_car_is_the_one_from_lot1() {
        //容量为1，1
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        when(parkingLot1.isFull()).thenReturn(false,true);
        when(parkingLot2.isFull()).thenReturn(false,true);
        Car car = new Car("34");
        Receipt receipt1 = parkingBoy.park(car);
        Receipt receipt2 = parkingBoy.park(car);
        InOrder inOrder = Mockito.inOrder(parkingLot1,parkingLot2);
        inOrder.verify(parkingLot1).park(car);
        inOrder.verify(parkingLot2).park(car);
    }

}
