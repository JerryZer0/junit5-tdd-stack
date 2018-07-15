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

    int[] size = {1,2};
    ParkingBoy parkingBoy = new ParkingBoy(size);
    List<ParkingBoy> parkingBoyList = new ArrayList<>();
    ParkingSystem system = new ParkingSystem(parkingBoyList);

    @Test
    public void should_return_false_when_input_is_incorrect(){
        //ParkingSystem parkingSystem = new ParkingSystem();

        ParkingSystemIO parkingSystemIO = mock(ParkingSystemIO.class);
        when(parkingSystemIO.getOrder()).thenReturn("343");

        boolean order = system.getOrder(parkingSystemIO);
        assertThat(order,is(false));
    }

    @Test
    public void should_return_true_when_input_is_correct(){
        //ParkingSystem parkingSystem = new ParkingSystem();

        ParkingSystemIO parkingSystemIO = mock(ParkingSystemIO.class);
        when(parkingSystemIO.getOrder()).thenReturn("1");

        boolean order = system.getOrder(parkingSystemIO);
        assertThat(order,is(true));
    }

    @Test
    public void should_return_true_when_parkingLot_is_full(){
        int[] size = {0,0};
        parkingBoyList.add(parkingBoy);
        ParkingSystem system = new ParkingSystem(parkingBoyList);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.isFull()).thenReturn(true);

        assertThat(system.isFull(parkingBoy),is(true));
    }

    @Test
    public void should_park_failed_and_when_parkingLot_is_full_and_order_is_1(){
        int[] size = {0,0};
        Receipt receipt = null;
        Car car = new Car("CZ123");
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        parkingBoyList.add(parkingBoy);
        ParkingSystem system = new ParkingSystem(parkingBoyList);

        when(parkingBoy.park(car)).thenReturn(null);

        Receipt receipt1 = system.park(1,car);
        assertThat(receipt1,is(receipt));
    }

    @Test
    public void should_ask_receiptID_when_order_is_1(){
        //ParkingSystem parkingSystem = new ParkingSystem();

        ParkingSystemIO io = mock(ParkingSystemIO.class);
        when(io.getReceiptId()).thenReturn("33344444-342");

        String receiptId = system.getReceiptId(io);
//        verify(io).askReceiptId();
        assertThat(receiptId,is("33344444-342"));
    }

    @Test
    public void should_ask_carID_when_parkingLot_is_not_full(){
        //ParkingSystem parkingSystem = new ParkingSystem();

        ParkingSystemIO parkingSystemIO = mock(ParkingSystemIO.class);
        when(parkingSystemIO.getCarId()).thenReturn("CZ123");

        String carId = system.getCarId(parkingSystemIO);
        assertThat(carId,is("CZ123"));
    }
    @Test
    public void should_park_successfully_and_return_uuid_of_the_receipt_when_parkingLot_is_not_full_and_order_is_1(){
        int[] size = {1,2};
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        Receipt receipt = new Receipt();
        Car car = new Car("CZ123");
        when(parkingBoy.park(car)).thenReturn(receipt);
        parkingBoyList.add(parkingBoy);
        ParkingSystem system = new ParkingSystem(parkingBoyList);

        Receipt receipt1 = system.park(1,car);
        assertThat(receipt1,is(receipt));
    }

    @Test
    public void should_get_out_the_car_when_receiptId_is_right(){
        //ParkingSystem parkingSystem = new ParkingSystem();
        Car car = new Car("234");
        Receipt receipt = new Receipt();
        receipt.setUuid("33344444-342");
        ParkingBoy boy = mock(ParkingBoy.class);
        when(boy.getOutCar(receipt)).thenReturn(car);
        Car carOut = system.getOutCar(boy,receipt);
        assertThat(carOut,is(car));
    }

    @Test
    public void should_get_out_the_car_failed_when_receiptId_is_wrong(){
        //ParkingSystem parkingSystem = new ParkingSystem();
        Car car = new Car("234");
        Receipt receipt = new Receipt();
        receipt.setUuid("33344444-342");
        ParkingBoy boy = mock(ParkingBoy.class);
        when(boy.getOutCar(receipt)).thenReturn(car);
        Car carOut = system.getOutCar(boy,new Receipt());
        assertThat(carOut,not(car));
    }



}
