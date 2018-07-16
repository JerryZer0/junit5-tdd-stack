package com.thoughtworks.parkingSystem5;

import com.thoughtworks.parkingSystem5.controllers.ParkingController;
import com.thoughtworks.parkingSystem5.controllers.ParkingManageController;
import com.thoughtworks.parkingSystem5.domain.Request;
import com.thoughtworks.parkingSystem5.domain.Response;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SystemTest {
    @Test
    public void should_chose_wrongOperation_when_input_is_incorrect(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController controller = mock(ParkingController.class);
        ParkingManageController manageController = mock(ParkingManageController.class);
        String currentPage = "choseOperationPage";
        Router router = new Router(controller,manageController, currentPage);

        when(request.getCommand()).thenReturn("21354");
        router.checkCurrentPage(request);
        verify(controller).wrongOperation();
    }

    @Test
    public void should_chose_parkingController_when_input_is_1(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController controller = mock(ParkingController.class);
        ParkingManageController manageController = mock(ParkingManageController.class);
        String currentPage = "choseOperationPage";
        Router router = new Router(controller,manageController, currentPage);

        when(request.getCommand()).thenReturn("1");
        router.checkCurrentPage(request);
        verify(controller).getMainPage();
    }

    @Test
    public void should_chose_manageController_when_input_is_2(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController controller = mock(ParkingController.class);
        ParkingManageController manageController = mock(ParkingManageController.class);
        String currentPage = "choseOperationPage";
        Router router = new Router(controller,manageController, currentPage);

        when(request.getCommand()).thenReturn("2");
        router.checkCurrentPage(request);
        verify(manageController).getMainPage();
    }
}
