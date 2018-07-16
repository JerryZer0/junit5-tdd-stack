package com.thoughtworks.parkingSystem4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParkingSystemTest {

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

    /*
     * Controller方法测试
     *
     * */
    private ParkingSystem init() {
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        Receipt receipt = new Receipt();
        Car car = new Car("CZ123");
        when(parkingBoy.isFull()).thenReturn(false);
        when(parkingBoy.park(car)).thenReturn(receipt);
        parkingBoyList.add(parkingBoy);
        ParkingSystem system = new ParkingSystem(parkingBoyList);
        return system;
    }

    @Test
    public void should_change_current_page_into_parkPage_when_input_1_and_parkingLot_is_not_full() {
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingSystem system = init();
        ParkingController controller = new ParkingController(system, request, response);
        String message = "请输入车牌号：";
        when(request.getCurrentPage()).thenReturn("operationPage");
        when(request.getCommand()).thenReturn("1");

        controller.checkCurrentPage(request);
        verify(response).send(message);
        verify(request).setCurrentPage("parkPage");
    }


}
