package com.thoughtworks.parkingSystem3;

import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParkingSystemTest {

    @Test
    public void should_return_false_when_input_is_incorrect() {
        //ParkingSystem parkingSystem = new ParkingSystem();

        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        ParkingSystem system = new ParkingSystem(parkingBoyList);
        ParkingSystemIO parkingSystemIO = mock(ParkingSystemIO.class);
        when(parkingSystemIO.getOrder()).thenReturn("343");

        String order = system.getOrder(parkingSystemIO);
        assertThat(order, is("343"));
    }

    @Test
    public void should_return_1_when_input_is_correct() {
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        ParkingSystem system = new ParkingSystem(parkingBoyList);

        ParkingSystemIO parkingSystemIO = mock(ParkingSystemIO.class);
        when(parkingSystemIO.getOrder()).thenReturn("1");

        String order = system.getOrder(parkingSystemIO);
        assertThat(order, is("1"));
    }

    @Test
    public void should_return_true_when_parkingLot_is_full() {

        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        parkingBoyList.add(parkingBoy);
        ParkingSystem system = new ParkingSystem(parkingBoyList);

        when(parkingBoy.isFull()).thenReturn(true);

        assertThat(system.isFull(), is(true));
    }

    @Test
    public void should_park_failed_and_when_parkingLot_is_full_and_order_is_1() {
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        Receipt receipt = null;
        Car car = new Car("CZ123");
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        parkingBoyList.add(parkingBoy);
        ParkingSystem system = new ParkingSystem(parkingBoyList);

        when(parkingBoy.park(car)).thenReturn(null);

        Receipt receipt1 = system.park(car);
        assertThat(receipt1, is(receipt));
    }

    @Test
    public void should_ask_receiptID_when_order_is_2() {
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        ParkingSystem system = new ParkingSystem(parkingBoyList);
        ParkingSystemIO io = mock(ParkingSystemIO.class);
        when(io.getReceiptId()).thenReturn("33344444-342");

        String receiptId = system.getReceiptId(io);

        assertThat(receiptId, is("33344444-342"));
    }

    @Test
    public void should_ask_carID_when_parkingLot_is_not_full() {
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        ParkingSystem system = new ParkingSystem(parkingBoyList);

        ParkingSystemIO parkingSystemIO = mock(ParkingSystemIO.class);
        when(parkingSystemIO.getCarId()).thenReturn("CZ123");

        String carId = system.getCarId(parkingSystemIO);
        assertThat(carId, is("CZ123"));
    }

    @Test
    public void should_park_successfully_and_return_receiptId_of_the_receipt_when_parkingLot_is_not_full_and_order_is_1() {

        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        Receipt receipt = new Receipt();
        Car car = new Car("CZ123");
        when(parkingBoy.park(car)).thenReturn(receipt);
        parkingBoyList.add(parkingBoy);
        ParkingSystem system = new ParkingSystem(parkingBoyList);

        Receipt receipt1 = system.park(car);
        assertThat(receipt1, is(receipt));
    }

    @Test
    public void should_get_out_the_car_when_receiptId_is_right() {
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        ParkingBoy boy = mock(ParkingBoy.class);
        parkingBoyList.add(boy);
        ParkingSystem system = new ParkingSystem(parkingBoyList);

        Car car = new Car("234");
        Receipt receipt = new Receipt();
        receipt.setUuid("33344444-342");

        when(boy.getOutCar(receipt)).thenReturn(car);
        Car carOut = system.getOutCar(receipt);
        assertThat(carOut, is(car));
    }

    @Test
    public void should_get_out_the_car_failed_when_receiptId_is_wrong() {
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        ParkingSystem system = new ParkingSystem(parkingBoyList);
        Car car = new Car("234");
        Receipt receipt = new Receipt();
        receipt.setUuid("33344444-342");
        ParkingBoy boy = mock(ParkingBoy.class);
        when(boy.getOutCar(receipt)).thenReturn(car);
        Car carOut = system.getOutCar(new Receipt());
        assertThat(carOut, not(car));
    }


}
