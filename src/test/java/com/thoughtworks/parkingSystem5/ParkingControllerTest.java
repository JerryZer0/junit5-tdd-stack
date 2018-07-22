package com.thoughtworks.parkingSystem5;

import com.thoughtworks.parkingSystem5.shell.Router;
import com.thoughtworks.parkingSystem5.shell.controller.ParkingController;
import com.thoughtworks.parkingSystem5.domain.*;
import com.thoughtworks.parkingSystem5.shell.io.Request;
import com.thoughtworks.parkingSystem5.shell.io.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParkingControllerTest {
    private List<ParkingBoy> init() {
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        Receipt receipt = new Receipt();
        Car car = new Car("CZ123");
        when(parkingBoy.isFull()).thenReturn(false);
        when(parkingBoy.park(car)).thenReturn(receipt);
        parkingBoyList.add(parkingBoy);

        return parkingBoyList;
    }

    @Test
    public void should_change_current_page_into_parkPage_when_input_1_and_parkingLot_is_not_full() {
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController system = new ParkingController(init(), request, response);
        String currentPage = "operationPage";
        Router controller = new Router(system,currentPage);
        String message = "请输入车牌号：";
        when(request.getCommand()).thenReturn("1");

        controller.checkCurrentPage(request);
        verify(response).send(message);
    }

    @Test
    public void should_park_successfully_and_change_current_page_into_operationPage_when_input_carID() {
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController controller = mock(ParkingController.class);
        String currentPage = "parkPage";
        Router router = new Router(controller, currentPage);

        router.checkCurrentPage(request);
        verify(controller).parkCar(request);
    }
    @Test
    public void should_park_failed_and_change_current_page_into_operationPage_when_parkingLot_is_full() {
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController controller = mock(ParkingController.class);
        String currentPage = "parkPage";
        Router router = new Router(controller, currentPage);

        when(controller.parkOperation()).thenReturn("operationPage");
        router.checkCurrentPage(request);
        verify(controller).parkCar(request);
    }

    @Test
    public void should_get_car_successfully_and_change_current_page_into_operationPage_when_receiptId_is_correct() {
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController controller = mock(ParkingController.class);
        String currentPage = "unparkPage";
        Router router = new Router(controller, currentPage);

        when(controller.parkOperation()).thenReturn("operationPage");
        router.checkCurrentPage(request);
        verify(controller).unParkCar();
    }

    @Test
    public void should_go_into_controller_unParkCar() {
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController controller = mock(ParkingController.class);
        String currentPage = "unparkPage";
        Router router = new Router(controller, currentPage);

        when(controller.parkOperation()).thenReturn("operationPage");
        router.checkCurrentPage(request);
        verify(controller).unParkCar();
    }
}
